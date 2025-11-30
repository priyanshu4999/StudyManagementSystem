package com.airtripe.studymanagement.DatabasePersistence;

import com.airtripe.studymanagement.entity.Student;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

public class StudentRepository {

    private final MongoCollection<Document> collection;

    public StudentRepository() {
        MongoDatabase db = MongoDBManager.getInstance().getDB();
        this.collection = db.getCollection("students");
    }

    // CREATE
    public void save(Student s) {
        Document doc = new Document("_id", s.getId())
                .append("name", s.getName());
        collection.insertOne(doc);
    }

    // FIND BY ID
    public Student findById(String id) {
        Document d = collection.find(eq("_id", id)).first();
        if (d == null) return null;
        return new Student(d.getString("name"), d.getString("_id"));
    }

    // FIND ALL
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        for (Document d : collection.find()) {
            list.add(new Student(d.getString("name"), d.getString("_id")));
        }
        return list;
    }

    // UPDATE
    public void update(Student s) {
        Document updated = new Document("_id", s.getId())
                .append("name", s.getName());
        collection.replaceOne(eq("_id", s.getId()), updated);
    }

    // DELETE
    public void delete(String id) {
        collection.deleteOne(eq("_id", id));
    }

    // SEARCH BY NAME
    public List<Student> findByName(String name) {
        List<Student> list = new ArrayList<>();
        for (Document d : collection.find(regex("name", name, "i"))) { // case-insensitive
            list.add(new Student(d.getString("name"), d.getString("_id")));
        }
        return list;
    }
}
