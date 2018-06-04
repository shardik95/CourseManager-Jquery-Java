package com.example.webdevsummer.model;

import javax.persistence.Entity;

@Entity
public class TrueOrFalseQuestion extends Question {
	private boolean isTrue;

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
	
	
	
}
