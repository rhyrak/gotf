package entities;

import java.awt.*;

/**
 * The base class for entities
 */
public abstract class Entity {
    /** position and bounds of the entity */
    protected Rectangle hitbox;
    /** HP of the entity */
    protected int hitpoints;
    /** a reference of entity's manager */
    protected EntityManager entityManager;

    /**
     * draws the entity (and its hitbox if the game is in debug mode)
     *
     * @param g graphics context for drawing
     */
    public abstract void draw(Graphics g);

    /** updates the entity */
    public abstract void update();

    /** @return entity's hitbox */
    public Rectangle getHitbox() {
        return hitbox;
    }
}
