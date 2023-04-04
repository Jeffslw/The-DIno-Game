package com.example.eecs4443project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.zip.Adler32;

public class GameView extends View {
    private MainCharacter mainCharacter;
    private Obstacles obstacles;
    private Paint paint;
    private GameLogic gameLogic;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        mainCharacter = new MainCharacter(context);
        obstacles = new Obstacles(mainCharacter, context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mainCharacter.draw(canvas);
        if (gameLogic != null) {
            gameLogic.update();
        }
        for (Enemy enemy : obstacles.getEnemies()) {
            enemy.draw(canvas);
        }
    }

    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public void setObstacles(Obstacles obstacles) {
        this.obstacles = obstacles;
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    public Obstacles getObstacles() {
        return obstacles;
    }
}
