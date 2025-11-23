package com.airtripe.studymanagement.service;

import com.airtripe.studymanagement.entity.Enrollment;
import com.airtripe.studymanagement.service.StudentService;
import com.airtripe.studymanagement.service.CourseService;

import java.util.*;

public class EnrollmentService {

    private final Map<String, Enrollment> enrollments = new HashMap<>();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment createEnrollment(String id, String studentId, String courseId) {
        studentService.getStudentById(studentId);
        courseService.getCourseById(courseId);
        Enrollment e = new Enrollment(id, studentId, courseId);
        enrollments.put(id, e);
        return e;
    }


    public Enrollment getEnrollmentById(String id) {
        Enrollment e = enrollments.get(id);
        if (e == null)
            throw new IllegalArgumentException("Enrollment with id " + id + " not found");
        return e;
    }


    public List<Enrollment> listAllEnrollments() {
        return new ArrayList<>(enrollments.values());
    }


    public List<Enrollment> getEnrollmentsByStudent(String studentId) {
        return enrollments.values().stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .toList();
    }


    public List<Enrollment> getEnrollmentsByCourse(String courseId) {
        return enrollments.values().stream()
                .filter(e -> e.getCourseId().equals(courseId))
                .toList();
    }


    public void updateGrade(String enrollmentId, float grade) {
        Enrollment e = enrollments.get(enrollmentId);

        if (e == null)
            throw new IllegalArgumentException("Enrollment with id " + enrollmentId + " not found");

        e.setGrade(grade);
    }

    // DELETE ENROLLMENT
    public void deleteEnrollment(String id) {
        if (!enrollments.containsKey(id))
            throw new IllegalArgumentException("Enrollment with id " + id + " not found");

        enrollments.remove(id);
    }
}
