package entities;

import main.Game;
import util.AssetManager;
import util.Directions;

import java.awt.*;
import java.awt.image.BufferedImage;

import static util.Directions.DOWN;
import static util.Directions.LEFT;
import static util.Directions.RIGHT;
import static util.Directions.UP;

public class Caveman extends Entity{

    private BufferedImage[][] sprite;
    private boolean moveUp, moveDown, moveRight, moveLeft;
    private Rectangle attackHitbox;
    private boolean attacking;
    private int animIndex, animTick;
    private Directions direction;
    private int camOffsetX, camOffsetY;
    public int actionLockCounter = 0;
    public int startX, startY;
    public boolean returning = false;
    private int attackCoolDown=0;
    private boolean isDead = false;
    private BufferedImage[] healthBar;
    
    // constructor
    public Caveman(EntityManager entityManager, Rectangle hitbox) { 
    	this.entityManager = entityManager;
        this.hitbox = hitbox;
        this.attackHitbox = new Rectangle(hitbox.x + hitbox.width, hitbox.y, hitbox.width, hitbox.height);
        this.direction = LEFT;
        this.hitpoints = 250;
        loadSprite();
        startX = hitbox.x;
        startY = hitbox.y;
    }

    private void loadSprite() {
        BufferedImage temp = AssetManager.getSprite(AssetManager.CAVEMAN);
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
        sprite[8][0] = AssetManager.getSprite(AssetManager.CLUB_V);
        sprite[8][1] = AssetManager.getSprite(AssetManager.CLUB_H);
        healthBar = new BufferedImage[2];
    	temp = AssetManager.getSprite(AssetManager.HEALTH_BAR_BG);
    	healthBar[0] = temp.getSubimage(3, 0, 103,7);
    	temp = AssetManager.getSprite(AssetManager.HEALTH_BAR_RED);
    	healthBar[1] = temp.getSubimage(0, 0, 100,7);
    }
    
