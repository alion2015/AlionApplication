package com.alion.pageanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyView extends View {
    private final Context mContext;
    private List<Bitmap> mBitmaps = new ArrayList<>();// 位图数据列表
    private Paint mTextPaint;
    private float mTextSizeLarger = 96;
    private float mTextSizeNormal = 48;
    private int mViewWidth,mViewHeight;
    private float mClipX= mViewWidth;
    private float autoAreaLeft,autoAreaRight;
    private boolean isLastPage;
    private int pageIndex;
    private boolean isNextPage;
    private float mCurPointX;
    private double mMoveValid;

    public MyView(Context context, AttributeSet attrs) {
        super(context,attrs);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmaps.size()<1){
            defaultDisplay(canvas);
        }else{
            drawBtimaps(canvas);
        }
    }

    /**
     * 设置位图数据
     *
     * @param mBitmaps
     *            位图数据列表
     */
    public synchronized void setBitmaps(List<Bitmap> mBitmaps) {
        /*
         * 如果数据为空则抛出异常
         */
        if (null == mBitmaps || mBitmaps.size() == 0)
            throw new IllegalArgumentException("no bitmap to display");

        /*
         * 如果数据长度小于2则GG思密达
         */
        if (mBitmaps.size() < 2)
            throw new IllegalArgumentException("fuck you and fuck to use imageview");

        this.mBitmaps = mBitmaps;

        invalidate();
    }

    /**
     * 默认显示
     *
     * @param canvas
     *            Canvas对象
     */
    private void defaultDisplay(Canvas canvas) {
        // 绘制底色
        canvas.drawColor(Color.WHITE);

        // 绘制标题文本
        mTextPaint.setTextSize(mTextSizeLarger);
        mTextPaint.setColor(Color.RED);
        canvas.drawText("FBI WARNING", mViewWidth / 4, mViewHeight / 4, mTextPaint);

        // 绘制提示文本
        mTextPaint.setTextSize(mTextSizeNormal);
        mTextPaint.setColor(Color.BLACK);
        canvas.drawText("Please set data use setBitmaps method", mViewWidth / 4, mViewHeight / 3, mTextPaint);
    }

    /**
     * 初始化位图数据
     * 缩放位图尺寸与屏幕匹配
     */
    private void initBitmaps() {
        List<Bitmap> temp = new ArrayList<Bitmap>();
        for (int i = mBitmaps.size() - 1; i >= 0; i--) {
            Bitmap bitmap = Bitmap.createScaledBitmap(mBitmaps.get(i), mViewWidth, mViewHeight, true);
            temp.add(bitmap);
        }
        mBitmaps = temp;
    }

    /**
     * 绘制位图
     *
     * @param canvas
     *            Canvas对象
     */
    private void drawBtimaps(Canvas canvas) {
        // 绘制位图前重置isLastPage为false
        isLastPage = false;

        // 限制pageIndex的值范围
        pageIndex = pageIndex < 0 ? 0 : pageIndex;
        pageIndex = pageIndex > mBitmaps.size() ? mBitmaps.size() : pageIndex;

        // 计算数据起始位置
        int start = mBitmaps.size() - 2 - pageIndex;
        int end = mBitmaps.size() - pageIndex;

        /*
         * 如果数据起点位置小于0则表示当前已经到了最后一张图片
         */
        if (start < 0) {
            // 此时设置isLastPage为true
            isLastPage = true;

            // 并显示提示信息
            Toast.makeText(mContext,"This is fucking lastest page",Toast.LENGTH_SHORT).show();

            // 强制重置起始位置
            start = 0;
            end = 1;
        }

        for (int i = start; i < end; i++) {
            canvas.save();

            /*
             * 仅裁剪位于最顶层的画布区域
             * 如果到了末页则不在执行裁剪
             */
            if (!isLastPage && i == end - 1) {
                canvas.clipRect(0, 0, mClipX, mViewHeight);
            }
            canvas.drawBitmap(mBitmaps.get(i), 0, 0, null);

            canvas.restore();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 每次触发TouchEvent重置isNextPage为true
        isNextPage = true;

        /*
         * 判断当前事件类型
         */
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:// 触摸屏幕时
                // 获取当前事件点x坐标
                mCurPointX = event.getX();

                /*
                 * 如果事件点位于回滚区域
                 */
                if (mCurPointX < autoAreaLeft) {
                    // 那就不翻下一页了而是上一页
                    isNextPage = false;
                    pageIndex--;
                    mClipX = mCurPointX;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:// 滑动时
                float SlideDis = mCurPointX - event.getX();
                if (Math.abs(SlideDis) > mMoveValid) {
                    // 获取触摸点的x坐标
                    mClipX = event.getX();

                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:// 触点抬起时
                // 判断是否需要自动滑动
                judgeSlideAuto();

                /*
                 * 如果当前页不是最后一页
                 * 如果是需要翻下一页
                 * 并且上一页已被clip掉
                 */
                if (!isLastPage && isNextPage && mClipX <= 0) {
                    pageIndex++;
                    mClipX = mViewWidth;
                    invalidate();
                }
                break;
        }
        return true;
    }

    /**
     * 判断是否需要自动滑动
     * 根据参数的当前值判断绘制
     */
    private void judgeSlideAuto() {
        /*
         * 如果裁剪的右端点坐标在控件左端五分之一的区域内，那么我们直接让其自动滑到控件左端
         */
        if (mClipX < autoAreaLeft) {
            while (mClipX > 0) {
                mClipX--;
                invalidate();
            }
        }
        /*
         * 如果裁剪的右端点坐标在控件右端五分之一的区域内，那么我们直接让其自动滑到控件右端
         */
        if (mClipX > autoAreaRight) {
            while (mClipX < mViewWidth) {
                mClipX++;
                invalidate();
            }
        }
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // 计算控件左侧和右侧自动吸附的区域
        /*mViewWidth = mContext.getResources().getDisplayMetrics().widthPixels; //获取window宽高值，包含状态栏和标题栏等
        mViewHeight = mContext.getResources().getDisplayMetrics().heightPixels;*/
        mViewWidth = getMeasuredWidth();  //当前控件传入的宽高值
        mViewHeight = getMeasuredHeight();
        autoAreaLeft = mViewWidth * 1 / 3F;
        autoAreaRight = mViewWidth * 2 / 3F;
        mMoveValid = ViewConfiguration.get(mContext).getScaledWindowTouchSlop();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        initBitmaps();
    }
}
