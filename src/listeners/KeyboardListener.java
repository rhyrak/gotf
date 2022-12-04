package listeners;

import main.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        game.getGameState().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        game.getGameState().keyReleased(e);
    }
}
