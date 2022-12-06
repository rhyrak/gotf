package states;

import entities.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State {

    private Player player;

    public Playing() {
        this.player = new Player(new Rectangle(150,150,64,64));
    }

    @Override
    public void draw(Graphics g) {
        g.drawString("PLAYING",100,100);
        player.draw(g);
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.setMoveUp(true);
            case KeyEvent.VK_S -> player.setMoveDown(true);
            case KeyEvent.VK_D -> player.setMoveRight(true);
            case KeyEvent.VK_A -> player.setMoveLeft(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.setMoveUp(false);
            case KeyEvent.VK_S -> player.setMoveDown(false);
            case KeyEvent.VK_D -> player.setMoveRight(false);
            case KeyEvent.VK_A -> player.setMoveLeft(false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
