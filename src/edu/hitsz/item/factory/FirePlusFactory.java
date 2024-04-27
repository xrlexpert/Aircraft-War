package edu.hitsz.item.factory;

import edu.hitsz.item.BaseItem;
import edu.hitsz.item.FirePlus;

public class FirePlusFactory implements  ItemFactory{
    @Override
    public BaseItem createItem(int x, int y) {
        return new FirePlus(x,y,0,8);
    }
}
