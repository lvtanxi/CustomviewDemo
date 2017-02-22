package com.lv.customviewdemo;

import android.app.Application;

import com.antfortune.freeline.FreelineCore;

/**
 * Date: 2016-12-28
 * Time: 15:28
 * Description:
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);
    }
}
