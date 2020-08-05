package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.Course;

public interface CourseService {
    public Iterable<Course> listCourse();
    public Course createCourse(Course course);
}
