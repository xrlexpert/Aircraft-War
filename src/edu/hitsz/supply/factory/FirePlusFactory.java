package edu.hitsz.supply.factory;

import edu.hitsz.supply.BaseItem;
import edu.hitsz.supply.FirePlus;

public class FirePlusFactory implements  ItemFactory{
    @Override
    public BaseItem createItem(int x, int y) {
        return new FirePlus(x,y,0,8);
    }
}
