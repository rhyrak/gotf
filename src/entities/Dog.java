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

public class Dog extends Entity {

    private BufferedImage[][] sprite;
    private boolean moveUp, moveDown, moveRight, moveLeft;
    private int animIndex, animTick;
    private Directions direction;
    private int camOffsetX, camOffsetY;
    private int speed;

    // constructor
    public Dog(EntityManager entityManager, Rectangle hitbox) {
        this.entityManager = entityManager;
        this.hitbox = hitbox;
        this.direction = Directions.LEFT;
        loadSprite();
        this.speed = 1;
    }

    private void loadSprite() {
        BufferedImage temp = AssetManager.getSprite(AssetManager.ANIMALS);
        sprite = new BufferedImage[4][4];
        //walk right
        sprite[0][0] = temp.getSubimage(0, 24 * 16, 16, 16);
        sprite[0][1] = temp.getSubimage(16, 24 * 16, 16, 16);
        sprite[0][2] = temp.getSubimage(32, 24 * 16, 16, 16);
        sprite[0][3] = temp.getSubimage(48, 24 * 16, 16, 16);
        //walk down
        sprite[1][0] = temp.getSubimage(0, 25 * 16, 16, 16);
        sprite[1][1] = temp.getSubimage(16, 25 * 16, 16, 16);
        sprite[1][2] = temp.getSubimage(32, 25 * 16, 16, 16);
        sprite[1][3] = temp.getSubimage(48, 25 * 16, 16, 16);
        //walk up
        sprite[2][0] = temp.getSubimage(0, 26 * 16, 16, 16);
        sprite[2][1] = temp.getSubimage(16, 26 * 16, 16, 16);
        sprite[2][2] = temp.getSubimage(32, 26 * 16, 16, 16);
        sprite[2][3] = temp.getSubimage(48, 26 * 16, 16, 16);
        //walk left
        temp = AssetManager.getSprite(AssetManager.ANIMALS_LEFT);
        sprite[3][0] = temp.getSubimage(15 * 16, 24 * 16, 16, 16);
        sprite[3][1] = temp.getSubimage(14 * 16, 24 * 16, 16, 16);
        sprite[3][2] = temp.getSubimage(13 * 16, 24 * 16, 16, 16);
        sprite[3][3] = temp.getSubimage(12 * 16, 24 * 16, 16, 16);
    }

    @Override
    public void draw(Graphics g) {

        int index;
        if (moveRight || moveLeft || moveUp || moveDown)
            index = animIndex;
        else
            index = 0;

        switch (direction) {
            case LEFT:
                g.drawImage(sprite[3][index], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                break;
            case RIGHT:
                g.drawImage(sprite[0][index], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                break;
            case UP:
                g.drawImage(sprite[2][index], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                break;
            case DOWN:
                g.drawImage(sprite[1][index], hitbox.x + camOffsetX, hitbox.y + camOffsetY, hitbox.width, hitbox.height, null);
                break;
        }

    }

    @Override
    public void update() {
        camOffsetX = Game.gameWidth / 2 - entityManager.getPlayer().getHitbox().x - entityManager.getPlayer().getHitbox().width / 2;
        camOffsetY = Game.gameHeight / 2 - entityManager.getPlayer().getHitbox().y - entityManager.getPlayer().getHitbox().height / 2;
        setAction();
        animate();
        move();

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

        //if (moveDown || moveUp||moveLeft||moveRight)

       // else

        if (moveUp)
            ySpeed -= speed;
        if (moveDown)
            ySpeed += speed;
        if (moveLeft)
            xSpeed -= speed;
        if (moveRight)
            xSpeed += speed;

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
    private void setAction() {

        //reset directions
        moveRight = false;
        moveLeft = false;
        moveUp = false;
        moveDown = false;

        int xDiff = (int) entityManager.getPlayer().getHitbox().getCenterX() - (int) hitbox.getCenterX(); // x-axis distance between RedNinja and player
        int yDiff = (int) entityManager.getPlayer().getHitbox().getCenterY() - (int) hitbox.getCenterY(); // y-axis distance between RedNinja and player

        if (Math.abs(xDiff) <= 75 && Math.abs(yDiff) <= 75)
            speed = 0;
        else
            speed = 1;

        if (speed == 0)
            return;
        //following
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
    }

}

