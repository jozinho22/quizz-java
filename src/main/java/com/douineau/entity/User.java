package com.douineau.entity;

import java.util.Map;

public class User {
	
	private String uuid;
	private Integer score;
	private Map<Question, Reponse> map;
	private Integer nbQuestionsRestantes;
	private Integer nbQuestionsTotal;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public Integer getNbQuestionsRestantes() {
		return nbQuestionsRestantes;
	}

	public void setNbQuestionsRestantes(Integer nbQuestionsRestantes) {
		this.nbQuestionsRestantes = nbQuestionsRestantes;
	}

	public Integer getNbQuestionsTotal() {
		return nbQuestionsTotal;
	}

	public void setNbQuestionsTotal(Integer nbQuestionsTotal) {
		this.nbQuestionsTotal = nbQuestionsTotal;
	}

	@Override
	public String toString() {
		return "User [uuid=" + uuid + ", score=" + score + ", map=" + map + ", nbQuestionsRestantes="
				+ nbQuestionsRestantes + ", nbQuestionsTotal=" + nbQuestionsTotal + "]";
	}

}
