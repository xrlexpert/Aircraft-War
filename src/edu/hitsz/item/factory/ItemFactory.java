package edu.hitsz.item.factory;

import edu.hitsz.item.Item;

public interface ItemFactory {
    public abstract Item createItem(int x,int y);
}
