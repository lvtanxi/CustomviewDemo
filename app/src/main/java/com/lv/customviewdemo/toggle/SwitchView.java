package com.lv.customviewdemo.toggle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.lv.customviewdemo.R;

/**
 * Date: 2017-02-22
 * Time: 09:58
 * Description: SwitchView
 */

public class SwitchView extends View implements View.OnClickListener {
    private Paint mPaint, mBorderPaint;
    private Paint mRadiusPaint, mRadiusBorderPaint;
    private boolean mIsClick = true;
    private boolean mIsOpen = true;
    private float mStartX, mLastX, radius, mSlidLeft, smallRadius;
    private OnSwitchChangeListener mSwitchChangeListener;
    private int mOpenColor;
    private int mNomalColor;
    private int[] mOpenToClose, mCloseToOpen;

    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }


    public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SwitchView);
        mOpenColor = array.getColor(R.styleable.SwitchView_switch_open_bg_color, Color.GREEN);
        mNomalColor = array.getColor(R.styleable.SwitchView_switch_close_bg_color, Color.WHITE);
        int picColor = array.getColor(R.styleable.SwitchView_switch_pie_color, Color.WHITE);
        int borderColor = array.getColor(R.styleable.SwitchView_switch_border_color, Color.LTGRAY);
        float borderWitch = array.getDimension(R.styleable.SwitchView_switch_border_Width, 0);
        mIsOpen = array.getBoolean(R.styleable.SwitchView_switch_is_open, true);
        array.recycle();
        mOpenToClose = new int[]{mOpenColor, mNomalColor};
        mCloseToOpen = new int[]{mNomalColor, mOpenColor};
        radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        mSlidLeft = smallRadius = (float) (radius / 3.6);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mIsOpen ? mOpenColor : mNomalColor);


        mBorderPaint = getBorderPaint(borderWitch, borderColor);
        mRadiusBorderPaint = getBorderPaint(borderWitch, borderColor);


        mRadiusPaint = new Paint();
        mRadiusPaint.setAntiAlias(true);
        mRadiusPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRadiusPaint.setColor(picColor);
        setOnClickListener(this);
    }

    private Paint getBorderPaint(float borderWitch, int borderColor) {
        Paint borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(borderColor);
        if (borderWitch > 0)
            borderPaint.setStrokeWidth(borderWitch);
        return borderPaint;
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
        mPaint.setColor(mIsOpen ? mOpenColor : mNomalColor);
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rectF, radius, radius, mPaint);//第二个参数是x半径，第三个参数是y半径
        if (!mIsOpen)
            canvas.drawRoundRect(rectF, radius, radius, mBorderPaint);//第二个参数是x半径，第三个参数是y半径
        canvas.drawCircle(mSlidLeft, getHeight() / 2, smallRadius, mRadiusPaint);
        if (!mIsOpen)
            canvas.drawCircle(mSlidLeft, getHeight() / 2, smallRadius, mRadiusBorderPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mRadiusPaint.setShadowLayer(radius,10,10,Color.LTGRAY);
                mLastX = mStartX = event.getX();
                mIsClick = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float mMaxSlidLeft = getWidth()-smallRadius;
                float eventX = event.getX();
                float distancex = eventX - mStartX;
                mSlidLeft += distancex;
                if (mSlidLeft < smallRadius) {
                    mSlidLeft = smallRadius;
                    mIsOpen = true;
                } else if (mSlidLeft > mMaxSlidLeft) {
                    mSlidLeft = mMaxSlidLeft;
                    mIsOpen = false;
                }
                float section = mSlidLeft / mMaxSlidLeft;
                if(mSlidLeft==smallRadius||mSlidLeft==mMaxSlidLeft) {
                    mPaint.setShader(null);
                }else {
                    LinearGradient mLinearGradient = new LinearGradient(3, 3, mMaxSlidLeft* section, getHeight() - 3, mIsOpen ? mOpenToClose : mCloseToOpen, null, Shader.TileMode.CLAMP);
                    mPaint.setShader(mLinearGradient);
                }

                invalidate();
                mStartX = event.getX();
                mIsClick = Math.abs(eventX - mLastX) < 5;
                break;
            case MotionEvent.ACTION_UP:
                mPaint.setShader(null);
                mIsOpen = mSlidLeft > (getWidth() - smallRadius) / 2;
                flushView();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        if (mIsClick)
            flushView();
    }

    public boolean isOpen() {
        return mIsOpen;
    }

    public void setOpen(boolean open) {
        mIsOpen = open;
        flushView();
    }

    private void flushView() {
        mSlidLeft = mIsOpen ? (getWidth() - smallRadius) : smallRadius;
        mIsOpen = !mIsOpen;
        invalidate();
        if (mSwitchChangeListener != null)
            mSwitchChangeListener.onSwitchChange(this, mIsOpen);
    }

    public void setSwitchChangeListener(OnSwitchChangeListener switchChangeListener) {
        mSwitchChangeListener = switchChangeListener;
    }

    @Override
    protected void onDetachedFromWindow() {
        mPaint = null;
        mRadiusPaint = null;
        mBorderPaint = null;
        mRadiusBorderPaint = null;
        mSwitchChangeListener = null;
        super.onDetachedFromWindow();
    }

    public interface OnSwitchChangeListener {
        void onSwitchChange(SwitchView switchView, boolean isOpen);
    }

}
