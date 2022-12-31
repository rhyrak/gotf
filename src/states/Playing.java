package states;

import entities.EntityManager;
import entities.Player;
import main.Game;
import util.SaveData;
import util.SoundManager;
import util.WeatherTime;
import world.Dungeon;
import world.Level;
import world.Overworld;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;

public class Playing extends State implements Serializable {

    private Player player;
    private EntityManager entityManager;
    private boolean paused = false;
    private SaveData saveData;
    private Level level;
    private Color overlay = new Color(0, 0, 0, 100);
    private int rainTick;
    private int floorPrev;

    public Playing(SaveData saveData) {
        if (saveData == null)
            this.saveData = new SaveData();
        else
            this.saveData = saveData;
        initGame();
    }

    private void initGame() {
        this.player = new Player(new Rectangle(saveData.playerX, saveData.playerY, 64, 64));
        this.entityManager = new EntityManager(this, player);
        this.level = saveData.floor == 0 ? new Overworld(player, this) : new Dungeon(player, this);
        this.floorPrev = saveData.floor;
        player.setLevel(level);
    }

    @Override
    public void draw(Graphics g) {
        level.draw(g);
        entityManager.draw(g);
        player.draw(g);
        WeatherTime.draw(g);
        if (paused) {
            g.setColor(overlay);
            g.fillRect(0, 0, Game.gameWidth, Game.gameHeight);
        }
        if (Game.DEBUG_MODE) {
            g.setColor(overlay);
            g.fillRect(40, 40, 200, 300);
            g.setColor(Color.green);
            g.drawString("Plyr X: " + player.getHitbox().x + " Y: " + player.getHitbox().y, 45, 55);
            g.drawString("AHB X: " + player.getAttackHitbox().x + " Y: " + player.getAttackHitbox().y, 45, 80);
            g.drawString("Floor: " + saveData.floor, 45, 105);
        }
    }

    @Override
    public void update() {
        if (!paused) {
            checkSave();
            player.update();
            entityManager.update();
            level.update();
            toggleRain();
            WeatherTime.update();
        }
    }

    private void checkSave() {
        if (saveData.floor != floorPrev) {
            if (saveData.floor == 0) { // exit dungeon
                this.level = new Overworld(player, this);
                this.player.getHitbox().x = 1874;
                this.player.getHitbox().y = 2358;
                this.player.getMoveHitbox().x = 1876;
                this.player.getMoveHitbox().y = 2390;
            } else if (floorPrev == 0){ // enter dungeon
                this.level = new Dungeon(player, this);
            } else if (floorPrev < saveData.floor) {
                this.player.getHitbox().x = 900;
                this.player.getHitbox().y = 2550;
                this.player.getMoveHitbox().x = 902;
                this.player.getMoveHitbox().y = 2582;
            } else {
                this.player.getHitbox().x = 900;
                this.player.getHitbox().y = 576;
                this.player.getMoveHitbox().x = 902;
                this.player.getMoveHitbox().y = 608;
            }
            floorPrev = saveData.floor;
            player.setLevel(level);
        }
    }

    private void toggleRain() {
        if (saveData.floor != 0) {
            WeatherTime.raining = false;
            SoundManager.RainStop();
            rainTick = 0;
            return;
        }
        rainTick++;
        if (rainTick >= 2000) {
            if (Math.random() < 0.1)
                WeatherTime.toggleRain();
            rainTick = 0;
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
            case KeyEvent.VK_E -> level.playerInteract();
            case KeyEvent.VK_ESCAPE -> {
                paused = !paused;
                if (paused)
                    SoundManager.Pause();
                else
                    SoundManager.Continue();
            }
            case KeyEvent.VK_U -> saveAndExit();
            case KeyEvent.VK_1 -> player.useItem(1);
            case KeyEvent.VK_2 -> player.useItem(2);
            case KeyEvent.VK_3 -> player.useItem(3);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_D   )
        //  SoundManager.Stand();
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
            case MouseEvent.BUTTON1 -> player.setAttacking();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public SaveData getSaveData() {
        return saveData;
    }
}
