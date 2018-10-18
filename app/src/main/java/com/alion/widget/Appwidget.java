package com.alion.widget;

/**
 * Created by alion on 2018/10/18.
 */

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.wutong.ContentObserver.R;

/**
 * AppWidget学习demo
 * 个人心得：
 * onUpDate方法用于创建和更新AppWidget，将所需的ID通过intent进行传递。
 * onReceive方法通过接收intent传送的广播事件实现AppWidget的变化操作。
 */
public class Appwidget extends AppWidgetProvider {

    private static int i = 1;
    private static final String ACTION = "com.alion.timeclick";

    //当AppWidget实例第一次被创建时调用。
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    //接收广播事件。
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("widget", "onReceive + " + intent.getAction());
        super.onReceive(context, intent);
        ComponentName thisWidget = new ComponentName(context,Appwidget.class);//定义容器
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ui);//定义RemoteViews
        //定义AppWidgetManager，用于之后更新AppWidget
        AppWidgetManager appWidgetManager = AppWidgetManager
                .getInstance(context);

        //通过接收广播的Intent来进行widget的UI操作
        if (intent.getAction().equals(ACTION)) {
            views.setTextViewText(R.id.tvResult, "result = " + i);
            Log.d("alion", "onReceive: i=="+i);
            i++;
        }

        //更新AppWidget
        appWidgetManager.updateAppWidget(thisWidget, views);
    }

    //到达指定的更新时间或用户向桌面添加widget时候调用。
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {
        Log.e("widget", "onUpdate");
        for (int id : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ui);
            Intent intent1 = new Intent("click");//定义intent
            PendingIntent pendingIntentOne = PendingIntent.getBroadcast(context, 0, intent1, 0);//用PendingIntent将Intent包裹起来
            views.setOnClickPendingIntent(R.id.btnAdd, pendingIntentOne);//按钮点击事件监听
            //更新AppWidget
            appWidgetManager.partiallyUpdateAppWidget(id, views);
        }
    }

    //当AppWidget被删除时调用。
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    //当最后一个AppWidget被删除时调用。
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}