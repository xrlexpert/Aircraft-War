package edu.hitsz.application;

import javax.imageio.ImageIO;
import java.io.FileInputStream;

public class GameConfig {
    /**The interval of paint refreshing and all sorts of check*/
    public static  int timeInterval = 40;

    /**The interval of enemy summon and shoot action*/
    public static int cycleDuration = 800;

    /**The max number of enemies exist in the window*/
    public static int enemyMaxNumber = 5;
    public static int heroBasicHp = 1200;
    public static int heroBasicFire = 2;
    public static int mobBasicHp = 30;
    public static int eliteBasicHp = 30;
    public static int elitePlusBasicHp = 30;
    public static int bossHp = 520;

    public static int bossScoreThreshold = 400;
    public static double ratioOfEliteEnemy = 0.4;
    public static boolean musicFlag = true;
    public static String BACKGROUND_IMAGE = "src/images/bg.jpg";
    public static void setGameMode(int mode){
        if(mode == 1){
            enemyMaxNumber = 6;
            heroBasicHp = 1000;
            bossHp = 630;
            bossScoreThreshold = 300;
            ratioOfEliteEnemy = 0.4;
            BACKGROUND_IMAGE = "src/images/bg2.jpg";

        }
        else if(mode == 2) {
            enemyMaxNumber = 7;
            heroBasicHp = 1000;
            bossHp = 810;
            heroBasicFire = 1;
            bossScoreThreshold = 250;
            ratioOfEliteEnemy = 0.5;
            BACKGROUND_IMAGE = "src/images/bg3.jpg";
        }
    }
    public static void setMusicFlag(boolean res){
        musicFlag = res;
    }

}
