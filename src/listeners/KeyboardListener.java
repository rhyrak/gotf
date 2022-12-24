package listeners;

import main.Game;
import util.WeatherTime;

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
        if (e.getKeyCode() == KeyEvent.VK_0)
            Game.toggleDebug();
        else if (e.getKeyCode() == KeyEvent.VK_9)
            WeatherTime.raining = !WeatherTime.raining;
        else
            game.getGameState().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        game.getGameState().keyReleased(e);
    }
}
