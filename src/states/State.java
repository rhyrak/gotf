package states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {

    abstract public void draw(Graphics g);
    abstract public void update();

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);

    public abstract void mouseMoved(MouseEvent e);

    public abstract void mouseClicked(MouseEvent e);
}
