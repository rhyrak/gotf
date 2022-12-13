package entities;

import main.Game;
import util.Directions;

import java.awt.*;

import static util.Directions.*;

public class Player extends Entity {

    private boolean moveUp, moveDown, moveRight, moveLeft;
    private int playerSpeed = 2;
    private Rectangle attackHitbox;
    private boolean attacking;
    private Directions direction;

    public Player(Rectangle hitbox) {
        this.hitbox = hitbox;
        this.attackHitbox = new Rectangle(hitbox.x + hitbox.width, hitbox.y, hitbox.width, hitbox.height);
        this.direction = RIGHT;
    }

    public void update() {
        move();
        updateAttackHitbox();
    }

    private void move() {
        int xSpeed = 0, ySpeed = 0;
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

    public void draw(Graphics g) {
        if (Game.DEBUG_MODE) {
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
            if (attacking)
                g.setColor(Color.RED);
            else
                g.setColor(Color.GREEN);
            g.drawRect(attackHitbox.x, attackHitbox.y, attackHitbox.width, attackHitbox.height);
        }
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
        direction = UP;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
        direction = DOWN;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
        direction = RIGHT;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
        direction = LEFT;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
