package world;

import entities.Player;
import main.Game;
import util.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILE_SIZE;

public class Overworld extends Level {

    private Image[] tileSet;
    private Rectangle demoTile;
    private int camOffsetX = 0, camOffsetY = 0;
    private Player player;

    public Overworld(Player player) {
        this.player = player;
        demoTile = new Rectangle(0, 0, 64, 64);
        initTileSet();
    }

    private void initTileSet() {
        BufferedImage temp = AssetManager.getSprite(AssetManager.FLOOR_TS);
        tileSet = new Image[573];
        int iter = 1;
        for (int j = 0; j < 26; j++) {
            for (int i = 0; i < 22; i++) {
                tileSet[iter++] = temp.getSubimage(i * 16, j * 16, 16, 16)
                        .getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < Game.gameHeight / TILE_SIZE + 2; j++) {
            for (int i = 0; i < Game.gameWidth / TILE_SIZE + 2; i++) {
                g.drawImage(tileSet[269], i * TILE_SIZE + camOffsetX, j * TILE_SIZE + camOffsetY, TILE_SIZE, TILE_SIZE, null);
            }
        }
    }

    @Override
    public void update() {
        camOffsetX = Game.gameWidth / 2 - player.getHitbox().x - player.getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - player.getHitbox().y - player.getHitbox().height / 2;
    }
}
