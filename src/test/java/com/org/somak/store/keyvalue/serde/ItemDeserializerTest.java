package com.org.somak.store.keyvalue.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.somak.store.keyvalue.dto.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemDeserializerTest {

    @Test
    public void testDeserialize() throws JsonProcessingException {
        String json = "{\"name\":\"joe\", \"id\":\"someid\"}";

        Product response = new ObjectMapper().readValue(json, Product.class);
        assertNotNull(response);
        assertEquals("joe", response.getName());


    }
}
