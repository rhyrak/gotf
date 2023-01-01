package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class AssetManager {

    public static final String BUTTONS = "/assets/buttons.png";
    public static final String WINDOWS = "/assets/windows.png";
    
    public static final String HEALTH_BAR_BG = "/assets/hud/background.png";
    public static final String HEALTH_BAR_RED = "/assets/hud/red.png";
    public static final String BLUE_BAR = "/assets/hud/blue.png";

    public static final String PLAY_BTN = "/assets/ui/menu/start_btn.png";
    public static final String SETTINGS_BTN = "/assets/ui/menu/settings_btn.png";
    public static final String EXIT_BTN = "/assets/ui/menu/quit_btn.png";
    public static final String MENU_BG = "/assets/ui/menu/menu.png";
    public static final String HEADER = "/assets/ui/menu/header.png";

    public static final String ESC_CONTINUE = "/assets/ui/escmenu/continue.png";
    public static final String ESC_BG = "/assets/ui/escmenu/pause-bg.png";
    public static final String ESC_EXIT = "/assets/ui/escmenu/save&exit.png";

    public static final String S_BUTTONS = "/assets/ui/settingsmenu/buttons_4x.png";
    public static final String GUI = "/assets/ui/settingsmenu/GUI_4x.png";
    public static final String SCROLL_BAR_1 = "/assets/ui/settingsmenu/scrollbars1.png";
    public static final String SCROLL_BAR_2 = "/assets/ui/settingsmenu/scrollbars2.png";

    public static final String PLAYER_SPRITE = "/assets/player/player-sprite.png";
    public static final String BIG_SWORD_V = "/assets/player/big-sword-v.png";
    public static final String BIG_SWORD_H = "/assets/player/big-sword-h.png";
    public static final String AXE_ONE_V = "/assets/player/axe1-v.png";
    public static final String AXE_ONE_H = "/assets/player/axe1-h.png";
    public static final String AXE_TWO_V = "/assets/player/axe2-v.png";
    public static final String AXE_TWO_H = "/assets/player/axe2-h.png";
    public static final String HEART = "/assets/hud/heart.png";
    public static final String ITEM_SLOT = "/assets/hud/item-slot.png";
    public static final String LIFE_POT = "/assets/items/life-pot.png";
    public static final String MEDIPACK = "/assets/items/medipack.png";
    public static final String DEF_SCROLL = "/assets/items/def-scroll.png";
    public static final String SHIELD = "/assets/world/particles/shield.png";
    
    public static final String ANIMALS = "/assets/player/spritesheet.png";
    public static final String ANIMALS_LEFT = "/assets/player/spritesheet-left.png";
    
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
    
    public static final String BOSS_WALK_RIGHT = "/assets/boss/idle2.png";
    public static final String BOSS_WALK_LEFT = "/assets/boss/idle3.png";
    public static final String BOSS_ATTACK_RIGHT = "/assets/boss/attacking.png";
    public static final String BOSS_ATTACK_LEFT = "/assets/boss/attacking-left.png";
    public static final String BOSS_DEATH_RIGHT = "/assets/boss/death.png";
    public static final String BOSS_DEATH_LEFT = "/assets/boss/death-left.png";
    
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
