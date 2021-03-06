package com.lv.customviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;


/**
 * Date: 2016-12-28
 * Time: 13:47
 * Description:
 */
public class PieView extends View {
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private List<PieData> mDatas;
    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);//消除锯齿
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDatas == null)
            return;
        float currentStartAngle = mStartAngle;  // 当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2); // 将画布坐标原点移动到中心
        float r = (float) (Math.max(mWidth, mHeight) / 2 * 0.8);// 饼状图半径
        RectF rectF = new RectF(-r, -r, r, r); // 饼状图绘制区域
        for (PieData data : mDatas) {
            mPaint.setColor(data.color);
            canvas.drawArc(rectF, currentStartAngle, data.angle, true, mPaint);
            currentStartAngle += data.angle;
        }
    }

    // 设置起始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();   // 刷新
    }

    // 设置数据
    public void setData(List<PieData> mData) {
        this.mDatas = mData;
        initData();
        invalidate();   // 刷新
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

    private void initData() {
        if (mDatas == null || mDatas.isEmpty())
            return;
        float sumValue = 0;
        for (int i = 0; i < mDatas.size(); i++) {
            PieData pie = mDatas.get(i);
            sumValue += pie.value;       //计算数值和
            int j = i % mColors.length;       //设置颜色
            pie.color = mColors[j];
        }
        for (PieData data : mDatas) {
            float percentage = data.value / sumValue;
            data.percentage = percentage;
            data.angle = percentage * 360;
        }
    }
}
