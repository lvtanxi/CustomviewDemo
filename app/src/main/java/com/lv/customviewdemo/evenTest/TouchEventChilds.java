package com.lv.customviewdemo.evenTest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.lv.customviewdemo.EventActivty;

/**
 * Date: 2017-02-27
 * Time: 15:16
 * Description:
 */

public class TouchEventChilds extends LinearLayout{
    public TouchEventChilds(Context context) {
        this(context,null);
    }

    public TouchEventChilds(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchEventChilds(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        EventActivty.logMessage("Childs|onTouchEvent--->",event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        EventActivty.logMessage("Childs|dispatchTouchEvent--->",ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        EventActivty.logMessage("Childs|dispatchTouchEvent--->",ev);
        return super.dispatchTouchEvent(ev);
    }
}
