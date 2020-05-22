package com.example.mongock3;

import com.github.cloudyrock.mongock.SpringBootMongock;
import com.github.cloudyrock.mongock.SpringBootMongockBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@Profile("mongock3")
public class Mongock3Configuration {

    @Bean
    public SpringBootMongock mongock(MongoTemplate mongoTemplate, ApplicationContext applicationContext) {
        return new SpringBootMongockBuilder(mongoTemplate, "com.example.mongock3.migrations")
                .setApplicationContext(applicationContext)
                .setChangeLogCollectionName("dbchangelog") // compatibility with mongobee
//                .setLockCollectionName("mongobeelock") // DONT DO THIS mongobee will fail saying a different index exists
                .setLockQuickConfig()
                .build();
    }
}
