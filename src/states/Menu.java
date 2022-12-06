package states;

import main.Game;
import ui.MenuButton;
import util.AssetManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State {

    private MenuButton[] buttons;
    private Game game;

    public Menu(Game game) {
        this.game = game;
        initButtons();
    }

    private void initButtons() {
        buttons = new MenuButton[3];

        buttons[0] = new MenuButton(
                new Rectangle(Game.gameWidth / 2 - 100, Game.gameHeight / 2 - 160, 200, 80),
                AssetManager.PLAY_BTN
        );
        buttons[0].setAction(() -> game.changeGameState(GameState.PLAYING));
        buttons[1] = new MenuButton(
                new Rectangle(Game.gameWidth / 2 - 100, Game.gameHeight / 2 - 40, 200, 80),
                AssetManager.SETTINGS_BTN
        );
        buttons[2] = new MenuButton(
                new Rectangle(Game.gameWidth / 2 - 100, Game.gameHeight / 2 + 80, 200, 80),
                AssetManager.EXIT_BTN
        );
        buttons[2].setAction(() -> game.changeGameState(GameState.EXIT));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(240,217,181));
        g.fillRect(0, 0, Game.gameWidth, Game.gameHeight);
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
        for (MenuButton mb : buttons)
            mb.setMouseOver(false);

        for (MenuButton mb : buttons)
            if (mb.getHitbox().contains(e.getX(), e.getY()))
                mb.setMouseOver(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (MenuButton mb : buttons)
            if (mb.isMouseOver())
                mb.runAction();
    }
}
