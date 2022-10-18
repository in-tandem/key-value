package com.org.somak.store.keyvalue.factory;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.entity.ItemEntity;

public interface ItemFactory {

    Item createItem(Item item);
    Class getItemClass();
    Item createItem(ItemEntity itemEntity);
}
