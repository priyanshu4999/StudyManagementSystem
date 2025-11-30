package com.airtripe.studymanagement.DatabasePersistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

// SINGLETON PPATTERN IMPLEMENTATION
public class MongoDBManager {

    private static final MongoDBManager instance = new MongoDBManager();

    private MongoClient client;
    private MongoDatabase database;

    private MongoDBManager() {

    }

    public static MongoDBManager getInstance() {
        return instance;
    }

    public void init(String uri, String dbName) {
        if (client == null) {
            client = MongoClients.create(uri);
            database = client.getDatabase(dbName);
        }
    }

    public MongoDatabase getDB() {
        return database;
    }
}
