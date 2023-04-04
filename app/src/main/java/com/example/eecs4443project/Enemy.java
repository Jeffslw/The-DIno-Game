package com.example.eecs4443project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class Enemy {
    private Bitmap image;
    private int x, y;

    public Enemy(Bitmap image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void update() {
        // Implement update logic here
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public Rect getBounds() {
        return new Rect(x, y, x + image.getWidth(), y + image.getHeight());
    }

    public boolean isOutOfScreen(int screenWidth) {
        return x + image.getWidth() < 0;
    }


    public float getX() {
        return x;
    }
}