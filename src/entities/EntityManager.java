package entities;

import states.Playing;

import java.awt.*;

public class EntityManager {
    private Entity[] entities;
    private Playing state;

    public EntityManager(Playing playing, Player player) {
        this.state = playing;
        //TODO: Check savedata to find monster count
        //if (playing.getSaveData())
        entities = new Entity[1];
        entities[0] = new RedNinja(player);
    }

    public void update() {
        for(Entity e: entities)
            e.update();
    }

    public void draw(Graphics g) {
        for (Entity e: entities)
            e.draw(g);
    }
}
