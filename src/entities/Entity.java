package entities;

import java.awt.*;

public abstract class Entity {
    protected Rectangle hitbox;
    protected int hitpoints;
    protected EntityManager entityManager;
    
    public abstract void draw(Graphics g);

    public abstract void update();

    public enum state {
        IDLE, RETURN, CHASE
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
