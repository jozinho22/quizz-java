package com.douineau.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.douineau.utils.FileReader;
import com.douineau.utils.PersistenceUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionDao {

	private static List<Long> listIdQuestions;
	
	public static List<Long> getListIdQuestions() {
		return listIdQuestions;
	}

	public static List<Question> getRandomQuestions(int nb, int bound) {
    	
    	StringBuilder sb = buildQuery(nb, bound);
    	
    	EntityManager em = PersistenceUtil.getEntityManager();

		TypedQuery<Question> query = em.createQuery(sb.toString(), Question.class);
		List<Question> questions = query.getResultList();
		em.close();
		
		return questions;
	}
	
	public static List<Question> getRandomQuestionsJson(int nb, int bound) {
		
		FileReader reader = new FileReader();
		File jsonFile = null;
		try {
//			jsonFile = reader.getFile("questions/datas.json");
			jsonFile = reader.getFile("datas/questionswithquotes.json");

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(jsonFile);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
//		List<Question> questions = (List<Question>) mapper.readValue(json, Question.class);
		
		List<Question> questions = null;
		try {
			questions = mapper.reader()
				      .forType(new TypeReference<List<Question>>() {})
				      .readValue(jsonFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Integer> integers = new ArrayList<Integer>();
		
		for (int k = 0; k < nb ; k++) {
     		int r = getRandomNumber(bound, integers);
     		integers.add(r);
     	}
		
		List<Question> randomQuestions = new ArrayList<Question>();
		int k = 1;
		for(Integer i : integers) {
			questions.get(i).setId(Long.valueOf(i));
//			questions.get(i).setCreatedAt(new Date());
			
			for(Reponse reponse : questions.get(i).getReponses()) {
				reponse.setId(Long.valueOf(k++));
//				reponse.setCreatedAt(new Date());
			}
			randomQuestions.add(questions.get(i));
		}
		
		
		listIdQuestions = new ArrayList<Long>();
		for(Question q : randomQuestions) {
			System.out.println("id question : ");
			System.out.println(q.getId());
			listIdQuestions.add(q.getId());
			System.out.println("------------------");
			System.out.println(" id reponses : ");
			for(Reponse r : q.getReponses()) {
				System.out.println(r.getId());
			}
			System.out.println("------------------");
			System.out.println("------------------");
		}

		return randomQuestions;
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
