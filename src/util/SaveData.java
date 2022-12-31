package util;

import entities.EntityManager;

import java.io.Serializable;

public class SaveData implements Serializable {
    public int playerX, playerY, XP, HP;
    public int floor; // 0 overworld, 1-2-3-4 dungeon floor 1-2-3-4
    public int saveID;

    // called when a new game is created
    public SaveData() {
        floor = 0;
        playerX = 2310;
        playerY = 1664;
        XP = 0;
        HP = 12;
    }
}
