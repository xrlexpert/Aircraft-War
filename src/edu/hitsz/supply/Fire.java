package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.strategy.ScatterShootStrategy;
import edu.hitsz.application.Main;

public class Fire extends BaseItem {
    public Fire(int locationX, int locationY, int speedX, int speedY){
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
        heroAircraft.setShootNum(Math.min(5,heroAircraft.getShootNum() + 2));
        heroAircraft.setShootStrategy(new ScatterShootStrategy());
    }
}
