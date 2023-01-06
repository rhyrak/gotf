package entities;

import static util.Directions.DOWN;
import static util.Directions.LEFT;
import static util.Directions.RIGHT;
import static util.Directions.UP;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Game;
import util.AssetManager;
import util.Directions;
import util.SoundManager;

/**
 * Boss class <p>
 * It is a hostile to the player and if player is in chasing range of it, it starts to chase player. <p>
 * If player is in attacking range, it attacks to player. <p>
 * If player gets in and out of range, it returns to initialization position (startX, startY)
 * 
 * @author Tayfun Ozdemir
 */
public class Boss extends Entity {

    private BufferedImage[][] sprite;
    private BufferedImage[] healthBar;
    private boolean moveUp, moveDown, moveRight, moveLeft;
    private Rectangle attackHitbox;
    private boolean attacking;
    private int animIndex, animTick;
    private Directions direction;
    private int camOffsetX, camOffsetY;
    public int actionLockCounter = 0;
    public int startX, startY;
    public boolean returning = false;
    private int attackCoolDown = 0;
    private boolean isDead = false;
    private boolean deathAnim = false;

    /**
     * Class constructor 
     * @param  entityManager  an EntityManager to access player's properties
     * @param  hitbox  a Rectangle to assign Boss' hitbox
     * @see  EntityManager
     */
    public Boss(EntityManager entityManager, Rectangle hitbox) {
        this.entityManager = entityManager;
        this.hitbox = hitbox;
        this.hitpoints = 600;
        this.attackHitbox = new Rectangle(hitbox.x + hitbox.width, hitbox.y, hitbox.width, hitbox.height);
        this.direction = Directions.LEFT;
        loadSprite();
        startX = hitbox.x;
        startY = hitbox.y;
    }
    
