package util;

import states.Settings;

import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

import static util.WeatherTime.raining;

public class SoundManager {
    public static Clip attack;
    public static Clip walkingForest;
    public static Clip forest;
    public static Clip heal;
    public static Clip levelUp;
    public static Clip menu;
    public static Clip selectButton;
    public static Clip chop;
    public static Clip lifepot;
    public static Clip medipac;
    public static Clip rain;
    public static Clip walkingDungeon;
    public static Clip dungeonfloor1;
    public static Clip dungeonfloor2;
    public static Clip dungeonfloor3;
    public static Clip dungeonfloor4;
    public static Clip pain;
    public static Clip ninjadead;
    public static Clip bossAttack;
    public static Clip bossDead;
    public static Clip ninjaAttack;
    public static Clip dogSound;
    public static Clip cavegirlAttack;
    public static Clip cavegirlDead;
    public static Clip cavemanAttack;
    public static Clip cavemanDead;
    public static Clip shield;
    public static Clip skeletonAttack;
    public static Clip skeletonDead;
    public static FloatControl musıc;
    public static FloatControl sound;

    public SoundManager() throws UnsupportedAudioFileException, IOException, LineUnavailableException {


        menu = AudioSystem.getClip();
        menu.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/menu.wav")));
        musıc = (FloatControl) menu.getControl(FloatControl.Type.MASTER_GAIN);

        walkingForest = AudioSystem.getClip();
        walkingForest.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/walkForest.wav")));

        walkingDungeon = AudioSystem.getClip();
        walkingDungeon.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/walkDungeon.wav")));

        forest = AudioSystem.getClip();
        forest.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/forest_bird.wav")));
        sound = (FloatControl) forest.getControl(FloatControl.Type.MASTER_GAIN);

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
        levelUp.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/levelUp.wav")));

        rain = AudioSystem.getClip();
        rain.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/rain.wav")));

        selectButton = AudioSystem.getClip();
        selectButton.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/select_button.wav")));

        dungeonfloor1 = AudioSystem.getClip();
        dungeonfloor1.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/floor1.wav")));

        dungeonfloor2 = AudioSystem.getClip();
        dungeonfloor2.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/floor3.wav")));

        dungeonfloor3 = AudioSystem.getClip();
        dungeonfloor3.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/floor2.wav")));

        dungeonfloor4 = AudioSystem.getClip();
        dungeonfloor4.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/floor4.wav")));

        ninjadead = AudioSystem.getClip();
        ninjadead.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/ninjaDead.wav")));

        pain = AudioSystem.getClip();
        pain.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/pain.wav")));

        bossAttack = AudioSystem.getClip();
        bossAttack.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/bossAttack.wav")));

        bossDead = AudioSystem.getClip();
        bossDead.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/bossDead.wav")));

        ninjaAttack = AudioSystem.getClip();
        ninjaAttack.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/ninjaAttack.wav")));

        dogSound = AudioSystem.getClip();
        dogSound.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/dogSound.wav")));


        cavegirlAttack = AudioSystem.getClip();
        cavegirlAttack.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/cavegirlAttack.wav")));

        cavegirlDead = AudioSystem.getClip();
        cavegirlDead.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/cavegirlDead.wav")));

        cavemanAttack = AudioSystem.getClip();
        cavemanAttack.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/cavemanAttack.wav")));

        cavemanDead = AudioSystem.getClip();
        cavemanDead.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/cavemanDead.wav")));

        skeletonAttack = AudioSystem.getClip();
        skeletonAttack.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/skeletonAttack.wav")));

        skeletonDead = AudioSystem.getClip();
        skeletonDead.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/skeletonDead.wav")));

        shield = AudioSystem.getClip();
        shield.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/shield.wav")));

    }

