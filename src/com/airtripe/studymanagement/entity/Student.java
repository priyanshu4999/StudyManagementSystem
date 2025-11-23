package com.airtripe.studymanagement.entity;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private LocalDate dateOfJoining;
    private List<Course> enrolledCourses =  new ArrayList<>();
    private float GPA;

    public float getGPA() {
        return GPA;
    }
    public void setGPA(float GPA) {
        this.GPA = GPA;
    }
    public Student(String name , String id){
        super(name, id);
    }
    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }
    public void dropOutCourse(Course course) {
        enrolledCourses.remove(course);
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    @Override
    public String getRole() {
        return "Is a Student";
    }
}
