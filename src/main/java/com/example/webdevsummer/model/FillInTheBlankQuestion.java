package com.example.webdevsummer.model;

import javax.persistence.Entity;

@Entity
public class FillInTheBlankQuestion extends Question {
		
	private String variables;
	private String fib;
	

	public String getFib() {
		return fib;
	}

	public void setFib(String fib) {
		this.fib = fib;
	}

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}
	
}
