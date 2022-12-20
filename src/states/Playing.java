package states;

import entities.Player;
import main.Game;
import util.SaveData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;

public class Playing extends State implements Serializable {

    private final Player player;
    private boolean paused = false;
    private SaveData saveData;

    public Playing(SaveData saveData) {
        if (saveData == null)
            this.saveData = new SaveData();
        else
            this.saveData = saveData;
        this.player = new Player(new Rectangle(saveData.playerX,saveData.playerY,64,64));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0,0, Game.gameWidth, Game.gameHeight);
        player.draw(g);
        if (paused) {
            g.setColor(new Color(0,0,0,100));
            g.fillRect(0,0, Game.gameWidth, Game.gameHeight);
        }
    }

    @Override
    public void update() {
        if (!paused) {
            player.update();
        }
    }

    private void saveAndExit() {
        try {
            FileOutputStream fos = new FileOutputStream("save1.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            saveData.playerX = player.getHitbox().x;
            saveData.playerY = player.getHitbox().y;
            oos.writeObject(saveData);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Save successful");
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.setMoveUp(true);
            case KeyEvent.VK_S -> player.setMoveDown(true);
            case KeyEvent.VK_D -> player.setMoveRight(true);
            case KeyEvent.VK_A -> player.setMoveLeft(true);
            case KeyEvent.VK_ESCAPE -> paused = !paused;
            case KeyEvent.VK_U -> saveAndExit();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.setMoveUp(false);
            case KeyEvent.VK_S -> player.setMoveDown(false);
            case KeyEvent.VK_D -> player.setMoveRight(false);
            case KeyEvent.VK_A -> player.setMoveLeft(false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        player.setAttacking(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        player.setAttacking(false);
    }
}
