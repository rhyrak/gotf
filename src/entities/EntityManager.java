package entities;

import main.Game;
import states.Playing;
import util.SoundManager;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import static main.Game.TILE_SIZE;

/**
 * The controller class for entities in the game
 *
 * @author Selcuk Gencay
 */
public class EntityManager implements Serializable {

    private ArrayList<ArrayList<Entity>> entities;
    private Playing playing;
    private Player player;
    private Dog dog;
    private int camOffsetX, camOffsetY;

    /**
     * Generates the entities
     *
     * @param playing for accessing save data
     * @param player for accessing its position and hitpoints
     */
    public EntityManager(Playing playing, Player player) {
        this.playing = playing;
        this.player = player;
        this.dog = new Dog(this, new Rectangle(player.getHitbox().x - TILE_SIZE,player.getHitbox().y - TILE_SIZE,48,42));
        initEntities();
    }

    private void initEntities() {
        entities = new ArrayList<>(5);
        for (int i = 0; i < 5; i++)
            entities.add(new ArrayList<>(10));
        // Items
        entities.get(1).add(new Item(this, Item.ItemType.DEF_SCROLL, 1000, 2300));
        entities.get(2).add(new Item(this,Item.ItemType.LIFEPOT, 1000, 1000));
        entities.get(3).add(new Item(this,Item.ItemType.MEDIPACK, 1000, 1500));
        entities.get(4).add(new Item(this,Item.ItemType.MEDIPACK, 1000, 2300));
        entities.get(4).add(new Item(this,Item.ItemType.LIFEPOT, 1050, 2300));
        // enemies
        entities.get(1).add(new RedNinja(this, new Rectangle(16*64,20*64,64,64)));
        entities.get(1).add(new RedNinja(this, new Rectangle(22*64,20*64,64,64)));
        entities.get(1).add(new RedNinja(this, new Rectangle(19*64,30*64,64,64)));
        entities.get(3).add(new Cavegirl(this, new Rectangle(16*64,20*64,64,64)));
        entities.get(3).add(new Cavegirl(this, new Rectangle(22*64,20*64,64,64)));
        entities.get(3).add(new Caveman(this, new Rectangle(19*64,30*64,64,64)));
        entities.get(2).add(new Skeleton(this, new Rectangle(16*64,20*64,64,64)));
        entities.get(2).add(new Skeleton(this, new Rectangle(16*64,30*64,64,64)));
        entities.get(2).add(new Skeleton(this, new Rectangle(22*64,20*64,64,64)));
        entities.get(2).add(new Skeleton(this, new Rectangle(22*64,30*64,64,64)));
        entities.get(4).add(new Boss(this, new Rectangle(16*64,16*64,64*8,64*8)));
    }

    /** Updates camera offsets then the entities */
    public void update() {
        camOffsetX = Game.gameWidth / 2 - player.getHitbox().x - player.getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - player.getHitbox().y - player.getHitbox().height / 2;
        for(Entity e: entities.get(playing.getSaveData().floor))
            e.update();
        dog.update();
        if (player.hitpoints <= 0) {
            SoundManager.PlayerDead();
            playing.gameOver();
        }
    }

    /** @return the player instance */
    public Player getPlayer() {
        return player;
    }

    /** @return Player's companion */
    public Dog getDog() {
        return dog;
    }

    /**
     * Draws entities that are on the same floor as the player
     *
     * @param g graphics context for drawing
     */
    public void draw(Graphics g) {
        for(Entity e: entities.get(playing.getSaveData().floor))
            e.draw(g);
        dog.draw(g);
    }

    /**
     * @return the offset in the x-axis of the camera
     */
    public int getCamOffsetX() {
        return camOffsetX;
    }

    /**
     * @return the offset in the y-axis of the camera
     */
    public int getCamOffsetY() {
        return camOffsetY;
    }

}