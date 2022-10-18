/***
Source generated using apache freemarker
***/
package com.org.somak.store.keyvalue.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(as = ${name}.class)
@Builder
public class ${name} implements Item
{

    private String id;
    private String name;


    @Override
    public String getKind() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setID(String id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}