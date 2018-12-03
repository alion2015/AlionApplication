package com.alion.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alion.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 多线程间全局变量共享,时序控制是关键
 */
public class ThreadActivity extends AppCompatActivity {
    static final String TAG = "ThreadActivity";
    Handler mHandler;
    int k =6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        Log.d(TAG, "handleMessage: "+msg.what);
                        k =8;
                        super.handleMessage(msg);
                    }
                };
                Looper.loop();
            }
        }).start();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessage(7);
                Log.d(TAG, "onClick: "+k);
                EventBus.getDefault().post(new MessageEvent1("hello world"));
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent1 event) {
        Log.d(TAG, "onMessageEvent: "+event.Message);
    };
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
