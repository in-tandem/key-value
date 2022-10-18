package com.org.somak.store.keyvalue.repository;

import com.org.somak.store.keyvalue.entity.ItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<ItemEntity, String > {

    List<ItemEntity> findAllByObjectKind(String objectKind);
}
