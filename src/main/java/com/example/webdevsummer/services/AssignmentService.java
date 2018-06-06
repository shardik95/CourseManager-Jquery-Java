package com.example.webdevsummer.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.model.AssignmentWidget;
import com.example.webdevsummer.model.Topic;
import com.example.webdevsummer.model.Widget;
import com.example.webdevsummer.repositories.AssignmentWidgetRepository;
import com.example.webdevsummer.repositories.TopicRepository;

@RestController
public class AssignmentService {
	
	@Autowired
	private AssignmentWidgetRepository assignmentRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@GetMapping("/api/assignment/{aid}")
	public AssignmentWidget getAssignmentById(@PathVariable("aid") int aid) {
		Optional<AssignmentWidget> data=assignmentRepository.findById(aid);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
		
	@GetMapping("api/assignment")
	public List<AssignmentWidget> findAllAssignments(){
		return (List<AssignmentWidget>) assignmentRepository.findAll();
	}
	
	@GetMapping("api/topic/{topicId}/assignment")
	public List<AssignmentWidget> findAssignmentByTopicId(@PathVariable("topicId") int topicId){
		 Optional<Topic> data = topicRepository.findById(topicId);
		 List<AssignmentWidget> assignment=new ArrayList<>();
		 if(data.isPresent()) {
			 Topic topic=data.get();
			 List<Widget> widgets=topic.getWidgets();
			 Iterator<Widget> itr=widgets.iterator();
			 while(itr.hasNext()) {
				 Widget wid=itr.next();
				 if(wid.getWidgetType().equals("Assignment")) {
					 Optional<AssignmentWidget> assgn=assignmentRepository.findById(wid.getId());
					 if(assgn.isPresent()) {
						 assignment.add(assgn.get());
					 }
				 }
			 }
			 return assignment;
		 }
		 return null;
	}
	
	@PostMapping("api/topic/{topicId}/assignment")
	public AssignmentWidget addAssignment(@PathVariable("topicId") int topicId, 
			@RequestBody AssignmentWidget assignmentWidget) {
		 Optional<Topic> data = topicRepository.findById(topicId);
		 if(data.isPresent()) {
			 Topic topic=data.get();
			 assignmentWidget.setTopic(topic);
			 return assignmentRepository.save(assignmentWidget);
		 }
		 return null;
	}
	
	@DeleteMapping("api/assignment/{aid}")
	public void deleteAssignment(@PathVariable("aid") int aid) {
		assignmentRepository.deleteById(aid);
	}
	
	@PutMapping("api/assignment/{aid}")
	public AssignmentWidget updateAssignment(@PathVariable("aid") int aid,@RequestBody AssignmentWidget newassignment) {
		Optional<AssignmentWidget> data=assignmentRepository.findById(aid);
		//System.out.println(aid);
		if(data.isPresent()) {
			AssignmentWidget assignWidget=data.get();
			System.out.println(assignWidget.getTitle());
			System.out.println(newassignment.getTitle());
			assignWidget.setText(newassignment.getText());
			assignWidget.setWidgetType(newassignment.getWidgetType());
			assignWidget.setDescription(newassignment.getDescription());
			assignWidget.setTitle(newassignment.getTitle());
			assignWidget.setPoints(newassignment.getPoints());
			assignmentRepository.save(assignWidget);
			return assignWidget;
		}
		return null;
	}
}
