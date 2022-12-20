package util;

import java.io.Serializable;

public class SaveData implements Serializable {
    public int playerX, playerY;

    public SaveData() {
        playerX = 150;
        playerY = 150;
    }
}
