package com.alion.textview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.alion.myapplication.R;

public class MyShader extends View {
    private Bitmap bitmap;
    private float parentWidth;
    public static final int[] GRADIENT_COLORS = new int[]{
            Color.RED,Color.YELLOW,Color.BLUE, Color.GREEN, Color.WHITE, Color.RED };
    public static final float[] GRADIENT_POSITONS = new float[]{
            0.0f,0.5f,0.55f,0.6f,0.65f,1.0f};
    private int period = 0;

    public MyShader(Context context) {
        super(context);
    }

    public MyShader(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        my_shader(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        parentWidth = w;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    //shader 画笔填充
    private void my_shader(Canvas canvas){

        //Shader.TileMode是指平铺模式
        //Shader.TileMode.CLAMP是边缘拉伸模式，它会拉伸边缘的一个像素来填充其他区域。
        //Shader.TileMode.MIRROR是镜像模式，通过镜像变化来填充其他区域。需要注意的是，镜像模式先进行y轴方向的镜像操作，然后在进行x轴方向上的镜像操作。
        //Shader.TileMode.REPEAT是重复模式，通过复制来填充其他区域

        //bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.head);
        Shader shader[] = new Shader[8];
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
        shader[0] = new BitmapShader(bitmap,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(32);
        paint.setShader(shader[0]);

        int lineHeight = 100,lineOffset = 50;

        canvas.drawLine(0,lineHeight,parentWidth,100,paint);
        //canvas.drawCircle(240,240,100,paint);

        //LinearGradient是颜色线性渐变的着色器。
        //LinearGradient (float x0, float y0, float x1, float y1, int color0, int color1, Shader.TileMode tile)
        //（x0,y0）表示渐变的起点，（x1,y1）表示渐变的终点坐标，这两点都是相对于屏幕坐标系。color0,color1分别表示起点的颜色和终点的颜色。
        //LinearGradient (float x0, float y0, float x1, float y1, int[] colors, float[] positions, Shader.TileMode tile)
        //多色渐变的构造函数中，我们可以传入多个颜色，和每个颜色的占比。而且当positions参数传入null时，代表颜色是均匀的填充整个渐变区域的，显得比较柔和。
        lineHeight += lineOffset;
        shader[1] = new LinearGradient(0,lineHeight,parentWidth,lineHeight,Color.RED,Color.GREEN,Shader.TileMode.REPEAT);
        paint.setShader(shader[1]);
        canvas.drawLine(0,lineHeight,parentWidth,lineHeight,paint);

        lineHeight += lineOffset;
        shader[2] = new LinearGradient(0,lineHeight,parentWidth,lineHeight,GRADIENT_COLORS,null,Shader.TileMode.REPEAT);
        paint.setShader(shader[2]);
        canvas.drawLine(0,lineHeight,parentWidth,lineHeight,paint);

        //SweepGradient是梯度渐变，也称为扫描式渐变，效果有点类似与雷达扫描效果。
        //SweepGradient(float cx, float cy, int color0, int color1)
        // (cx,cy)表示渐变效果的中心点，也就是雷达扫描的圆点。color0和color1表示渐变的起点色和终点色。
        // 颜色渐变是顺时针的，从中心点的x轴正方形开始。
        // 注意:这里构造函数并不需要TileMode,因为梯度渐变的边界相当于无限大的。
        //SweepGradient(float cx, float cy,int colors[], float positions[])
        //colors[]颜色数组
        //positions数组，该数组中每一个position对应colors数组中每个颜色在360度中的相对位置，
        // position取值范围为[0,1]，0和1都表示3点钟位置，0.25表示6点钟位置，0.5表示9点钟位置，0.75表示12点钟位置，
        lineHeight += lineOffset +32;
        shader[3] = new SweepGradient(150,lineHeight,GRADIENT_COLORS,null);
        paint.setShader(shader[3]);
        canvas.drawCircle(150,lineHeight,50,paint);


        shader[4] = new SweepGradient(450,lineHeight,GRADIENT_COLORS,GRADIENT_POSITONS);
        paint.setShader(shader[4]);
        canvas.drawCircle(450,lineHeight,50,paint);

        //RadialGradient:创建从中心向四周发散的辐射渐变效果，其有两个构造函数：
        //RadialGradient(float centerX, float centerY, float radius, int centerColor, int edgeColor, Shader.TileMode tileMode)
        //centerX  圆心的X坐标
        //centerY  圆心的Y坐标
        //radius   圆的半径
        //centerColor  中心颜色
        //edgeColor   边缘颜色
        //RadialGradient(float centerX, float centerY, float radius, int[] colors, float[] stops, Shader.TileMode tileMode)
        //colors[]传入多个颜色值进去，这样就会用colors数组中指定的颜色值一起进行颜色线性插值。
        // stops数组，该数组中每一个stop对应colors数组中每个颜色在半径中的相对位置，
        // stop[]取值范围为[0,1]，0表示圆心位置，1表示圆周位置。如果stops数组为null，那么Android会自动为colors设置等间距的位置。
        lineHeight += lineOffset + 150;
        shader[5] = new RadialGradient(150,lineHeight,10,Color.GREEN,Color.RED,Shader.TileMode.MIRROR);
        paint.setShader(shader[5]);
        canvas.drawCircle(150,lineHeight,100,paint);

        if ( period < 250 || period >= 650){
            period = 250;
        }else {
            period += 5F;
        }
        shader[6] = new RadialGradient(period,lineHeight,30,GRADIENT_COLORS,null,Shader.TileMode.MIRROR);
        paint.setShader(shader[6]);
        canvas.drawCircle(450,period,100,paint);


        //ComposeShader用来组合不同的Shader,可以将两个不同的Shader组合在一起，它有两个构造函数:
        //ComposeShader (Shader shaderA, Shader shaderB, Xfermode mode)
        //ComposeShader (Shader shaderA, Shader shaderB, PorterDuff.Mode mode)
        lineHeight += lineOffset + 350;
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        shader[0] = new BitmapShader(bitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        shader[6] = new RadialGradient(150,lineHeight,550,Color.BLACK,Color.TRANSPARENT, Shader.TileMode.CLAMP);
        //混合产生新的Shader.
        shader[7] = new ComposeShader(shader[0],shader[6],PorterDuff.Mode.DST_IN);
        paint.setShader(shader[7]);
        //以新的Shader绘制一个圆。
        canvas.drawCircle(150,lineHeight,550,paint);
    }
}
