package com.example.mongock4;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.SpringDataMongo2Driver;
import com.github.cloudyrock.spring.MongockSpring5;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@Profile("mongock4")
public class Mongock4Configuration {

    @Bean
    public InitializingBean mongock(MongoTemplate mongoTemplate, ApplicationContext applicationContext) {
        SpringDataMongo2Driver driver = new SpringDataMongo2Driver(mongoTemplate);
        driver.setChangeLogCollectionName("dbchangelog"); // compatibility with mongobee
        driver.setLockCollectionName("mongobeelock");

        return MongockSpring5.builder()
                .setDriver(driver)
                .addChangeLogsScanPackage("com.example.mongock4.migrations")
                .setDefaultLock()
                .setSpringContext(applicationContext)
                .buildInitializingBeanRunner();
    }
}
