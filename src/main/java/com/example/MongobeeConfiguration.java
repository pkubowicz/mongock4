package com.example;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongobeeConfiguration {

    @Bean
    public Mongobee mongobee(@Value("${spring.data.mongodb.uri}") String mongoUri) {
        Mongobee mongobee = new Mongobee(new MongoClientURI(mongoUri));
        mongobee.setChangeLogsScanPackage("com.example.migrations");
        return mongobee;
    }
}
