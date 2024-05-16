package edu.hitsz.application.game;

import javax.imageio.ImageIO;
import java.io.FileInputStream;

    public class GameConfig {

    /** 游戏内敌机的血量控制 */
    public static int mobBasicHp = 30;
    public static int bossHp = 520;
    public static int eliteBasicHp = 60;
    public static int elitePlusBasicHp = 60;

    public static double maxRatioOfEliteEnemy = 0.8;
    public static int minCycleTime = 450;
    public static boolean musicFlag = true;
    public  static int gameMode = 0;
    public static void setGameMode(int mode){
        gameMode = mode;
    }
    public static void setMusicFlag(boolean res){
        musicFlag = res;
    }

}
