package com.example.webdevsummer.model;

import javax.persistence.Entity;

@Entity
public class MultipleChoiceQuestion extends Question {

	private String correctOption;
	private String Options;
	
	
	public String getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}
	public String getOptions() {
		return Options;
	}
	public void setOptions(String options) {
		Options = options;
	}
	
	
	
}
