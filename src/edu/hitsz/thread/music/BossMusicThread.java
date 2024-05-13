package edu.hitsz.thread.music;

import edu.hitsz.application.game.Game;

public class BossMusicThread implements Runnable{


    public static boolean musicEndFlag = true;
    public static MusicThread BossMusic = new MusicThread(MusicConfig.BOSS_MUSIC, true);

    @Override
    public void run() {
        while(!Game.gameOverFlag){
            if(Game.bossFlag){
                if(BossMusic.isMusicEndFlag()) {
                    BossMusic = new MusicThread(MusicConfig.BOSS_MUSIC, true);
                    BossMusic.setMusicEndFlag(false);
                    Game.executorService.execute(BossMusic);
                }
            }
            else{
                BossMusic.stop();
            }
            try{
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        BossMusic.stop();
    }

}