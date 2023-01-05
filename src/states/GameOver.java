package states;

import main.Game;
import util.AssetManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameOver extends State{

    private BufferedImage death;
    private boolean drawn = false;

    public GameOver() {
        death = AssetManager.getSprite("/assets/death.png");
    }

    @Override
    public void draw(Graphics g) {
        if (!drawn) {
            g.drawImage(death,0, Game.gameHeight/4, Game.gameWidth, Game.gameHeight/8, null);
            drawn = true;
        }
        /*
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.gameWidth, Game.gameHeight);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SERIF, Font.BOLD, 32));
        g.drawString("YOU DIED", Game.gameWidth/2, Game.gameHeight/2);

         */
    }

    @Override
    public void update() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ENTER -> System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
