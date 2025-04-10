package edu.hitsz.aircraft.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.application.game.GameConfig;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.util.Random;

public class ElitePlusEnemyFactory implements EnemyAircraftFactory  {
    public static int elitePlusBasicHp = 60;
    public static double rate = 1;
    private static final double maxRate = 1.5;
    /** 敌机属性随时间推移增加 */
    public static boolean increaseRate(){
        if(rate < maxRate){
            rate += 0.1;
            return true;
        }
        return false;
    }
    @Override
    public AbstractEnemyAircraft createAircraft() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2);
        int f = 1;
        if(randomNum == 1){
            f = -1;
        }
        return new ElitePlusEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                f*2,
                4,
                (int)(elitePlusBasicHp*rate));
    }
}
