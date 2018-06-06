package com.example.webdevsummer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.model.ExamWidget;
import com.example.webdevsummer.model.MultipleChoiceQuestion;
import com.example.webdevsummer.repositories.ExamWidgetRepository;
import com.example.webdevsummer.repositories.MultipleChoiceQuestionRepository;

@RestController
public class MultipleChoiceService {
	
	@Autowired
	private ExamWidgetRepository examRepository;
	
	@Autowired
	private MultipleChoiceQuestionRepository multipleChoiceRepository;
	
	@PostMapping("/api/exam/{eid}/choice")
	public MultipleChoiceQuestion addQuestionByExamId(@PathVariable("eid") int eid,@RequestBody MultipleChoiceQuestion mcQuestion) {
		Optional<ExamWidget> data=examRepository.findById(eid);
		 if(data.isPresent()) {
			 ExamWidget exam=data.get();
			 mcQuestion.setExam(exam);
			 return 	multipleChoiceRepository.save(mcQuestion);
		 }
		return null;
	}
	
	@PutMapping("/api/question/{questionId}/choice")
	public MultipleChoiceQuestion updateQuestion(@PathVariable("questionId") int questionId,@RequestBody MultipleChoiceQuestion newMCQ) {
		Optional<MultipleChoiceQuestion> data=multipleChoiceRepository.findById(questionId);
		if(data.isPresent()) {
			MultipleChoiceQuestion mcq=data.get();
			mcq.setTitle(newMCQ.getTitle());
			mcq.setDescription(newMCQ.getDescription());
			mcq.setSubtitle(newMCQ.getSubtitle());
			mcq.setPoints(newMCQ.getPoints());
			mcq.setCorrectOption(newMCQ.getCorrectOption());
			mcq.setOptions(newMCQ.getOptions());
			multipleChoiceRepository.save(mcq);
			return mcq;
		}
		return null;
	}
}
