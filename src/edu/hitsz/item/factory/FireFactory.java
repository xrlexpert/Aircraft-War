package edu.hitsz.item.factory;

import edu.hitsz.item.BaseItem;
import edu.hitsz.item.Fire;
import edu.hitsz.item.BaseItem;

public class FireFactory implements ItemFactory {
    @Override
    public BaseItem createItem(int x, int y) {
        return new Fire(x, y, 0,8);
    }
}
