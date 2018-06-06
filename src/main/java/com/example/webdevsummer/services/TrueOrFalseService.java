package com.example.webdevsummer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.model.ExamWidget;
import com.example.webdevsummer.model.TrueOrFalseQuestion;
import com.example.webdevsummer.repositories.ExamWidgetRepository;
import com.example.webdevsummer.repositories.TrueOrFalseRepository;

@RestController
public class TrueOrFalseService {

	@Autowired
	private ExamWidgetRepository examRepository;
	
	@Autowired
	private TrueOrFalseRepository tfRepository;
	
	@PostMapping("/api/exam/{eid}/truefalse")
	public TrueOrFalseQuestion addQuestionByExamId(@PathVariable("eid") int eid,@RequestBody TrueOrFalseQuestion tfQuestion) {
		 Optional<ExamWidget> data=examRepository.findById(eid);
		 if(data.isPresent()) {
			 ExamWidget exam=data.get();
			 tfQuestion.setExam(exam);
			 return 	tfRepository.save(tfQuestion);
		 }
		return null;
	}
	
}
