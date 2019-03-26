package com.alion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.alion.myapplication.R;

public class CircleView  extends View {
    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    int circle = 15;
    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,210,mPaint);
        Paint mPaint1 = new Paint(); mPaint1.setAntiAlias(true);mPaint1.setColor(getResources().getColor(R.color.mytext_color_h));
        canvas.drawArc(0,0,getMeasuredWidth(),getMeasuredHeight(),0,-60,true,mPaint1);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bg_circle1),0,0,mPaint);
        super.onDraw(canvas);
    }
}
