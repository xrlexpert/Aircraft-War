package edu.hitsz.application.game;

import javax.imageio.ImageIO;
import java.io.FileInputStream;

    public class GameConfig {


    public static boolean musicFlag = true;
    public  static int gameMode = 0;
    public static void setGameMode(int mode){
        gameMode = mode;
    }
    public static void setMusicFlag(boolean res){
        musicFlag = res;
    }

}
