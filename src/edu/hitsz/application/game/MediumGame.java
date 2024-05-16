package edu.hitsz.application.game;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.factory.BossEnemyFactory;
import edu.hitsz.aircraft.factory.EnemyAircraftFactory;

public class MediumGame extends Game{
    public MediumGame(){
        super();
        timeInterval = 40;
        cycleDuration = 720;
        enemyMaxNumber = 7;
        bossScoreThreshold = 500;
        ratioOfEliteEnemy = 0.5;
    }
    @Override
    protected void createBossAircraft() {
        if(score / bossScoreThreshold >= cntOfMeetingBoss && !bossFlag){
            EnemyAircraftFactory factory;
            AbstractEnemyAircraft enemyAircraft;
            factory = new BossEnemyFactory();
            enemyAircraft = factory.createAircraft();
            enemyAircrafts.add(enemyAircraft);
            cntOfMeetingBoss += 1;
            bossFlag = true;
        }
    }

    @Override
    protected void increaseDifficulty() {

    }
}
