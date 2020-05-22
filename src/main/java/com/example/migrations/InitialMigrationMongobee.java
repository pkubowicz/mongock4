package com.example.migrations;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@ChangeLog
public class InitialMigrationMongobee {

    @ChangeSet(id = "InitialMigration", author = "migrator", order = "1")
    public void doMigrate(MongoDatabase db) {
        db.getCollection("users").insertOne(new Document().append("_id", "admin"));
    }
}
