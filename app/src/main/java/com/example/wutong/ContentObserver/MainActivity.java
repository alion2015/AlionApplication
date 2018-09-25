package com.example.wutong.ContentObserver;

import android.annotation.SuppressLint;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * ContentObserver 可以用来监听任何数据的变化
 */
public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ContentObserver observer;
    private Uri uri = Uri.parse("content://alion.test.a");  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new TimeCount(5000, 200, new TimeCount.TimerCallback() {
            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish" + 1);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i(TAG, "onTick" + (1-millisUntilFinished/5000f));
                getContentResolver().notifyChange(uri, null);
            }
        }).start();
        observer = new ContentObserver(new Handler()) {

            @SuppressLint("NewApi") @Override
            public void onChange(boolean selfChange, Uri uri) {
                // TODO Auto-generated method stub
                super.onChange(selfChange, uri);
                Log.i(TAG, "DB Chang " + uri.toString());
            }

            @Override
            public void onChange(boolean selfChange) {
                Log.i(TAG, "DB Chang");
                super.onChange(selfChange);
            }
        };
        getContentResolver().registerContentObserver(uri,false/*false通知父类和本身更新,true包含子类*/,observer);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentResolver().notifyChange(uri, null);
            }
        });
    }
}
