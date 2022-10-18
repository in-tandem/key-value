/***
Source generated using apache freemarker
***/
package com.org.somak.store.keyvalue.factory;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.dto.Pencil;
import com.org.somak.store.keyvalue.entity.ItemEntity;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class PencilFactory implements ItemFactory{


    @Override
    public Item createItem(Item item) {
        return Pencil
                .builder()
                .id(UUID.randomUUID().toString())
                .name(item.getName())
                .build();

    }

    @Override
    public Class getItemClass() {
        return Pencil.class;
    }

    @Override
    public Item createItem(ItemEntity itemEntity) {
        return Pencil
                .builder()
                .id(itemEntity.getId())
                .name(itemEntity.getName())
                .build();
    }
}