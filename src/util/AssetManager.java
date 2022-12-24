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

    public static final String RAIN_P = "/assets/world/particles/rain.png";
    public static final String GRASS_P = "/assets/world/particles/grass.png";
    public static final String RAIN_FLOOR = "/assets/world/particles/rain-on-floor.png";

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
