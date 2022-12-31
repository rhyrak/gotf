package entities;
import main.Game;
import states.Playing;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class EntityManager implements Serializable {

    private ArrayList<ArrayList<Entity>> entities;
    private Playing playing;
    private Player player;
    private int camOffsetX, camOffsetY;

    public EntityManager(Playing playing, Player player) {
        this.playing = playing;
        this.player = player;
        initEntities();
    }

    private void initEntities() {
        //TODO: Check savedata to find monster count
        entities = new ArrayList<>(5);
        for (int i = 0; i < 5; i++)
            entities.add(new ArrayList<>(10));
        entities.get(1).add(new RedNinja(this, new Rectangle(16*64,16*64,64,64)));
        entities.get(2).add(new Item(this,Item.ItemType.LIFEPOT, 1000, 1000));
        entities.get(3).add(new Item(this,Item.ItemType.MEDIPACK, 1000, 1500));
    }

    public void update() {
        camOffsetX = Game.gameWidth / 2 - player.getHitbox().x - player.getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - player.getHitbox().y - player.getHitbox().height / 2;
        for(Entity e: entities.get(playing.getSaveData().floor))
            e.update();
    }

    public Player getPlayer() {
        return player;
    }

    public void draw(Graphics g) {
        for(Entity e: entities.get(playing.getSaveData().floor))
            e.draw(g);
    }

    public int getCamOffsetX() {
        return camOffsetX;
    }
    public int getCamOffsetY() {
        return camOffsetY;
    }

}