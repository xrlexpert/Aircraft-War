package edu.hitsz.item.factory;

import edu.hitsz.item.BaseItem;

public interface ItemFactory {
    public abstract BaseItem createItem(int x,int y);
}
