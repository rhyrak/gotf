package states;

import entities.EntityManager;
import entities.Player;
import main.Game;
import util.AssetManager;
import util.SaveData;
import util.SoundManager;
import util.WeatherTime;
import world.Dungeon;
import world.Level;
import world.Overworld;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class Playing extends State implements Serializable {

    private Player player;
    private EntityManager entityManager;
    private boolean paused = false;
    private static SaveData saveData;
    private Level level;
    private Color overlay = new Color(0, 0, 0, 100);
    private int rainTick;
    private int floorPrev;
    private Rectangle continueBtn, exitBtn;
    private BufferedImage[] continueImg, exitImg;
    private int continueIndex = 0, exitIndex = 0;
    private int mouseX, mouseY;
    private BufferedImage pauseBg;

    public Playing(SaveData saveData) {
        if (saveData == null)
            this.saveData = new SaveData();
        else
            this.saveData = saveData;
        initGame();
        initPause();
    }

    private void initPause() {
        continueBtn = new Rectangle(Game.gameWidth / 2 - 75, 100 + Game.gameHeight / 4, 150, 50);
        exitBtn = new Rectangle(Game.gameWidth / 2 - 75, 75 + Game.gameHeight / 2, 150, 50);
        pauseBg = AssetManager.getSprite(AssetManager.ESC_BG);
        continueImg = new BufferedImage[2];
        continueImg[0] = AssetManager.getSprite(AssetManager.ESC_CONTINUE).getSubimage(0, 0, 311, 76);
        continueImg[1] = AssetManager.getSprite(AssetManager.ESC_CONTINUE).getSubimage(0, 122, 311, 76);
        exitImg = new BufferedImage[2];
        exitImg[0] = AssetManager.getSprite(AssetManager.ESC_EXIT).getSubimage(0, 0, 311, 76);
        exitImg[1] = AssetManager.getSprite(AssetManager.ESC_EXIT).getSubimage(0, 122, 311, 76);
    }

    private void initGame() {
        player = new Player(saveData);
        entityManager = new EntityManager(this, player);
        level = saveData.floor == 0 ? new Overworld(player, this) : new Dungeon(player, this);
        floorPrev = saveData.floor;
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
            g.drawImage(pauseBg, Game.gameWidth / 2 - 150, Game.gameHeight / 4 + 25, 300, 400, null);
            g.drawImage(continueImg[continueIndex], continueBtn.x, continueBtn.y, continueBtn.width, continueBtn.height, null);
            g.drawImage(exitImg[exitIndex], exitBtn.x, exitBtn.y, exitBtn.width, exitBtn.height, null);
        }
        if (Game.DEBUG_MODE) {
            g.setColor(overlay);
            g.fillRect(40, 40, 200, 300);
            g.setColor(Color.green);
            g.drawString("Plyr X: " + player.getHitbox().x + " Y: " + player.getHitbox().y, 45, 55);
            g.drawString("AHB X: " + player.getAttackHitbox().x + " Y: " + player.getAttackHitbox().y, 45, 80);
            g.drawString("Floor: " + saveData.floor, 45, 105);
            if (paused) {
                g.setColor(Color.MAGENTA);
                g.drawRect(continueBtn.x, continueBtn.y, continueBtn.width, continueBtn.height);
                g.drawRect(exitBtn.x, exitBtn.y, exitBtn.width, exitBtn.height);
            }
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
        } else {
            continueIndex = 0;
            exitIndex = 0;
            if (continueBtn.contains(mouseX,mouseY))
                continueIndex = 1;
            else if (exitBtn.contains(mouseX,mouseY))
                exitIndex = 1;
        }
    }

    private void checkSave() {
        if (saveData.floor != floorPrev) {
            if (saveData.floor == 0) { // exit dungeon
                level = new Overworld(player, this);
                player.getHitbox().x = 1874;
                player.getHitbox().y = 2358;
                player.getMoveHitbox().x = 1876;
                player.getMoveHitbox().y = 2390;
            } else if (floorPrev == 0) { // enter dungeon
                level = new Dungeon(player, this);
            } else if (floorPrev < saveData.floor) {
                player.getHitbox().x = 900;
                player.getHitbox().y = 2550;
                player.getMoveHitbox().x = 902;
                player.getMoveHitbox().y = 2582;
            } else {
                player.getHitbox().x = 900;
                player.getHitbox().y = 576;
                player.getMoveHitbox().x = 902;
                player.getMoveHitbox().y = 608;
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
            case KeyEvent.VK_E -> {
                level.playerInteract();
                System.out.println(getSaveData().floor);
                switch (getSaveData().floor){
                    case 0-> SoundManager.StartForest();
                    case 1-> SoundManager.DungeonEnter();
                    case 2-> SoundManager.DungeonFloor2();
                    case 3-> SoundManager.DungeonFloor3();
                    case 4-> SoundManager.DungeonFloor4();
                }
            }
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.setMoveUp(false);
            case KeyEvent.VK_S -> player.setMoveDown(false);
            case KeyEvent.VK_D -> player.setMoveRight(false);
            case KeyEvent.VK_A -> player.setMoveLeft(false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (paused) {
            if (continueBtn.contains(e.getX(),e.getY()))
                paused = false;
            else if (exitBtn.contains(e.getX(), e.getY()))
                saveAndExit();
        }
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

    public static SaveData getSaveData() {
        return saveData;
    }
}
