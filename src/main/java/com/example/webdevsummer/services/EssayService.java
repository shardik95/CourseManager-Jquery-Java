package com.example.webdevsummer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.model.EssayQuestion;
import com.example.webdevsummer.model.ExamWidget;
import com.example.webdevsummer.repositories.EssayQuestionRepository;
import com.example.webdevsummer.repositories.ExamWidgetRepository;

@RestController
public class EssayService {
	@Autowired
	private ExamWidgetRepository examRepository;
	
	@Autowired
	private EssayQuestionRepository essayRepository;
	
	
	@PostMapping("/api/exam/{eid}/essay")
	public EssayQuestion addQuestionByExamId(@PathVariable("eid") int eid,@RequestBody EssayQuestion essQuestion) {
		 Optional<ExamWidget> data=examRepository.findById(eid);
		 if(data.isPresent()) {
			 ExamWidget exam=data.get();
			 essQuestion.setExam(exam);
			 return 	essayRepository.save(essQuestion);
		 }
		return null;
	}
	
	@PutMapping("/api/question/{questionId}/essay")
	public EssayQuestion updateQuestion(@PathVariable("questionId") int questionId,@RequestBody EssayQuestion newEssay) {
		Optional<EssayQuestion> data=essayRepository.findById(questionId);
		if(data.isPresent()) {
			EssayQuestion essay=data.get();
			essay.setTitle(newEssay.getTitle());
			essay.setDescription(newEssay.getDescription());
			essay.setSubtitle(newEssay.getSubtitle());
			essay.setPoints(newEssay.getPoints());
			essayRepository.save(essay);
			return essay;
		}
		return null;
	}
}
