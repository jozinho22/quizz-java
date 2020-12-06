package com.douineau.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.douineau.utils.FileReader;
import com.douineau.utils.TopicEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionDao {

//	public static List<Question> getRandomQuestions(int nb, int bound) {
//    	
//    	StringBuilder sb = buildQuery(nb, bound);
//    	
//    	EntityManager em = PersistenceUtil.getEntityManager();
//
//		TypedQuery<Question> query = em.createQuery(sb.toString(), Question.class);
//		List<Question> questions = query.getResultList();
//		em.close();
//		
//		return questions;
//	}
	
	public static List<Question> getRandomQuestionsJson(int nbQuestionsTotal, String[] topics) {
		
		FileReader reader = new FileReader();
		File jsonFile = null;
		try {
//			jsonFile = reader.getFile("questions/datas.json");
			jsonFile = reader.getFile("datas/questionswithcodes.json");

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
//		List<Question> questions = (List<Question>) mapper.readValue(json, Question.class);
		
		List<Question> questions = null;
		try {
			questions = mapper.reader()
				      .forType(new TypeReference<List<Question>>() {})
				      .readValue(jsonFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Question> customQuestions = new ArrayList<Question>();
		
		List<Question> l =	questions.stream()
				.filter(q -> TopicEnum.JAVA.name().equals(q.getTopic()))
				.collect(Collectors.toList());
		
		customQuestions.addAll(l);
		
		// Ajout de questions sur un topic
		if(topics != null) {
			List<TopicEnum> topicList = new ArrayList<TopicEnum>();
			for(String s : topics) {
				for(TopicEnum t : TopicEnum.values()) {
					if(s.equals(t.name())) {
						topicList.add(t);
					}
				}
			}
			
			for(TopicEnum t : topicList) {
				List<Question> l1 =	questions.stream()
						.filter(q -> t.name().equals(q.getTopic()))
						.collect(Collectors.toList());
				
				customQuestions.addAll(l1);
			}
		}
		
		Collections.shuffle(customQuestions);

		// Set id pour les questions
		List<Question> randomQuestions = new ArrayList<Question>();

		for(int i = 0 ; i < nbQuestionsTotal ; i++) {
			customQuestions.get(i).setId(Long.valueOf(i));
			for(Reponse reponse : customQuestions.get(i).getReponses()) {
				reponse.setId(Long.valueOf(i));
			}
			randomQuestions.add(customQuestions.get(i));
		}
		
		
		return randomQuestions;
	}

	
}
