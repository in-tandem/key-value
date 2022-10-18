package com.org.somak.store.keyvalue.serde;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.exception.CustomEventException;
import com.org.somak.store.keyvalue.factory.ItemAbstractFactory;

import java.io.IOException;

public class ItemDeserializer extends JsonDeserializer<Item> {

    @Override
    public Item deserialize(JsonParser p, DeserializationContext context) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        ObjectNode root = mapper.readTree(p);
        if (root.has("kind")) {
            ItemAbstractFactory factory = new ItemAbstractFactory(root.get("kind").asText());
            try {
                return mapper.readValue(root.toString(), factory.getItemClass());
            } catch (CustomEventException e) {
                throw new IOException("Bad Request.Please provide proper input for kind, It needs " +
                        "to be a single alphabetical word");
            }
        }
        return null;
    }
}

