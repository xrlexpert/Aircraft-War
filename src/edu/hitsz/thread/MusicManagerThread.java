package edu.hitsz.thread;

import edu.hitsz.application.Game;

public class MusicManagerThread implements Runnable{
    private static final String BACK_GROUND_MUSIC = "src/videos/bgm.wav";
    private static final String BOSS_MUSIC = "src/videos/bgm_boss.wav";
    private static final String BULLET_MUSIC = "src/videos/bullet.wav";
    private static final String BULLET_HIT_MUSIC = "src/videos/bullet_hit.wav";
    private static final String GET_SUPPLY_MUSIC = "src/videos/get_supply.wav";
    private static final String GAME_OVER_MUSIC = "src/videos/get_supply.wav";

    public static boolean musicEndFlag = true;

    @Override
    public void run() {
        while(!Game.gameOverFlag){
            if(musicEndFlag) {
                MusicThread thread;
                if (Game.bossMusicFlag) {
                    thread = new MusicThread(BOSS_MUSIC, true);
                } else {
                    thread = new MusicThread(BACK_GROUND_MUSIC, true);
                }
                musicEndFlag = false;
                Game.executorService.execute(thread);
            }
            try{
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}