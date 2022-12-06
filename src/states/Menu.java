package states;

import main.Game;
import ui.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLOutput;

public class Menu extends State {

    private MenuButton[] buttons;

    public Menu() {
        buttons = new MenuButton[3];
        System.out.println("Creating buttons");
        buttons[0] = new MenuButton(new Rectangle(Game.gameWidth/2 - 100, Game.gameHeight/2 - 160, 200, 80));
        buttons[1] = new MenuButton(new Rectangle(Game.gameWidth/2 - 100, Game.gameHeight/2 - 40, 200, 80));
        buttons[2] = new MenuButton(new Rectangle(Game.gameWidth/2 - 100, Game.gameHeight/2 + 80, 200, 80));
    }

    @Override
    public void draw(Graphics g) {
        g.drawString("MENU", 100, 100);
        for (MenuButton mb : buttons)
            mb.draw(g);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb: buttons)
            mb.setMouseOver(false);

        for (MenuButton mb: buttons)
            if (mb.getHitbox().contains(e.getX(), e.getY()))
                mb.setMouseOver(true);
    }
}
