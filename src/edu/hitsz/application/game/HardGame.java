package edu.hitsz.application.game;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.factory.*;
import edu.hitsz.scores.ScoreDaoImpl;

public class HardGame extends Game {
    public HardGame(){
        super();
        scoreDao = new ScoreDaoImpl("src/edu/hitsz/scores/hardScoreRecord.dat");
        timeInterval = 40;
        cycleDuration = 600;
        enemyMaxNumber = 8;
        bossScoreThreshold = 400;
        ratioOfEliteEnemy = 0.6;
        BossEnemyFactory.setMaxBossHp(2000);
        diffCycleDuration = 6000;
        minCycleTime = 200;
        maxRatioOfEliteEnemy = 0.75;
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
            //困难模式增加boss机血量
            BossEnemyFactory.increaseBossHp();
        }

    }

    @Override
    protected void increaseDifficulty() {
        boolean tag = false;
        tag |= MobEnemyFactory.increaseRate();
        tag |= EliteEnemyFactory.increaseRate();
        tag |= ElitePlusEnemyFactory.increaseRate();
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
