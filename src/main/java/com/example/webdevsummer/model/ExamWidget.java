package com.example.webdevsummer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class ExamWidget extends Widget {


	@OneToMany(mappedBy="exam",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private List<Question> questions;

	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	
	
}
