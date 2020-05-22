package com.douineau.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.douineau.entity.Question;
import com.douineau.utils.PersistenceUtil;

public class QuestionDao {

	public static List<Question> getRandomQuestions(int nb, int bound) {
    	
    	StringBuilder sb = buildQuery(nb, bound);
    	
    	EntityManager em = PersistenceUtil.getEntityManager();

		TypedQuery<Question> query = em.createQuery(sb.toString(), Question.class);
		List<Question> questions = query.getResultList();
		em.close();
		
		return questions;
	}

	private static StringBuilder buildQuery(int nb, int bound) {
		
		List<Integer> integers = new ArrayList<Integer>();
    	
     	for (int k = 0; k < nb ; k++) {
     		int r = getRandomNumber(bound, integers);
     		integers.add(r);
     	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT q FROM Question q WHERE q.id in (");
    	for(int i : integers) {
    		sb.append(i);	
    		sb.append(",");
    	}
    	sb.deleteCharAt(sb.length() - 1);
    	sb.append(")");
    	
		return sb;
	}
	
	public static int getRandomNumber(int bound, List<Integer> integers) {
		
		Random random = new Random();

		// +1 pour éviter le 0
		int r = random.nextInt(bound) + 1;
		if(integers.size() == 0) {
			return r;
		} else {
			for(Integer i : integers) {
	 			if(i == r) {
	 				return getRandomNumber(bound, integers);
	 			}
	 		}
		}
		return r;
		
	}
	
}
