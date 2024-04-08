package edu.hitsz.item.factory;

import edu.hitsz.item.Blood;
import edu.hitsz.item.Item;

public class BloodFactory implements ItemFactory{
    @Override
    public Item createItem(int x,int y) {
        return new Blood(x,y,0,8);
    }
}