    //checks and sets idle/chase situations
    public void setAction() {
    	
    	//reset directions
    	moveRight = false;
    	moveLeft = false;
    	moveUp = false;
    	moveDown = false;
		
        int xDiff = entityManager.getPlayer().getHitbox().x - hitbox.x; // x-axis distance between monster and player
    	int yDiff = entityManager.getPlayer().getHitbox().y - hitbox.y; // y-axis distance between monster and player
    	
    	int posDiffX = startX - hitbox.x; // x-axis distance between monster's starting position and initial position
    	int posDiffY = startY - hitbox.y; // y-axis distance between monster's starting position and initial position
    	
    	if(Math.abs(xDiff) <= 250 && Math.abs(yDiff) <= 250) { // chase the player
    		
    		//set attacking
    		if(Math.abs(entityManager.getPlayer().getHitbox().getCenterX() - hitbox.getCenterX()) <= 80 &&
    			Math.abs(Math.abs(entityManager.getPlayer().getHitbox().getCenterY() - hitbox.getCenterY())) <= 80)
    			attacking = true;
    		else
    			attacking = false;
    		
    		//chasing
    		if(xDiff == 0 && yDiff < 0) 
				moveUp = true;
			else if(xDiff == 0 && yDiff > 0)
				moveDown = true;
			else if(xDiff < 0 && yDiff == 0)
				moveLeft = true;
			else if(xDiff > 0 && yDiff == 0)
				moveRight = true;
			else if(xDiff < 0 && yDiff < 0) {
				moveLeft = true; 
				moveUp = true;
			}else if(xDiff < 0 && yDiff > 0) {
				moveLeft = true; 
				moveDown = true;
			}else if(xDiff > 0 && yDiff < 0) {
				moveRight = true; 
				moveUp = true;
			}else if(xDiff > 0 && yDiff > 0) {
				moveRight = true; 
				moveDown = true;
			}
		}else if(Math.abs(posDiffX) >= 301 || Math.abs(posDiffY) >= 301 || returning){ // returning to starting position
			attacking = false;
			returning = true;
			actionLockCounter = 0;
			
			if(posDiffY != 0)
				if(posDiffY < 0) 
					moveUp = true;
				else 
					moveDown = true;
	
			if(posDiffX != 0)
				if(posDiffX != 0)
					if(posDiffX < 0)
						moveLeft = true;
					else
						moveRight = true;
			
			if(hitbox.x == startX && hitbox.y == startY)
				returning = false;
			
		}else if(!returning){ // idle movements
			attacking = false;
			actionLockCounter++;

			if(actionLockCounter>=0 && actionLockCounter < 300) {
				moveRight = true;
			}else if(actionLockCounter >= 400 && actionLockCounter < 700) {
				moveDown = true;
			}else if(actionLockCounter >= 800 && actionLockCounter < 1100) {
				moveLeft = true;
			}else if(actionLockCounter >= 1200 && actionLockCounter < 1500)
				moveUp = true;
			if(actionLockCounter == 1600)
				actionLockCounter = 0;	
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
    
    @Override
    public void draw(Graphics g) {
    	if(isDead)
    		return;
    	drawHealthBar(g);
        switch (direction) {
            case DOWN -> {
                if (attacking) {
                    g.drawImage(sprite[4][0], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                    g.drawImage(sprite[8][0], hitbox.x + camOffsetX - hitbox.x + attackHitbox.x + attackHitbox.width / 4, hitbox.y + camOffsetY + 64, attackHitbox.width / 2, attackHitbox.height / 2, null);
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
    
    private void drawHealthBar(Graphics g) {
    	g.drawImage(healthBar[0], hitbox.x + camOffsetX + 7, hitbox.y + camOffsetY - 15, 50, 4, null);
		g.drawImage(healthBar[1], hitbox.x + camOffsetX + 7, hitbox.y + camOffsetY - 15, hitpoints/5, 4, null);
	}
    @Override
    public void update() {
    	if(isDead)
    		return;
        camOffsetX = Game.gameWidth / 2 - entityManager.getPlayer().getHitbox().x - entityManager.getPlayer().getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - entityManager.getPlayer().getHitbox().y - entityManager.getPlayer().getHitbox().height / 2;
        setAction();
        updateAttackHitbox();
        updateCooldowns();
        updateHitpoints();
        animate();
        move();
    }

    private void updateCooldowns() {
        //works only if player is always in attack range,
    	//if player gets in and out of the attack range repeatedly,
    	//monster attacks without waiting to reset attack cooldown.
    	if(attacking) {
        	if(attackCoolDown<125) //attack
        		attackCoolDown++;
        	else if(attackCoolDown>=125 && attackCoolDown<250){ //attack on cooldown
        		attackCoolDown++;
        		attacking = false;
        	}else if(attackCoolDown==250) //reset attack cooldown
        		attackCoolDown=0;
        }
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
    
    private void updateHitpoints() {
    	if(attacking && attackHitbox.contains(entityManager.getPlayer().getHitbox()) &&
    		entityManager.getPlayer().isInvincible() == false &&
                entityManager.getPlayer().isShielded() == false
        ) {
    		entityManager.getPlayer().hitpoints--;
    		entityManager.getPlayer().setInvincible(true);
    	}
	if(attacking && hitbox.contains(entityManager.getPlayer().getHitbox()) &&
        	entityManager.getPlayer().isInvincible() == false &&
            entityManager.getPlayer().isShielded() == false
    ) {
        	entityManager.getPlayer().hitpoints--;
        	entityManager.getPlayer().setInvincible(true);
        }
   	 if(entityManager.getPlayer().getAttackHitbox().intersects(hitbox) && entityManager.getPlayer().isAttacking())
    		hitpoints--;
    	else if(entityManager.getPlayer().getHitbox().intersects(hitbox) && entityManager.getPlayer().isAttacking())
    		hitpoints--;
	if(hitpoints == 0) {
		isDead = true;
		entityManager.getPlayer().addExp(35);
	}
    }
    
}
