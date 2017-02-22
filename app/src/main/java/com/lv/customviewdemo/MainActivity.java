package com.lv.customviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CustomTitleView mTestCustomView, mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTestCustomView = (CustomTitleView) findViewById(R.id.testCustomView);
        mTitleView = (CustomTitleView) findViewById(R.id.customTitle);
        mTestCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TbsWebViewAct.startTbsWebViewAct(MainActivity.this);
            }
        });
        mTitleView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                Log.d("MainActivity", "触摸点相对于其所在组件坐标系的坐标 x:" + x + "     y:" + y);
                float rawX = event.getRawX();
                float rawY = event.getRawY();
                Log.d("MainActivity", "触摸点相对于屏幕默认坐标系的坐标 rawX:" + rawX + "     rawY:" + rawY);
                return false;
            }
        });
    }

    public void showDialog(View v) {
        int top = v.getTop();
        int left = v.getLeft();
        int bottom = v.getBottom();
        int right = v.getRight();
        Log.d("MainActivity", "这是相对于父控件的 top:" + top + "     left:" + left + "     bottom:" + bottom + "     right:" + right);
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("dialog")
                .setMessage("测试我的信息")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // .....
                    }
                })
                .setNegativeButton("取消", null)
                .show();*/
    }

    public void showPieView(View view) {
        PieAct.startPieAct(this);
    }
}
