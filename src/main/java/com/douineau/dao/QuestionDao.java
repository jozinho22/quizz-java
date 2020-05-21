package com.douineau.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.douineau.entity.Question;
import com.douineau.utils.PersistenceUtil;

public class QuestionDao {

	public static List<Question> getRandomQuestions(int nb) {
    	EntityManager em = PersistenceUtil.getEntityManager();
    	
    	Random random = new Random();
    	// Set pour ne pas avoir deux fois la même question
    	Set<Integer> integers = new HashSet<Integer>();
    	
     	while(integers.size() < nb) {
    		//Eviter le zéro 
    		integers.add(random.nextInt(nb) + 1);
    	}
    	
    	StringBuilder sb = new StringBuilder();
//    	sb.append("SELECT q FROM Question q WHERE q.id in (1, 2)");
    	sb.append("SELECT q FROM Question q WHERE q.id in (");
    	for(Integer i : integers) {
    		sb.append(i);	
    		sb.append(",");
    	}
    	sb.deleteCharAt(sb.length() - 1);
    	sb.append(")");
    	
		TypedQuery<Question> query = em.createQuery(sb.toString(), Question.class);
		List<Question> questions = query.getResultList();
		em.close();
		
		return questions;
	}
	
}
