package entities;

import java.awt.*;

public class Player extends Entity {

    public Player() {
        this.hitbox = new Rectangle(100,100,64,64);
    }

    public void update() {

    }

    public void draw(Graphics g) {
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
}
