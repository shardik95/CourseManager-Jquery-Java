package com.example.webdevsummer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.model.ExamWidget;
import com.example.webdevsummer.model.Question;
import com.example.webdevsummer.repositories.ExamWidgetRepository;
import com.example.webdevsummer.repositories.QuestionRepository;

@RestController
public class QuestionService {
	
	@Autowired
	private ExamWidgetRepository examRepository;
	
	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/api/exam/{eid}/question")
	public List<Question> getQuestionByExamId(@PathVariable("eid") int eid){
		Optional<ExamWidget> data=examRepository.findById(eid);
		 if(data.isPresent()) {
			 ExamWidget exam=data.get();
			 return exam.getQuestions();
		 }
		 return null;
	}
	
	@DeleteMapping("api/question/{questionId}")
	public void deleteQuestion(@PathVariable("questionId") int questionId) {
		questionRepository.deleteById(questionId);
	}
	
}
