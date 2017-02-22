package com.lv.customviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2016-12-28
 * Time: 14:35
 * Description:
 */
public class PieAct extends AppCompatActivity {
    private PieView mPieview;

    public static void startPieAct(Activity activity) {
        activity.startActivity(new Intent(activity, PieAct.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_act);
        initView();
        initData();
    }

    private void initData() {
        List<PieData> datas= new ArrayList<>();
        PieData pieData = new PieData("sloop", 60);
        PieData pieData2 = new PieData("sloop", 30);
        PieData pieData3 = new PieData("sloop", 40);
        PieData pieData4 = new PieData("sloop", 20);
        PieData pieData5 = new PieData("sloop", 20);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        mPieview.setData(datas);
    }

    private void initView() {
        mPieview = (PieView) findViewById(R.id.pieview);
    }
}
