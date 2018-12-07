package com.alion.canvasdraw;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    public MyIntentService(){
        super("default");
    }
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("aliontest111", "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("aliontest111", "onHandleIntent: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                    /*mCanvas.drawLine(0,0,(float)(Math.random()%500),(float)(Math.random()%600),mPaint);
                    postInvalidate();*/
            }
        }).start();
    }
}