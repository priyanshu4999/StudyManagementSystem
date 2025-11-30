package com.airtripe.studymanagement.service;

import com.airtripe.studymanagement.entity.Course;
import com.airtripe.studymanagement.exception.CourcesNotFoundException;
import com.airtripe.studymanagement.DatabasePersistence.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CourseService {

    private final CourseRepository repo = new CourseRepository();

    // -------------------------------------------------------
    // CREATE
    // -------------------------------------------------------
    public void addCourse(Course course) {
        repo.save(course);
    }

    // -------------------------------------------------------
    // READ
    // -------------------------------------------------------
    public Course getCourseById(String id) {
        Course c = repo.findById(id);
        if (c == null) throw new CourcesNotFoundException(id);
        return c;
    }

    public List<Course> listAllCourses() {
        return repo.findAll();
    }

    // -------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------
    public void updateCourse(String id, Course newCourse) {
        Course existing = repo.findById(id);
        if (existing == null) throw new CourcesNotFoundException(id);

        repo.update(newCourse);  // new method in repository
    }

    // -------------------------------------------------------
    // DELETE
    // -------------------------------------------------------
    public void deleteCourse(String id) {
        Course c = repo.findById(id);
        if (c == null) throw new CourcesNotFoundException(id);

        repo.delete(id);
    }

    // -------------------------------------------------------
    // SEARCH (Name contains)
    // -------------------------------------------------------
    public List<Course> getCourseByName(String name) {
        return repo.findAll()
                .stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
