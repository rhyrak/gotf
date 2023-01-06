package listeners;

import main.Game;
import util.SoundManager;
import util.WeatherTime;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * When added to the gamePanel, KeyboardListener takes inputs and passes them to the current State's methods
 *
 * @author Selcuk Gencay
 */
public class KeyboardListener implements KeyListener {

    private Game game;

    /**
     * @param game Game is used for accessing the current state
     */
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
        else if (e.getKeyCode() == KeyEvent.VK_9) {
            WeatherTime.raining = !WeatherTime.raining;
            if (WeatherTime.raining)
                SoundManager.RainStart();
            else
                SoundManager.RainStop();
        } else
            game.getGameState().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        game.getGameState().keyReleased(e);
    }
}
