package listeners;

import main.Game;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * When added to the gamePanel, MouseListener takes mouse inputs and passes them to the current State's methods
 *
 * @author Selcuk Gencay
 */
public class MouseListener implements MouseInputListener, MouseMotionListener {

    private final Game game;
    /** instance of this class */
    private static MouseListener instance;

    /**
     * @param game Game is used for accessing the current state
     * @return the MouseListener instance
     */
    public static MouseListener getInstance(Game game) {
        if (instance == null)
            instance = new MouseListener(game);
        return instance;
    }

    private MouseListener(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.getGameState().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        game.getGameState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        game.getGameState().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        game.getGameState().mouseMoved(e);
    }
}
