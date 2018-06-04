package com.example.webdevsummer.model;

import javax.persistence.Entity;

@Entity
public class FillInTheBlankQuestion extends Question {
		
	private String variables;

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}
	
}
