package states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The base class for game states
 */
public abstract class State {

    /**
     * Draws the state
     *
     * @param g graphics context for drawing
     */
    abstract public void draw(Graphics g);

    /**
     * updates the state
     */
    abstract public void update();

    /**
     * Invoked by the KeyboardListener
     *
     * @param e the event
     */
    public abstract void keyPressed(KeyEvent e);

    /**
     * Invoked by the KeyboardListener
     *
     * @param e the event
     */
    public abstract void keyReleased(KeyEvent e);


    /**
     * Invoked by the MouseListener
     *
     * @param e the event
     */
    public abstract void mouseMoved(MouseEvent e);


    /**
     * Invoked by the MouseListener
     *
     * @param e the event
     */
    public abstract void mouseClicked(MouseEvent e);

    /**
     * Invoked by the MouseListener
     *
     * @param e the event
     */
    public abstract void mousePressed(MouseEvent e);

    /**
     * Invoked by the MouseListener
     *
     * @param e the event
     */
    public abstract void mouseReleased(MouseEvent e);
}
