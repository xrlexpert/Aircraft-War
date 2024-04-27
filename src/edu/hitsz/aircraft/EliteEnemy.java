package edu.hitsz.aircraft;

import edu.hitsz.aircraft.strategy.DirectShootStrategy;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.item.BaseItem;
import edu.hitsz.item.factory.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class EliteEnemy extends AbstractEnemyAircraft {

    @Override
    public List<BaseItem> createItems() {
        List<BaseItem> items = new LinkedList<>();
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        BaseItem item;
        ItemFactory factory;
        if(randomNum < 25){
            factory = new BloodFactory();
            item = factory.createItem(this.getLocationX(),this.getLocationY());
            items.add(item);
        }
        else if(randomNum >= 25 && randomNum <50){
            factory = new BombFactory();
            item = factory.createItem(this.getLocationX(),this.getLocationY());
            items.add(item);
        }
        else if(randomNum >=50 && randomNum < 75) {
            factory = new FireFactory();
            item = factory.createItem(this.getLocationX(), this.getLocationY());
            items.add(item);
        }
        else if(randomNum >=75 && randomNum < 90){
            factory = new FirePlusFactory();
            item = factory.createItem(this.getLocationX(), this.getLocationY());
            items.add(item);

        }
        return items;
    }

    /**
     * 子弹射击方向 (向上发射：-1，向下发射：1)
     */
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 1;
        this.direction = 1;
        this.power = 30;
        this.shootStrategy = new DirectShootStrategy();
    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }

    }

}
