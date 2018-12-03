package com.alion.layout;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import static android.view.MotionEvent.ACTION_CANCEL;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class Linerlayout1 extends LinearLayout {
    static final String TAG = "alionlayout";
    public Linerlayout1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case ACTION_DOWN:
                Log.d(TAG, "Linerlayout1-onInterceptTouchEvent: Down");
                break;
            case ACTION_UP:
                Log.d(TAG, "Linerlayout1-onInterceptTouchEvent: Up");
                return true;
            //break;
            case ACTION_MOVE:
                Log.d(TAG, "Linerlayout1-onInterceptTouchEvent: MOVE");
                break;
            default:
                Log.d(TAG, "Linerlayout1-onInterceptTouchEvent: other");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case ACTION_DOWN:
                Log.d(TAG, "Linerlayout1-onTouchEvent: Down");
                break;
            case ACTION_UP:
                Log.d(TAG, "Linerlayout1-onTouchEvent: Up");
                return true;
            //break;
            case ACTION_MOVE:
                Log.d(TAG, "Linerlayout1-onTouchEvent: MOVE");
                break;
            default:
                Log.d(TAG, "Linerlayout1-onTouchEvent: other");
                break;
        }
        return super.onTouchEvent(event);
    }
}
