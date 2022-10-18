package com.org.somak.store.keyvalue.entity;

import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "item")
@Getter
@Setter
@Builder
public class ItemEntity {

    @Id
    private String id;
    @NonNull
    private String objectKind;
    @NonNull
    private String name;

}
