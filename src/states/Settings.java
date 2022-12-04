package states;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Settings extends State {
    @Override
    public void draw(Graphics g) {
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
}
