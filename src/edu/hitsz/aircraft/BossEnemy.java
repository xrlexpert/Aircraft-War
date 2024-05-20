package edu.hitsz.aircraft;

import edu.hitsz.aircraft.strategy.CircleShootStrategy;
import edu.hitsz.application.game.Game;
import edu.hitsz.supply.BaseItem;
import edu.hitsz.supply.factory.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BossEnemy extends AbstractEnemyAircraft {

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum  = 20;
        this.direction = 1;
        this.power = 30;
        this.shootStrategy = new CircleShootStrategy();
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
    public List<BaseItem> createItems() {
        Random rand = new Random();
        List<BaseItem> items = new LinkedList<>();
        for(int i = 0; i < 3; i++) {
            int randomNum = rand.nextInt(100);
            BaseItem item;
            ItemFactory factory;
            if(randomNum < 20){
                factory = new BloodFactory();
                item = factory.createItem(this.getLocationX() + i*50,this.getLocationY());
                items.add(item);
            }
            else if(randomNum >= 20 && randomNum <35){
                factory = new BombFactory();
                item = factory.createItem(this.getLocationX() + i*50,this.getLocationY());
                items.add(item);
            }
            else if(randomNum >=35 && randomNum < 50) {
                factory = new FireFactory();
                item = factory.createItem(this.getLocationX()+ i*50, this.getLocationY());
                items.add(item);
            }
            else if(randomNum >= 50 && randomNum < 60){
                factory = new FirePlusFactory();
                item = factory.createItem(this.getLocationX() + i*50, this.getLocationY());
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public void forward() {
        super.forward();
    }
    @Override
    public void vanish(){
        super.vanish();
        Game.bossFlag = false;
    }
}
