package edu.hitsz.supply.factory;

import edu.hitsz.supply.BaseItem;
import edu.hitsz.supply.Bomb;

public class BombFactory implements ItemFactory{
    @Override
    public BaseItem createItem(int x, int y) {
        return new Bomb(x,y,0,8);
    }
}
