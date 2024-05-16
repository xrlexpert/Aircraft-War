package edu.hitsz.aircraft;

import edu.hitsz.supply.BaseItem;

import java.util.List;

public abstract class AbstractEnemyAircraft extends AbstractAircraft {
    public AbstractEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    public abstract List<BaseItem> createItems();
}
