package util;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The class which controls rain and time
 *
 * @author Selcuk Gencay
 */
public abstract class WeatherTime {

    private static BufferedImage[] rain = new BufferedImage[3];
    private static BufferedImage[] rainOnFloor = new BufferedImage[3];
    private static int animIndx = 0, animTick = 0;
    private static int rainX = 0, rainY = 0, xShift = 0, yShift = 0;
    /** flag for the weather */
    public static boolean raining = false;

    static {
        BufferedImage temp = AssetManager.getSprite(AssetManager.RAIN_P);
        rain[0] = temp.getSubimage(0, 0, 8, 8);
        rain[1] = temp.getSubimage(8, 0, 8, 8);
        rain[2] = temp.getSubimage(16, 0, 8, 8);
        temp = AssetManager.getSprite(AssetManager.RAIN_FLOOR);
        rainOnFloor[0] = temp.getSubimage(0, 0, 8, 8);
        rainOnFloor[1] = temp.getSubimage(8, 0, 8, 8);
        rainOnFloor[2] = temp.getSubimage(16, 0, 8, 8);
    }

    /**
     * updates rain position if it is raining
     */
    public static void update() {
        if (raining) {
            animTick++;
            rainY++;
            rainX--;
            if (rainY >= 32)
                rainY = 0;
            if (rainX <= -64)
                rainX = 0;
            if (animTick >= 66) {
                animTick = 0;
                animIndx++;
                if (animIndx >= 3) {
                    animIndx = 0;
                    xShift = (int) (Math.random() * 8);
                    yShift = (int) (Math.random() * 8);
                }
            }
        }
    }

    /**
     * Draws the rain if it is raining
     *
     * @param g graphics context for drawing
     */
    public static void draw(Graphics g) {
        if (raining)
            for (int i = 0; i < Game.gameWidth / 64; i++)
                for (int j = 0; j < Game.gameHeight / 32; j++)
                    if (Math.random() < 0.15)
                        g.drawImage(rain[animIndx], i * 64 + xShift + rainX, j * 32 + rainY + yShift, 16, 16, null);
    }

    /**
     * start/stop the rain
     */
    public static void toggleRain() {
        raining = !raining;
        if (raining)
            SoundManager.RainStart();
        else
            SoundManager.RainStop();
    }
}
