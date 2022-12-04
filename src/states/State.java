package states;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class State {

    abstract public void draw(Graphics g);
    abstract public void update();

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);
}
