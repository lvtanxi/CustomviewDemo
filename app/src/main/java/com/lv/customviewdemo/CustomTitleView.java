package com.lv.customviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


/**
 * User: 吕勇
 * Date: 2016-09-13
 * Time: 13:29
 * Description:
 */

/**
 * 自定义控件步奏
 * 1、自定义属性的声明和获取
 * 2、测量onMeasure
 * 3、布局onLayout（ViewGroup）
 * 4、绘制onDraw
 * 5、onTouchEvent
 * 6、onTnterceptTouchEvent(ViewGroup)
 */
public class CustomTitleView extends View {

    private String mTitleStr;
    private int mTitleTextColor;
    private float mTitleTextSize;

    private Paint mPaint;
    private Rect mRect;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //1、自定义属性的声明和获取
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);
        mTitleTextColor = array.getColor(R.styleable.CustomTitleView_titleTextColor, ContextCompat.getColor(getContext(), R.color.colorAccent));
        mTitleStr = array.getString(R.styleable.CustomTitleView_titleText);
        mTitleTextSize = array.getDimensionPixelSize(R.styleable.CustomTitleView_titleTextSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        array.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mTitleTextColor);
        mPaint.setTextSize(mTitleTextSize);
        mRect = new Rect();
        mPaint.getTextBounds(mTitleStr, 0, mTitleStr.length(), mRect);
    }

    //4、绘制onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(mTitleStr, getWidth() / 2 - mRect.centerX(), getHeight() / 2 - mRect.centerY(), mPaint);
    }

    /*2、测量onMeasure
    EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
      AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
      UNSPECIFIED：表示子布局想要多大就多大，很少使用*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec); //取出宽度的测量模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec); //取出宽度的确切数值
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);//取出高度的测量模式
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);//取出高度的确切数值

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            float textWidth = mRect.width();
            width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            float textHeight = mRect.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
        }
        setMeasuredDimension(width, height);
    }
}
