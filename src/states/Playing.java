package states;

import java.awt.*;

public class Playing extends State {

    @Override
    public void draw(Graphics g) {
        g.drawString("PLAYING",100,100);
    }

    @Override
    public void update() {

    }
}
