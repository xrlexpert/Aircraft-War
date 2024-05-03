package edu.hitsz.aircraft;

import edu.hitsz.aircraft.strategy.ShootStrategy;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    protected int shootNum = 20;

    /**
     * 子弹伤害
     */
    protected int power = 30;

    /**
     * 子弹射击方向 (向上发射：-1，向下发射：1)
     */
    protected int direction = 1;
    protected ShootStrategy shootStrategy;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public int getHp() {
        return hp;
    }
    public int getShootNum(){return shootNum;}
    public int getPower(){return power;}
    public int getDirection(){return direction;}
    public int getMaxHp(){return getMaxHp();}
    public List<BaseBullet> shoot(){return this.shootStrategy.shoot(this);}
    public void setShootNum(int shootNum){
        this.shootNum = shootNum;
    }
    public void setShootStrategy(ShootStrategy shootStrategy){
        this.shootStrategy = shootStrategy;

    }


    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */

}


