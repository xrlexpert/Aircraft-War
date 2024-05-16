package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.application.game.GameConfig;
import edu.hitsz.thread.music.SupplyMusic;

public class Blood extends BaseItem {


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
        heroAircraft.increaseHp(270);
        SupplyMusic.music(0);
    }
}
