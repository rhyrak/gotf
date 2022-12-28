package world;

import entities.Player;
import main.Game;
import util.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILE_SIZE;

public class Overworld extends Level {


    private Image[] floor1;
    private Rectangle demoTile;
    private int camOffsetX = 0, camOffsetY = 0;
    private Player player;

    public Overworld(Player player) {
        this.player = player;
        demoTile = new Rectangle(0, 0, 64, 64);
        initTileSet();
    }

    private void initTileSet() {
        BufferedImage temp = AssetManager.getSprite(AssetManager.FLOOR1_TS);
        floor1 = new Image[573];
        int iter = 1;
        for (int j = 0; j < 26; j++) {
            for (int i = 0; i < 22; i++) {
                floor1[iter++] = temp.getSubimage(i * 16, j * 16, 16, 16)
                        .getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int layer = 0; layer < OverworldData.arr.length; layer++) {
            for (int y = 0; y < OverworldData.arr[layer].length; y++) {
                for (int x = 0; x < OverworldData.arr[layer][y].length; x++) {
                    switch (layer) {
                        case 0 -> g.drawImage(floor1[OverworldData.arr[layer][y][x]], x * TILE_SIZE + camOffsetX, y * TILE_SIZE + camOffsetY, TILE_SIZE, TILE_SIZE, null);
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
}

