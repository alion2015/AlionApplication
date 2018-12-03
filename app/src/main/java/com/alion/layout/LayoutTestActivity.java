package com.alion.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.alion.myapplication.R;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

/**
 * 1.AC是底板，GP包含view，AC和View默认拦截
 * 2.寻找newTarget，找到后判断：
 * oldTarget!=null && oldTarget!=newTarget {
 * 	传递cancel直到oldTarget
 * 	传递event给AC
 * }
 * oldTarget = newTarget
 * 3.GP若是oldTarget，直接执行Touch，不访问拦截
 */
public class LayoutTestActivity extends AppCompatActivity {
    static final String TAG = "alionlayout";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layouttest);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case ACTION_DOWN:
                Log.d(TAG, "LayoutTestActivity-onTouchEvent: Down");
                break;
            case ACTION_UP:
                Log.d(TAG, "LayoutTestActivity-onTouchEvent: Up");
                break;
            case ACTION_MOVE:
                Log.d(TAG, "LayoutTestActivity-onTouchEvent: MOVE");
                break;
            default:
                Log.d(TAG, "LayoutTestActivity-onTouchEvent: other");
                break;
        }
        return super.onTouchEvent(event);
    }
}
