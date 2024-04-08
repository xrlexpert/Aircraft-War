package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

public class Blood extends BaseItem {

        private static final int Heal_HP = 20;
        public Blood(int locationX, int locationY, int speedX, int speedY){
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
    public void work(HeroAircraft heroAircraft){
        heroAircraft.increaseHp(Heal_HP);
    }
}
