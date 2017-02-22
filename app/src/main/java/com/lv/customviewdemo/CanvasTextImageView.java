package com.lv.customviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2016-12-28
 * Time: 16:14
 * Description:Canvas Picture
 */
public class CanvasTextImageView extends View {

    public CanvasTextImageView(Context context) {
        this(context, null);
    }

    public CanvasTextImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 3.在构造函数中初始化
        initPaint();
        recording();
    }

    // 创建Picture 需要关闭硬件加速
    private Picture mPicture = new Picture();

    //录制内容方法
    private void recording() {
        Canvas canvas = mPicture.beginRecording(500, 500);
        canvas.translate(250, 250);
        canvas.drawCircle(0, 0, 100, mPaint);
        mPicture.endRecording();
    }

    //创建一个画笔
    private Paint mPaint = new Paint();


    //初始化画笔

    private void initPaint() {
        mPaint.setColor(Color.BLUE); //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL); //设置画笔模式为填充
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = widthMode == MeasureSpec.EXACTLY ? widthSize : getPaddingLeft() + getPaddingRight();
        int height = heightMode == MeasureSpec.EXACTLY ? heightSize : getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPicture(mPicture);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        /*canvas.drawBitmap(bitmap, new Matrix(), mPaint);
        canvas.drawBitmap(bitmap, 200, 200, mPaint);*/

        // 指定图片绘制区域(左上角的四分之一)
        Rect src = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(0, 0, 100, 100);
        canvas.drawBitmap(bitmap, src, dst, null);
        // 参数分别为 (文本 基线x 基线y 画笔)
        mPaint.setTextSize(50);
        canvas.drawText("我是的的", 120, 100, mPaint);
        // 文本(要绘制的内容)
        String str = "ABCDEFG";
        // 参数分别为 (字符串 开始截取位置 结束截取位置 基线x 基线y 画笔)
        canvas.drawText(str,1,3,350,100,mPaint);
        // 字符数组(要绘制的内容)
        char[] chars = str.toCharArray();
        // 参数为 (字符数组 起始坐标 截取长度 基线x 基线y 画笔)
        canvas.drawText(chars,1,3,450,100,mPaint);



    }
}
