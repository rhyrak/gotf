package entities;

import main.Game;
import util.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {

    private BufferedImage img;
    private EntityManager manager;
    private ItemType type;
    private boolean pickedUp = false;

    public enum ItemType {
        LIFEPOT, MEDIPACK, DEF_SCROLL
    }

    public Item(EntityManager entityManager, ItemType type, int x, int y) {
        loadImg(type);
        this.manager = entityManager;
        this.type = type;
        this.hitbox = new Rectangle(x, y, 32, 32);
    }

    private void loadImg(ItemType type) {
        switch (type) {
            case LIFEPOT -> img = AssetManager.getSprite(AssetManager.LIFE_POT);
            case MEDIPACK -> img = AssetManager.getSprite(AssetManager.MEDIPACK);
            case DEF_SCROLL -> img = AssetManager.getSprite(AssetManager.DEF_SCROLL);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (pickedUp)
            return;
        g.drawImage(img, hitbox.x + manager.getCamOffsetX(), hitbox.y + manager.getCamOffsetY(), 32, 32, null);
        if (Game.DEBUG_MODE)
            g.drawRect(hitbox.x + manager.getCamOffsetX(), hitbox.y + manager.getCamOffsetY(), 32,32);
    }

    @Override
    public void update() {
        if (pickedUp)
            return;
        if (this.hitbox.intersects(manager.getPlayer().getHitbox())) {
            boolean added = manager.getPlayer().addItem(type);
            if (added) {
                pickedUp = true;
            }
        }
    }
}
