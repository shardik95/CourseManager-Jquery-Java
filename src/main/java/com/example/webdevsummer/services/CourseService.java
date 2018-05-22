package com.example.webdevsummer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.model.Course;
import com.example.webdevsummer.repositories.CourseRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class CourseService {

	@Autowired
	CourseRepository repository;
	
	@GetMapping("api/course")
	public Iterable<Course> findAllCourses(){
		return repository.findAll();
	}
	
	@PostMapping("api/course")
	public Course createCourse(@RequestBody Course course) {
		return this.repository.save(course);
	}
	
	@DeleteMapping("api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int courseId) {
		 repository.deleteById(courseId);
	}
	
	@GetMapping("/api/course/{id}")
	public Course findCourseById(@PathVariable("id") int id) {
		Optional<Course> data = repository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PutMapping("/api/course")
	public Course updateCourse(@RequestBody Course newcourse) {
		 Optional<Course> data = repository.findById(newcourse.getId());
		 if(data.isPresent()) {
			 Course course=data.get();
			 course.setTitle(newcourse.getTitle());
			 course.setModified(newcourse.getModified());
			 repository.save(course);
			 return newcourse;
		 }
		 return null;
	}
}
