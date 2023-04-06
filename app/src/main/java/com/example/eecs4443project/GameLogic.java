package com.example.eecs4443project;

import android.content.Intent;

public class GameLogic {

    protected final static int TOUCH_INPUT = 0;
    protected final static int VOICE_INPUT = 1;

    private Obstacles obstacles;
    private MainCharacter mainCharacter;
    private GameView gameView;
    private int gameType;

    public GameLogic(MainCharacter mainCharacter, Obstacles obstacles, GameView gameView, int gameType) {
        this.mainCharacter = mainCharacter;
        this.obstacles = obstacles;
        this.gameView = gameView;
        this.gameView.setMainCharacter(mainCharacter);
        this.gameView.setObstacles(obstacles);
        this.gameType = gameType; // -> either TOUCH_INPUT or VOICE_INPUT
    }
    public void update() {
        // Update the position of the main character
        mainCharacter.update();

        // Update the position of the enemies
        for (Enemy enemy : obstacles.getEnemies()) {
            enemy.update(mainCharacter.getX());
        }

        // Generate new enemies at random positions off-screen to the right
        float lastEnemyX = obstacles.getLastEnemyX();
        obstacles.generateNewEnemies(lastEnemyX);

        // Check for collisions with enemies
        for (Enemy enemy : obstacles.getEnemies()) {
            if (mainCharacter.collidesWith(enemy)) {
                endGame();
                return;
            }
        }
    }

    public void endGame() {
        // Display game over screen
        Intent intent = new Intent(gameView.getContext(), ResultsActivity.class);
        intent.putExtra("timeSurvived", Timer.getTimeSurvived());
        intent.putExtra("gameType", gameType);
        gameView.getContext().startActivity(intent);
    }

    public void moveRight() {
        mainCharacter.setSpeedX(5);
    }

    public void jump() {
        mainCharacter.jump();
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    public Obstacles getObstacles() {
        return obstacles;
    }

}

