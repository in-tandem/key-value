package com.org.somak.store.keyvalue.factory;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.entity.ItemEntity;
import com.org.somak.store.keyvalue.exception.CustomEventException;
import com.org.somak.store.keyvalue.util.ReflectionUtil;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;
import java.util.regex.Pattern;

public final class ItemAbstractFactory {

    private String itemKind;
    private final String FACTORY = "Factory";

    public ItemAbstractFactory(String itemKind){
        this.itemKind = itemKind;
    }

    public Item createConcreteItem(ItemEntity item) throws CustomEventException {
        return generateItemFactory(this.itemKind).createItem(item);
    }

    public Class<Item> getItemClass() throws CustomEventException {
        return generateItemFactory(itemKind).getItemClass();
    }

    private ItemFactory generateItemFactory(String itemKind) throws CustomEventException {
        try {
            return (ItemFactory) ReflectionUtil.of(generateFullyQualifiedFactoryName(itemKind));
        } catch (ClassNotFoundException e) {
            throw new CustomEventException("Incorrect input for kind parameter");
        }
    }

    private String generateFullyQualifiedFactoryName(String itemKind) throws CustomEventException {
        Objects.requireNonNull(itemKind);
        if(!Pattern.compile("^[A-Za-z]+$").matcher(itemKind).matches())
            throw new CustomEventException("Incorrect input for kind parameter");
        return ReflectionUtil.FACTORY_PACKAGE_NAME+"."+WordUtils.capitalize(itemKind) + FACTORY;
    }

}
