package edu.hitsz.item;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.basic.AbstractFlyingObject;

public abstract class Item extends AbstractFlyingObject {
    public abstract void work(AbstractFlyingObject obj);
    public Item(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
}
