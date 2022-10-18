package com.org.somak.store.keyvalue.factory;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.entity.ItemEntity;
import com.org.somak.store.keyvalue.exception.CustomEventException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ItemAbstractFactoryTest {

    @ParameterizedTest
    @ValueSource(strings = {"product", "pen", "Pen", "Pencil", "pencil"})
    public void testCreateConcreteItem(String kindName) throws CustomEventException {

        //when
        ItemAbstractFactory factory = new ItemAbstractFactory(kindName);
        ItemEntity entity = new ItemEntity();
        entity.setId("someId");
        entity.setName("somename");
        Item item = factory.createConcreteItem(entity);
        //then
        assertNotNull(item);
        assertEquals(item.getID(), entity.getId());
        assertEquals(item.getName(), entity.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"product","pen"})
    public void testGetItemClass(String kindName) throws CustomEventException {

        //when
        ItemAbstractFactory factory = new ItemAbstractFactory(kindName);
        Class clazz = factory.getItemClass();
        //then
        assertNotNull(clazz);
        System.out.println(clazz);
        assertEquals("com.org.somak.store.keyvalue.dto",clazz.getPackageName());

    }

    @ParameterizedTest
    @ValueSource(strings = {"1pen","$sks", "abc def", "abc ", " ", "123"})
    public void testGetItemClassInvalidKind(String kindName){

        //when & //then
        ItemAbstractFactory factory = new ItemAbstractFactory(kindName);
        assertThrows(CustomEventException.class,()->factory.getItemClass(),"Incorrect input for kind parameter");
    }

    @ParameterizedTest
    @ValueSource(strings = {"phone","animal"})
    public void testGetItemClassNotPresentKind(String kindName){

        //when & //then
        ItemAbstractFactory factory = new ItemAbstractFactory(kindName);
        assertThrows(CustomEventException.class,()->factory.getItemClass(),"Incorrect input for kind parameter");
    }

}
