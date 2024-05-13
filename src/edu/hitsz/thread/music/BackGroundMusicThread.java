package edu.hitsz.thread.music;

import edu.hitsz.application.game.Game;

public class BackGroundMusicThread implements Runnable{

    public static boolean musicEndFlag = true;
    public static boolean musicSwitch = false;
    public static MusicThread BackGroundMusic= new MusicThread(MusicConfig.BACK_GROUND_MUSIC, true);

    @Override
    public void run() {
        while(!Game.gameOverFlag){
            if(BackGroundMusic.isMusicEndFlag()) {
                BackGroundMusic = new MusicThread(MusicConfig.BACK_GROUND_MUSIC, true);
                BackGroundMusic.setMusicEndFlag(false);
                Game.executorService.execute(BackGroundMusic);
            }
            try{
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        BackGroundMusic.stop();
    }

}