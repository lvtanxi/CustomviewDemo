package com.lv.customviewdemo.evenTest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.lv.customviewdemo.EventActivty;

/**
 * Date: 2017-02-27
 * Time: 15:44
 * Description:
 */

public class MyImageView extends ImageView{
    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        EventActivty.logMessage("ImageView|onTouchEvent--->",event);
        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        EventActivty.logMessage("ImageView|dispatchTouchEvent--->",ev);
        return super.dispatchTouchEvent(ev);
    }
}
