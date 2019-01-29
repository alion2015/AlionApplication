package com.alion.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.alion.accessibility.utils.AccessibilityUtil;
import com.alion.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.app.PendingIntent.getActivity;
import static android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;


/**
 * Created by alion on 2019/1/1.
 */

@TargetApi(16)
public class AccessibilitySampleService extends AccessibilityService {
    public static final String TARGETPACKAGENAME = "com.jifen.qukan";
    public static final String RECYCLEVIEW = "android.support.v7.widget.RecyclerView"; //目标节点的父类
    public static final String PARENTCLASSID = "com.jifen.qukan:id/os"; //目标节点的父类的id
    public static final String DETAIL = "com.jifen.qukan.content.newsdetail.news.NewsDetailActivity";
    public static final String PAGEMAIN = "com.jifen.qkbase.main.MainActivity";
    public static final String VIDEO = "com.jifen.qukan.content.newsdetail.video.VideoNewsDetailActivity";
    public static final String WEBVIEW = "android.webkit.WebView";
    public static final String REFRESH = "刷新";
    public static final String GETME = "领取";
    public static final String IKNOW = "我知道了";
    public static final String AGAINN = "您已经多次阅读";

    private final int MSG_MAIN = 100;
    private final int MSG_DETAIL = 101;

    private final int MSG_ADDPAPER = 102;

    private final int MSG_BACK_MAIN = 104;
    private final int MSG_REFRESH_PAPER = 105;

    private final int MSG_MAIN_LOOPER = 107;

    private boolean isFinishedAdd=false;

    Stack<AccessibilityNodeInfo> mainS = new Stack<>();
    ArrayList<String> names = new ArrayList<>();

    final int min=2;
    final int max=7;
    final Random random = new Random();
    ExecutorService singleT = Executors.newSingleThreadExecutor();

    int timeSpanAction = 2*1000;
    private int N=3,Default=3,D=2*N;
    AccessibilityOperator ao;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("alionlog", "handleMessage: " + msg.what);
            otherOP();
            switch (msg.what) {
                case MSG_MAIN:
                    if(isFinishedAdd){
                        if(N-->0){
                            if(mainS.size()>0) {
                                Log.d("alionlog", "handleMessage: start pop paper");
                                AccessibilityNodeInfo node = mainS.pop();
                                if (node.isEnabled()) {
                                    if (!node.performAction(AccessibilityNodeInfo.ACTION_CLICK)) {
                                        Log.d("alionlog", "handleMessage: click failed");
                                        sendMsg(MSG_MAIN,0);
                                    }
                                }
                            }else {
                                sendMsg(MSG_MAIN_LOOPER,0);
                            }
                        }else{
                            resetN();
                            sendMsg(MSG_REFRESH_PAPER,0);
                        }
                    }else {
                        Log.d("alionlog", "handleMessage: start to add paper");
                        sendMsg(MSG_ADDPAPER,0);
                    }
                    break;
                case MSG_DETAIL:
                    if (D-- > 0) {
                        Log.d("alionlog", "handleMessage: start loop Detail");
                        scrollN(WEBVIEW, MSG_DETAIL);
                    }else{
                        Log.d("alionlog", "handleMessage: Finish read paper");
                        sendMsg(MSG_BACK_MAIN,0);
                    }
                    break ;
                case MSG_ADDPAPER:
                    getEnableNode();
                    break ;
                case MSG_REFRESH_PAPER:
                    refresh(REFRESH);
                    break ;
                case MSG_MAIN_LOOPER:
                    if (N > 0) {
                        Log.d("alionlog", "handleMessage: start scroll Recycle");
                        scrollN(RECYCLEVIEW,MSG_ADDPAPER);
                    }else {
                        isFinishedAdd = true;
                        Log.d("alionlog", "handleMessage: finish add paper");
                        sendMsg(MSG_MAIN,0);
                    }
                    break;
                case MSG_BACK_MAIN:
                    resetN();
                    Log.d("alionlog", "handleMessage: back to main");
                    ao.clickBackKey();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void resetN(){
        N=Default;
        D=2*N;
    }
    private void refresh(String name) {
        initOperator();
        List<AccessibilityNodeInfo> views = ao.findNodesByText(name);
        if(views==null || views.size()<1){
            views=ao.findNodesById("com.jifen.qukan:id/kr");
        }
        if (views!=null && views.size()>0) {
            AccessibilityNodeInfo view = views.get(0);
            if(view!=null) {
                view.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                isFinishedAdd = false;
                sendMsg(MSG_MAIN,0);
            }else{
                Log.d("alionlog", "scrollN:view==null");
            }
        }else {
            Log.d("alionlog", "can't find "+name);
            startMain();
        }
    }

    private void sendMsg(final int msg,int time){
        int temp = random.nextInt(max)%(max-min+1) + min;
        if(MSG_DETAIL==msg || MSG_MAIN_LOOPER==msg || MSG_ADDPAPER==msg){
            if(temp<5)temp+=4;
        }
        final int mun = Math.max(temp,time);
        singleT.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("alionlog", "run: temp====="+mun);
                    Thread.sleep(mun*1000);
                    Log.d("alionlog", "run: "+mun);
                    mHandler.sendEmptyMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void scrollN(String name,int msg) {
        AccessibilityNodeInfo root = ao.getRootNodeInfo();
        if(null!=root){
            if(root.findAccessibilityNodeInfosByText(AGAINN).size() > 0){
                sendMsg(MSG_BACK_MAIN,0);
                Log.d("alionlog", "scrollN: "+AGAINN);
            }else {
                Log.d("alionlog", "scrollN: scrolling " + name);
                AccessibilityNodeInfo view = getView(name);
                if (view != null) {
                    if (!view.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)) {
                        Log.d("alionlog", "scrollN: scrolling failed");
                        //ao.clickBackKey();
                        view.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                        sendMsg(msg,0);
                    } else {
                        sendMsg(msg,0);
                    }
                } else {
                    Log.d("alionlog", "scrollN:view==null || !view.isScrollable()");
                    sendMsg(msg,0);
                }
            }
        }else {
            Log.d("alionlog", "scrollN: Root is null");
            sendMsg(MSG_REFRESH_PAPER,0);
        }

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        initOperator();

        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = AccessibilityUtil.getAccessibilitySettingPageIntent(getApplicationContext());
        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                .setContentTitle("前台占位") // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText("要显示的内容") // 设置上下文内容
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音

        // 参数一：唯一的通知标识；参数二：通知消息。
        startForeground(110, notification);// 开始前台服务
    }

    @Override
    public void onDestroy() {
        stopForeground(true);// 停止前台服务--参数：表示是否移除之前的通知
        super.onDestroy();
    }
boolean isNeedScroll = false;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // 此方法是在主线程中回调过来的，所以消息是阻塞执行的
        // 获取包名
        if (event.getPackageName().equals(TARGETPACKAGENAME) && event.getEventType() == TYPE_WINDOW_STATE_CHANGED) {
            Log.d("alionlog", "onAccessibilityEvent: " + event.getClassName());
            if (event.getClassName().equals(PAGEMAIN)) {
                if(isNeedScroll){
                    sendMsg(MSG_MAIN_LOOPER,0);
                    isNeedScroll=false;
                    Log.d("alionlog", "onAccessibilityEvent: enter main----need scroll");
                }else {
                    sendMsg(MSG_MAIN,0);
                    Log.d("alionlog", "onAccessibilityEvent: enter main");
                }
            } else if (event.getClassName().equals(DETAIL)) {
                sendMsg(MSG_DETAIL,0);
                Log.d("alionlog", "onAccessibilityEvent: enter Detail");
            }else if(event.getClassName().equals(VIDEO)){
                /*mHandler.removeMessages(MSG_BACK_MAIN);
                mHandler.sendEmptyMessageDelayed(MSG_BACK_MAIN,timeSpanAction*30);*/
                sendMsg(MSG_BACK_MAIN,20);
            }else{
                /*isNeedScroll = true;
                ao.clickBackKey();*/
            }
        }else /*if(event.getPackageName().equals("com.android.packageinstaller")){
            ao.clickBackKey();
        }else */if(!event.getPackageName().equals(TARGETPACKAGENAME)){
            /*isNeedScroll = true;
            ao.clickBackKey();*/
        }
    }

