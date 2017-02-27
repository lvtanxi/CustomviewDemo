package com.lv.customviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Date: 2017-02-23
 * Time: 13:47
 * Description: 自定义ViewPager
 */

public class CustViewPager extends ViewGroup {
    /**
     * 手势识别
     * 1、定义出来
     * 2、实例化-吧想要的方法重写
     * 3、在onTouchEvent()把时间传递给手势识别器
     */
    private GestureDetector mGestureDetector;
    private int mCurrentPage;//当前
    private float mStartX, mDownX, mDownY;
    private Scroller mScroller;
    private onPageChangeListenter mPageChangeListenter;


    public CustViewPager(Context context) {
        this(context, null);
    }

    public CustViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy((int) distanceX, 0);
                return true;
            }
        });
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //遍历孩子，给每个孩子指定屏幕的坐标
        int childCount = getChildCount();
        View childView;
        for (int i = 0; i < childCount; i++) {
            childView = getChildAt(i);
            childView.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                int tempIndex = mCurrentPage;
                int minMove = getWidth() / 8;
                if ((mStartX - endX) > minMove)
                    tempIndex++;
                else if ((endX - mStartX) > minMove)
                    tempIndex--;
                scrollToPager(tempIndex);
                break;
        }
        return true;
    }

    /**
     * 返回为true，触发当前控件的的onTouchEvent，false事件则继续传递
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        boolean result = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();
                float distanceX = Math.abs(endX - mDownX);
                float distanceY = Math.abs(endY - mDownY);
                result = (distanceX > distanceY && distanceX > 10);
                break;
        }
        return result;
    }

    public void scrollToPager(int tempIndex) {
        if (tempIndex < 0)
            tempIndex = 0;
        else if (tempIndex > getChildCount() - 1)
            tempIndex = getChildCount() - 1;
        mCurrentPage = tempIndex;
        if (mPageChangeListenter != null)
            mPageChangeListenter.onPageChange(this, mCurrentPage);
        float distanceX = mCurrentPage * getWidth() - getScrollX();
        mScroller.startScroll(getScrollX(), getScrollY(), (int) distanceX, 0, 800);
        invalidate();
    }

    public void setOnPageChangeListenter(onPageChangeListenter pageChangeListenter) {
        mPageChangeListenter = pageChangeListenter;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            float currx = mScroller.getCurrX();
            scrollTo((int) currx, 0);
            invalidate();
        }
    }

    public interface onPageChangeListenter {
        void onPageChange(CustViewPager custViewPager, int position);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        View child;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
