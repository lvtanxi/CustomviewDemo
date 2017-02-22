package com.lv.customviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2016-12-28
 * Time: 10:22
 * Description:
 */
public class CanvasView extends View {


    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 3.在构造函数中初始化
        initPaint();
    }

    //创建一个画笔
    private Paint mPaint = new Paint();

    //初始化画笔

    private void initPaint() {
        mPaint.setColor(Color.WHITE); //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL); //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);  //设置画笔宽度为10px
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = widthMode == MeasureSpec.EXACTLY ? widthSize : getPaddingLeft() + getPaddingRight();
        int height = heightMode == MeasureSpec.EXACTLY ? heightSize : getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawColor(Color.BLUE);
        int width = getWidth();
        canvas.drawPoint(width / 2, 10, mPaint); //画一个点
        canvas.drawPoints(new float[]{width / 2, 30, width / 2, 50}, mPaint);//绘制多个点

        canvas.translate(0, 70);//移动

        canvas.drawLine(10, 70, width / 2, 70, mPaint);//在坐标(10,80)(width/2,80)之间绘制一条直线 (有点绕)

        canvas.translate(0, 90);//移动

        //绘制矩形
        // 第一种
        canvas.drawRect(10, 90, 80, 200, mPaint);

        // 第二种
        Rect rect = new Rect(90, 90, 160, 200);
        canvas.drawRect(rect, mPaint);
        // 第三种
        RectF rectF = new RectF(170, 90, 240, 200);//Rect与RectF两者最大的区别就是精度不同，Rect是int(整形)的，而RectF是float(单精度浮点型)的
        canvas.drawRect(rectF, mPaint);

        //绘制圆角矩形
        RectF rect1 = new RectF(250, 90, 320, 200);
        canvas.drawRoundRect(rect1, 30, 30, mPaint); //圆角矩形的角实际上不是一个正圆的圆弧，而是椭圆的圆弧，这里的两个参数实际上是椭圆的两个半径
        //rx大于宽度的一半， ry大于高度的一半 时奇迹就出现了， 你会发现圆角矩形变成了一个椭圆，
        //绘制椭圆
        RectF rectF1 = new RectF(330, 90, 400, 200);
        canvas.drawOval(rectF1, mPaint);

        //绘制圆
        canvas.drawCircle(445, 145, 35, mPaint);

        // 绘制圆弧 无中心点
        RectF rectFArc = new RectF(490, 90, 560, 200);
        canvas.drawArc(rectFArc, 0, 90, false, mPaint);
        // 绘制圆弧 有中心点
        RectF rectFArc2 = new RectF(570, 90, 640, 200);
        canvas.drawArc(rectFArc2, 0, 90, true, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(690, 145, 35, mPaint);//空心圆 STROKE 描边 FILL 填充 FILL_AND_STROKE  描边加填充
        super.onDraw(canvas);
    }
}