    private void startMain() {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.alion.myapplication", "com.alion.accessibility.AccessibilityMainActivity");
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void printNote(AccessibilityNodeInfo node) {
        Log.d("alionlog", "printNote: ClassName" + node.getClassName());
        Log.d("alionlog", "printNote: Text" + node.getText());
        if (Build.VERSION.SDK_INT >= 16)
            Log.d("alionlog", "printNote: ViewIdResourceName" + node.getViewIdResourceName());
        Log.d("alionlog", "printNote: ------------------------------------------------------------------");
        int nodeCount = node.getChildCount();
        for (int i = 0; i < nodeCount; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                Log.d("alionlog", "printNote: Text" + child.getText());
            }
        }
        Log.d("alionlog", "printNote: ------------------------------------------------------------------");
    }


    @Override
    public void onInterrupt() {

    }



    public void getEnableNode() {
        Log.d("alionlog", "getEnableNode: adding paper");
        AccessibilityNodeInfo main = getView(RECYCLEVIEW);
        if (null != main) {
            for (int i = 0; i < main.getChildCount(); i++) {
                AccessibilityNodeInfo item = main.getChild(i);
                if (item != null && item.findAccessibilityNodeInfosByText("评").size() > 0 && item.findAccessibilityNodeInfosByText("置顶").size() <= 0) {
                    if(null!=item.getChild(0).getText() && !names.contains(item.getChild(0).getText().toString())) {
                        if (null != item.getChild(1).getText() && !(item.getChild(1).getText().toString().contains("广告"))) {
                            mainS.push(item);
                            names.add(item.getChild(0).getText().toString());
                        }
                    }
                }
            }
            isFinishedAdd = true;
            sendMsg(MSG_MAIN,0);
        } else {
            Log.d("alionlog", "getEnableNode: null==main");
            sendMsg(MSG_REFRESH_PAPER,0);
        }
    }

    private void initOperator() {
        if(ao==null){
            ao = AccessibilityOperator.getInstance();
            ao.updateEvent(this);
        }
    }

    AccessibilityNodeInfo view;
    private AccessibilityNodeInfo getView(String name){
        if(null!=view && !(view.getClassName().equals(name))){
            view=null;
        }
        initOperator();
        if(null==view){
            view = ao.getNodeByClass(ao.getRootNodeInfo(), name);
        }
        return view;
    }

    private void otherOP(){
        ao.clickByText(IKNOW);
        ao.clickByText(GETME);
    }

    private void waitNS(final int msg, final int time){
        new Thread(new Runnable(){

            public void run(){

                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mHandler.sendEmptyMessage(msg); //告诉主线程执行任务

            }

        }).start();
    }
}
