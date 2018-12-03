package com.alion.layout;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static android.view.MotionEvent.ACTION_CANCEL;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class TargetView extends android.support.v7.widget.AppCompatTextView {
    static final String TAG = "alionlayout";
    public TargetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case ACTION_DOWN:
                Log.d(TAG, "TargetView-onTouchEvent: Down");
                return true;
            //break;
            case ACTION_UP:
                Log.d(TAG, "TargetView-onTouchEvent: Up");
                break;
            case ACTION_MOVE:
                Log.d(TAG, "TargetView-onTouchEvent: MOVE");
                break;
            default:
                Log.d(TAG, "TargetView-onTouchEvent: other");
                break;
        }
        return super.onTouchEvent(event);
    }
}
