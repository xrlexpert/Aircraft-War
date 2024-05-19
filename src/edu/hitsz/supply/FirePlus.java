package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.strategy.CircleShootStrategy;
import edu.hitsz.aircraft.strategy.DirectShootStrategy;
import edu.hitsz.application.game.Game;
import edu.hitsz.application.game.GameConfig;
import edu.hitsz.application.Main;
import edu.hitsz.thread.music.SupplyMusic;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
        heroAircraft.setShootNum(Math.min(20,heroAircraft.getShootNum() + 8));
        heroAircraft.setShootStrategy(new CircleShootStrategy());
        SupplyMusic.music(0);
        Game.executorService.schedule(new TimerTask() {
            @Override
            public void run() {
                heroAircraft.setShootNum(HeroAircraft.hero_basicFire);
                heroAircraft.setShootStrategy(new DirectShootStrategy());
            }
        },12, TimeUnit.SECONDS);

    }
}
