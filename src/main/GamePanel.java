package main;

import listeners.KeyboardListener;
import listeners.MouseListener;
import util.AssetManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The panel where the game will be drawn
 *
 * @author Selcuk Gencay
 */
public class GamePanel extends JPanel {

    private final Game game;
    private BufferedImage buffer;

    /**
     * Generates a new GamePanel then sets focus to itself for the mouse and keyboard listeners.
     *
     * @param game Game is used for accessing the current state's draw method
     */
    public GamePanel(Game game) {
        this.game = game;
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();
    }

    /**
     * generates and adds mouse and keyboard listeners
     */
    public void initListeners() {
        addKeyListener(new KeyboardListener(game));
        addMouseListener(MouseListener.getInstance(game));
        addMouseMotionListener(MouseListener.getInstance(game));
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
            temp.fillRect(0, 0, size.width, size.height);
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

    /**
     * If gameState is null, the game is changing state, draws the background image as a loading screen.
     * If not, calls the gameState's draw method with given graphics context
     *
     * @param g graphics context of the target
     */
    private void paintGame(Graphics g) {
        if (game.getGameState() != null)
            game.getGameState().draw(g);
        else
            g.drawImage(AssetManager.getSprite(AssetManager.MENU_BG), 0, 0, Game.gameWidth, Game.gameHeight, null);
    }
}
