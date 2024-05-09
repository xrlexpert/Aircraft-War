package edu.hitsz.thread.music;

import edu.hitsz.application.Game;

public class SupplyMusic {


    public static void music(int type) {
        MusicThread musicThread;
        if(type == 0){
            musicThread = new MusicThread(MusicConfig.GET_SUPPLY_MUSIC,false);
        }
        else{
            musicThread = new MusicThread(MusicConfig.BOMB_MUSIC,false);
        }

        Game.executorService.execute(musicThread);
    }
}
