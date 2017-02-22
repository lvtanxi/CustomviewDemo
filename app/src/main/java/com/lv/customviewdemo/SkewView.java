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
 * Time: 16:01
 * Description:Canvas 的错切(skew)
 */
public class SkewView extends View{
    public SkewView(Context context) {
        this(context, null);
    }

    public SkewView(Context context, AttributeSet attrs) {
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
        canvas.translate(getWidth()/2,getHeight()/2);
        RectF rectF=new RectF(0,0,200,200);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF,mPaint);

        canvas.skew(1,0); // 水平错切 <- 45度
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,mPaint);
    }
}
