package util;

import java.io.Serializable;

public class SaveData implements Serializable {
    public int playerX, playerY;
    public int floor; // 0 overworld, 1-2-3-4 dungeon floor 1-2-3-4

    public SaveData() {
        floor = 0;
        playerX = 150;
        playerY = 150;

    }
}
