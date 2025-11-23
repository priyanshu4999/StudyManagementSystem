package com.airtripe.studymanagement;

import com.airtripe.studymanagement.entity.*;
import com.airtripe.studymanagement.service.*;
import com.airtripe.studymanagement.util.DateUtil;

public class Main {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

        System.out.println("===== STUDENT MANAGEMENT TEST =====");

        // --------------------------------------------------------------------
        // 1. Create Students
        // --------------------------------------------------------------------
        Student s1 = new Student("Sonu", "S1");
        Student s2 = new Student("Monu", "S2");

        studentService.createStudent(s1);
        studentService.createStudent(s2);

        System.out.println("Students added:");
        studentService.listAllStudents().forEach(x ->
                System.out.println(x.getId() + " -> " + x.getName())
        );


        // --------------------------------------------------------------------
        // 2. Create Courses (using DateUtil.Builder)
        // --------------------------------------------------------------------

        DateUtil startDate = new DateUtil.Builder()
                .year(2025)
                .month(2)
                .date(14)
                .build();

        DateUtil endDate = new DateUtil.Builder()
                .year(2025)
                .month(5)
                .date(14)
                .build();

        Course c1 = new Course("C1", "Maths", "Raju Sir");
        c1.setStartDate(startDate.getDateTime().toLocalDate());
        c1.setEndDate(endDate.getDateTime().toLocalDate());

        Course c2 = new Course("C2", "Physics", "Pinky Mam");
        c2.setStartDate(startDate.getDateTime().toLocalDate());
        c2.setEndDate(endDate.getDateTime().toLocalDate());

        courseService.addCourse(c1);
        courseService.addCourse(c2);

        System.out.println("\nCourses added:");
        courseService.listAllCourses().forEach(x ->
                System.out.println(x.getId() + " -> " + x.getName() +
                        " (Starts: " + x.getStartDate() + ")")
        );


        // --------------------------------------------------------------------
        // 3. Enroll Students
        // --------------------------------------------------------------------
        Enrollment e1 = enrollmentService.createEnrollment("E1", "S1", "C1");
        Enrollment e2 = enrollmentService.createEnrollment("E2", "S1", "C2");

        System.out.println("\nEnrollments for Sonu (S1):");
        enrollmentService.getEnrollmentsByStudent("S1").forEach(x ->
                System.out.println(x.getId() + " -> Course: " + x.getCourseId())
        );


        // --------------------------------------------------------------------
        // 4. Update Grade
        // --------------------------------------------------------------------
        enrollmentService.updateGrade("E1", 88.5f);

        System.out.println("\nUpdated Grade for E1:");
        System.out.println("Grade: " + enrollmentService.getEnrollmentById("E1").getGrade());


        System.out.println("\n===== END =====");
    }
}
