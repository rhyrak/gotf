package world;

import entities.Player;
import main.Game;
import util.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILE_SIZE;

public class Dungeon extends Level {

    private Image wall;
    private Image[] floor;
    private Image[] doorLadder;
    private int camOffsetX, camOffsetY;
    private Player player;

    public Dungeon(Player player) {
        this.player = player;
        this.player.getHitbox().x = 900;
        this.player.getHitbox().y = 2550;
        this.player.getMoveHitbox().x = 902;
        this.player.getMoveHitbox().y = 2582;
        initTileSet();
    }

    private void initTileSet() {
        BufferedImage temp = AssetManager.getSprite(AssetManager.FLOOR5_TS);

        wall = temp.getSubimage(12 * 16, 13 * 16, 16, 16)
                .getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);

        temp = AssetManager.getSprite(AssetManager.FLOOR6_TS);
        floor = new Image[573];
        int iter = 1;
        for (int j = 0; j < 26; j++) {
            for (int i = 0; i < 22; i++) {
                floor[iter++] = temp.getSubimage(i * 16, j * 16, 16, 16)
                        .getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
            }
        }

        temp = AssetManager.getSprite(AssetManager.FLOOR7_TS);
        doorLadder = new Image[145];
        iter = 1;
        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 12; i++) {
                doorLadder[iter++] = temp.getSubimage(i * 16, j * 16, 16, 16)
                        .getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int layer = 0; layer < DungeonData.arr.length; layer++) {
            for (int y = 0; y < DungeonData.arr[layer].length; y++) {
                for (int x = 0; x < DungeonData.arr[layer][y].length; x++) {
                    if (DungeonData.arr[layer][y][x] > 0)
                        switch (layer) {
                            case 0 ->
                                    g.drawImage(wall, x * TILE_SIZE + camOffsetX, y * TILE_SIZE + camOffsetY, TILE_SIZE, TILE_SIZE, null);
                            case 1 ->
                                    g.drawImage(floor[DungeonData.arr[layer][y][x] - 374], x * TILE_SIZE + camOffsetX, y * TILE_SIZE + camOffsetY, TILE_SIZE, TILE_SIZE, null);
                            case 2 ->
                                    g.drawImage(doorLadder[DungeonData.arr[layer][y][x] - 946], x * TILE_SIZE + camOffsetX, y * TILE_SIZE + camOffsetY, TILE_SIZE, TILE_SIZE, null);
                        }
                }
            }
        }
    }

    @Override
    public void update() {
        camOffsetX = Game.gameWidth / 2 - player.getHitbox().x - player.getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - player.getHitbox().y - player.getHitbox().height / 2;
    }

    @Override
    public boolean canMove(int x, int y) {
        if (x<768 || x>2496 || y>2816 || y<448)
            return false;
        return true;
    }
}
