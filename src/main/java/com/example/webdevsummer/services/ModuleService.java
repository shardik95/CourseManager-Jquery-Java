package com.example.webdevsummer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.model.Course;
import com.example.webdevsummer.model.Module;
import com.example.webdevsummer.repositories.CourseRepository;
import com.example.webdevsummer.repositories.LessonRepository;
import com.example.webdevsummer.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class ModuleService {

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@PathVariable("courseId") int courseId, @RequestBody Module module) {
			Optional<Course> data= courseRepository.findById(courseId);
			if(data.isPresent()) {
				Course course=data.get();
				module.setCourse(course);
				return moduleRepository.save(module);
			}
			return null;
	}
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;		
	}
	
	@DeleteMapping("/api/course/{courseId}/module/{moduleId}")
	public void deleteModuleById(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId) {
		
		moduleRepository.deleteById(moduleId);
	}
	
	@GetMapping("/api/module")
	public  Iterable<Module> findAllModules(){
		return moduleRepository.findAll();
	}
	
	
}
