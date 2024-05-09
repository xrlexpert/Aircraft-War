package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.strategy.DirectShootStrategy;
import edu.hitsz.aircraft.strategy.ScatterShootStrategy;
import edu.hitsz.application.Game;
import edu.hitsz.application.GameConfig;
import edu.hitsz.application.Main;
import edu.hitsz.thread.music.SupplyMusic;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
        SupplyMusic.music(0);
        Game.executorService.schedule(new TimerTask() {
            @Override
            public void run() {
                heroAircraft.setShootNum(GameConfig.heroBasicFire);
                heroAircraft.setShootStrategy(new DirectShootStrategy());
            }
        },5, TimeUnit.SECONDS);
    }
}
