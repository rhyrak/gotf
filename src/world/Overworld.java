package world;

import entities.Player;
import main.Game;
import util.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Overworld extends Level {

    private BufferedImage[] tileSet;
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
        tileSet = new BufferedImage[573];
        int iter = 1;
        for (int j = 0; j < 26; j++) {
            for (int i = 0; i < 22; i++) {
                tileSet[iter++] = temp.getSubimage(i * 16, j * 16, 16, 16);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < Game.gameHeight / Game.TILE_SIZE; j++) {
            for (int i = 0; i < Game.gameWidth / Game.TILE_SIZE; i++) {
                g.drawImage(tileSet[269], i*64 + camOffsetX,j*64 + camOffsetY,64,64,null);
            }
        }
    }

    @Override
    public void update() {
        camOffsetX = Game.gameWidth / 2 - player.getHitbox().x - player.getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - player.getHitbox().y - player.getHitbox().height / 2;
    }
}
