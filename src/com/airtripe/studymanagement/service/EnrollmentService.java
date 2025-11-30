package com.airtripe.studymanagement.service;

import com.airtripe.studymanagement.entity.Enrollment;
import com.airtripe.studymanagement.entity.Student;
import com.airtripe.studymanagement.entity.Course;

import com.airtripe.studymanagement.DatabasePersistence.EnrollmentRepository;

import java.util.List;

public class EnrollmentService {

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentRepository repo = new EnrollmentRepository();

    public EnrollmentService(StudentService ss, CourseService cs) {
        this.studentService = ss;
        this.courseService = cs;
    }

    // --------------------------------------------------------
    // CREATE
    // --------------------------------------------------------
    public Enrollment createEnrollment(String id, String studentId, String courseId) {

        // Validate student + course
        Student student = studentService.getStudentById(studentId);
        if (student == null)
            throw new RuntimeException("Student not found: " + studentId);

        Course course = courseService.getCourseById(courseId);
        if (course == null)
            throw new RuntimeException("Course not found: " + courseId);

        Enrollment e = new Enrollment(id, studentId, courseId);
        repo.save(e);

        // Keep your original behavior:
        student.enrollInCourse(course);

        return e;
    }

    // --------------------------------------------------------
    // READ
    // --------------------------------------------------------
    public Enrollment getEnrollmentById(String id) {
        Enrollment e = repo.findById(id);
        if (e == null)
            throw new RuntimeException("Enrollment not found: " + id);
        return e;
    }

    public List<Enrollment> getEnrollmentsByStudent(String studentId) {
        return repo.findByStudent(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(String courseId) {
        return repo.findByCourse(courseId);
    }

    public List<Enrollment> listAllEnrollments() {
        return repo.findAll();
    }

    // --------------------------------------------------------
    // UPDATE
    // --------------------------------------------------------
    public void updateGrade(String enrollmentId, float grade) {
        Enrollment e = repo.findById(enrollmentId);
        if (e == null)
            throw new RuntimeException("Enrollment not found: " + enrollmentId);

        repo.updateGrade(enrollmentId, grade);
    }

    // --------------------------------------------------------
    // DELETE
    // --------------------------------------------------------
    public void deleteEnrollment(String id) {
        Enrollment e = repo.findById(id);
        if (e == null)
            throw new RuntimeException("Enrollment not found: " + id);

        repo.delete(id);
    }
}
