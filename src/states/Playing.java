package states;

import entities.Player;
import main.Game;
import util.SaveData;
import world.Level;
import world.Overworld;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;

public class Playing extends State implements Serializable {

    private Player player;
    private boolean paused = false;
    private SaveData saveData;
    private Level level;
    private Color overlay = new Color(0,0,0,100);

    public Playing(SaveData saveData) {
        if (saveData == null)
            this.saveData = new SaveData();
        else
            this.saveData = saveData;
        initGame();
    }

    private void initGame() {
        this.player = new Player(new Rectangle(saveData.playerX,saveData.playerY,64,64));
        this.level = new Overworld(player);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0,0, Game.gameWidth, Game.gameHeight);
        level.draw(g);
        player.draw(g);
        if (paused) {
            g.setColor(overlay);
            g.fillRect(0,0, Game.gameWidth, Game.gameHeight);
        }
        if (Game.DEBUG_MODE) {
            g.setColor(overlay);
            g.fillRect(40,40,200,300);
            g.setColor(Color.green);
            g.drawString("Player X: " + player.getHitbox().x + " Y: " + player.getHitbox().y, 45,55);
            g.drawString("AHB X: " + player.getAttackHitbox().x + " Y: " + player.getAttackHitbox().y, 45,75);
        }
    }

    @Override
    public void update() {
        if (!paused) {
            player.update();
            level.update();
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
        switch (e.getButton()) {
            case MouseEvent.BUTTON1 -> player.setAttacking(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1 -> player.setAttacking(false);
        }
    }
}
