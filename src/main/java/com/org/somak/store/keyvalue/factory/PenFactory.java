/***
Source generated using apache freemarker
***/
package com.org.somak.store.keyvalue.factory;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.dto.Pen;
import com.org.somak.store.keyvalue.entity.ItemEntity;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class PenFactory implements ItemFactory{


    @Override
    public Item createItem(Item item) {
        return Pen
                .builder()
                .id(UUID.randomUUID().toString())
                .name(item.getName())
                .build();

    }

    @Override
    public Class getItemClass() {
        return Pen.class;
    }

    @Override
    public Item createItem(ItemEntity itemEntity) {
        return Pen
                .builder()
                .id(itemEntity.getId())
                .name(itemEntity.getName())
                .build();
    }

}