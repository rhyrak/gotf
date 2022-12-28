package entities;

import states.Playing;

import java.awt.*;

public class EntityManager {
    private Entity[] entities;
    private Playing state;
    private Player player;
    public EntityManager(Playing playing, Player player) {
        this.state = playing;
        //TODO: Check savedata to find monster count
        //if (playing.getSaveData())
        this.player = player;
        entities = new Entity[1];
        entities[0] = new RedNinja(this);
    }

    public void update() {
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
}
