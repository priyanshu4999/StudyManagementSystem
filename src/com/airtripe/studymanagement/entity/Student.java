package com.airtripe.studymanagement.entity;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
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

    private LocalDate dateOfJoining;
    private List<Course> enrolledCourses =  new ArrayList<>();

    @Override
    public String getRole() {
        return "Is a Student";
    }
}
