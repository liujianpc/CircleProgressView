package com.example.jingle.circleprogressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liujian on 2017/10/15.
 */

public class CircleProgressView extends View {
    private Paint circlePaint, prePaint;
    private RectF oval;
    private int startAngle = 0;
    private int endAngle = 360;
    private int trueAngle = 0;


    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.GRAY);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(20);

        prePaint = new Paint();
        prePaint.setStrokeWidth(20);
        prePaint.setAntiAlias(true);
        prePaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int len = Math.min(width, height);
        oval = new RectF(0 + 20, 0 + 20, len - 20, len - 20);
        setMeasuredDimension(len, len);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(oval, -90, 360, false, circlePaint);
        drawPreView(canvas);
    }

    private void drawPreView(Canvas canvas) {
        float percent = trueAngle * 1f / endAngle;
        int red = 255 - (int) (percent * 255);
        int green = (int) (percent * 255);
        prePaint.setARGB(255, red, green, 0);
        canvas.drawArc(oval, -90, trueAngle, false, prePaint);
    }

    public void changeAngle() {
        trueAngle = 0;
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                trueAngle += 5;
                if (trueAngle >= endAngle) {
                    trueAngle = endAngle;
                    timer.cancel();
                }
                postInvalidate();
            }
        }, 500, 30);
    }

}
