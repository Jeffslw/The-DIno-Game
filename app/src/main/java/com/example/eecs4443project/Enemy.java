package com.example.eecs4443project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class Enemy {
    private static final int ENEMY_SPEED = 5;
    private Bitmap image;
    private int x, y;

    public Enemy(Bitmap image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void update(float mainCharacterX) {
      float distance = mainCharacterX - x;
        if (distance > 0) {
         // move the enemy towards the main character
           this.moveLeft();
        }
    }

    public abstract void update();

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

    public void moveLeft() {
        System.out.println("Before: x = " + x);
        x -= ENEMY_SPEED;
        System.out.println("After: x = " + x);
    }
}