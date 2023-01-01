package entities;

import main.Game;
import states.Playing;
import util.AssetManager;
import util.Directions;
import util.SaveData;
import util.SoundManager;
import world.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

import static util.Directions.*;

public class Player extends Entity {

    private boolean moveUp, moveDown, moveRight, moveLeft;
    private Rectangle attackHitbox;
    private Rectangle moveHitbox;
    private boolean attacking;
    private int maxHP;
    private Directions direction;
    private BufferedImage[][] sprite;
    private BufferedImage[] hudHeart, shield;
    private BufferedImage itemSlot, lifePot, medipack, defScroll;
    private int lifePotCount = 5, medipackCount = 2, defScrollCount = 1;
    private BufferedImage expBar;
    private BufferedImage expBarBg;
    private Color cdColor = new Color(222, 222, 222, 200);
    private int animIndex, animTick;
    private int attackCoolDown;
    private Level level;
    private boolean invincible;
    public int invinceTick;
    private int exp = 0;
    private int playerLevel;
    private boolean shielded;
    private int shieldTick, shieldIndex;

    public Player(SaveData saveData) {
        this.hitbox = new Rectangle(saveData.playerX, saveData.playerY, 64, 64);
        this.attackHitbox = new Rectangle(hitbox.x + hitbox.width, hitbox.y, hitbox.width, hitbox.height);
        this.moveHitbox = new Rectangle(hitbox.x + 2, hitbox.y + 32, hitbox.width - 4, hitbox.height - 32);
        this.direction = RIGHT;
        this.hitpoints = 4;
        // TODO: determine max hp from saveData
        this.maxHP = 12;
        this.playerLevel = 0;
        loadSprite();
    }

    @Override
    public void update() {
        animate();
        move();
        updateAttackHitbox();
        updateCooldowns();
        if (invincible)
            invinceTick++;
        if (invinceTick > 250) {
            invinceTick = 0;
            invincible = false;
        }
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
        if (shielded) {
            shieldTick++;
            if (shieldTick >= 1000) {
                shielded = false;
            }
            if (shieldTick % 15 == 0) {
                shieldIndex--;
                if (shieldIndex == -1)
                    shieldIndex = 5;
            }
        }
    }

