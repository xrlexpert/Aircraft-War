package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.item.BaseItem;
import edu.hitsz.item.factory.BloodFactory;
import edu.hitsz.item.factory.BombFactory;
import edu.hitsz.item.factory.FireFactory;
import edu.hitsz.item.factory.ItemFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class EliteEnemy extends AbstractEnemyAircraft {

    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 30;

    @Override
    public List<BaseItem> createItems() {
        List<BaseItem> items = new LinkedList<>();
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        BaseItem item;
        ItemFactory factory;
        if(randomNum < 30){
            factory = new BloodFactory();
            item = factory.createItem(this.getLocationX(),this.getLocationY());
            items.add(item);
        }
        else if(randomNum >= 30 && randomNum <60){
            factory = new BombFactory();
            item = factory.createItem(this.getLocationX(),this.getLocationY());
            items.add(item);
        }
        else if(randomNum >=60 && randomNum < 90) {
            factory = new FireFactory();
            item = factory.createItem(this.getLocationX(), this.getLocationY());
            items.add(item);
        }
        return items;
    }

    /**
     * 子弹射击方向 (向上发射：-1，向下发射：1)
     */
    private int direction = 1;
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }

    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }
}
