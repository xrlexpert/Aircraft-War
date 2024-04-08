package edu.hitsz.item.factory;

import edu.hitsz.item.Fire;
import edu.hitsz.item.Item;

public class FireFactory implements ItemFactory {
    @Override
    public Item createItem(int x, int y) {
        return new Fire(x, y, 0,8);
    }
}