    public static void MenuSound() {
        menu.setFramePosition(0);
        menu.start();
        menu.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public static void shieldSound() {
        shield.loop(6);
        shield.setFramePosition(0);
        shield.start();

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

    public static void dungeonFloor1() {
        dungeonfloor1.setFramePosition(0);
        dungeonfloor1.start();
        dungeonfloor1.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void dungeonFloor1Close() {
        dungeonfloor1.stop();
    }

    public static void dungeonFloor2() {
        dungeonfloor2.setFramePosition(0);
        dungeonfloor2.start();
        dungeonfloor2.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void dungeonFloor2Close() {
        dungeonfloor2.stop();
    }

    public static void dungeonFloor3() {
        dungeonfloor3.setFramePosition(0);
        dungeonfloor3.start();
        dungeonfloor3.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void dungeonFloor3Close() {
        dungeonfloor3.stop();
    }

    public static void dungeonFloor4() {
        dungeonfloor4.setFramePosition(0);
        dungeonfloor4.start();
        dungeonfloor4.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void dungeonFloor4Close() {
        dungeonfloor4.stop();
    }

    public static void DogSound() {
        dogSound.setFramePosition(0);
        dogSound.start();
    }

    public static void HealSound() {
        heal.setFramePosition(0);
        heal.start();
    }

    public static void ninjaDeadSound() {
        ninjadead.setFramePosition(0);
        ninjadead.start();
    }

    public static void bossAttackSound() {
        bossAttack.setFramePosition(0);
        bossAttack.start();

    }

    public static void ninjaAttackSound() {
        ninjaAttack.setFramePosition(0);
        ninjaAttack.start();
    }

    public static void skeletonAttackSound() {
        skeletonAttack.setFramePosition(0);
        skeletonAttack.start();
    }

    public static void cavegirlAttackSound() {
        cavegirlAttack.setFramePosition(0);
        cavegirlAttack.start();
    }

    public static void cavemanAttackSound() {
        cavemanAttack.setFramePosition(0);
        cavemanAttack.start();
    }

    public static void cavegirlDeadSound() {
        cavegirlDead.setFramePosition(0);
        cavegirlDead.start();
    }

    public static void cavemanDeadSound() {
        cavemanDead.setFramePosition(0);
        cavemanDead.start();
    }

    public static void skeletonDeadSound() {
        skeletonDead.setFramePosition(0);
        skeletonDead.start();
    }

    public static void bossDeadSound() {
        bossDead.setFramePosition(0);
        bossDead.start();
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

    public static void levelup() {
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

    public static void painSound() {
        pain.setFramePosition(0);
        pain.start();
    }

    public static void WalkForest() {
        walkingForest.start();
        walkingForest.loop(attack.LOOP_CONTINUOUSLY);
    }

    public static void WalkDungeon() {
        walkingDungeon.start();
        walkingDungeon.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void WalkingDungeonClose() {
        walkingDungeon.stop();
    }

    public static void WalkForestClose() {
        walkingForest.stop();
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
        dungeonFloor1Close();
        MenuSoundClose();
        ForestSound();
    }

    public static void DungeonEnter() {
        dungeonFloor1();
        dungeonFloor2Close();
        dungeonFloor3Close();
        dungeonFloor4Close();
        ForestSoundClose();
    }

    public static void DungeonFloor2() {
        dungeonFloor2();
        dungeonFloor1Close();
        dungeonFloor3Close();
        dungeonFloor4Close();
        ForestSoundClose();
    }

    public static void DungeonFloor3() {
        dungeonFloor3();
        dungeonFloor1Close();
        dungeonFloor2Close();
        dungeonFloor4Close();
        ForestSoundClose();
    }

    public static void DungeonFloor4() {
        dungeonFloor4();
        dungeonFloor1Close();
        dungeonFloor2Close();
        dungeonFloor3Close();
        ForestSoundClose();
    }

    public static void NinjaDead() {
        ninjaDeadSound();
    }

    public static void BossDead() {
        bossDeadSound();
    }

    public static void CavegirlDead() {
        cavegirlDeadSound();
    }

    public static void SkeletonDead() {
        skeletonDeadSound();
    }

    public static void BossAttack() {
        painSound();
        bossAttackSound();
    }

    public static void CavegirlAttack() {
        painSound();
        cavegirlAttackSound();
    }

    public static void CavemanAttack() {
        painSound();
        cavemanAttackSound();
    }

    public static void CavemanDead() {
        cavemanDeadSound();
    }

    public static void Walkingforest() {
        WalkingDungeonClose();
        WalkForest();
    }

    public static void WalkingDungeon() {
        WalkForestClose();
        WalkDungeon();
    }

    public static void Stand() {
        WalkForestClose();
        walkingDungeon.stop();
    }

    public static void Attack() {
        AttackSound();
    }

    public static void Hitt() {
        ChopSound();
    }

    public static void DogWoof() {
        DogSound();
    }

    public static void NinjaAttack() {
        ninjaAttackSound();
        painSound();
    }

    public static void SkeletonAttack() {
        skeletonAttackSound();
        painSound();
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

    public static void UseShield() {
        shieldSound();
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

    public static void SetMusicLower() {
        musıc.setValue(-5f);
    }

    public static void SetMusicUpward() {
        musıc.setValue(+5f);
    }

    public static void SetSoundLower() {
        sound.setValue(-5f);
    }

    public static void SetSoundUpward() {
        sound.setValue(+5f);
    }

    public static void LevelUp() {
        levelup();
    }

    public static void MouseSelect() {
        SelectButton();
    }

}