    /**
     * Loads needed sprites for Boss
     */
    private void loadSprite() {
        BufferedImage temp = AssetManager.getSprite(AssetManager.BOSS_WALK_RIGHT);
        sprite = new BufferedImage[6][8];
        //-----------------walk right-----------------
        sprite[0][0] = temp.getSubimage(0, 0, 100, 100);
        sprite[0][1] = temp.getSubimage(100, 0, 100, 100);
        sprite[0][2] = temp.getSubimage(200, 0, 100, 100);
        sprite[0][3] = temp.getSubimage(300, 0, 100, 100);
        sprite[0][4] = temp.getSubimage(0, 100, 100, 100);
        sprite[0][5] = temp.getSubimage(100, 100, 100, 100);
        sprite[0][6] = temp.getSubimage(200, 100, 100, 100);
        sprite[0][7] = temp.getSubimage(300, 100, 100, 100);
        //-----------------walk left--------------------
        temp = AssetManager.getSprite(AssetManager.BOSS_WALK_LEFT);
        sprite[1][0] = temp.getSubimage(300, 0, 100, 100);
        sprite[1][1] = temp.getSubimage(200, 0, 100, 100);
        sprite[1][2] = temp.getSubimage(100, 0, 100, 100);
        sprite[1][3] = temp.getSubimage(0, 0, 100, 100);
        sprite[1][4] = temp.getSubimage(300, 100, 100, 100);
        sprite[1][5] = temp.getSubimage(200, 100, 100, 100);
        sprite[1][6] = temp.getSubimage(100, 100, 100, 100);
        sprite[1][7] = temp.getSubimage(0, 100, 100, 100);
        //-------------------attack right------------------
        temp = AssetManager.getSprite(AssetManager.BOSS_ATTACK_RIGHT);
        sprite[2][0] = temp.getSubimage(100, 0, 100, 100);
        sprite[2][1] = temp.getSubimage(200, 0, 100, 100);
        sprite[2][2] = temp.getSubimage(300, 0, 100, 100);
        sprite[2][3] = temp.getSubimage(400, 0, 100, 100);
        sprite[2][4] = temp.getSubimage(500, 100, 100, 100);
        sprite[2][5] = temp.getSubimage(0, 100, 100, 100);
        sprite[2][6] = temp.getSubimage(100, 100, 100, 100);
        sprite[2][7] = temp.getSubimage(200, 100, 100, 100);
        //------------------attack left--------------------
        temp = AssetManager.getSprite(AssetManager.BOSS_ATTACK_LEFT);
        sprite[3][0] = temp.getSubimage(400, 0, 100, 100);
        sprite[3][1] = temp.getSubimage(300, 0, 100, 100);
        sprite[3][2] = temp.getSubimage(200, 0, 100, 100);
        sprite[3][3] = temp.getSubimage(100, 0, 100, 100);
        sprite[3][4] = temp.getSubimage(0, 0, 100, 100);
        sprite[3][5] = temp.getSubimage(500, 100, 100, 100);
        sprite[3][6] = temp.getSubimage(400, 100, 100, 100);
        sprite[3][7] = temp.getSubimage(300, 100, 100, 100);
        //----------------- death right----------------------
        temp = AssetManager.getSprite(AssetManager.BOSS_DEATH_RIGHT);
        sprite[4][0] = temp.getSubimage(100, 0, 100, 100);
        sprite[4][1] = temp.getSubimage(200, 0, 100, 100);
        sprite[4][2] = temp.getSubimage(300, 0, 100, 100);
        sprite[4][3] = temp.getSubimage(600, 0, 100, 100);
        sprite[4][4] = temp.getSubimage(700, 0, 100, 100);
        sprite[4][5] = temp.getSubimage(900, 0, 100, 100);
        sprite[4][6] = temp.getSubimage(300, 100, 100, 100);
        sprite[4][7] = temp.getSubimage(600, 100, 100, 100);
        //-----------------death left-----------------------
        temp = AssetManager.getSprite(AssetManager.BOSS_DEATH_LEFT);
        sprite[5][0] = temp.getSubimage(800, 0, 100, 100);
        sprite[5][1] = temp.getSubimage(700, 0, 100, 100);
        sprite[5][2] = temp.getSubimage(600, 0, 100, 100);
        sprite[5][3] = temp.getSubimage(200, 0, 100, 100);
        sprite[5][4] = temp.getSubimage(300, 0, 100, 100);
        sprite[5][5] = temp.getSubimage(0, 0, 100, 100);
        sprite[5][6] = temp.getSubimage(600, 100, 100, 99);
        sprite[5][7] = temp.getSubimage(300, 100, 100, 99);
        //-------------------health bar----------------------
        healthBar = new BufferedImage[2];
        temp = AssetManager.getSprite(AssetManager.HEALTH_BAR_BG);
        healthBar[0] = temp.getSubimage(3, 0, 103, 7);
        temp = AssetManager.getSprite(AssetManager.HEALTH_BAR_RED);
        healthBar[1] = temp.getSubimage(0, 0, 100, 7);
    }
    
