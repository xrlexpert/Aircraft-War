package edu.hitsz.item.factory;

import edu.hitsz.item.Blood;
import edu.hitsz.item.BaseItem;

public class BloodFactory implements ItemFactory{
    @Override
    public BaseItem createItem(int x,int y) {
        return new Blood(x,y,0,8);
    }
}
