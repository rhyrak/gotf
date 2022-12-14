package states;

import main.Game;
import ui.MenuButton;
import util.AssetManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Initial state which has buttons for entering the game and settings or exiting the game
 *
 * @author Selcuk Gencay
 */
public class Menu extends State {

    private MenuButton[] buttons;
    private BufferedImage bg;
    private BufferedImage header;
    private Game game;

    /**
     * Inits the menu buttons
     *
     * @param game for changing the state
     */
    public Menu(Game game) {
        this.game = game;
        initButtons();
    }

    private void initButtons() {
        bg = AssetManager.getSprite(AssetManager.MENU_BG);
        header = AssetManager.getSprite(AssetManager.HEADER);
        buttons = new MenuButton[3];

        buttons[0] = new MenuButton(
                new Rectangle(Game.gameWidth / 2 - 348/4, Game.gameHeight / 2 + 20, 348/2, 170/2),
                AssetManager.PLAY_BTN
        );
        buttons[0].setAction(() -> game.changeGameState(GameState.PLAYING));
        buttons[1] = new MenuButton(
                new Rectangle(Game.gameWidth / 2 - 348/4, Game.gameHeight / 2 + 120, 348/2, 170/2),
                AssetManager.SETTINGS_BTN
        );
        buttons[1].setAction(() -> game.changeGameState(GameState.SETTINGS));
        buttons[2] = new MenuButton(
                new Rectangle(Game.gameWidth / 2 - 348/4, Game.gameHeight / 2 + 220, 348/2, 170/2),
                AssetManager.EXIT_BTN
        );
        buttons[2].setAction(() -> game.changeGameState(GameState.EXIT));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bg,0,0,Game.gameWidth,Game.gameHeight,null);
        g.drawImage(header, Game.gameWidth/3, (int) (Game.gameHeight/3.5), Game.gameWidth/3, 100,null);
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
                mb.setClicked(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
