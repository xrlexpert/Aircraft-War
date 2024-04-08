package edu.hitsz.item.factory;

import edu.hitsz.item.Bomb;
import edu.hitsz.item.Item;

public class BombFactory implements ItemFactory{
    @Override
    public Item createItem(int x, int y) {
        return new Bomb(x,y,0,8);
    }
}
