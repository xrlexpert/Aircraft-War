package edu.hitsz.supply.factory;

import edu.hitsz.supply.Blood;
import edu.hitsz.supply.BaseItem;

public class BloodFactory implements ItemFactory{
    @Override
    public BaseItem createItem(int x,int y) {
        return new Blood(x,y,0,8);
    }
}
