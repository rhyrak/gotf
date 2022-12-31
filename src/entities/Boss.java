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

public class Boss extends Entity{
	
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
    private boolean deathAnim = false;
    // constructor
    public Boss(EntityManager entityManager, Rectangle hitbox) { 
    	this.entityManager = entityManager;
        this.hitbox = hitbox;
        this.hitpoints = 10;
        this.attackHitbox = new Rectangle(hitbox.x + hitbox.width, hitbox.y, hitbox.width, hitbox.height);
        this.direction = Directions.LEFT;
        loadSprite();
        startX = hitbox.x;
        startY = hitbox.y;
    }
    
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
    	sprite[0][7]= temp.getSubimage(300, 100, 100, 100);
    	//-----------------walk left--------------------
    	temp = AssetManager.getSprite(AssetManager.BOSS_WALK_LEFT);
    	sprite[1][0] = temp.getSubimage(300, 0, 100, 100);
    	sprite[1][1] = temp.getSubimage(200, 0, 100, 100);
    	sprite[1][2] = temp.getSubimage(100, 0, 100, 100);
    	sprite[1][3] = temp.getSubimage(0, 0, 100, 100);
    	sprite[1][4] = temp.getSubimage(300, 100, 100, 100);
    	sprite[1][5] = temp.getSubimage(200, 100, 100, 100);
    	sprite[1][6] = temp.getSubimage(100, 100, 100, 100);
    	sprite[1][7]= temp.getSubimage(0, 100, 100, 100);
    	//-------------------attack right------------------
    	temp = AssetManager.getSprite(AssetManager.BOSS_ATTACK_RIGHT);
    	sprite[2][0] = temp.getSubimage(100, 0, 100, 100);
    	sprite[2][1] = temp.getSubimage(200, 0, 100, 100);
    	sprite[2][2] = temp.getSubimage(300, 0, 100, 100);
    	sprite[2][3] = temp.getSubimage(400, 0, 100, 100);
    	sprite[2][4] = temp.getSubimage(500, 100, 100, 100);
    	sprite[2][5] = temp.getSubimage(0, 100, 100, 100);
    	sprite[2][6] = temp.getSubimage(100, 100, 100, 100);
    	sprite[2][7]= temp.getSubimage(200, 100, 100, 100);
    	//------------------attack left--------------------
    	temp = AssetManager.getSprite(AssetManager.BOSS_ATTACK_LEFT);
    	sprite[3][0] = temp.getSubimage(400, 0, 100, 100);
    	sprite[3][1] = temp.getSubimage(300, 0, 100, 100);
    	sprite[3][2] = temp.getSubimage(200, 0, 100, 100);
    	sprite[3][3] = temp.getSubimage(100, 0, 100, 100);
    	sprite[3][4] = temp.getSubimage(0, 0, 100, 100);
    	sprite[3][5] = temp.getSubimage(500, 100, 100, 100);
    	sprite[3][6] = temp.getSubimage(400, 100, 100, 100);
    	sprite[3][7]= temp.getSubimage(300, 100, 100, 100);
    	//----------------- death right----------------------
    	temp = AssetManager.getSprite(AssetManager.BOSS_DEATH_RIGHT);
    	sprite[4][0] = temp.getSubimage(100, 0, 100, 100);
    	sprite[4][1] = temp.getSubimage(200, 0, 100, 100);
    	sprite[4][2] = temp.getSubimage(300, 0, 100, 100);
    	sprite[4][3] = temp.getSubimage(600, 0, 100, 100);
    	sprite[4][4] = temp.getSubimage(700, 0, 100, 100);
    	sprite[4][5] = temp.getSubimage(900, 0, 100, 100);
    	sprite[4][6] = temp.getSubimage(300, 100, 100, 100);
    	sprite[4][7]= temp.getSubimage(600, 100, 100, 100);
    	//-----------------death left-----------------------
    	temp = AssetManager.getSprite(AssetManager.BOSS_DEATH_LEFT);
    	sprite[5][0] = temp.getSubimage(800, 0, 100, 100);
    	sprite[5][1] = temp.getSubimage(700, 0, 100, 100);
    	sprite[5][2] = temp.getSubimage(600, 0, 100, 100);
    	sprite[5][3] = temp.getSubimage(200, 0, 100, 100);
    	sprite[5][4] = temp.getSubimage(300, 0, 100, 100);
    	sprite[5][5] = temp.getSubimage(0, 0, 100, 100);
    	sprite[5][6] = temp.getSubimage(600, 100, 100, 99);
    	sprite[5][7]= temp.getSubimage(300, 100, 100, 99);
    }
    
	@Override
	public void draw(Graphics g) {
		if(isDead)
			return;
		if(deathAnim)
			if(moveRight)
				g.drawImage(sprite[4][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
			else
				g.drawImage(sprite[5][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
		else
		switch(direction) {
		case LEFT:
			if(attacking)
				g.drawImage(sprite[3][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
			else
				g.drawImage(sprite[1][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
			break;
		case RIGHT:
			if(attacking)
				g.drawImage(sprite[2][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
			else
				g.drawImage(sprite[0][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
			break;
		case UP:
			if(attacking) {
				if(moveLeft)
					g.drawImage(sprite[3][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
				else
					g.drawImage(sprite[2][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
			}
			else {
				if(moveLeft)
					g.drawImage(sprite[1][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
				else
					g.drawImage(sprite[0][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
			}
			break;
		case DOWN:
			if(attacking) {
				if(moveLeft)
					g.drawImage(sprite[3][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
				else
					g.drawImage(sprite[2][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
			}
			else {
				if(moveLeft)
					g.drawImage(sprite[1][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
				else
					g.drawImage(sprite[0][animIndex], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
			}
			break;
		}
		
	}

	@Override
	public void update() {
		if(isDead)
			return;
		if(deathAnim)
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
            if(animIndex >= 8)
            	isDead = true;
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
	
	//checks and sets RedNinja's idle/chase situations
    public void setAction() {
    	
    	//reset directions
    	moveRight = false;
    	moveLeft = false;
    	moveUp = false;
    	moveDown = false;
		
    	int xDiff = (int)entityManager.getPlayer().getHitbox().getCenterX() - (int)hitbox.getCenterX(); // x-axis distance between Boss and player
    	int yDiff = (int)entityManager.getPlayer().getHitbox().getCenterY() - (int)hitbox.getCenterY(); // y-axis distance between Boss and player
    	
    	int posDiffX = startX - hitbox.x; // x-axis distance between Boss' starting position and initial position
    	int posDiffY = startY - hitbox.y; // y-axis distance between Boss' starting position and initial position
    	
    	if(Math.abs(xDiff) <= 450 && Math.abs(yDiff) <= 450) { // chase the player
    		
    		//set attacking
    		if(Math.abs(entityManager.getPlayer().getHitbox().getCenterX() - hitbox.getCenterX()) <= 280 &&
    			Math.abs(Math.abs(entityManager.getPlayer().getHitbox().getCenterY() - hitbox.getCenterY())) <= 280)
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
			
		}else if(!returning)
			attacking = false;
    }
    
    private void updateCooldowns() {
        //works only if player is always in attack range,
    	//if player gets in and out of the attack range repeatedly,
    	//Boss attacks without waiting to reset attack cooldown.
    	if(attacking) {
        	if(attackCoolDown<300) //attack
        		attackCoolDown++;
        	else if(attackCoolDown>=300 && attackCoolDown<600){ //attack on cooldown
        		attackCoolDown++;
        		attacking = false;
        	}else if(attackCoolDown==600) //reset attack cooldown
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
    	if(attacking) {
    		if(attackHitbox.contains(entityManager.getPlayer().getHitbox()) &&
    	    		entityManager.getPlayer().getInvincible() == false) {
    	    		entityManager.getPlayer().hitpoints--;
    	    		entityManager.getPlayer().setInvincible(true);
    	    	}else if(hitbox.contains(entityManager.getPlayer().getHitbox()) &&
    	            entityManager.getPlayer().getInvincible() == false) {
    	        	entityManager.getPlayer().hitpoints--;
    	        	entityManager.getPlayer().setInvincible(true);
    	        }
    	}
    	
    	if(hitbox.contains(entityManager.getPlayer().getHitbox()) && entityManager.getPlayer().getAttacking()) 
    		hitpoints--;
    	if(hitpoints == 0) {
    		deathAnim = true;
    		animTick = 0;
    		animIndex = 0;
    	}	
    }
}
