package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="student")
public class Student {
	
	@Id
	@Column
	String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEnglishscore() {
		return englishscore;
	}
	public void setEnglishscore(int englishscore) {
		this.englishscore = englishscore;
	}
	public int getMathscore() {
		return mathscore;
	}
	public void setMathscore(int mathscore) {
		this.mathscore = mathscore;
	}
	String email;
	int englishscore;
	int mathscore;
}
