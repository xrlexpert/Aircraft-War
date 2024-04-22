package edu.hitsz.aircraft;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.item.BaseItem;
import edu.hitsz.item.factory.BloodFactory;
import edu.hitsz.item.factory.BombFactory;
import edu.hitsz.item.factory.FireFactory;
import edu.hitsz.item.factory.ItemFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BossEnemy extends AbstractEnemyAircraft {
    private int shootNum = 20;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：-1，向下发射：1)
     */
    private int direction = 1;
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY, hp);

    }
    @Override
    public void decreaseHp(int decrease) {
        super.decreaseHp(decrease);
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX() + speedX * 4;
        int y = this.getLocationY();
        int speedX = 5;
        int speedY = 5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet((int)(x + 200*Math.cos(2 * Math.PI / shootNum * i )), (int)(y + 200*Math.sin(2 * Math.PI / shootNum * i)), (int)(speedX*Math.cos(2 * Math.PI / shootNum * i)), (int)(speedY*Math.sin(2 * Math.PI / shootNum * i)), power);
            res.add(bullet);
        }
        return res;
    }

    @Override
    public List<BaseItem> createItems() {
        Random rand = new Random();
        List<BaseItem> items = new LinkedList<>();
        for(int i = 0; i < 3; i++) {
            int randomNum = rand.nextInt(100);
            BaseItem item;
            ItemFactory factory;
            if(randomNum < 30){
                factory = new BloodFactory();
                item = factory.createItem(this.getLocationX() + i * 50,this.getLocationY());
                items.add(item);
            }
            else if(randomNum >= 30 && randomNum <60){
                factory = new BombFactory();
                item = factory.createItem(this.getLocationX() + i * 50,this.getLocationY());
                items.add(item);
            }
            else if(randomNum >=60 && randomNum < 90) {
                factory = new FireFactory();
                item = factory.createItem(this.getLocationX() + i * 50, this.getLocationY());
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public void forward() {
        super.forward();
    }
}
