package com.airtripe.studymanagement.DatabasePersistence;

import com.airtripe.studymanagement.entity.Enrollment;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class EnrollmentRepository {

    private final MongoCollection<Document> collection;

    public EnrollmentRepository() {
        MongoDatabase db = MongoDBManager.getInstance().getDB();
        this.collection = db.getCollection("enrollments");
    }

    // CREATE
    public void save(Enrollment e) {
        Document doc = new Document("_id", e.getId())
                .append("studentId", e.getStudentId())
                .append("courseId", e.getCourseId())
                .append("grade", e.getGrade());
        collection.insertOne(doc);
    }

    // READ
    public Enrollment findById(String id) {
        Document d = collection.find(eq("_id", id)).first();
        if (d == null) return null;

        return new Enrollment(
                d.getString("_id"),
                d.getString("studentId"),
                d.getString("courseId")

        );
    }

    public List<Enrollment> findAll() {
        List<Enrollment> list = new ArrayList<>();
        for (Document d : collection.find()) {
            list.add(new Enrollment(
                    d.getString("_id"),
                    d.getString("studentId"),
                    d.getString("courseId")

            ));
        }
        return list;
    }

    // UPDATE only grade
    public void updateGrade(String id, float grade) {
        collection.updateOne(eq("_id", id),
                new Document("$set", new Document("grade", grade)));
    }

    // DELETE
    public void delete(String id) {
        collection.deleteOne(eq("_id", id));
    }

    // SEARCH helpers
    public List<Enrollment> findByStudent(String studentId) {
        List<Enrollment> list = new ArrayList<>();
        for (Document d : collection.find(eq("studentId", studentId))) {
            list.add(new Enrollment(
                    d.getString("_id"),
                    d.getString("studentId"),
                    d.getString("courseId")
            ));
        }
        return list;
    }

    public List<Enrollment> findByCourse(String courseId) {
        List<Enrollment> list = new ArrayList<>();
        for (Document d : collection.find(eq("courseId", courseId))) {
            list.add(new Enrollment(
                    d.getString("_id"),
                    d.getString("studentId"),
                    d.getString("courseId")

            ));
        }
        return list;
    }
}