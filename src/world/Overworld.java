package world;

import entities.Player;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Overworld extends Level {

    private BufferedImage tileSet;
    private Rectangle demoTile;
    private int camOffsetX = 0, camOffsetY = 0;
    private Player player;

    public Overworld(Player player) {
        this.player = player;
        demoTile = new Rectangle(0, 0, 64, 64);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(demoTile.x + camOffsetX, demoTile.y + camOffsetY, demoTile.width, demoTile.height);
    }

    @Override
    public void update() {
        camOffsetX = Game.gameWidth / 2 - player.getHitbox().x - player.getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - player.getHitbox().y - player.getHitbox().height / 2;
    }
}
