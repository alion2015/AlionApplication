package com.alion.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

public class Yinpintu extends View {
    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private int mWidth,mRectHeight,mRectWidth,mRectCount=20;

    public Yinpintu(Context context) {
        super(context);
    }

    public Yinpintu(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = new Paint();
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int) (mWidth * 0.6 / mRectCount);
        mLinearGradient = new LinearGradient( 0,0,mRectWidth,
                mRectHeight,
                Color.RED,
                Color.BLUE,
                Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float currentHeight = 0;
        for (int i = 0; i < mRectCount; i++) {
            currentHeight = (float) (mRectHeight *Math.random());
            canvas.drawRect(
                    (float) (mWidth * 0.4 / 2 + mRectWidth * i + 0), currentHeight,
                    (float) (mWidth * 0.4 / 2 + mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint);
        }
        postInvalidateDelayed(500);
    }
}
