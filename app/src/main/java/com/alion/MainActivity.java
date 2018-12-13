package com.alion;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;

import com.alion.countdowntimer.TimeCount;
import com.alion.myapplication.R;

/**
 * ContentObserver 可以用来监听任何数据的变化
 */
public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ContentObserver observer;
    private Uri uri = Uri.parse("content://.test.a");  ;
    private Button mButton;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);

        ViewOutlineProvider viewOutlineProvider1 =
                new ViewOutlineProvider() {

                    @Override
                    public void getOutline(View view, Outline outline) {
                        //修改outline为特定形状
                        outline.setRoundRect(0, 0,
                                view.getWidth(),
                                view.getHeight(), 90);
                    }
                };
        mButton.setOutlineProvider(viewOutlineProvider1);
       /* findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimeCount(500000, 1000, new TimeCount.TimerCallback() {
                @Override
                public void onFinish() {
                    Log.i(TAG, "onFinish" + 1);
                }

                @Override
                public void onTick(long millisUntilFinished) {
                    Log.i(TAG, "onTick" + (1-millisUntilFinished/5000f));

                    sendBroadcast(new Intent("com.alion.timeclick"));
                }
            }).start();
            }
        });*/
    }
}
