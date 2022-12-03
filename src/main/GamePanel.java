package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;
    private Image buffer;

    public GamePanel(Game game) {
        this.game = game;
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // get the size of this panel
        Dimension size = getSize();

        // if the buffer is not initialized properly
        if (
                buffer == null ||
                        buffer.getWidth(this) != size.width ||
                        buffer.getHeight(this) != size.height
        ) {
            // initialize the buffer
            buffer = createImage(size.width, size.height);

            // FIXME
            // option2: make sure the player doesn't spawn close to the level's border

        }

        if (buffer == null) {
            // if the buffer is still null, paint to the panel
            paintGame(g);
        } else {
            // get the Graphics object for painting to the buffer
            Graphics g2 = buffer.getGraphics();


            // paint the game to the buffer
            paintGame(g2);
            g2.dispose();

            // draw the buffer to the panel
            g.drawImage(buffer, 0, 0, null);
        }

    }

    private void paintGame(Graphics g) {
        game.getGameState().draw(g);
    }

    public Game getGame() {
        return game;
    }

}
