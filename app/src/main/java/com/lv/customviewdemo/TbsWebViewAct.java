package com.lv.customviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * User: 吕勇
 * Date: 2016-09-13
 * Time: 14:20
 * Description:
 */
public class TbsWebViewAct extends AppCompatActivity {
    private static final String TAG = "TbsWebViewAct";
    private Toolbar mTooal;
    private boolean change;

    public static void startTbsWebViewAct(Activity activity) {
        activity.startActivity(new Intent(activity, TbsWebViewAct.class).putExtra("Str","Str"));
    }

    private WebView mForumContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tbs_web_view);
        initView();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    private void initView() {
        mForumContext = (WebView) findViewById(R.id.forum_context);
        WebSettings webSettings = mForumContext.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mForumContext.requestFocus();
        mForumContext.requestFocusFromTouch();
        mForumContext.setWebViewClient(new WebViewClient());
        mForumContext.loadUrl("http://data.wumart.com/?wmart_token=eJrkfbzt0IMnZz0%2BQK3lav%2FcKOmuRp3yjbB72nPni6he7RYUtilo%2Fmo7cuL0mCXMcLbdAnGXVEMi%0AtH0ZsQ%3D%3D");
        mTooal = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTooal);
    }

    @Override
    public void onBackPressed() {
        if (mForumContext.canGoBack()) {
            mForumContext.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (change) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            change = !change;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        String message = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "屏幕设置为：横屏" : "屏幕设置为：竖屏";

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        String str = getIntent().getStringExtra("Str");
        Log.d(TAG, "onConfigurationChanged:"+str);
    }
}
