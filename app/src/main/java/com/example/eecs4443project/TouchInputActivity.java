package com.example.eecs4443project;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TouchInputActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView player, land;
    private float playerX, playerY;
    private float screenWidth, screenHeight;
    private float touchX, touchY;
    private GameLogic gameLogic;
    private GameView gameView;
    private MainCharacter mainCharacter;
    private Obstacles obstacles;
    private boolean isJumping = false;
    private boolean gameOver = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_input);

        gameView = findViewById(R.id.game_view);
        gameView.setOnTouchListener(this);

        mainCharacter = gameView.getMainCharacter();
        obstacles = gameView.getObstacles();
        gameLogic = new GameLogic(mainCharacter, obstacles, gameView);

        // Get the player ImageView and the screen dimensions
        player = (ImageView) findViewById(R.id.characterImage);
        land = (ImageView) findViewById(R.id.landImage);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        // Set touch listener on the player ImageView
        player.setOnTouchListener(this);

        // Set the floor image as the background of the floor ImageView
        land.setBackgroundResource(R.drawable.land);

        handler.post(gameLoopRunnable);
    }

    private Runnable gameLoopRunnable = new Runnable() {
        @Override
        public void run() {
            gameLogic.update();
            gameView.invalidate();
            handler.postDelayed(this, 20);
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && !isJumping) {
            isJumping = true;
            gameLogic.jump();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isJumping = false;
                }
            }, 1000);
        }
        return true;
    }

}
