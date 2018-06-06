package com.example.webdevsummer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.model.ExamWidget;
import com.example.webdevsummer.model.FillInTheBlankQuestion;
import com.example.webdevsummer.model.TrueOrFalseQuestion;
import com.example.webdevsummer.repositories.ExamWidgetRepository;
import com.example.webdevsummer.repositories.FillInTheBlankRepository;

@RestController
public class FillInTheBlankService {
	
	@Autowired
	private ExamWidgetRepository examRepository;
	
	@Autowired
	private FillInTheBlankRepository fibRepository;
	
	@PostMapping("/api/exam/{eid}/blanks")
	public FillInTheBlankQuestion addQuestionByExamId(@PathVariable("eid") int eid,@RequestBody FillInTheBlankQuestion fib) {
		 Optional<ExamWidget> data=examRepository.findById(eid);
		 if(data.isPresent()) {
			 ExamWidget exam=data.get();
			 fib.setExam(exam);
			 return 	fibRepository.save(fib);
		 }
		return null;
	}
	
	@PutMapping("/api/question/{questionId}/blanks")
	public FillInTheBlankQuestion updateQuestion(@PathVariable("questionId") int questionId,@RequestBody FillInTheBlankQuestion newFIB) {
		Optional<FillInTheBlankQuestion> data=fibRepository.findById(questionId);
		if(data.isPresent()) {
			FillInTheBlankQuestion fib=data.get();
			fib.setTitle(newFIB.getTitle());
			fib.setDescription(newFIB.getDescription());
			fib.setSubtitle(newFIB.getSubtitle());
			fib.setPoints(newFIB.getPoints());
			fib.setVariables(newFIB.getVariables());
			fib.setFib(newFIB.getFib());
			fibRepository.save(fib);
			return fib;
		}
		return null;
	}
	
}
