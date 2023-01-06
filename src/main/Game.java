package main;

import states.*;
import util.SaveData;
import util.SoundManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Game is a class that implement Runnable, which inits the game and starts game loop in a new thread by passing itself
 *
 * @author Selcuk Gencay
 */
public class Game implements Runnable {

    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private State gameState;
    private Thread gameThread;

    /** Size of each tile */
    public static final int TILE_SIZE = 64;
    /** Screen width */
    public static int gameWidth;
    /** Screen height */
    public static int gameHeight;
    /** Debug flag */
    public static boolean DEBUG_MODE = false;

    /**
     * Inits necessary classes and the game thread. Then starts the game thread which runs the game loop.
     */
    public Game() {
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

    /**
     * @return current game state
     */
    public State getGameState() {
        return gameState;
    }

    /**
     * Takes a GameState enum, changes the state accordingly
     *
     * @param newState new game state
     * @see GameState
     */
    public void changeGameState(GameState newState) {
        switch (newState) {
            case PLAYING -> {
                SoundManager.StartForest();
                this.gameState = new Playing(loadGame(1), this);
            }
            case MENU -> this.gameState = new Menu(this);
            case SETTINGS -> this.gameState = new Settings(this);
            case EXIT -> System.exit(0);
            case GAMEOVER -> this.gameState = new GameOver();
        }
    }

    /**
     * @param id saved game's id
     * @return SaveData if id mathces, if not null
     */
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
    private void startmusic() {
        try {
            // necessary for loading sounds
            SoundManager sound = new SoundManager();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        SoundManager.Menu();
    }

    /**
     * turn debug on/off
     */
    public static void toggleDebug() {
        DEBUG_MODE = !DEBUG_MODE;
    }
}
