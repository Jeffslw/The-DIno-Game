package com.example.eecs4443project;

public class GameLogic {
    private Obstacles obstacles;
    private MainCharacter mainCharacter;
    private GameView gameView;

    public GameLogic(MainCharacter mainCharacter, Obstacles obstacles, GameView gameView) {
        this.mainCharacter = mainCharacter;
        this.obstacles = obstacles;
        this.gameView = gameView;
        this.gameView.setMainCharacter(mainCharacter);
        this.gameView.setObstacles(obstacles);
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
        // Display game over screen or dialog
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

