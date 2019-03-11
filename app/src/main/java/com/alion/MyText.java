package com.alion;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.alion.myapplication.R;

public class MyText extends View {
    private int mColor,mColorH,mFontSize,alternation;
    private Paint mPaint,mPaintH;
    private Paint.FontMetricsInt mFmi;
    private float s_x,e_x;
    private String str = "";

    public void setStr(String str,float startX) {
        this.str = str;
        this.s_x = startX*getMeasuredWidth()+getPaddingLeft();
        alternation = (int)(mPaint.measureText(str)/9);
        invalidate();
    }

    public MyText(Context context) {
        this(context,null);
    }

    public MyText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        Resources r = context.getResources();
        mColor = r.getColor(R.color.mytext_color);
        mColorH = r.getColor(R.color.mytext_color_h);

        mFontSize = r.getDimensionPixelSize(R.dimen.mytext_textsize);

        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mFontSize);
        mPaintH = new Paint();;
        mPaintH.setColor(mColorH);
        mPaintH.setAntiAlias(true);
        mPaintH.setTextSize(mFontSize);
        mFmi = mPaint.getFontMetricsInt();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int heigh = mFmi.descent-mFmi.ascent+getPaddingBottom()+getPaddingTop(),width = getMeasuredWidth();
        canvas.drawLine(getPaddingLeft(),heigh/2,width-getPaddingRight(),heigh/2,mPaint);
        s_x=Math.min(s_x,width-alternation-getPaddingRight()-mPaint.measureText(str));
        s_x=Math.max(s_x,alternation);
        e_x = s_x+mPaint.measureText(str);

        canvas.drawRoundRect(new RectF(s_x-alternation,getPaddingTop(),e_x+alternation,getPaddingBottom()+mFmi.descent-mFmi.ascent),15,15,mPaintH);
        if(!"".equals(str)) {
            canvas.drawLine(getPaddingLeft(), heigh / 2, s_x, heigh / 2, mPaintH);
            canvas.drawText(str, s_x, getPaddingTop() - mFmi.ascent, mPaint);
        }
    }
}
