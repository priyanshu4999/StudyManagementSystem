package com.airtripe.studymanagement.service;

import com.airtripe.studymanagement.entity.Student;
import com.airtripe.studymanagement.exception.StudentNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentService {
    private final Map<String , Student> students = new HashMap<>();

    public Student getStudentById(String id) {
        Student res = students.get(id);
        if (res == null){
            throw new StudentNotFoundException(id);
        }
        return res;

    }
    public List<Student> getStudentByName(String name){
        return  students.values().stream().filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    public void createStudent(Student s) {
        students.put(s.getId(), s);
    }
    public void updateStudent(String id, Student s ) {
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        students.put(id, s);
    }
    public void deleteStudent(String id) {
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        students.remove(id);
    }
    public List<Student> listAllStudents() {
        return new ArrayList<>(students.values());
    }

}
