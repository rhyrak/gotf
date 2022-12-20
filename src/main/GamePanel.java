package main;

import listeners.KeyboardListener;
import listeners.MouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    private Game game;
    private BufferedImage buffer;

    public GamePanel(Game game) {
        this.game = game;
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();
    }

    public void initListeners() {
        addKeyListener(new KeyboardListener(game));
        MouseListener mouseListener = new MouseListener(game);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
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
            buffer = (BufferedImage) createImage(size.width, size.height);

            Graphics temp = buffer.getGraphics();
            temp.setColor(Color.GRAY);
            temp.fillRect(0,0,size.width,size.height);
            temp.dispose();

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
        if (game.getGameState() != null)
            game.getGameState().draw(g);
        else
            g.drawString("LOADING",250,250);
    }

    public Game getGame() {
        return game;
    }

}
