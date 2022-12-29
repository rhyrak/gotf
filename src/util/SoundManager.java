package util;

import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

import static util.WeatherTime.raining;

public class SoundManager {
    public static Clip attack;
    public static Clip walking;
    public static Clip forest;
    public static Clip heal;
    public static Clip levelUp;
    public static Clip menu;
    public static Clip selectButton;
    public static Clip chop;
    public static Clip lifepot;
    public static Clip medipac;
    public static Clip rain;

    public SoundManager() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        menu = AudioSystem.getClip();
        menu.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/menu.wav")));


        walking = AudioSystem.getClip();
        walking.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/walking.wav")));

        forest = AudioSystem.getClip();
        forest.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/forest_bird.wav")));

        attack = AudioSystem.getClip();
        attack.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/sword_attack.wav")));

        chop = AudioSystem.getClip();
        chop.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/chop.wav")));


        heal = AudioSystem.getClip();
        heal.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/heal.wav")));

        lifepot = AudioSystem.getClip();
        lifepot.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/life_pot.wav")));

        medipac = AudioSystem.getClip();
        medipac.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/med_pac.wav")));

        levelUp = AudioSystem.getClip();
        levelUp.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/level_up.wav")));

        rain = AudioSystem.getClip();
        rain.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/rain.wav")));

        selectButton = AudioSystem.getClip();
        selectButton.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/select_button.wav")));


    }

    public static void MenuSound() {
        menu.setFramePosition(0);
        menu.start();
        menu.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void MenuSoundClose() {
        menu.stop();
    }

    public static void ForestSound() {
        forest.setFramePosition(0);
        forest.start();
        forest.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void ForestSoundClose() {
        forest.stop();
    }

    public static void HealSound() {
        heal.setFramePosition(0);
        heal.start();
    }

    public static void LifePotSound() {
        lifepot.setFramePosition(0);
        lifepot.start();
    }

    public static void MedPacSound() {
        medipac.setFramePosition(0);
        medipac.start();
    }

    public static void SelectButton() {
        selectButton.setFramePosition(0);
        selectButton.start();
    }

    public static void LevelUp() {
        levelUp.setFramePosition(0);
        levelUp.start();
    }

    public static void AttackSound() {
        attack.setFramePosition(0);
        attack.start();
    }

    public static void ChopSound() {
        chop.setFramePosition(0);
        chop.start();
    }

    public static void WalkForest() {
        walking.start();
        walking.loop(attack.LOOP_CONTINUOUSLY);
    }

    public static void WalkForestClose() {
        walking.stop();
    }

    public static void rainSound() {
        rain.setFramePosition(0);
        rain.start();
        rain.loop(Clip.LOOP_CONTINUOUSLY);
    }


    public static void Menu() {
        MenuSound();
    }

    public static void StartForest() {
        MenuSoundClose();
        ForestSound();
    }

    public static void Walking() {
        WalkForest();
    }

    public static void Stand() {
        WalkForestClose();
    }

    public static void Attack() {
        AttackSound();
    }

    public static void GetHealthItem() {
        HealSound();
    }

    public static void UseLifePot() {
        LifePotSound();
    }

    public static void UseMedPac() {
        MedPacSound();
    }

    public static void RainStart() {
        rainSound();
        ForestSoundClose();
    }

    public static void RainStop() {
        rain.stop();
        ForestSound();
    }

    public static void Pause() {
        Menu();
        RainStop();
        ForestSoundClose();
    }

    public static void Continue() {
        StartForest();
        if (raining)
            RainStart();
        else
            RainStop();
    }

    public static void MouseSelect() {
        SelectButton();
    }


}