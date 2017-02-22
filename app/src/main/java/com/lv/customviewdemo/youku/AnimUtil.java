package com.lv.customviewdemo.youku;

import android.animation.ObjectAnimator;
import android.view.View;

public class AnimUtil {
    public static int animCount = 0;

    public static void closeMenu(View view, long startOffset) {
        startAnim(view,false,startOffset);
    }

    public static void showMenu(View view, long startOffset) {
        startAnim(view,true,startOffset);
    }

    private static void startAnim(View view, boolean show, long startOffset) {
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());
      /*  view.animate()
                .rotation(show?360:180)
                .setDuration(500)
                .setStartDelay(startOffset)
                .start();*/
        int base = show ? 1 : 0;
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ROTATION, base * 180, (base+1) * 180 )
                .setDuration(500);
        animator.setStartDelay(startOffset);
        animator.start();
    }

}
