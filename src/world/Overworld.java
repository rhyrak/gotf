package world;

import entities.Player;
import main.Game;
import states.Playing;
import util.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILE_SIZE;

/**
 * Forest level where the dungeon entrance is located
 *
 * @author Selcuk Gencay
 */
public class Overworld extends Level {

    private Image[] floor1;
    private Image[] floor2;
    private Image[] floor3;
    private Image[] floor4;
    private int camOffsetX = 0, camOffsetY = 0;
    private final Player player;
    private final Rectangle dungeonEntrance;
    private BufferedImage map;

    /**
     * inits the tile set and dungeon entrance
     *
     * @param player  for accessing player's position
     */
    public Overworld(Player player) {
        this.player = player;
        this.dungeonEntrance = new Rectangle(1792, 2240, 192, 128);
        initTileSet();
    }

    private void initTileSet() {
        //This part,using the dimensions of the images, each tile is separated as a single piece with two for loops.
        BufferedImage temp = AssetManager.getSprite(AssetManager.FLOOR1_TS);
        floor1 = new Image[573];
        int iter = 1;
        for (int j = 0; j < 26; j++) {
            for (int i = 0; i < 22; i++) {
                floor1[iter++] = temp.getSubimage(i * 16, j * 16, 16, 16)
                        .getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
            }
        }

        temp = AssetManager.getSprite(AssetManager.FLOOR2_TS);
        floor2 = new Image[337];
        iter = 1;
        for (int j = 0; j < 21; j++) {
            for (int i = 0; i < 16; i++) {
                floor2[iter++] = temp.getSubimage(i * 16, j * 16, 16, 16)
                        .getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
            }
        }

        temp = AssetManager.getSprite(AssetManager.FLOOR3_TS);
        floor3 = new Image[760];
        iter = 1;
        for (int j = 0; j < 23; j++) {
            for (int i = 0; i < 33; i++) {
                floor3[iter++] = temp.getSubimage(i * 16, j * 16, 16, 16)
                        .getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
            }
        }

        temp = AssetManager.getSprite(AssetManager.FLOOR4_TS);
        floor4 = new Image[145];
        iter = 1;
        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 12; i++) {
                floor4[iter++] = temp.getSubimage(i * 16, j * 16, 16, 16)
                        .getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        //this part set up for loops that take the layer, x, and y values to print the single tile we get
        //the layers were taken separately and the elements such as trees and stones
        // that the character should not collide with were counted in different layers with the switch case, so no collision occurred.
        if (map == null) {
            map = new BufferedImage(OverworldData.arr[0][0].length*TILE_SIZE,OverworldData.arr[0].length*TILE_SIZE,BufferedImage.TYPE_INT_RGB);
            Graphics mapG = map.getGraphics();
            for (int layer = 0; layer < OverworldData.arr.length; layer++) {
                for (int y = 0; y < OverworldData.arr[layer].length; y++) {
                    for (int x = 0; x < OverworldData.arr[layer][y].length; x++) {
                        if (OverworldData.arr[layer][y][x] > 0)
                            switch (layer) {
                                case 0 ->
                                        mapG.drawImage(floor1[OverworldData.arr[layer][y][x]], x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                                case 1 ->
                                        mapG.drawImage(floor2[OverworldData.arr[layer][y][x] - 572], x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                                case 2 ->
                                        mapG.drawImage(floor3[OverworldData.arr[layer][y][x] - 1052], x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                                case 3 ->
                                        mapG.drawImage(floor4[OverworldData.arr[layer][y][x] - 908], x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                            }
                    }
                }
            }
        }
        g.drawImage(map,camOffsetX,camOffsetY,map.getWidth(), map.getHeight(),null);
        if (Game.DEBUG_MODE) {
            g.drawRect(dungeonEntrance.x + camOffsetX, dungeonEntrance.y + camOffsetY, dungeonEntrance.width, dungeonEntrance.height);
        }
    }

    @Override
    public void update() {
        //Position is set to the middle, taking half the x and y lengths so that the character stays in the middle of the main screen
        camOffsetX = Game.gameWidth / 2 - player.getHitbox().x - player.getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - player.getHitbox().y - player.getHitbox().height / 2;
    }

    @Override
    public boolean canMove(int x, int y) {
        //The area that the character wants to go is determined and prevented from leaving the area.
        int xIndex = x / 64, yIndex = y / 64;
        boolean canMove = true;

        for (int layer = 1; layer < OverworldData.arr.length; layer++)
            try {
                if (OverworldData.arr[layer][yIndex][xIndex] > 0) {
                    canMove = false;
                    break;
                }
            } catch (Exception e) {
                canMove = false;
            }

        return canMove;
    }

    @Override
    public void playerInteract() {
        if (player.getHitbox().intersects(dungeonEntrance))
            Playing.getSaveData().floor = 1;
    }
}

