package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class AssetManager {

    public static final String BUTTONS = "/assets/buttons.png";
    public static final String WINDOWS = "/assets/windows.png";

    public static final String PLAY_BTN = "/assets/ui/menu/start_btn.png";
    public static final String SETTINGS_BTN = "/assets/ui/menu/settings_btn.png";
    public static final String EXIT_BTN = "/assets/ui/menu/quit_btn.png";

    public static final String PLAYER_SPRITE = "/assets/player/player-sprite.png";
    public static final String BIG_SWORD_V = "/assets/player/big-sword-v.png";
    public static final String BIG_SWORD_H = "/assets/player/big-sword-h.png";
    public static final String HEART = "/assets/hud/heart.png";
    public static final String ITEM_SLOT = "/assets/hud/item-slot.png";
    public static final String LIFE_POT = "/assets/items/life-pot.png";
    public static final String MEDIPACK = "/assets/items/medipack.png";

    public static final String LIZARD = "/assets/monsters/lizard.png";
    public static final String RED_NINJA = "/assets/monsters/red-ninja.png";
    public static final String CAVEGIRL = "/assets/monsters/cavegirl.png";
    public static final String CAVEMAN = "/assets/monsters/Caveman.png";
    public static final String SKELETON = "/assets/monsters/skeleton.png";
    public static final String CLUB_V = "/assets/weapons/club_v.png";
    public static final String CLUB_H = "/assets/weapons/club_h.png";
    public static final String BONE_V = "/assets/weapons/bone_v.png";
    public static final String BONE_H = "/assets/weapons/bone_h.png";
    public static final String KATANA_V = "/assets/weapons/katana_v.png";
    public static final String KATANA_H = "/assets/weapons/katana_h.png";

    public static final String RAIN_P = "/assets/world/particles/rain.png";
    public static final String GRASS_P = "/assets/world/particles/grass.png";
    public static final String RAIN_FLOOR = "/assets/world/particles/rain-on-floor.png";

    public static final String FLOOR1_TS = "/assets/world/tilesets/first-floor.png";
    public static final String FLOOR2_TS = "/assets/world/tilesets/second-floor.png";
    public static final String FLOOR3_TS = "/assets/world/tilesets/third-floor.png";
    public static final String FLOOR4_TS = "/assets/world/tilesets/fourth-floor.png";
    public static final String FLOOR5_TS = "/assets/world/tilesets/fifth-floor.png";
    public static final String FLOOR6_TS = "/assets/world/tilesets/sixth-floor.png";
    public static final String FLOOR7_TS = "/assets/world/tilesets/seventh-floor.png";

    public static BufferedImage getSprite(String filePath) {
        BufferedImage image = null;
        InputStream is = AssetManager.class.getResourceAsStream(filePath);
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

}
