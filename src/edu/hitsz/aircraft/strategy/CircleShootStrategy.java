package edu.hitsz.aircraft.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class CircleShootStrategy implements ShootStrategy {
    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX() + aircraft.getSpeedX() * 4;
        int y = aircraft.getLocationY();
        int shootNum = aircraft.getShootNum();
        int speedX = 5;
        int speedY = 5;
        BaseBullet bullet;
        if(aircraft instanceof HeroAircraft){
            for(int i=0; i<shootNum; i++){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new HeroBullet((int)(x + 200*Math.cos(2 * Math.PI /shootNum * i )), (int)(y + 200*Math.sin(2 * Math.PI / shootNum * i)), (int)(speedX*Math.cos(2 * Math.PI / shootNum * i)), (int)(speedY*Math.sin(2 * Math.PI /shootNum * i)), aircraft.getPower());
                res.add(bullet);
            }
        }
        else{
            for(int i=0; i<shootNum; i++){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new EnemyBullet((int)(x + 200*Math.cos(2 * Math.PI / shootNum * i )), (int)(y + 200*Math.sin(2 * Math.PI / shootNum * i)), (int)(speedX*Math.cos(2 * Math.PI / shootNum * i)), (int)(speedY*Math.sin(2 * Math.PI / shootNum * i)), aircraft.getPower());
                res.add(bullet);
            }
        }
        return res;
    }
}
