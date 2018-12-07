package com.alion.canvasdraw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alion.myapplication.R;

/**
 * ContentObserver 可以用来监听任何数据的变化
 */
public class TextActivity extends AppCompatActivity {

    private String TAG = "TextActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texttest);
    }
}
