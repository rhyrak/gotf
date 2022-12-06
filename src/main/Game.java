package main;

import states.Menu;
import states.Playing;
import states.State;

public class Game implements Runnable {

    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private State gameState;
    private Thread gameThread;

    public static int gameWidth;
    public static int gameHeight;
    public static final boolean DEBUG_MODE = true;

    public Game() {
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        this.gameState = new Menu();
        startGame();
    }

    private void startGame() {
        this.gameThread = new Thread(this, "Game Thread");
        this.gameThread.start();
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
        gameState.update();
    }

    private void draw() {
        gamePanel.repaint();
    }

    public State getGameState() {
        return gameState;
    }

    public static void updateWidthHeight(int gameWidth, int gameHeight) {
        Game.gameWidth = gameWidth;
        Game.gameHeight = gameHeight;
        System.out.println("Width: " + Game.gameWidth + ", Height: " + Game.gameHeight);
    }
}
