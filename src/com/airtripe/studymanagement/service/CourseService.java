package com.airtripe.studymanagement.service;

import com.airtripe.studymanagement.entity.Course;
import com.airtripe.studymanagement.exception.CourcesNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseService {

    private final Map<String , Course> courses = new HashMap<>();

    public void addCourse(Course course)
    {
        courses.put(course.getId(), course);
    }
    public Course getCourseById(String id)
    {   if (!courses.containsKey(id)) throw new CourcesNotFoundException(id);
        return courses.get(id);
    }

    public List<Course> listAllCourses()
    {
        return new ArrayList<>(courses.values());
    }

    public void deleteCourse(String id)
    {
        if (!courses.containsKey(id)) throw new CourcesNotFoundException(id);
        courses.remove(id);
    }
    public void updateCourse(String id, Course course)
    {
        if (!courses.containsKey(id)) throw new CourcesNotFoundException(id);
        courses.put(id, course);
    }
    public List<Course> getCourseByName(String name)
    {
        return courses.values().stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
