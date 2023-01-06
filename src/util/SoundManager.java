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
    public static Clip playerDead;

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

        playerDead = AudioSystem.getClip();
        playerDead.open(AudioSystem.getAudioInputStream(new File("res/assets/sound/playerDead.wav")));

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
/**Start menu, set start position to 0 and looping*/
    public static void MenuSound() {
        menu.setFramePosition(0);
        menu.start();
        menu.loop(Clip.LOOP_CONTINUOUSLY);

    }
 /**Start shield and repeats 6 times*/
    public static void shieldSound() {
        shield.loop(6);
        shield.setFramePosition(0);
        shield.start();

    }
/**stops the menu */
    public static void MenuSoundClose() {
        menu.stop();
    }
    /**Start forest, set start position to 0 and looping*/
    public static void ForestSound() {
        forest.setFramePosition(0);
        forest.start();
        forest.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**stops the forest */
    public static void ForestSoundClose() {
        forest.stop();
    }
    /**Start first floor sound, set start position to 0 and looping*/
    public static void dungeonFloor1() {
        dungeonfloor1.setFramePosition(0);
        dungeonfloor1.start();
        dungeonfloor1.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**stops the first floor sound */
    public static void dungeonFloor1Close() {
        dungeonfloor1.stop();
    }
    /**Start second floor sound, set start position to 0 and looping*/
    public static void dungeonFloor2() {
        dungeonfloor2.setFramePosition(0);
        dungeonfloor2.start();
        dungeonfloor2.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**stops the second floor sound */
    public static void dungeonFloor2Close() {
        dungeonfloor2.stop();
    }
    /**Start third floor sound, set start position to 0 and looping*/
    public static void dungeonFloor3() {
        dungeonfloor3.setFramePosition(0);
        dungeonfloor3.start();
        dungeonfloor3.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**stops the third floor sound */
    public static void dungeonFloor3Close() {
        dungeonfloor3.stop();
    }
    /**Start fourth floor sound, set start position to 0 and looping*/
    public static void dungeonFloor4() {
        dungeonfloor4.setFramePosition(0);
        dungeonfloor4.start();
        dungeonfloor4.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**stops the fourth floor sound */
    public static void dungeonFloor4Close() {
        dungeonfloor4.stop();
    }
    /**start dog sound from 0 position */
    public static void DogSound() {
        dogSound.setFramePosition(0);
        dogSound.start();
    }
    /**start heal sound from 0 position */
    public static void HealSound() {
        heal.setFramePosition(0);
        heal.start();
    }
    /**start lifepot sound from 0 position */
    public static void LifePotSound() {
        lifepot.setFramePosition(0);
        lifepot.start();
    }
    /**start medpac sound from 0 position */
    public static void MedPacSound() {
        medipac.setFramePosition(0);
        medipac.start();
    }
    /**start ninja dead sound from 0 position */
    public static void ninjaDeadSound() {
        ninjadead.setFramePosition(0);
        ninjadead.start();
    }
    /**start cavegirl dead sound from 0 position */
    public static void cavegirlDeadSound() {
        cavegirlDead.setFramePosition(0);
        cavegirlDead.start();
    }
    /**start caveman dead sound from 0 position */
    public static void cavemanDeadSound() {
        cavemanDead.setFramePosition(0);
        cavemanDead.start();
    }
    /**start skeleton dead sound from 0 position */
    public static void skeletonDeadSound() {
        skeletonDead.setFramePosition(0);
        skeletonDead.start();
    }
    /**start boss dead sound from 0 position */
    public static void bossDeadSound() {
        bossDead.setFramePosition(0);
        bossDead.start();
    }
    /**start player dead sound from 0 position */
    public static void playerDeadSound() {
        playerDead.setFramePosition(0);
        playerDead.start();
    }
    /**start boss attack sound from 0 position */
    public static void bossAttackSound() {
        bossAttack.setFramePosition(0);
        bossAttack.start();

    }
    /**start ninja attack sound from 0 position */
    public static void ninjaAttackSound() {
        ninjaAttack.setFramePosition(0);
        ninjaAttack.start();
    }
    /**start skeleton attack sound from 0 position */
    public static void skeletonAttackSound() {
        skeletonAttack.setFramePosition(0);
        skeletonAttack.start();
    }
    /**start cavegirl attack sound from 0 position */
    public static void cavegirlAttackSound() {
        cavegirlAttack.setFramePosition(0);
        cavegirlAttack.start();
    }
    /**start caveman attack sound from 0 position */
    public static void cavemanAttackSound() {
        cavemanAttack.setFramePosition(0);
        cavemanAttack.start();
    }
    /**start selecet sound from 0 position */
    public static void SelectButton() {
        selectButton.setFramePosition(0);
        selectButton.start();
    }
    /**start level sound from 0 position */
    public static void levelup() {
        levelUp.setFramePosition(0);
        levelUp.start();
    }
    /**start attack sound from 0 position */
    public static void AttackSound() {
        attack.setFramePosition(0);
        attack.start();
    }
    /**start chop sound (when player hits) from 0 position */
    public static void ChopSound() {
        chop.setFramePosition(0);
        chop.start();
    }
    /**start pain sound (when player get damage) from 0 position */
    public static void painSound() {
        pain.setFramePosition(0);
        pain.start();
    }
    /**start walking sound at forest from 0 position */
    public static void WalkForest() {
        walkingForest.start();
        walkingForest.loop(attack.LOOP_CONTINUOUSLY);
    }
    /**close walking sound at forest*/
    public static void WalkForestClose() {
        walkingForest.stop();
    }
    /**start walking sound at dungeon from 0 position */
    public static void WalkDungeon() {
        walkingDungeon.start();
        walkingDungeon.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**close walking sound at dungeon*/
    public static void WalkingDungeonClose() {
        walkingDungeon.stop();
    }
    /**Start rain sound, set start position to 0 and looping*/
    public static void rainSound() {
        rain.setFramePosition(0);
        rain.start();
        rain.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Now start the methods to uses other classes
     */

    /**Call the MenuSound method when the menu opens */
    public static void Menu() {
        MenuSound();
    }
    /**Call ForestSound and stop other background sounds*/
    public static void StartForest() {
        dungeonFloor1Close();
        MenuSoundClose();
        ForestSound();
    }
    /**Call dungeonFloor1 and stop other background sounds when player enter dungeon*/
    public static void DungeonEnter() {
        dungeonFloor1();
        dungeonFloor2Close();
        dungeonFloor3Close();
        dungeonFloor4Close();
        ForestSoundClose();
    }
    /**Call dungeonFloor2 and stop other background sounds when player enter second floor*/
    public static void DungeonFloor2() {
        dungeonFloor2();
        dungeonFloor1Close();
        dungeonFloor3Close();
        dungeonFloor4Close();
        ForestSoundClose();
    }
    /**Call dungeonFloor3 and stop other background sounds when player enter third floor*/
    public static void DungeonFloor3() {
        dungeonFloor3();
        dungeonFloor1Close();
        dungeonFloor2Close();
        dungeonFloor4Close();
        ForestSoundClose();
    }
    /**Call dungeonFloor4 and stop other background sounds when player enter fourth floor*/
    public static void DungeonFloor4() {
        dungeonFloor4();
        dungeonFloor1Close();
        dungeonFloor2Close();
        dungeonFloor3Close();
        ForestSoundClose();
    }
    /**Call ninjaDeadSound when the ninja dead*/
    public static void NinjaDead() {
        ninjaDeadSound();
    }
    /**Call bossDeadSound when the boss dead*/
    public static void BossDead() {
        bossDeadSound();
    }
    /**Call cavemanDeadSound when the caveman dead*/
    public static void CavemanDead() {
        cavemanDeadSound();
    }
    /**Call cavegirlDeadSound when the cavegirl dead*/
    public static void CavegirlDead() {
        cavegirlDeadSound();
    }
    /**Call skeletonDeadSound when the skeleton dead*/
    public static void SkeletonDead() {
        skeletonDeadSound();
    }
    /**Call painSound and bossAttackSound when the boss attack*/
    public static void BossAttack() {
        painSound();
        bossAttackSound();
    }
    /**Call painSound and cavegirlAttackSound when the cavegirl attack*/
    public static void CavegirlAttack() {
        painSound();
        cavegirlAttackSound();
    }
    /**Call painSound and cavemanAttackSound when the caveman attack*/
    public static void CavemanAttack() {
        painSound();
        cavemanAttackSound();
    }
    /**Call painSound and ninjaAttackSound when the ninja attack*/
    public static void NinjaAttack() {
        ninjaAttackSound();
        painSound();
    }
    /**Call painSound and skeletonAttackSound when the ninja skeleton*/
    public static void SkeletonAttack() {
        skeletonAttackSound();
        painSound();
    }
    /**Call AttackSound when player attack*/
    public static void Attack() {
        AttackSound();
    }
    /**Call WalkForest and WalkingDungeonClose when player start walk on the forest*/
    public static void Walkingforest() {
        WalkingDungeonClose();
        WalkForest();
    }
    /**Call WalkForestClose and WalkDungeon when player start walk on the dungeon*/
    public static void WalkingDungeon() {
        WalkForestClose();
        WalkDungeon();
    }
    /**Call WalkForestClose and walkingDungeon.stop() while player standing*/
    public static void Stand() {
        WalkForestClose();
        walkingDungeon.stop();
    }
    /**Call ChopSound when player get damage*/
    public static void Hitt() {
        ChopSound();
    }
    /**Call DogSound while player fighting with enemies */
    public static void DogWoof() {
        DogSound();
    }
    /**Call HealSound when player get health item*/
    public static void GetHealthItem() {
        HealSound();
    }
    /**Call LifePotSound when player use life pot*/
    public static void UseLifePot() {
        LifePotSound();
    }
    /**Call MedPacSound when player use medpac*/
    public static void UseMedPac() {
        MedPacSound();
    }
    /**Call shieldSound when player use shield*/
    public static void UseShield() {
        shieldSound();
    }
    /**Call rainSound and ForestSoundClose when rain is start*/
    public static void RainStart() {
        rainSound();
        ForestSoundClose();
    }
    /**Call playerDeadSound when player dead*/
    public static void PlayerDead() {
        playerDeadSound();
    }
    /**Raining sound stop and forest sound start */
    public static void RainStop() {
        rain.stop();
        ForestSound();
    }
    /**Pause the game call Menu, RainStop and ForestSoundClose */
    public static void Pause() {
        Menu();
        RainStop();
        ForestSoundClose();
    }
    /**Continue game if raining true raining continues else raining stop and call StartForest*/
    public static void Continue() {
        StartForest();
        if (raining)
            RainStart();
        else
            RainStop();
    }
    /**Subtract 5 to musıc volume every called*/
    public static void SetMusicLower() {
        musıc.setValue(-5f);
    }
    /**Add 5 to musıc volume every called*/
    public static void SetMusicUpward() {
        musıc.setValue(+5f);
    }
    /**Subtract 5 to sound volume every called*/
    public static void SetSoundLower() {
        sound.setValue(-5f);
    }
    /**Add 5 to sound volume every called*/
    public static void SetSoundUpward() {
        sound.setValue(+5f);
    }
    /**Call levelup when level up*/
    public static void LevelUp() {
        levelup();
    }
    /**Call SelectButton when mouse select something*/
    public static void MouseSelect() {
        SelectButton();
    }

}