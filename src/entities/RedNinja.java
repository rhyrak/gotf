package entities;

import main.Game;
import util.AssetManager;
import util.Directions;

import java.awt.*;
import java.awt.image.BufferedImage;

import static util.Directions.LEFT;

public class RedNinja extends Entity{

    private BufferedImage[][] sprite;
    private boolean moveUp, moveDown, moveRight, moveLeft;
    private Rectangle attackHitbox;
    private boolean attacking;
    private Player player;
    private int animIndex;
    private Directions direction;
    private int camOffsetX, camOffsetY;

    public RedNinja(Player player) {
        this.player = player;
        this.hitbox = new Rectangle(0,0,64,64);
        this.direction = LEFT;
        loadSprite();
    }

    private void loadSprite() {
        BufferedImage temp = AssetManager.getSprite(AssetManager.RED_NINJA);
        sprite = new BufferedImage[9][4];
        for (int i = 0; i < 4; i++) {
            sprite[0][i] = temp.getSubimage(0, i * 16, 16, 16); // down
            sprite[1][i] = temp.getSubimage(16, i * 16, 16, 16); // up
            sprite[2][i] = temp.getSubimage(32, i * 16, 16, 16); // left
            sprite[3][i] = temp.getSubimage(48, i * 16, 16, 16); // right
        }
        sprite[4][0] = temp.getSubimage(0, 64, 16, 16); // attack down
        sprite[4][1] = temp.getSubimage(16, 64, 16, 16); // attack up
        sprite[4][2] = temp.getSubimage(32, 64, 16, 16); // attack left
        sprite[4][3] = temp.getSubimage(48, 64, 16, 16); // attack right
    }

    @Override
    public void draw(Graphics g) {
        switch (direction) {
            case DOWN -> {
                if (attacking) {
                    g.drawImage(sprite[4][0], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][0], hitbox.x + camOffsetX - hitbox.x + attackHitbox.x + attackHitbox.width / 4, Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + attackHitbox.y, attackHitbox.width / 2, attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[0][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
            }
            case UP -> {
                if (attacking) {
                    g.drawImage(sprite[4][1], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][0], hitbox.x + camOffsetX - hitbox.x + attackHitbox.x + (int) (attackHitbox.width / 1.75), hitbox.y + camOffsetY - hitbox.y + attackHitbox.y + attackHitbox.height, -attackHitbox.width / 2, -attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[1][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
            }
            case LEFT -> {
                if (attacking) {
                    g.drawImage(sprite[4][2], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][1], hitbox.x + camOffsetX - hitbox.x + attackHitbox.x + attackHitbox.width, hitbox.y + camOffsetY - hitbox.y + attackHitbox.y + attackHitbox.height / 2, -attackHitbox.width / 2, attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[2][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
            }
            case RIGHT -> {
                if (attacking) {
                    g.drawImage(sprite[4][3], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][1], hitbox.x + camOffsetX - hitbox.x + attackHitbox.x, hitbox.y + camOffsetY - hitbox.y + attackHitbox.y + attackHitbox.height / 2, attackHitbox.width / 2, attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[3][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
            }
        }
    }

    @Override
    public void update() {
        camOffsetX = Game.gameWidth / 2 - player.getHitbox().x - player.getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - player.getHitbox().y - player.getHitbox().height / 2;
    }
}
