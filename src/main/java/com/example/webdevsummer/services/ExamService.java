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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.model.ExamWidget;
import com.example.webdevsummer.model.Topic;
import com.example.webdevsummer.model.Widget;
import com.example.webdevsummer.repositories.ExamWidgetRepository;
import com.example.webdevsummer.repositories.TopicRepository;

@RestController
public class ExamService {
	
	@Autowired
	private ExamWidgetRepository examRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@GetMapping("/api/exam")
	public List<ExamWidget> findAllExams(){
		return (List<ExamWidget>) examRepository.findAll();
	}
	
	@PostMapping("/api/topic/{topicId}/exam")
	public ExamWidget addExam(@PathVariable("topicId") int topicId,@RequestBody ExamWidget exam) {
		Optional<Topic> data = topicRepository.findById(topicId);
		 if(data.isPresent()) {
			 Topic topic=data.get();
			 exam.setTopic(topic);
			 return examRepository.save(exam);
		 }
		 return null;
	}
	
	@GetMapping("/api/topic/{topicId}/exam")
	public List<ExamWidget> findExamForTopic(@PathVariable("topicId") int topicId){
		 Optional<Topic> data = topicRepository.findById(topicId);
		 List<ExamWidget> exams=new ArrayList<>();
		 if(data.isPresent()) {
			 Topic topic=data.get();
			 List<Widget> widgets=topic.getWidgets();
			 Iterator<Widget> itr=widgets.iterator();
			 while(itr.hasNext()) {
				 Widget wid=itr.next();
				 if(wid.getWidgetType().equals("Exam")) {
					 Optional<ExamWidget> exam1=examRepository.findById(wid.getId());
					 if(exam1.isPresent()) {
						 exams.add(exam1.get());
					 }
				 }
			 }
			 return exams;
		 }
		 return null;
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId) {
		examRepository.deleteById(examId);
	}
	
	
}
