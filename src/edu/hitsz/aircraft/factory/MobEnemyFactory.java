package edu.hitsz.aircraft.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.game.GameConfig;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobEnemyFactory implements EnemyAircraftFactory{
    public static int mobBasicHp = 30;
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
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                4,
                (int)(mobBasicHp*rate)
        );
    }
}
