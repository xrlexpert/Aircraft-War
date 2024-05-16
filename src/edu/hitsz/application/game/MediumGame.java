package edu.hitsz.application.game;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.factory.BossEnemyFactory;
import edu.hitsz.aircraft.factory.EnemyAircraftFactory;
import edu.hitsz.scores.ScoreDaoImpl;

public class MediumGame extends Game{
    public MediumGame(){
        super();
        scoreDao = new ScoreDaoImpl("src/edu/hitsz/scores/mediumScoreRecord.dat");
        timeInterval = 40;
        cycleDuration = 720;
        enemyMaxNumber = 7;
        bossScoreThreshold = 500;
        diffCycleDuration = 10000;
        ratioOfEliteEnemy = 0.5;
        minCycleTime = 400;
        maxRatioOfEliteEnemy = 0.65;
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
        boolean tag = false;
        if(ratioOfEliteEnemy < maxRatioOfEliteEnemy){
            ratioOfEliteEnemy += 0.02;
            tag = true;
        }
        if(cycleDuration < minCycleTime){
            cycleDuration -= 10;
            tag = true;
        }
        if(tag) {
            System.out.printf("已提高难度，目前精英机出现概率为 %f, 敌机产生周期：%d \n",ratioOfEliteEnemy,cycleDuration);
        }
        else{
            HeroAircraft.setHero_basicFire(2);
            System.out.printf("已达最高难度\n");
        }

    }
}
