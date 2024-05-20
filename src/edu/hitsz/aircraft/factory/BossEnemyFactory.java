package edu.hitsz.aircraft.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.game.GameConfig;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.util.Random;

public class BossEnemyFactory implements EnemyAircraftFactory{
    public static int bossHp = 600;
    private static int maxBossHp = 960;
    /** Boss机在困难模式随出现次数而血量增加 */
    public static void increaseBossHp(){
        BossEnemyFactory.bossHp = Math.min(bossHp + 120,maxBossHp);
    }
    public static void setMaxBossHp(int bossHp){
        BossEnemyFactory.maxBossHp = bossHp;
    }
    @Override
    public AbstractEnemyAircraft createAircraft() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2);
        int f = 1;
        if(randomNum == 1){
            f = -1;
        }
        return new BossEnemy((int) (Math.random() *( Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Main.WINDOW_HEIGHT * 0.15),
                f*2,
                0,
                bossHp);
    }
}
