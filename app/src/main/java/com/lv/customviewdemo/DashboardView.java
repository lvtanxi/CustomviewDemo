package com.lv.customviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2016-12-28
 * Time: 15:51
 * Description:主要是讲述Canvas的旋转rotate
 */
public class DashboardView extends View {
    public DashboardView(Context context) {
        this(context, null);
    }

    public DashboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 3.在构造函数中初始化
        initPaint();
    }

    //创建一个画笔
    private Paint mPaint = new Paint();

    //初始化画笔

    private void initPaint() {
        mPaint.setColor(Color.BLACK); //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE); //设置画笔模式为填充
        mPaint.setStrokeWidth(3f);  //设置画笔宽度为10px
        mPaint.setAntiAlias(true);
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
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(0, 0, 400, mPaint);
        canvas.drawCircle(0, 0, 380, mPaint);
        for (int i = 0; i < 360; i += 10) {
            canvas.drawLine(0,380,0,400,mPaint);
            canvas.rotate(10);
        }
    }
}
