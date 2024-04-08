package edu.hitsz.item.factory;

import edu.hitsz.item.BaseItem;
import edu.hitsz.item.Bomb;
import edu.hitsz.item.BaseItem;

public class BombFactory implements ItemFactory{
    @Override
    public BaseItem createItem(int x, int y) {
        return new Bomb(x,y,0,8);
    }
}
