package com.example.springbootmonolith.repositories;

import com.example.springbootmonolith.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CourseRepository extends CrudRepository<Course, Integer> {

}
