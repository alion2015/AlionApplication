package com.alion.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by alion on 2019/1/1.
 */

@TargetApi(16)
public class AccessibilitySampleService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        // 通过代码可以动态配置，但是可配置项少一点
//        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
//        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPE_WINDOWS_CHANGED
//                | AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
//                | AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
//                | AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
//        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
//        accessibilityServiceInfo.notificationTimeout = 0;
//        accessibilityServiceInfo.flags = AccessibilityServiceInfo.DEFAULT;
//        setServiceInfo(accessibilityServiceInfo);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // 此方法是在主线程中回调过来的，所以消息是阻塞执行的
        // 获取包名
        AccessibilityOperator ao = AccessibilityOperator.getInstance();
        ao.updateEvent(this, event);
        printEvent(event);
        AccessibilityNodeInfo ani = ao.getRootNodeInfo();
        printNote(ani);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void printNote(AccessibilityNodeInfo node) {
        Log.d("alionlog", "printNote: ------------------------------------------------------------------");
        Log.d("alionlog", "printNote: PackageName"+node.getPackageName());
        Log.d("alionlog", "printNote: ClassName"+node.getClassName());
        Log.d("alionlog", "printNote: Text"+node.getText());
        Log.d("alionlog", "printNote: ContentDescription"+node.getContentDescription());
        if (Build.VERSION.SDK_INT >= 16)Log.d("alionlog", "printNote: ViewIdResourceName"+node.getViewIdResourceName());
        Rect out=new Rect();
        node.getBoundsInParent(out);
        Log.d("alionlog", "printNote: BoundsInParent"+out.toString());
        node.getBoundsInScreen(out);
        Log.d("alionlog", "printNote: BoundsInScreen"+out.toString());
        Log.d("alionlog", "printNote: ------------------------------------------------------------------");


        int nodeCount = node.getChildCount();
        for(int i =0;i<nodeCount;i++){
            AccessibilityNodeInfo child = node.getChild(i);
            if(child!=null){
                printNote(child);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void printEvent(AccessibilityEvent event) {
        Log.d("alionlog", "printEvent: ");
        Log.d("alionlog", "printEvent: event.type"+event.getEventType());
        Log.d("alionlog", "printEvent: event.action"+event.getAction());
        if (Build.VERSION.SDK_INT >= 16)Log.d("alionlog", "printEvent: event.type"+event.getContentChangeTypes());
        Log.d("alionlog", "printEvent: event.PackageName"+event.getPackageName());
        Log.d("alionlog", "printEvent: event.Source"+event.getSource());
    }

    @Override
    public void onInterrupt() {

    }
}
