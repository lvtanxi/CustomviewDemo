package com.lv.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Date: 2017-02-27
 * Time: 15:00
 * Description:
 */

public class EventActivty extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        logMessage( "Activity|dispatchTouchEvent--->",ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        logMessage( "Activity|onTouchEvent--->",event);
        return super.onTouchEvent(event);
    }

    public static void logMessage(String defMessage,MotionEvent ev){
        String messgae= "down";
        if(ev.getAction()==MotionEvent.ACTION_MOVE)
            messgae= "move";
        else if (ev.getAction()==MotionEvent.ACTION_UP)
            messgae= "up";
        Log.d("evenTest", defMessage+messgae);
    }

}
