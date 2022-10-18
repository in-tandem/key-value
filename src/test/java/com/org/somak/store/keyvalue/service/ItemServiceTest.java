package com.org.somak.store.keyvalue.service;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.dto.Product;
import com.org.somak.store.keyvalue.entity.ItemEntity;
import com.org.somak.store.keyvalue.exception.CustomEventException;
import com.org.somak.store.keyvalue.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ItemServiceTest {


    @Test
    public void testListByObjectKindFailed() throws CustomEventException {
        //given
        ItemRepository repository = spy(ItemRepository.class);
        ItemEntity entity = new ItemEntity();
        entity.setId("someId");
        entity.setName("someName");
        entity.setObjectKind("product1");
        List<ItemEntity> entityList = Arrays.asList(entity);
        //when
        ItemService service = new ItemService();
        ReflectionTestUtils.setField(service, "repository", repository);
        doReturn(entityList).when(repository).findAllByObjectKind(eq("product1"));

        //then
        assertThrows(CustomEventException.class, ()-> service.listObjects("product1"));

    }

    @Test
    public void testListByObjectKind() throws CustomEventException {
        //given
        ItemRepository repository = spy(ItemRepository.class);
        ItemEntity entity = new ItemEntity();
        entity.setId("someId");
        entity.setName("someName");
        entity.setObjectKind("product");
        List<ItemEntity> entityList = Arrays.asList(entity);
        //when
        ItemService service = new ItemService();
        ReflectionTestUtils.setField(service, "repository", repository);
        doReturn(entityList).when(repository).findAllByObjectKind(eq("product"));

        //then
        List<Item> item = service.listObjects("product");
        assertNotNull(item);
        assertTrue(item.size() == 1);
        assertTrue(item.get(0).getID().equals("someId"));
        assertTrue(item.get(0).getName().equals("someName"));
    }

    @Test
    public void testDeleteObjectFailed() throws CustomEventException {
        //given
        ItemRepository repository = spy(ItemRepository.class);
        ItemEntity entity = new ItemEntity();
        entity.setId("someId");
        entity.setName("someName");

        //when
        ItemService service = new ItemService();
        ReflectionTestUtils.setField(service, "repository", repository);
        doReturn(Optional.ofNullable(null)).when(repository).findById(eq("someId"));
        doNothing().when(repository).delete(any());
        //then
        assertThrows(NoSuchElementException.class, () -> service.deleteObject("someId"));

    }

    @Test
    public void testDeleteObject() throws CustomEventException {
        //given
        ItemRepository repository = spy(ItemRepository.class);
        ItemEntity entity = new ItemEntity();
        entity.setId("someId");
        entity.setName("someName");

        //when
        ItemService service = new ItemService();
        ReflectionTestUtils.setField(service, "repository", repository);
        doReturn(Optional.ofNullable(entity)).when(repository).findById(eq("someId"));
        doNothing().when(repository).delete(any());
        //then
        assertTrue(service.deleteObject("someId"));

    }

    @Test
    public void testGetByIdFail() throws CustomEventException {
        //given
        ItemRepository repository = spy(ItemRepository.class);
        ItemEntity entity = new ItemEntity();
        entity.setId("someId");
        entity.setName("someName");

        //when
        ItemService service = new ItemService();
        ReflectionTestUtils.setField(service, "repository", repository);
        doReturn(Optional.ofNullable(null)).when(repository).findById(eq("someId"));
        assertThrows(NoSuchElementException.class, () -> service.getById("someId"));

    }

    @Test
    public void testGetById() throws CustomEventException {
        //given
        ItemRepository repository = spy(ItemRepository.class);
        ItemEntity entity = new ItemEntity();
        entity.setId("someId");
        entity.setName("someName");
        entity.setObjectKind("product");

        //when
        ItemService service = new ItemService();
        ReflectionTestUtils.setField(service, "repository", repository);
        doReturn(Optional.ofNullable(entity)).when(repository).findById(eq("someId"));
        Item response = service.getById("someId");
        //then
        assertNotNull(response);
        assertEquals("someId", response.getID());
        assertEquals("someName", response.getName());

    }

    @Test
    public void testSaveItem() {
        //given
        Product p = new Product();
        p.setID("someId");
        p.setName("someName");
        ItemRepository repository = spy(ItemRepository.class);
        ItemEntity entity = new ItemEntity();
        entity.setId("someId");

        //when
        ItemService service = new ItemService();
        ReflectionTestUtils.setField(service, "repository", repository);
        doReturn(entity).when(repository).save(any());
        String response = service.saveItem(p);
        //then
        assertNotNull(response);
        assertEquals("someId", response);

    }

    @Test
    public void testGetObjectByName() throws CustomEventException {
        //given
        String name = "something";
        MongoTemplate template = spy(new MongoTemplate(new SimpleMongoClientDbFactory("mongodb://localhost/test")));
        ItemEntity entity = new ItemEntity();
        entity.setId("someId");
        entity.setObjectKind("product");
        entity.setName("somename");

        //when
        ItemService service = new ItemService();
        ReflectionTestUtils.setField(service, "template", template);
        doReturn(entity).when(template).findOne(any(), any());
        Item response = service.getObjectByName(name);

        //then
        assertNotNull(response);
        assertEquals("somename", response.getName());

    }

    @Test
    public void testGetObjectByNameFailed() throws CustomEventException {
        //given
        String name = "something";
        MongoTemplate template = spy(new MongoTemplate(new SimpleMongoClientDbFactory("mongodb://localhost/test")));
        ItemEntity entity = new ItemEntity();
        entity.setId("someId");
        entity.setObjectKind("product");
        entity.setName("somename");

        //when
        ItemService service = new ItemService();
        ReflectionTestUtils.setField(service, "template", template);
        doReturn(null).when(template).findOne(any(), any());
        //then
        assertThrows(NoSuchElementException.class, () -> service.getObjectByName(name));

    }
}
