package edu.hitsz.item;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

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
    public void work(AbstractFlyingObject flyingObject) {
        System.out.println("FireSupply active!");
    }
}