    private void move() {
        int xSpeed = 0, ySpeed = 0;
        int playerSpeed = 2;
        if (moveDown || moveUp || moveLeft || moveRight)
            if (Playing.getSaveData().floor == 0)
                SoundManager.Walkingforest();
            else
                SoundManager.WalkingDungeon();
        else
            SoundManager.Stand();

        if (moveUp)
            ySpeed -= playerSpeed;
        if (moveDown)
            ySpeed += playerSpeed;
        if (moveLeft)
            xSpeed -= playerSpeed;
        if (moveRight)
            xSpeed += playerSpeed;


        if (level != null) {
            if (xSpeed > 0)
                if (level.canMove(moveHitbox.x + xSpeed + moveHitbox.width, moveHitbox.y) && level.canMove(moveHitbox.x + xSpeed + moveHitbox.width, moveHitbox.y + moveHitbox.height)) {
                    hitbox.x += xSpeed;
                    moveHitbox.x += xSpeed;
                }
            if (xSpeed < 0)
                if (level.canMove(moveHitbox.x + xSpeed, moveHitbox.y) && level.canMove(moveHitbox.x + xSpeed, moveHitbox.y + moveHitbox.height)) {
                    hitbox.x += xSpeed;
                    moveHitbox.x += xSpeed;
                }
            if (ySpeed > 0)
                if (level.canMove(moveHitbox.x, moveHitbox.y + ySpeed + moveHitbox.height) && level.canMove(moveHitbox.x + moveHitbox.width, moveHitbox.y + ySpeed + moveHitbox.height)) {
                    hitbox.y += ySpeed;
                    moveHitbox.y += ySpeed;
                }
            if (ySpeed < 0)
                if (level.canMove(moveHitbox.x, moveHitbox.y + ySpeed) && level.canMove(moveHitbox.x + moveHitbox.width, moveHitbox.y + ySpeed)) {
                    hitbox.y += ySpeed;
                    moveHitbox.y += ySpeed;
                }
        }

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
        int index;
        if (moveRight || moveLeft || moveUp || moveDown)
            index = animIndex;
        else
            index = 0;
        switch (direction) {
            case DOWN -> {
                if (attacking) {
                    SoundManager.Attack();
                    g.drawImage(sprite[4][0], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][0], Game.gameWidth / 2 - hitbox.width / 2 - hitbox.x + attackHitbox.x + attackHitbox.width / 4, Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + attackHitbox.y, attackHitbox.width / 2, attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[0][index], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
            }
            case UP -> {
                if (attacking) {
                    SoundManager.Attack();
                    g.drawImage(sprite[4][1], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][0], Game.gameWidth / 2 - hitbox.width / 2 - hitbox.x + attackHitbox.x + (int) (attackHitbox.width / 1.75), Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + attackHitbox.y + attackHitbox.height, -attackHitbox.width / 2, -attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[1][index], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
            }
            case LEFT -> {
                if (attacking) {
                    SoundManager.Attack();
                    g.drawImage(sprite[4][2], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][1], Game.gameWidth / 2 - hitbox.width / 2 - hitbox.x + attackHitbox.x + attackHitbox.width, Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + attackHitbox.y + attackHitbox.height / 2, -attackHitbox.width / 2, attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[2][index], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
            }
            case RIGHT -> {
                if (attacking) {
                    SoundManager.Attack();
                    g.drawImage(sprite[4][3], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][1], Game.gameWidth / 2 - hitbox.width / 2 - hitbox.x + attackHitbox.x, Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + attackHitbox.y + attackHitbox.height / 2, attackHitbox.width / 2, attackHitbox.height / 2, null);
                } else
                    g.drawImage(sprite[3][index], Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2, hitbox.width, hitbox.height, null);
            }
        }
        if (shielded)
            g.drawImage(shield[shieldIndex], Game.gameWidth / 2 - hitbox.width / 2 - 10, Game.gameHeight / 2 - hitbox.height / 2 - 10, hitbox.width + 20, hitbox.height + 20, null);
        // FIXME
        if (invincible)
            g.drawString("OOF", Game.gameWidth / 2 - hitbox.width / 2, Game.gameHeight / 2 - hitbox.height / 2);
    }

    private void drawHUD(Graphics g) {
        // hitpoints
        for (int i = 0; i < maxHP / 4; i++)
            if (hitpoints - i * 4 >= 4)
                g.drawImage(hudHeart[0], 48 + 36 * i, 48, 32, 32, null);
            else if (hitpoints - i * 4 > 0)
                g.drawImage(hudHeart[4 - (hitpoints - i * 4) % 5], 48 + 36 * i, 48, 32, 32, null);
            else
                g.drawImage(hudHeart[4], 48 + 36 * i, 48, 32, 32, null);

        // items
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        for (int i = 0; i < 3; i++) {
            g.drawImage(itemSlot, 48 + 54 * i, 96, 48, 48, null);
            if (i == 0) {
                g.drawImage(lifePot, 60, 108, 24, 24, null);
                if (lifePotCount == 0) {
                    g.setColor(cdColor);
                    g.fillRect(60, 108, 24, 24);
                }
                g.setColor(Color.WHITE);
                g.drawString(lifePotCount + "", 88, 140);
            }
            if (i == 1) {
                g.drawImage(medipack, 114, 108, 24, 24, null);
                if (medipackCount == 0) {
                    g.setColor(cdColor);
                    g.fillRect(114, 108, 24, 24);
                }
                g.setColor(Color.WHITE);
                g.drawString(medipackCount + "", 138, 140);
            }
            if (i == 2) {
                g.drawImage(defScroll, 168, 108, 24, 24, null);
                if (defScrollCount == 0) {
                    g.setColor(cdColor);
                    g.fillRect(168, 108, 24, 24);
                }
                g.setColor(Color.WHITE);
                g.drawString(defScrollCount + "", 200, 140);
            }
        }
        //EXP
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        g.drawString(Integer.toString(playerLevel), 35, 33);
        g.drawString(Integer.toString(playerLevel+1), 151, 33);
        g.drawImage(expBarBg, 48, 24, 100, 7, null);
        g.drawImage(expBar, 48, 24, exp, 7, null);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
        g.drawString(Integer.toString(exp) + "/100", 85, 30);
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
        shield = new BufferedImage[6];
        temp = AssetManager.getSprite(AssetManager.SHIELD);
        for (int i = 0; i < 6; i++)
            shield[i] = temp.getSubimage(24 * i, 0, 24, 26);
        itemSlot = AssetManager.getSprite(AssetManager.ITEM_SLOT);
        lifePot = AssetManager.getSprite(AssetManager.LIFE_POT);
        medipack = AssetManager.getSprite(AssetManager.MEDIPACK);
        defScroll = AssetManager.getSprite(AssetManager.DEF_SCROLL);
        this.itemSlot = AssetManager.getSprite(AssetManager.ITEM_SLOT);
        this.lifePot = AssetManager.getSprite(AssetManager.LIFE_POT);
        this.medipack = AssetManager.getSprite(AssetManager.MEDIPACK);
        temp = AssetManager.getSprite(AssetManager.BLUE_BAR);
        this.expBar = temp.getSubimage(0, 0, 100, 7);
        temp = AssetManager.getSprite(AssetManager.HEALTH_BAR_BG);
        this.expBarBg = temp.getSubimage(5, 0, 100, 7);
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
            g.setColor(Color.MAGENTA);
            g.drawRect(Game.gameWidth / 2 - hitbox.width / 2 - hitbox.x + moveHitbox.x, Game.gameHeight / 2 - hitbox.height / 2 - hitbox.y + moveHitbox.y, moveHitbox.width, moveHitbox.height);
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

    public Rectangle getMoveHitbox() {
        return moveHitbox;
    }

    public void useItem(int item) {
        if (item == 1 && hitpoints != maxHP) {
            if (lifePotCount > 0) {
                SoundManager.UseLifePot();
                lifePotCount--;
                hitpoints += 2;
            }
        } else if (item == 2 && hitpoints != maxHP) {
            if (medipackCount > 0) {
                SoundManager.UseMedPac();
                medipackCount--;
                hitpoints += 4;
            }
        } else if (item == 3 && shieldTick == 0) {
            if (defScrollCount > 0) {
                defScrollCount--;
                shieldTick = 0;
                shieldIndex = 5;
                shielded = true;
            }
        }
        if (hitpoints > maxHP)
            hitpoints = maxHP;
    }

    public boolean addItem(Item.ItemType type) {
        switch (type) {
            case LIFEPOT -> {
                if (lifePotCount < 5) {
                    SoundManager.GetHealthItem();
                    lifePotCount++;
                    return true;
                }
            }
            case MEDIPACK -> {
                if (medipackCount < 5) {
                    SoundManager.GetHealthItem();
                    medipackCount++;
                    return true;
                }
            }
            case DEF_SCROLL -> {
                if (defScrollCount < 5) {
                    SoundManager.GetHealthItem();
                    defScrollCount++;
                    return true;
                }
            }
        }
        return false;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean getInvincible() {
        return invincible;
    }

    public void setInvincible(boolean bool) {
        invincible = bool;
    }

    public boolean getAttacking() {
        return attacking;
    }

    private void changeWeapon() {
        if (playerLevel == 1) {
            sprite[8][0] = AssetManager.getSprite(AssetManager.AXE_ONE_V);
            sprite[8][1] = AssetManager.getSprite(AssetManager.AXE_ONE_H);
        } else if (playerLevel == 2) {
            sprite[8][0] = AssetManager.getSprite(AssetManager.AXE_TWO_V);
            sprite[8][1] = AssetManager.getSprite(AssetManager.AXE_TWO_H);
        }
    }

    public void setExp(int exp) {
        this.exp += exp;
        if (this.exp >= 100) {
            this.playerLevel++;
            changeWeapon();
            this.exp = this.exp % 100;
        }
    }

}
