package com.douineau.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douineau.dao.QuestionDao;
import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.douineau.entity.User;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5384359235916855711L;

	private static List<Question> questions;
	private static Integer nbQuestions;
	private static boolean init;
	private static Question currentQuestion;

	private static List<Reponse> reponses;

	private static User user;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		if(request.getParameter("theme") != null) {
			String theme = (String) request.getParameter("theme");
			session.setAttribute("theme", theme);
		}
		
		if(!init) {
			init = true;

			String uuid = (String) request.getParameter("uuid");
			session.setAttribute("uuid", uuid);
			
			if (uuid == null) {
				response.sendRedirect("test.jsp");
			} else {
				createUser(uuid);

				session.setAttribute("user", user);

				questions = QuestionDao.getRandomQuestionsJson(5, 75);
				nbQuestions = questions.size();
				session.setAttribute("time-out", 20);

				currentQuestion = questions.get(0);
				setRequestAttributes(request);

				RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
				rd.forward(request, response);
			}
		} else {
			
			setRequestAttributes(request);
			
			RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
			rd.forward(request, response);
		}
		
	}

	private void createUser(String uuid) {
		user = new User();
		user.setUuid(uuid);
		user.setScore(0);
		Map<Question, Reponse> map = new HashMap<Question, Reponse>();
		user.setMap(map);
	}

	private void setRequestAttributes(HttpServletRequest request) {

		request.setAttribute("question", currentQuestion);

		reponses = currentQuestion.getReponses();
		request.setAttribute("reponses", reponses);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		if(request.getParameter("theme") != null) {
			String theme = (String) request.getParameter("theme");
			session.setAttribute("theme", theme);
		}

		String idQuestionStr = request.getParameter("id-question");
		Long idQuestion = Long.parseLong(idQuestionStr);
		
		currentQuestion = getQuestionById(idQuestion);
		
		String idReponseStr = request.getParameter("id-reponse");

		if (idReponseStr != null) {
			Long idReponse = Long.parseLong(idReponseStr);

			for (Reponse reponse : reponses) {
				if (reponse.getId().equals(idReponse)) {
					if(!questionAlreadyExistsInMap(currentQuestion, reponse)) {
						user.getMap().put(currentQuestion, reponse);
					}
					if (reponse.getIsTrue()) {
						user.setScore(user.getScore() + 1);
						break;
					}
				}
			}

		} else {
			user.getMap().put(currentQuestion, null);
		}

		questions.remove(currentQuestion);		
		System.out.println("questions.size() : " + questions.size());

		if (questions.size() == 0) {
			
			questions = null;
			init = false;
			
			request.setAttribute("nbQuestions", nbQuestions);

			RequestDispatcher rd = request.getRequestDispatcher("fin");
			rd.forward(request, response);
		} else {
			
			currentQuestion = questions.get(0);
			setRequestAttributes(request);
			
			RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
			rd.forward(request, response);
		}
	}

	private boolean questionAlreadyExistsInMap(Question currentQuestion, Reponse reponse) {
		boolean alreadyExists = false;
		for (Map.Entry<Question, Reponse> entry : user.getMap().entrySet()) {
			if(entry.getKey().equals(currentQuestion)) {
				entry.setValue(reponse);
				alreadyExists = true;
				break;
			}
		}
		return alreadyExists;
	}

	private Question getQuestionById(Long idQuestion) {
		Question question = null;
		for(Question q : questions) {
			if(q.getId().equals(idQuestion)) {
				question = q;
				break;
			}
		}
		return question;
	}

}
