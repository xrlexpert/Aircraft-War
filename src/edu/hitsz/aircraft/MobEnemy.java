package edu.hitsz.aircraft;

import edu.hitsz.aircraft.strategy.DirectShootStrategy;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.item.BaseItem;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractEnemyAircraft {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 0;
        this.direction = 1;
        this.shootStrategy = new DirectShootStrategy();

    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }

    @Override
    public List<BaseItem> createItems() {
        return new LinkedList<>();
    }
}
