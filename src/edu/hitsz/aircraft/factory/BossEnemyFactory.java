package edu.hitsz.aircraft.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.util.Random;

public class BossEnemyFactory implements EnemyAircraftFactory{
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
                450);
    }
}
