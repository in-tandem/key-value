/***
Source generated using apache freemarker
***/
package com.org.somak.store.keyvalue.factory;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.dto.${itemName};
import com.org.somak.store.keyvalue.entity.ItemEntity;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class ${factoryName} implements ItemFactory{


    @Override
    public Item createItem(Item item) {
        return ${itemName}
                .builder()
                .id(UUID.randomUUID().toString())
                .name(item.getName())
                .build();

    }

    @Override
    public Class getItemClass() {
        return ${itemName}.class;
    }

    @Override
    public Item createItem(ItemEntity itemEntity) {
        return ${itemName}
                .builder()
                .id(itemEntity.getId())
                .name(itemEntity.getName())
                .build();
    }
}