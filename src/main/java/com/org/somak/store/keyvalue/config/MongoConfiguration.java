package com.org.somak.store.keyvalue.config;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfiguration {

    @Bean
    public MongoTemplate mongoTemplate(@Value("${data.name}") String dataBaseName,
                                       @Value("${data.username}") String userName, @Value("${data.password}") String password
            , @Value("${spring.data.mongodb.uri}") String connectionString) throws Exception {
        MongoCredential credential = MongoCredential.createCredential(userName, dataBaseName, password.toCharArray());
        MongoTemplate template =  new MongoTemplate(mongo(connectionString), dataBaseName);
        template.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        int maxStaleness = 91;
        template.setReadPreference(ReadPreference.secondary(maxStaleness, TimeUnit.SECONDS));
        return template;
    }

    @Bean
    public MongoClient mongo(String connectionString) {
        return MongoClients.create(connectionString);
    }


    public static TransactionOptions getTransactionOptions() {
        return TransactionOptions.builder()
                .readPreference(ReadPreference.primary())
                .readConcern(ReadConcern.SNAPSHOT)
                .writeConcern(WriteConcern.MAJORITY)
                .build();
    }

}
