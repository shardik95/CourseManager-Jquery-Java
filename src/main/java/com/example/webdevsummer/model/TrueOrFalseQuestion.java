package com.example.webdevsummer.model;

import javax.persistence.Entity;




@Entity
public class TrueOrFalseQuestion extends Question {
	
	private int isTrue;

	public int getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(int isTrue) {
		this.isTrue = isTrue;
	}

	
	
	
	
}
