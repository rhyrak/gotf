package main;

import javax.swing.*;

/**
 * The container for GamePanel
 *
 * @author Selcuk Gencay
 */
public class GameWindow extends JFrame {

    private GamePanel gamePanel;

    /**
     * Generates a Fullscreen, undecorated window which has the GamePanel as its content
     *
     * @param gamePanel game
     */
    public GameWindow(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setTitle("Guardian of the Forest");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        add(gamePanel);
        setVisible(true);
        Game.gameWidth = getWidth();
        Game.gameHeight = getHeight();
    }
}
