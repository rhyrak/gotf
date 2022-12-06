package ui;

import main.Game;
import util.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {

    private Rectangle hitbox;
    private boolean mouseOver, clicked;
    private Action action;
    private BufferedImage[] images;
    private static final int NORMAL = 0, HOVER = 1, CLICKED = 2;

    public MenuButton(Rectangle hitbox, String imagePath) {
        this.hitbox = hitbox;
        loadImages(imagePath);
    }

    private void loadImages(String imagePath) {
        BufferedImage sprite = AssetManager.getSprite(imagePath);
        images = new BufferedImage[3];
        images[0] = sprite.getSubimage(177,202,362,178);
        images[1] = sprite.getSubimage(0,0,100,80);
        images[2] = sprite.getSubimage(0,0,100,80);
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
        if (clicked)
            g.drawImage(images[CLICKED], hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
        else if (mouseOver)
            g.drawImage(images[HOVER], hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
        else
            g.drawImage(images[NORMAL], hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);

    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void runAction() {
        action.action();
    }
}
