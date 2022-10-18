package com.org.somak.store.keyvalue.endpoint;

import com.org.somak.store.keyvalue.dto.Item;

import java.util.List;

public interface ItemDB {

    // Store will store the object in the data store. The object will have a
    // name and kind, and the store() method should create a unique ID.
    String storeObject(Item item);

    // getObjectByID will retrieve the object with the provided ID.
    Item getObjectByID(String id);

    // getObjectByName will retrieve the object with the given name.
    Item getObjectByName(String name);

    // listObjects will return a list of all objects of the given kind.
    List<Item> listObjects(String kind);

    // deleteObject will delete the object.
    boolean deleteObject(String id);


}
