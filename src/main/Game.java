package main;

import states.*;
import states.Menu;
import util.SaveData;
import util.SoundManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

public class Game implements Runnable {

    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private State gameState;
    private Thread gameThread;

    public static final int TILE_SIZE = 64;
    public static int gameWidth;
    public static int gameHeight;
    public static boolean DEBUG_MODE = false;

    public Game() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        this.gameState = new Menu(this);
        startGame();
        startmusic();
        this.gamePanel.initListeners();
    }

    private void startGame() {
        this.gameThread = new Thread(this, "Game Thread");
        this.gameThread.start();
    }

    @Override
    public void run() {

        final int FPS = 144;
        final int UPS = 200;
        final long NANOSECS_IN_A_SEC = 1_000_000_000L;

        // the game will update itself 128 times per second (collision check, animations, player pos etc.)
        double updatePeriod = NANOSECS_IN_A_SEC * 1.0 / UPS;
        // the game will render 200 frames per second
        double renderPeriod = NANOSECS_IN_A_SEC * 1.0 / FPS;

        // for tracking fps/ups
        long fpsLastCheck = System.currentTimeMillis();
        int renderCount = 0, updateCount = 0;

        // for the actual game loop
        long lastUpdateTime = System.nanoTime();
        double timeSinceLastUpdate = 0;
        double timeSinceLastRender = 0;

        //noinspection InfiniteLoopStatement
        while (true) {

            long fpsNow = System.currentTimeMillis();

            long now = System.nanoTime();

            // detect how many nanoseconds have passed since the last run of loop
            timeSinceLastUpdate += now - lastUpdateTime;
            timeSinceLastRender += now - lastUpdateTime;

            // if at least "updatePeriod" amount of time have passed, update the game
            if (timeSinceLastUpdate >= updatePeriod) {
                timeSinceLastUpdate -= updatePeriod;
                updateCount++;
                update();
            }

            // if at least "renderPeriod" amount of time have passed, render the next frame
            if (timeSinceLastRender >= renderPeriod) {
                timeSinceLastRender -= renderPeriod;
                renderCount++;
                draw();
            }

            // for the next iteration of loop, "now" is "lastUpdateTime"
            lastUpdateTime = now;

            // calculate fps/ups
            if (DEBUG_MODE) {
                if (fpsNow - fpsLastCheck >= 1000) {
                    fpsLastCheck = fpsNow;
                    System.out.println("Update count: " + updateCount);
                    System.out.println("Generated frame count: " + renderCount);
                    updateCount = 0;
                    renderCount = 0;
                }
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

    public void changeGameState(GameState newState) {
        switch (newState) {
            case PLAYING -> {
                SoundManager.StartForest();
                this.gameState = new Playing(loadGame(1));
            }
            case MENU -> this.gameState = new Menu(this);
            case SETTINGS -> this.gameState = new Settings(this);
            case EXIT -> System.exit(0);
        }
    }

    private SaveData loadGame(int id) {
        SaveData saveData;
        try {
            FileInputStream fis;
            if (id == 1)
                fis = new FileInputStream("save1.dat");
            else if (id == 2)
                fis = new FileInputStream("save2.dat");
            else if (id == 3)
                fis = new FileInputStream("save3.dat");
            else
                fis = new FileInputStream("nope");
            ObjectInputStream ois = new ObjectInputStream(fis);
            saveData = (SaveData) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            saveData = new SaveData();
        }
        saveData.saveID = id;
        return saveData;
    }
    private void startmusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        SoundManager sound = new SoundManager();
        SoundManager.Menu();
    }

    public static void toggleDebug() {
        DEBUG_MODE = !DEBUG_MODE;
    }
}
