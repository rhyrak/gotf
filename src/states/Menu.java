package states;

import java.awt.*;

public class Menu extends State {

    @Override
    public void draw(Graphics g) {
        g.drawString("MENU", 100, 100);
    }

    @Override
    public void update() {

    }
}
