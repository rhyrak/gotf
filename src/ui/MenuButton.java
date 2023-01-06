package ui;

import main.Game;
import util.AssetManager;
import util.SoundManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 *
 * @author Selcuk Gencay
 */
public class MenuButton {

    private Rectangle hitbox;
    private boolean mouseOver, clicked;
    private Action action;
    private BufferedImage[] images;
    private int timer = 0;
    private static final int NORMAL = 0, HOVER = 1, CLICKED = 2;

    /**
     * @param hitbox boundaries of the button
     * @param imagePath image of the button
     */
    public MenuButton(Rectangle hitbox, String imagePath) {
        this.hitbox = hitbox;
        loadImages(imagePath);
    }

    private void loadImages(String imagePath) {
        BufferedImage sprite = AssetManager.getSprite(imagePath);
        images = new BufferedImage[3];
        images[0] = sprite.getSubimage(350, 270, 348, 170);
        images[1] = sprite.getSubimage(350, 445, 348, 170);
        images[2] = sprite.getSubimage(350, 624, 348, 170);
    }

    /**
     * updates the button
     */
    public void update() {
        if (clicked)
            if (timer > 100)
                runAction();
            else
                timer++;
    }

    /**
     * Displays the button
     *
     * @param g graphic context for drawing
     */
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

    /**
     * @return button's hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * change mouseOver status
     *
     * @param mouseOver new status
     */
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * @param action defines what will happen after the button is clicked
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * @return mouseOver status
     */
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * called when the button is clicked
     */
    public void runAction() {
        action.action();
    }

    /**
     * change button's clicked status
     *
     * @param clicked new status
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
        if (clicked)
            SoundManager.MouseSelect();
    }
}
