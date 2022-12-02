package main;

import javax.swing.*;

public class GameWindow extends JFrame {

    GamePanel gamePanel;

    public GameWindow(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setTitle("Guardian of the Forest");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        add(gamePanel);
        setVisible(true);
    }

}
