/***
Source generated using apache freemarker
***/
package com.org.somak.store.keyvalue.factory;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.dto.Lappie;
import com.org.somak.store.keyvalue.entity.ItemEntity;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class LappieFactory implements ItemFactory{


    @Override
    public Item createItem(Item item) {
        return Lappie
                .builder()
                .id(UUID.randomUUID().toString())
                .name(item.getName())
                .build();

    }

    @Override
    public Class getItemClass() {
        return Lappie.class;
    }

    @Override
    public Item createItem(ItemEntity itemEntity) {
        return Lappie
                .builder()
                .id(itemEntity.getId())
                .name(itemEntity.getName())
                .build();
    }
}