    /**
     * Draws all the sprites and animations, if boss is dead, returns nothing and leaves the function and draws nothing all the time
     * @param  g  it is a needed instance of Graphics to be able to draw sprites
     */
    @Override
    public void draw(Graphics g) {
        if (isDead)
            return;
        if (deathAnim)
            if (moveRight)
                g.drawImage(sprite[4][animIndex], hitbox.x + entityManager.getCamOffsetX(), hitbox.y + entityManager.getCamOffsetY(), hitbox.width, hitbox.height, null);
            else
                g.drawImage(sprite[5][animIndex], hitbox.x + entityManager.getCamOffsetX(), hitbox.y + entityManager.getCamOffsetY(), hitbox.width, hitbox.height, null);
        else {
            drawHealthBar(g);
            switch (direction) {
                case LEFT:
                    if (attacking)
                        g.drawImage(sprite[3][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    else
                        g.drawImage(sprite[1][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    break;
                case RIGHT:
                    if (attacking)
                        g.drawImage(sprite[2][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    else
                        g.drawImage(sprite[0][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    break;
                case UP:
                    if (attacking) {
                        if (moveLeft)
                            g.drawImage(sprite[3][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                        else
                            g.drawImage(sprite[2][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    } else {
                        if (moveLeft)
                            g.drawImage(sprite[1][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                        else
                            g.drawImage(sprite[0][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    }
                    break;
                case DOWN:
                    if (attacking) {
                        if (moveLeft)
                            g.drawImage(sprite[3][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                        else
                            g.drawImage(sprite[2][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    } else {
                        if (moveLeft)
                            g.drawImage(sprite[1][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                        else
                            g.drawImage(sprite[0][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    }
                    break;
            }
        }
        if (Game.DEBUG_MODE)
            g.drawRect(hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height);
    }
    
    /**
     * Runs 200 times per second, calls some functions and updates everything. <p>
     * If Boss is dead, returns nothing to leave function. So nothing is updated no more.
     */
    @Override
    public void update() {
        if (isDead)
            return;
        if (deathAnim)
            animate();
        else {
            camOffsetX = Game.gameWidth / 2 - entityManager.getPlayer().getHitbox().x - entityManager.getPlayer().getHitbox().width / 2;
            camOffsetY = Game.gameHeight / 2 - entityManager.getPlayer().getHitbox().y - entityManager.getPlayer().getHitbox().height / 2;
            setAction();
            animate();
            updateCooldowns();
            updateAttackHitbox();
            updateHitpoints();
            move();
        }

    }
    
    /**
     * A function to draw health bar above the Boss
     * @param  g  it is a needed instance of Graphics to be able to draw health bar
     */
    private void drawHealthBar(Graphics g) {
        g.drawImage(healthBar[0], hitbox.x + camOffsetX + 150, hitbox.y + camOffsetY + 20, 200, 14, null);
        g.drawImage(healthBar[1], hitbox.x + camOffsetX + 150, hitbox.y + camOffsetY + 20, hitpoints / 3, 14, null);
    }
    
    /**
     * Sets and resets cool-down time to draw next sprite of current animation
     */
    private void animate() {
        animTick++;
        if (animTick >= 50 && deathAnim == false) {
            animTick = 0;
            animIndex++;
            if (animIndex >= 8)
                animIndex = 0;
        }
        if (animTick >= 75 && deathAnim == true) {
            animTick = 0;
            animIndex++;
            if (animIndex >= 8)
                isDead = true;
        }
    }
    
    /**
     * Updates the x-axis and y-axis speed according to direction booleans, <p>
     * then changes the coordinates according to x-axis and y-axis speed, <p>
     * and then sets the enum Direction according to x-axis and y-axis speed.
     */
    private void move() {
        int xSpeed = 0, ySpeed = 0;
        int ninjaSpeed = 1;
        if (moveUp)
            ySpeed -= ninjaSpeed;
        if (moveDown)
            ySpeed += ninjaSpeed;
        if (moveLeft)
            xSpeed -= ninjaSpeed;
        if (moveRight)
            xSpeed += ninjaSpeed;

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

    /**
     * Checks and sets idle, chasing, attacking situations
     */
    public void setAction() {

        //reset directions
        moveRight = false;
        moveLeft = false;
        moveUp = false;
        moveDown = false;

        int xDiff = (int) entityManager.getPlayer().getHitbox().getCenterX() - (int) hitbox.getCenterX(); // x-axis distance between RedNinja and player
        int yDiff = (int) entityManager.getPlayer().getHitbox().getCenterY() - (int) hitbox.getCenterY(); // y-axis distance between RedNinja and player

        int posDiffX = startX - hitbox.x; // x-axis distance between RedNinja's starting position and initial position
        int posDiffY = startY - hitbox.y; // y-axis distance between RedNinja's starting position and initial position

        if (Math.abs(xDiff) <= 450 && Math.abs(yDiff) <= 450) { // chase the player

            //set attacking
            if (Math.abs(entityManager.getPlayer().getHitbox().getCenterX() - hitbox.getCenterX()) <= 280 &&
                    Math.abs(Math.abs(entityManager.getPlayer().getHitbox().getCenterY() - hitbox.getCenterY())) <= 280) {
                attacking = true;
                SoundManager.DogWoof();
            }
            else
                attacking = false;

            //chasing
            if (xDiff == 0 && yDiff < 0)
                moveUp = true;
            else if (xDiff == 0 && yDiff > 0)
                moveDown = true;
            else if (xDiff < 0 && yDiff == 0)
                moveLeft = true;
            else if (xDiff > 0 && yDiff == 0)
                moveRight = true;
            else if (xDiff < 0 && yDiff < 0) {
                moveLeft = true;
                moveUp = true;
            } else if (xDiff < 0 && yDiff > 0) {
                moveLeft = true;
                moveDown = true;
            } else if (xDiff > 0 && yDiff < 0) {
                moveRight = true;
                moveUp = true;
            } else if (xDiff > 0 && yDiff > 0) {
                moveRight = true;
                moveDown = true;
            }
        } else if (Math.abs(posDiffX) >= 301 || Math.abs(posDiffY) >= 301 || returning) { // returning to starting position
            attacking = false;
            returning = true;
            actionLockCounter = 0;

            if (posDiffY != 0)
                if (posDiffY < 0)
                    moveUp = true;
                else
                    moveDown = true;

            if (posDiffX != 0)
                if (posDiffX != 0)
                    if (posDiffX < 0)
                        moveLeft = true;
                    else
                        moveRight = true;

            if (hitbox.x == startX && hitbox.y == startY)
                returning = false;

        } else if (!returning)
            attacking = false;
    }
    
    /**
     * Sets and resets the attacking cooldown. <p>
     * If Boss attacked once, attacking goes on cooldown to prevent to attack for every run of update method (200 attack per second). <p>
     * After a while, attacking cooldown resets.
     */
    private void updateCooldowns() {
        //works only if player is always in attack range,
        //if player gets in and out of the attack range repeatedly,
        //ninja attacks without waiting to reset attack cooldown.
        if (attacking) {
            if (attackCoolDown < 300) //attack
                attackCoolDown++;
            else if (attackCoolDown >= 300 && attackCoolDown < 600) { //attack on cooldown
                attackCoolDown++;
                attacking = false;
            } else if (attackCoolDown == 600) //reset attack cooldown
                attackCoolDown = 0;
        }
    }
    
    /**
     * Designates attackHitbox according to direction
     */
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
    
    /**
     * Updates Boss' and player's hitpoints if they are attacked. <p>
     * If Boss attacks player, plays the attacking sound and <p>
     * sets player's invincible boolean variable to true which prevents player to be attacked repeatedly.
     */
    private void updateHitpoints() {
        if (attacking) {
            if (attackHitbox.contains(entityManager.getPlayer().getHitbox()) &&
                    entityManager.getPlayer().isInvincible() == false &&
                    entityManager.getPlayer().isShielded() == false
            ) {
                SoundManager.BossAttack();
                entityManager.getPlayer().hitpoints -= 2;
                entityManager.getPlayer().setInvincible(true);
            } else if (hitbox.contains(entityManager.getPlayer().getHitbox()) &&
                    entityManager.getPlayer().isInvincible() == false &&
                    entityManager.getPlayer().isShielded() == false
            ) {
                SoundManager.BossAttack();
                entityManager.getPlayer().hitpoints -= 2;
                entityManager.getPlayer().setInvincible(true);
            }
        }

        if (entityManager.getPlayer().getAttackHitbox().intersects(hitbox) && entityManager.getPlayer().isAttacking()) {
            SoundManager.Hitt();
            hitpoints--;
        } else if (entityManager.getPlayer().getHitbox().intersects(hitbox) && entityManager.getPlayer().isAttacking()) {
            SoundManager.Hitt();
            hitpoints--;
        }
        if (hitpoints == 0) {
            SoundManager.BossDead();
            deathAnim = true;
            entityManager.getPlayer().addExp(50);
            animTick = 0;
            animIndex = 0;
        }
    }
}
