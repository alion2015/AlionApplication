package com.alion.pageanimation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;

/**
 * //DIFFERENCE是第一次不同于第二次的部分显示出来
 * //REPLACE是显示第二次的
 * //REVERSE_DIFFERENCE 是第二次不同于第一次的部分显示
 * //INTERSECT交集显示
 * //UNION全部显示
 * //XOR补集 就是全集的减去交集剩余部分显示
 *
 * clip 是对画布的裁剪，需要其他绘制
 */
public class PictureTestActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }
    private static class SampleView extends View {
        private Paint mPaint;
        private Path mPath;
        public SampleView(Context context) {
            super(context);
            setFocusable(true);

            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStrokeWidth(6);
            mPaint.setTextSize(16);
            mPaint.setTextAlign(Paint.Align.RIGHT);

            mPath = new Path();
        }

        private void drawScene(Canvas canvas) {
            canvas.clipRect(0, 0, 100*5, 100*5);

            canvas.drawColor(Color.WHITE);

            mPaint.setColor(Color.RED);
            canvas.drawLine(0, 0, 100*5, 100*5, mPaint);

            mPaint.setColor(Color.GREEN);
            canvas.drawCircle(30*5, 70*5, 30*5, mPaint);

            mPaint.setColor(Color.BLUE);
            mPaint.setTextSize(60);
            canvas.drawText("Clipping", 100*5, 30*5, mPaint);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.GRAY);
            canvas.save();
            canvas.translate(10*5, 10*5);
            drawScene(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(160*5, 10*5);
            //canvas.clipRect(10*5, 10*5, 90*5, 90*5);
            canvas.clipRect(30*5, 30*5, 70*5, 70*5, Region.Op.DIFFERENCE);
            mPath.addCircle(50*5, 50*5, 50*5, Path.Direction.CCW);
            canvas.clipPath(mPath, Region.Op.DIFFERENCE);
            drawScene(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(10*5, 160*5);
            mPath.reset();
            canvas.clipPath(mPath); // makes the clip empty
            mPath.addCircle(50*5, 50*5, 50*5, Path.Direction.CCW);
            canvas.clipPath(mPath, Region.Op.REPLACE);
            drawScene(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(160*5, 160*5);
            canvas.clipRect(0, 0, 60*5, 60*5);
            canvas.clipRect(40*5, 40*5, 100*5, 100*5, Region.Op.UNION);
            drawScene(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(10, 310*5);
            canvas.clipRect(0, 0, 60*5, 60*5);
            canvas.clipRect(40*5, 40*5, 100*5, 100*5, Region.Op.XOR);
            drawScene(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(160*5, 310*5);
            canvas.clipRect(0, 0, 60*5, 60*5);
            canvas.clipRect(40*5, 40*5, 100*5, 100*5, Region.Op.REVERSE_DIFFERENCE);
            drawScene(canvas);
            canvas.restore();
        }
    }
}