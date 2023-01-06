package util;

import java.io.Serializable;

/**
 * A class which can be stored as a file to save player's progress
 *
 * @author Selcuk Gencay
 */
public class SaveData implements Serializable {
    /** player's info which needs to be saved */
    public int playerX, playerY, XP, HP, maxHP, level, floor;
    /** id of the saved game */
    public int saveID;

    /**
     * generates a new game
     */
    public SaveData() {
        floor = 0;
        playerX = 2310;
        playerY = 1664;
        XP = 0;
        HP = 12;
        maxHP = 12;
        level = 0;
    }
}
