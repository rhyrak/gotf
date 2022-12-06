package ui;

import main.Game;

import java.awt.*;

public class MenuButton {

    private Rectangle hitbox;
    private boolean mouseOver;

    public MenuButton(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public void update() {

    }

    public void draw(Graphics g) {
        if (Game.DEBUG_MODE) {
            if (mouseOver)
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.BLUE);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
}
