package com.example.webdevsummer.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			 //System.out.println(topic);
			 List<Widget> widgets=topic.getWidgets();
			 Iterator<Widget> itr=widgets.iterator();
			 while(itr.hasNext()) {
				 Widget wid=itr.next();
				 if(wid.getWidgetType().equals("Assignment")) {
					 assignment.add((AssignmentWidget) wid);
				 }
			 }
			 System.out.println(assignment.size());
			 return assignment;
		 }
		 return null;
	}
	
}
