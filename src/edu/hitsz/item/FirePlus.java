package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.strategy.CircleShootStrategy;
import edu.hitsz.application.Main;

public class FirePlus extends BaseItem {
    public FirePlus(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }


    @Override
    public void work(HeroAircraft heroAircraft) {
        heroAircraft.setShootNum(Math.min(20,heroAircraft.getShootNum() + 5));
        heroAircraft.setShootStrategy(new CircleShootStrategy());

    }
}
