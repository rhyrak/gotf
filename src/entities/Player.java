package entities;

import java.awt.*;

public class Player extends Entity {

    private boolean moveUp, moveDown, moveRight, moveLeft;
    private int playerSpeed = 2;

    public Player(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public void update() {

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

    public void draw(Graphics g) {
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
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
}
