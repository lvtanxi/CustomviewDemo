package com.lv.customviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2016-12-28
 * Time: 10:22
 * Description:Canvas的部分操作
 */
public class CanvasOperateView extends View {


    public CanvasOperateView(Context context) {
        this(context, null);
    }

    public CanvasOperateView(Context context, AttributeSet attrs) {
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
        mPaint.setStrokeWidth(5f);  //设置画笔宽度为5px
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
        mPaint.setStyle(Paint.Style.STROKE);
        //1、位移(translate)
        //translate是坐标系的移动，可以为图形绘制选择一个合适的坐标系。 请注意，位移是基于当前位置移动，而不是每次基于屏幕左上角的(0,0)点移动
        mPaint.setColor(Color.BLACK);
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,mPaint);

        // 在坐标原点绘制一个蓝色圆形
        mPaint.setColor(Color.BLUE);
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,mPaint);
        //2、缩放(scale)
        canvas.translate(0,0);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        RectF rect = new RectF(0,-200,200,0);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);
        canvas.scale(0.5f,0.5f,100,0);
        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);

        canvas.scale(-0.5f,-0.5f);          // 画布缩放当缩放比例为负数的时候会根据缩放中心轴进行翻转

        mPaint.setColor(Color.RED);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);

        //3、旋转(rotate)
        //默认的旋转中心依旧是坐标原点
        canvas.rotate(180);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rect,mPaint);
        super.onDraw(canvas);
    }
}
