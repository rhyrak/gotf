package entities;

import main.Game;
import util.AssetManager;
import util.Directions;

import java.awt.*;
import java.awt.image.BufferedImage;

import static util.Directions.*;

public class Player extends Entity {

    private boolean moveUp, moveDown, moveRight, moveLeft;
    private Rectangle attackHitbox;
    private boolean attacking;
    private Directions direction;
    private BufferedImage[][] sprite;
    private BufferedImage[] hudHeart;
    private BufferedImage itemSlot;
    private BufferedImage lifePot;
    private int lifePotCount = 2;
    private BufferedImage medipack;
    private int medipackCount = 2;
    private Color cdColor = new Color(222, 222, 222, 200);
    private int animIndex, animTick;
    private int attackCoolDown;


    public Player(Rectangle hitbox) {
        this.hitbox = hitbox;
        this.attackHitbox = new Rectangle(hitbox.x + hitbox.width, hitbox.y, hitbox.width, hitbox.height);
        this.direction = RIGHT;
        this.hitpoints = 4;
        loadSprite();
    }

    @Override
    public void update() {
        animate();
        move();
        updateAttackHitbox();
        updateCooldowns();
    }

    private void updateCooldowns() {
        if (attackCoolDown > 0) {
            attackCoolDown--;
            if (attackCoolDown <= 140)
                attacking = false;
        }
    }

    private void animate() {
        animTick++;
        if (animTick >= 50) {
            animTick = 0;
            animIndex++;
            if (animIndex >= 4)
                animIndex = 0;
        }
    }

    private void move() {
        int xSpeed = 0, ySpeed = 0;
        int playerSpeed = 2;
        if (moveUp)
            ySpeed -= playerSpeed;
        if (moveDown)
            ySpeed += playerSpeed;
        if (moveLeft)
            xSpeed -= playerSpeed;
        if (moveRight)
            xSpeed += playerSpeed;

        hitbox.x += xSpeed;
        hitbox.y += ySpeed;
        if (ySpeed > 0)
            direction = DOWN;
        else if (ySpeed < 0)
            direction = UP;
        else if (xSpeed < 0)
            direction = LEFT;
        else if (xSpeed > 0)
            direction = RIGHT;
    }

    private void updateAttackHitbox() {
        switch (direction) {
            case UP -> {
                attackHitbox.y = hitbox.y - hitbox.height;
                attackHitbox.x = hitbox.x;
            }
            case DOWN -> {
                attackHitbox.y = hitbox.y + hitbox.height;
                attackHitbox.x = hitbox.x;
            }
            case RIGHT -> {
                attackHitbox.y = hitbox.y;
                attackHitbox.x = hitbox.x + hitbox.width;
            }
            case LEFT -> {
                attackHitbox.y = hitbox.y;
                attackHitbox.x = hitbox.x - hitbox.width;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        drawHitbox(g);
        drawHUD(g);
        switch (direction) {
            case DOWN -> {
                if (attacking) {
                    g.drawImage(sprite[4][0], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][0], Game.gameWidth / 2 - hitbox.width / 2 - hitbox.x + attackHitbox.x + attackHitbox.width / 4, Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + attackHitbox.y, attackHitbox.width / 2, attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[0][animIndex], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
            }
            case UP -> {
                if (attacking) {
                    g.drawImage(sprite[4][1], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][0], Game.gameWidth / 2 - hitbox.width / 2 - hitbox.x + attackHitbox.x + (int) (attackHitbox.width / 1.75), Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + attackHitbox.y + attackHitbox.height, -attackHitbox.width / 2, -attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[1][animIndex], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
            }
            case LEFT -> {
                if (attacking) {
                    g.drawImage(sprite[4][2], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][1], Game.gameWidth / 2 - hitbox.width / 2 - hitbox.x + attackHitbox.x + attackHitbox.width, Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + attackHitbox.y + attackHitbox.height / 2, -attackHitbox.width / 2, attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[2][animIndex], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
            }
            case RIGHT -> {
                if (attacking) {
                    g.drawImage(sprite[4][3], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][1], Game.gameWidth / 2 - hitbox.width / 2 - hitbox.x + attackHitbox.x, Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + attackHitbox.y + attackHitbox.height / 2, attackHitbox.width / 2, attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[3][animIndex], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
            }
        }
    }

    private void drawHUD(Graphics g) {
        // hitpoints
        for (int i = 0; i < 5; i++)
            if (hitpoints - i * 4 >= 4)
                g.drawImage(hudHeart[0], 48 + 36 * i, 48, 32, 32, null);
            else if (hitpoints - i * 4 > 0)
                g.drawImage(hudHeart[4 - (hitpoints - i * 4) % 5], 48 + 36 * i, 48, 32, 32, null);
            else
                g.drawImage(hudHeart[4], 48 + 36 * i, 48, 32, 32, null);

        // items
        g.setFont(new Font(Font.DIALOG, Font.LAYOUT_RIGHT_TO_LEFT, 20));
        for (int i = 0; i < 3; i++) {
            g.drawImage(itemSlot, 48 + 54 * i, 96, 48, 48, null);
            if (i == 0) {
                g.drawImage(lifePot, 60, 108, 24, 24, null);
                if (lifePotCount == 0) {
                    g.setColor(cdColor);
                    g.fillRect(60, 108, 24, 24);
                }
                g.setColor(Color.WHITE);
                g.drawString(lifePotCount+"", 88,140);
            }
            if (i == 1) {
                g.drawImage(medipack, 114, 108, 24, 24, null);
                if (medipackCount == 0) {
                    g.setColor(cdColor);
                    g.fillRect(114, 108, 24, 24);
                }
                g.setColor(Color.WHITE);
                g.drawString(medipackCount+"", 138,140);
            }
        }
    }

    private void loadSprite() {
        BufferedImage temp = AssetManager.getSprite(AssetManager.PLAYER_SPRITE);
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
        sprite[8][0] = AssetManager.getSprite(AssetManager.BIG_SWORD_V);
        sprite[8][1] = AssetManager.getSprite(AssetManager.BIG_SWORD_H);
        hudHeart = new BufferedImage[5];
        temp = AssetManager.getSprite(AssetManager.HEART);
        for (int i = 0; i < 5; i++)
            hudHeart[i] = temp.getSubimage(16 * i, 0, 16, 16);
        this.itemSlot = AssetManager.getSprite(AssetManager.ITEM_SLOT);
        this.lifePot = AssetManager.getSprite(AssetManager.LIFE_POT);
        this.medipack = AssetManager.getSprite(AssetManager.MEDIPACK);
    }

    private void drawHitbox(Graphics g) {
        if (Game.DEBUG_MODE) {
            g.setColor(Color.BLUE);
            g.drawRect(Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height);
            if (attacking)
                g.setColor(Color.RED);
            else
                g.setColor(Color.GREEN);
            g.drawRect(Game.gameWidth / 2 - hitbox.width / 2 - hitbox.x + attackHitbox.x, Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + attackHitbox.y, attackHitbox.width, attackHitbox.height);
        }
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setAttacking() {
        if (attackCoolDown <= 0) {
            this.attacking = true;
            attackCoolDown = 200;
        }
    }

    public Rectangle getAttackHitbox() {
        return attackHitbox;
    }

    public void useItem(int item) {
        if (item == 1) {
            if (lifePotCount>0){
                lifePotCount--;
                hitpoints += 2;
            }
        }else if (item == 2) {
            if (medipackCount>0){
                medipackCount--;
                hitpoints += 4;
            }
        }
        if (hitpoints>20)
            hitpoints = 20;
    }
}
