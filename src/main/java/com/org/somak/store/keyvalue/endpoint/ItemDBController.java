package com.org.somak.store.keyvalue.endpoint;

import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.exception.CustomEventException;
import com.org.somak.store.keyvalue.service.ItemService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/rest/store/")
@Validated
public class ItemDBController implements ItemDB {


    @Autowired
    private ItemService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public String storeObject(@Valid @RequestBody Item item) {
        item.setID(UUID.randomUUID().toString());
        return service.saveItem(item);
    }

    @GetMapping
    @Override
    @ResponseStatus(HttpStatus.OK)
    public Item getObjectByID(@Valid @NotBlank @RequestParam("id") String id) {
        try {
            return service.getById(id);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (CustomEventException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name")
    @Override
    @ResponseStatus(HttpStatus.OK)
    public Item getObjectByName(@Valid @NotBlank @RequestParam("name") String name) {
        try {
            return service.getObjectByName(name);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (CustomEventException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findAll")
    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<Item> listObjects(@Valid
                                  @NotBlank
                                  @Pattern(regexp = "^[A-Za-z]+$", message = "Please provide proper input for kind, It needs " +
                                          "to be a single alphabetical word")
                                  @RequestParam("kind") String kind) {

        try {
            return service.listObjects(kind);
        } catch (CustomEventException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping
    @Override
    public boolean deleteObject(@Valid @NotBlank @RequestParam("id") String id) {
        try {
            return service.deleteObject(id);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
}
