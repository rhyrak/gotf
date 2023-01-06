package world;

import java.awt.*;

/**
 * Abstract base class for Levels
 *
 * @author Selcuk Gencay
 */
public abstract class Level {

    /**
     * Draws the level
     *
     * @param g graphics context for drawing
     */
    public abstract void draw(Graphics g);

    /**
     * Updates the level
     */
    public abstract void update();

    /**
     * Takes the coordinate of the point where the player wants to move
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return true if player can move to given position
     */
    public abstract boolean canMove(int x, int y);

    /**
     * Enables level-player interaction<p>
     * When pressed 'E' during playing state, this method is called
     */
    public abstract void playerInteract();
}
