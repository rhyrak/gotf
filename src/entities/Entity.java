package entities;

import java.awt.*;

public class Entity {
    protected Rectangle hitbox;

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
