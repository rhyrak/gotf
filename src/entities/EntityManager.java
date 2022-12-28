package entities;

import main.Game;
import states.Playing;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager {

    private ArrayList<Entity> entities;
    private Playing state;
    private Player player;
    private int camOffsetX, camOffsetY;

    public EntityManager(Playing playing, Player player) {
        this.state = playing;
        //TODO: Check savedata to find monster count
        //if (playing.getSaveData())
        this.player = player;
        initEntities();
    }

    private void initEntities() {
        entities = new ArrayList<>(20);
        entities.add(new RedNinja(this));
        entities.add(new Item(this,Item.ItemType.LIFEPOT, 500, 500));
        entities.add(new Item(this,Item.ItemType.MEDIPACK, 500, 800));
    }

    public void update() {
        camOffsetX = Game.gameWidth / 2 - player.getHitbox().x - player.getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - player.getHitbox().y - player.getHitbox().height / 2;
        for(Entity e: entities)
            e.update();
    }
    
    public Player getPlayer() {
    	return player;
    }
    
    public void draw(Graphics g) {
        for (Entity e: entities)
            e.draw(g);
    }

    public int getCamOffsetX() {
        return camOffsetX;
    }

    public int getCamOffsetY() {
        return camOffsetY;
    }
}
