package edu.hitsz.supply.factory;

import edu.hitsz.supply.BaseItem;

public interface ItemFactory {
    public abstract BaseItem createItem(int x,int y);
}
