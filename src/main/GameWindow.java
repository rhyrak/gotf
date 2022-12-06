package main;

import javax.swing.*;

public class GameWindow extends JFrame {

    private GamePanel gamePanel;

    public GameWindow(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setTitle("Guardian of the Forest");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        add(gamePanel);
        setVisible(true);
        Game.updateWidthHeight(getWidth(), getHeight());
    }

}
