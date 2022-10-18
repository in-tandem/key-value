package com.org.somak.store.keyvalue.service;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.entity.ItemEntity;
import com.org.somak.store.keyvalue.exception.CustomEventException;
import com.org.somak.store.keyvalue.factory.ItemAbstractFactory;
import com.org.somak.store.keyvalue.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private MongoTemplate template;

    public String saveItem(Item item){
        return repository.save(convert(item)).getId();
    }

    public List<Item> listObjects(String kind) throws CustomEventException {

        List<Item> listItem = new ArrayList<>();
        List<ItemEntity> entityList = repository.findAllByObjectKind(kind);
        ItemAbstractFactory factory = new ItemAbstractFactory(kind);
        for(ItemEntity e: entityList){
            listItem.add(factory.createConcreteItem(e));
        }
        return listItem;
    }

    public Item getObjectByName(String name) throws CustomEventException {

        Criteria criteria = Criteria.where("name").is(name);
        Query query = new Query(criteria);
        ItemEntity itemEntity = template.findOne(query,ItemEntity.class);
        requireNonNull(itemEntity);
        ItemAbstractFactory factory = new ItemAbstractFactory(itemEntity.getObjectKind());
        return factory.createConcreteItem(itemEntity);

    }

    private void requireNonNull(ItemEntity itemEntity) {
        if(itemEntity==null)
            throw new NoSuchElementException();

    }

    public Item getById(String id) throws CustomEventException {
        ItemEntity item = repository.findById(id).orElseThrow();
        ItemAbstractFactory factory = new ItemAbstractFactory(item.getObjectKind());
        return factory.createConcreteItem(item);
    }

    public boolean deleteObject(String id)
    {
        repository.delete(repository.findById(id).orElseThrow());
        return true;
    }

    private ItemEntity convert(Item item) {
        return  ItemEntity
                .builder()
                .id(item.getID())
                .objectKind(item.getKind())
                .name(item.getName())
                .build();
    }
}
