package edu.hitsz.aircraft;

import edu.hitsz.aircraft.strategy.ScatterShootStrategy;
import edu.hitsz.application.Main;
import edu.hitsz.application.game.Game;
import edu.hitsz.observer.Observer;
import edu.hitsz.supply.BaseItem;
import edu.hitsz.supply.factory.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ElitePlusEnemy extends AbstractEnemyAircraft implements Observer {
    public ElitePlusEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX,locationY,speedX,speedY,hp);
        this.shootNum = 3;
        this.direction = 1;
        this.power = 30;
        this.shootStrategy = new ScatterShootStrategy();
    }
    @Override
    public void decreaseHp(int decrease) {
        super.decreaseHp(decrease);
    }

    @Override
    public List<BaseItem> createItems() {
        List<BaseItem> items = new LinkedList<>();
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        BaseItem item;
        ItemFactory factory;
        if(randomNum < 20){
            factory = new BloodFactory();
            item = factory.createItem(this.getLocationX(),this.getLocationY());
            items.add(item);
        }
        else if(randomNum >= 20 && randomNum <35){
            factory = new BombFactory();
            item = factory.createItem(this.getLocationX(),this.getLocationY());
            items.add(item);
        }
        else if(randomNum >=35 && randomNum < 50) {
            factory = new FireFactory();
            item = factory.createItem(this.getLocationX(), this.getLocationY());
            items.add(item);
        }
        else if(randomNum >= 50 && randomNum < 60){
            factory = new FirePlusFactory();
            item = factory.createItem(this.getLocationX(), this.getLocationY());
            items.add(item);

        }
        return items;
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

//    @Override
//    public List<BaseBullet> shoot() {
//        List<BaseBullet> res = new LinkedList<>();
//        int x = this.getLocationX();
//        int y = this.getLocationY() + direction*2;
//        int speedX = 2;
//        int speedY = this.getSpeedY() + direction*5;
//        BaseBullet bullet;
//        int [] f = {-1,0,1};
//        for(int i=0; i<shootNum; i++){
//            // 子弹发射位置相对飞机位置向前偏移
//            // 多个子弹横向分散
//            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX*f[i], speedY, power);
//            res.add(bullet);
//        }
//        return res;
//    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public void update() {
        decreaseHp(30);
        if(notValid()){
            Game.score += 10;
        }
    }
}
