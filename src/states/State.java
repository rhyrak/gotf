package states;

import java.awt.*;

public abstract class State {

    abstract public void draw(Graphics g);
    abstract public void update();

}
