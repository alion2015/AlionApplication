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

public class Linerlayout2 extends LinearLayout {
    static final String TAG = "alionlayout";
    public Linerlayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case ACTION_DOWN:
                Log.d(TAG, "Linerlayout2-onInterceptTouchEvent: Down");
                return true;
            //break;
            case ACTION_UP:
                Log.d(TAG, "Linerlayout2-onInterceptTouchEvent: Up");
                break;
            case ACTION_MOVE:
                Log.d(TAG, "Linerlayout2-onInterceptTouchEvent: MOVE");
                break;
            default:
                Log.d(TAG, "Linerlayout2-onInterceptTouchEvent: other");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case ACTION_DOWN:
                Log.d(TAG, "Linerlayout2-onTouchEvent: Down");
                return true;
            //break;
            case ACTION_UP:
                Log.d(TAG, "Linerlayout2-onTouchEvent: Up");
                break;
            case ACTION_MOVE:
                Log.d(TAG, "Linerlayout2-onTouchEvent: MOVE");
                break;
            default:
                Log.d(TAG, "Linerlayout2-onTouchEvent: other"+event.getAction());
                break;
        }
        return super.onTouchEvent(event);
    }
}
