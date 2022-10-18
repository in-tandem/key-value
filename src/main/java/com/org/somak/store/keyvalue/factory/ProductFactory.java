package com.org.somak.store.keyvalue.factory;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.dto.Product;
import com.org.somak.store.keyvalue.entity.ItemEntity;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class ProductFactory implements ItemFactory{


    @Override
    public Item createItem(Item item) {
        return Product
                .builder()
                .id(UUID.randomUUID().toString())
                .name(item.getName())
                .build();

    }

    @Override
    public Class getItemClass() {
        return Product.class;
    }

    @Override
    public Item createItem(ItemEntity itemEntity) {
        return Product
                .builder()
                .id(itemEntity.getId())
                .name(itemEntity.getName())
                .build();
    }
}
