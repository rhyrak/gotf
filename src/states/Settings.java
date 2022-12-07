package states;

import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Settings extends State {
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(0,0, Game.gameWidth, Game.gameHeight);
        g.drawString("Options",100,100);
    }

    @Override
    public void update() {

    }

    @Override
    public void keyPressed(KeyEvent e) {

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
}
