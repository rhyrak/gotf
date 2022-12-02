package main;

import states.Playing;
import states.State;

public class Game implements Runnable {

    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private State gameState;
    private Thread gameThread;

    public Game() {
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        this.gameState = new Playing();
        startGame();
    }

    private void startGame() {
        this.gameThread = new Thread(this, "Game Thread");
    }

    @Override
    public void run() {
        while (true) {

            update();
            draw();

            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void update() {

    }

    private void draw() {
        gamePanel.repaint();
    }

    public State getGameState() {
        return gameState;
    }
}
