package com.example.eecs4443project;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Timer {
    private Paint paint;
    private long startTime;
    private static long elapsedTime;

    public Timer() {
        paint = new Paint();
        paint.setTextSize(50);
        startTime = System.currentTimeMillis();
    }

    public void update() {
        elapsedTime = System.currentTimeMillis() - startTime;
    }

    public void draw(Canvas canvas) {
        canvas.drawText("Time: " + elapsedTime / 1000, canvas.getWidth() - 300, 100, paint);
    }

    public static long getTimeSurvived() {
        return elapsedTime / 1000;
    }
}
