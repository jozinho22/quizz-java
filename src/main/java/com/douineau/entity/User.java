package com.douineau.entity;

import java.util.Map;

public class User {
	
	private String name;
	private Integer score;
	private Map<Question, Reponse> map;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}

	
	public Map<Question, Reponse> getMap() {
		return map;
	}

	public void setMap(Map<Question, Reponse> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", score=" + score + ", map=" + map + "]";
	}
	
}
