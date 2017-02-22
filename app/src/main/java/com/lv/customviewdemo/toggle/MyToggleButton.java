package com.lv.customviewdemo.toggle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lv.customviewdemo.R;

/**
 * Date: 2016-12-29
 * Time: 15:39
 * Description:
 */
public class MyToggleButton extends View implements View.OnClickListener {

    private Bitmap mBackgroudBitmap, slidingBitmap;
    private int mSlidLeftMax, mSlidLeft = -80;
    private Paint mPaint;
    private boolean mIsOpen = true;
    private float mStartX, mLastX;
    private boolean mIsEnableClick = true;

    public MyToggleButton(Context context) {
        this(context, null);
    }

    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        bindListener();
    }

    private void bindListener() {
        setOnClickListener(this);
    }

    private void initView() {
        mBackgroudBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.checkswitch_bottom);
        slidingBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.checkswitch_btn_pressed);
        mSlidLeftMax = mBackgroudBitmap.getWidth() - slidingBitmap.getWidth();//这里是图片的问题
        mSlidLeftMax = 80;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mBackgroudBitmap.getWidth(), mBackgroudBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBackgroudBitmap, 0, 0, mPaint);
        canvas.drawBitmap(slidingBitmap, mSlidLeft, 0, mPaint);
    }

    @Override
    public void onClick(View v) {
        if (mIsEnableClick)
            flushView();
    }

    private void flushView() {
        mSlidLeft = mIsOpen ? mSlidLeftMax : -80;
        mIsOpen = !mIsOpen;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = mStartX = event.getX();
                mIsEnableClick = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float endx = event.getX();
                float distancex = endx - mStartX;
                mSlidLeft += distancex;
                if (mSlidLeft < -80)
                    mSlidLeft = -80;
                else if (mSlidLeft > mSlidLeftMax)
                    mSlidLeft = mSlidLeftMax;
                invalidate();
                mStartX = event.getX();
                if (Math.abs(endx - mLastX) > 5)
                    mIsEnableClick = false;
                break;
            case MotionEvent.ACTION_UP:
                if (!mIsEnableClick) {
                    mIsOpen = mSlidLeft > mSlidLeftMax / 2;
                    flushView();
                }
                break;
        }
        return true;
    }
}