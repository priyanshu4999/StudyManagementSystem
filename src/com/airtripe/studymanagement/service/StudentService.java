package com.airtripe.studymanagement.service;

import com.airtripe.studymanagement.entity.Student;
import com.airtripe.studymanagement.exception.StudentNotFoundException;
import com.airtripe.studymanagement.DatabasePersistence.StudentRepository;

import java.util.List;

public class StudentService {

    private final StudentRepository repo = new StudentRepository();

    // -------------------------------------------------------
    // READ (ID)
    // -------------------------------------------------------
    public Student getStudentById(String id) {
        Student s = repo.findById(id);
        if (s == null) throw new StudentNotFoundException(id);
        return s;
    }

    // -------------------------------------------------------
    // READ (NAME SEARCH)
    // -------------------------------------------------------
    public List<Student> getStudentByName(String name) {
        return repo.findByName(name);
    }

    // -------------------------------------------------------
    // CREATE
    // -------------------------------------------------------
    public void createStudent(Student s) {
        repo.save(s);
    }

    // -------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------
    public void updateStudent(String id, Student s) {
        Student existing = repo.findById(id);
        if (existing == null) throw new StudentNotFoundException(id);

        repo.update(s);
    }

    // -------------------------------------------------------
    // DELETE
    // -------------------------------------------------------
    public void deleteStudent(String id) {
        Student existing = repo.findById(id);
        if (existing == null) throw new StudentNotFoundException(id);

        repo.delete(id);
    }

    // -------------------------------------------------------
    // READ ALL
    // -------------------------------------------------------
    public List<Student> listAllStudents() {
        return repo.findAll();
    }
}
