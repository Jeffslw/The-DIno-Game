package com.example.eecs4443project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class CactusEnemy extends Enemy {
    private int posX;
    private int width;
    private int height;
    private Bitmap image;
    private MainCharacter mainCharacter;

    private Rect rectBound;

    public static final int Y_LAND = 730;

    public CactusEnemy(MainCharacter mainCharacter, int posX, int width, int height, Bitmap image) {
        super(image, posX, Y_LAND - image.getHeight());
        this.mainCharacter = mainCharacter;
        this.posX = posX;
        this.width = width;
        this.height = height;
        this.image = image;
        this.rectBound = new Rect();
    }

    @Override
    public void update() {
        // Calculate the new x-position based on the current speed and the elapsed time since the last update
        int newX = (int) (posX - mainCharacter.getSpeedX());

        // Update the enemy's x-position to the new position
        setPosX(newX);
    }

    private void setPosX(int newX) {
        this.posX = newX;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, posX, Y_LAND - image.getHeight(), null);
    }

    @Override
    public Rect getBounds() {
        rectBound.left = posX + (image.getWidth() - width) / 2;
        rectBound.top = Y_LAND - image.getHeight() + (image.getHeight() - height) / 2;
        rectBound.right = posX + (image.getWidth() - width) / 2 + width;
        rectBound.bottom = Y_LAND - image.getHeight() + (image.getHeight() - height) / 2 + height;
        return rectBound;
    }

    @Override
    public boolean isOutOfScreen(int screenWidth) {
        if (posX + image.getWidth() < 0) {
            return true;
        }
        return false;
    }
}