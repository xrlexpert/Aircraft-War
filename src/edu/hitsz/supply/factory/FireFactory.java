package edu.hitsz.supply.factory;

import edu.hitsz.supply.BaseItem;
import edu.hitsz.supply.Fire;

public class FireFactory implements ItemFactory {
    @Override
    public BaseItem createItem(int x, int y) {
        return new Fire(x, y, 0,8);
    }
}
