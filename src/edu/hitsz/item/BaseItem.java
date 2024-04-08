package edu.hitsz.item;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.basic.AbstractFlyingObject;

public abstract class BaseItem extends AbstractFlyingObject {
    public abstract void work(HeroAircraft heroAircraft);
    public BaseItem(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
}
