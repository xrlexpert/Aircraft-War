package edu.hitsz.application.game;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.factory.BossEnemyFactory;
import edu.hitsz.aircraft.factory.EnemyAircraftFactory;

public class EasyGame extends Game {
    public EasyGame(){
        super();
        timeInterval = 40;
        cycleDuration = 800;
        enemyMaxNumber = 5;
        bossScoreThreshold = 500;
        ratioOfEliteEnemy = 0.5;

    }

    @Override
    protected void createBossAircraft() {
    }

    //简单模式不随时间推移变难
    @Override
    protected void increaseDifficulty() {}
}
