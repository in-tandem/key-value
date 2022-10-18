package com.org.somak.store.keyvalue.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.org.somak.store.keyvalue.serde.ItemDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonDeserialize(using = ItemDeserializer.class)
@Schema(description = "Type object for persisting in key value store",title = "Item")
public interface Item {

    //getKind() returns the kind (i.e., type) of the Item.
    @Schema(description = "kind of key value pair")
    @NotNull
    @Pattern(regexp="^[A-Za-z]+$", message = "Please provide proper input for kind, It needs " +
            "to be a single alphabetical word")
    String getKind();

    @Schema(description = "Unique Identifier for the type, not required for save operation")
    // getID() returns a unique UUID for the Item.
    String getID();

    // getName returns the name of the Item. Names are not unique.
    @NotNull
    @Schema(description = "Name of Object you would like to persist")
    String getName();


    // setID sets the ID of the Item.
    void setID(String id);

    @NotNull
    // setName sets the name of the Item.
    void setName(String name);

}
