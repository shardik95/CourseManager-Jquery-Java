package com.example.webdevsummer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer.model.Course;

public interface CourseRepository extends CrudRepository<Course, Integer>{
	
}