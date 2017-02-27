package com.lv.customviewdemo.youku;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lv.customviewdemo.CustViewPager;
import com.lv.customviewdemo.R;
import com.lv.customviewdemo.toggle.SwitchView;

public class YouKuAct extends Activity implements OnClickListener {
    private RelativeLayout level1, level2, level3;
    private ImageView iv_home;
    private ImageView iv_menu;
    private boolean isShowLevel2 = true;
    private boolean isShowLevel3 = true;
    private boolean isShowMenu = true;
    private SwitchView mSwitchView;
    private CustViewPager mCustViewPager;
    private RadioGroup mRadioGroup;

    private int[] imags = {R.mipmap.tut_page_1_background,R.mipmap.tut_page_1_front,R.mipmap.tut_page_3_foreground,R.mipmap.tut_page_4_background};
    private int[] cors= {android.R.color.holo_blue_light,android.R.color.holo_green_light,android.R.color.holo_orange_light,android.R.color.holo_red_light};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {
        setContentView(R.layout.youku_act);
        iv_home = (ImageView) findViewById(R.id.icon_home);
        iv_menu = (ImageView) findViewById(R.id.icon_menu);
        level1 = (RelativeLayout) findViewById(R.id.level1);
        level2 = (RelativeLayout) findViewById(R.id.level2);
        level3 = (RelativeLayout) findViewById(R.id.level3);
        mSwitchView = (SwitchView) findViewById(R.id.sw);
        mCustViewPager= (CustViewPager) findViewById(R.id.cu);
        mRadioGroup= (RadioGroup) findViewById(R.id.myg);
        mCustViewPager.addView(View.inflate(this,R.layout.test,null));
        for (int i = 0; i < imags.length; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(imags[i]);
            imageView.setBackgroundResource(cors[i]);
            mCustViewPager.addView(imageView);
            RadioButton radioButton=new RadioButton(this);
            radioButton.setId(i);
            radioButton.setChecked(i==0);
            mRadioGroup.addView(radioButton);
        }
        RadioButton radioButton=new RadioButton(this);
        radioButton.setId(imags.length);
        mRadioGroup.addView(radioButton);
    }

    private void initListener() {
        iv_home.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        level1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YouKuAct.this, "level1", Toast.LENGTH_SHORT).show();
            }
        });
        level2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YouKuAct.this, "level2", Toast.LENGTH_SHORT).show();
            }
        });
        level3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YouKuAct.this, "level3", Toast.LENGTH_SHORT).show();
            }
        });
        mSwitchView.setSwitchChangeListener(new SwitchView.OnSwitchChangeListener() {
            @Override
            public void onSwitchChange(SwitchView switchView, boolean isOpen) {
                Log.d("YouKuAct", "isOpen:" + isOpen);
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mCustViewPager.scrollToPager(checkedId);
            }
        });
        mCustViewPager.setOnPageChangeListenter(new CustViewPager.onPageChangeListenter() {
            @Override
            public void onPageChange(CustViewPager custViewPager, int position) {
                mRadioGroup.check(position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_home:
                if (AnimUtil.animCount != 0) {
                    //说明有动画在执行
                    return;
                }
                if (isShowLevel2) {
                    // 需要隐藏
                    // Log.e(tag, "隐藏");
                    int startOffset = 0;
                    if (isShowLevel3) {
                        AnimUtil.closeMenu(level3, startOffset);
                        isShowLevel3 = false;
                        startOffset += 200;
                    }
                    AnimUtil.closeMenu(level2, startOffset);
                } else {
                    // 显示
                    // Log.e(tag, "显示");
                    AnimUtil.showMenu(level2, 0);
                }
                isShowLevel2 = !isShowLevel2;
                break;
            case R.id.icon_menu:
                if (AnimUtil.animCount != 0) {
                    //说明有动画在执行
                    return;
                }
                if (isShowLevel3) {
                    AnimUtil.closeMenu(level3, 0);
                } else {
                    AnimUtil.showMenu(level3, 0);
                }
                isShowLevel3 = !isShowLevel3;
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (isShowMenu) {
            // 显示，变成隐藏
            int startOffset = 0;
            if (isShowLevel3) {
                AnimUtil.closeMenu(level3, startOffset);
                isShowLevel3 = false;
                startOffset += 200;
            }
            if (isShowLevel2) {
                AnimUtil.closeMenu(level2, startOffset);
                isShowLevel2 = false;
                startOffset += 200;
            }
            AnimUtil.closeMenu(level1, startOffset);
        } else {
            // 隐藏变成显示
            AnimUtil.showMenu(level1, 0);
            AnimUtil.showMenu(level2, 200);
            isShowLevel2 = true;
            AnimUtil.showMenu(level3, 400);
            isShowLevel3 = true;

        }
        isShowMenu = !isShowMenu;
    }

}
