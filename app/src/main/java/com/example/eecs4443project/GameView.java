package com.example.eecs4443project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.zip.Adler32;

public class GameView extends View {
    private MainCharacter mainCharacter;
    private Obstacles obstacles;
    private Paint paint;
    private GameLogic gameLogic;
    private Timer timer;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        mainCharacter = new MainCharacter(context);
        obstacles = new Obstacles(mainCharacter, context);
        timer = new Timer();
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
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
            enemy.moveLeft();
        }
        timer.update();
        timer.draw(canvas);
        invalidate(); //redraw the view
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
