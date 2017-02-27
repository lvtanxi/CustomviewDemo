package com.lv.customviewdemo.evenTest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.lv.customviewdemo.EventActivty;

/**
 * Date: 2017-02-27
 * Time: 15:10
 * Description:
 */

public class TouchEventFater extends LinearLayout{


    public TouchEventFater(Context context) {
        this(context,null);
    }

    public TouchEventFater(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchEventFater(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
        EventActivty.logMessage("Fater|onTouchEvent--->",event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        EventActivty.logMessage("Fater|onInterceptTouchEvent--->",ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        EventActivty.logMessage("Fater|dispatchTouchEvent--->",ev);
        return super.dispatchTouchEvent(ev);
    }
}
