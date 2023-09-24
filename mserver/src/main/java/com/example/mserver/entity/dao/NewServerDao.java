package com.example.mserver.entity.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@Document(collection = "server")
public class NewServerDao {

    @Id
    @Field("id")
    private String id;
    @Field("name")
    private String name;
    @Field("language")
    private String language;
    @Field("framework")
    private String framework;

}
