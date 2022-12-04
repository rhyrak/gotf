package states;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu extends State {

    @Override
    public void draw(Graphics g) {
        g.drawString("MENU", 100, 100);
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
}
