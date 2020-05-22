package com.example.migrations;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@ChangeLog
public class InitialMigrationMongock {

    @ChangeSet(id = "InitialMigration", author = "migrator", order = "1")
    public void doMigrate(MongoDatabase db) {
        db.getCollection("users").insertOne(new Document().append("_id", "admin"));
    }
}
