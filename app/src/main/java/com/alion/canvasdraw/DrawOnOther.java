package com.alion.canvasdraw;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class DrawOnOther extends View {
    Canvas mCanvas;
    Bitmap mBitmap;
    Paint mPaint;
    float mFloatY=200;
    Context mContext;
    Handler mHandler;
    public DrawOnOther(Context context) {
        super(context);
        mContext = context;
    }

    public DrawOnOther(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        mCanvas.drawLine(0,0,500,mFloatY,mPaint);
                        mFloatY+=30;
                        postInvalidate();

                        Log.d("aliontest111", "handleMessage: ");
                        super.handleMessage(msg);
                    }
                };
                Looper.loop();
            }
        }).start();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            mHandler.sendEmptyMessage(7);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap,0,0,mPaint);
        mCanvas = canvas;
        super.onDraw(canvas);
    }


}
