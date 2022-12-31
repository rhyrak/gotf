package states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import util.AssetManager;

public class Settings extends State {

    private BufferedImage window;
    private BufferedImage closeBtn;
    private BufferedImage soundBtn;
    private BufferedImage scrollBar;
    private BufferedImage scrollBtn;
    private Rectangle closeRect = new Rectangle(1275, 75, 64, 64);
    private Rectangle barRect = new Rectangle(Game.gameWidth / 2, Game.gameHeight / 2 + 10, 144, 50);
    private Rectangle scrollBtnRect = new Rectangle(Game.gameWidth / 2, Game.gameHeight / 2 + 5, 48, 64);
    private boolean pressed;
    private Game game;

    public Settings(Game game) {
        this.game = game;
        loadSprite();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(window, 0, 0, Game.gameWidth, Game.gameHeight, null);
        g.drawImage(closeBtn, 1275, 75, 64, 64, null);
        g.drawImage(soundBtn, Game.gameWidth / 2 - 50, Game.gameHeight / 2, 48, 64, null);
        g.drawImage(scrollBar, barRect.x, barRect.y, barRect.width, barRect.height, null);
        g.drawImage(scrollBtn, scrollBtnRect.x, scrollBtnRect.y, 48, 64, null);
        drawHitbox(g);
    }

    private void drawHitbox(Graphics g) {
        if (Game.DEBUG_MODE) {
            g.setColor(Color.MAGENTA);
            g.drawRect(closeRect.x, closeRect.y, closeRect.width, closeRect.height);
            g.setColor(Color.GREEN);
            g.drawRect(barRect.x, barRect.y, barRect.width, barRect.height);
            g.setColor(Color.RED);
            g.drawRect(scrollBtnRect.x, scrollBtnRect.y, scrollBtnRect.width, scrollBtnRect.height);
        }
    }

    public void loadSprite() {
        BufferedImage temp = AssetManager.getSprite(AssetManager.GUI);
        window = temp.getSubimage(0, 0, 64, 64);
        temp = AssetManager.getSprite(AssetManager.S_BUTTONS);
        closeBtn = temp.getSubimage(352, 0, 32, 32);
        soundBtn = temp.getSubimage(320, 32, 12, 32);
        temp = AssetManager.getSprite(AssetManager.SCROLL_BAR_1);
        scrollBar = temp.getSubimage(16, 32, 48, 16);
        scrollBtn = temp.getSubimage(0, 0, 16, 16);
    }

    @Override
    public void update() {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (pressed) {
            if (isIn(e, scrollBtnRect) || isIn(e, barRect)) {
                scrollBtnRect.x = e.getX() - scrollBtnRect.width / 2;
                if (scrollBtnRect.x < barRect.x)
                    scrollBtnRect.x = barRect.x;
                if (scrollBtnRect.x + scrollBtnRect.width > barRect.x + barRect.width)
                    scrollBtnRect.x = barRect.x + barRect.width - scrollBtnRect.width;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isIn(e, closeRect))
            game.changeGameState(GameState.MENU);
        else if (isIn(e, barRect) || isIn(e, scrollBtnRect))
            pressed = !pressed;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    private boolean isIn(MouseEvent e, Rectangle rect) {
        return rect.contains(e.getX(), e.getY());
    }
}
