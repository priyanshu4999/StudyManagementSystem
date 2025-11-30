package com.airtripe.studymanagement.DatabasePersistence;

import com.airtripe.studymanagement.entity.Course;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CourseRepository {

    private final MongoCollection<Document> collection;

    public CourseRepository() {
        MongoDatabase db = MongoDBManager.getInstance().getDB();
        this.collection = db.getCollection("courses");
    }

    public void save(Course c) {
        Document doc = new Document("_id", c.getId())
                .append("name", c.getName())
                .append("instructor", c.getInstructor());
        collection.insertOne(doc);
    }

    public Course findById(String id) {
        Document d = collection.find(eq("_id", id)).first();
        if (d == null) return null;

        return new Course(
                d.getString("_id"),
                d.getString("name"),
                d.getString("instructor")
        );
    }

    public List<Course> findAll() {
        List<Course> list = new ArrayList<>();
        for (Document d : collection.find()) {
            list.add(new Course(
                    d.getString("_id"),
                    d.getString("name"),
                    d.getString("instructor")
            ));
        }
        return list;
    }

    public void update(Course c) {
        Document updated = new Document("_id", c.getId())
                .append("name", c.getName())
                .append("instructor", c.getInstructor());

        collection.replaceOne(eq("_id", c.getId()), updated);
    }

    public void delete(String id) {
        collection.deleteOne(eq("_id", id));
    }
}
