package world;

import java.awt.*;

public abstract class Level {
    protected int spawnX, spawnY;

    public abstract void draw(Graphics g);
    public abstract void update();

    public abstract boolean canMove(int x, int y);

    public abstract void playerInteract();
}
