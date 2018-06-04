package com.example.webdevsummer.model;

import javax.persistence.Entity;

@Entity
public class MultipleChoiceQuestion extends Question {

	private int correctOption;
	private String Options;
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}
	public String getOptions() {
		return Options;
	}
	public void setOptions(String options) {
		Options = options;
	}
	
	
	
}
