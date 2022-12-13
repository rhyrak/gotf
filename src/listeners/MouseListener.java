package listeners;

import main.Game;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListener implements MouseInputListener, MouseMotionListener {

    private Game game;

    public MouseListener(Game game) {
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
