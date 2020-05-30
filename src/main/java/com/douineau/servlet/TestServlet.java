package com.douineau.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.douineau.utils.PrintUtil;
import com.douineau.utils.RequestUtil;
import com.douineau.utils.SessionUtil;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5384359235916855711L;

	private static Map<Question, Boolean> gameMap;
	private static Question currentQuestion;

	private static List<Reponse> currentReponses;

	private static final int TIME_OUT = 12;
	private final static Integer NB_QUESTIONS_TOTAL = 5;

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
		User user = (User) session.getAttribute("user");
//		PrintUtil.printInfo(this.getClass().getName(), request.getMethod(), "uuid de session",
//				user != null ? user.getUuid() : null);

		boolean checked = SessionUtil.checkSessionByUuid(user);

		if (!checked) {
			request.setAttribute("permission", "checked");
			request = SessionUtil.setThemeAttribute(request);

			PrintUtil.printDebut();

			List<Question> questions = QuestionDao.getRandomQuestionsJson(NB_QUESTIONS_TOTAL, 60);
			gameMap = new HashMap<Question, Boolean>();

			for (Question q : questions) {
				gameMap.put(q, false);
			}
//			PrintUtil.printInfo(this.getClass().getName(), request.getMethod(), "gameMap", gameMap);
//						questions = QuestionDao.getRandomQuestions(5, 20);

			User userToCreate = createUser();

			session.setAttribute("user", userToCreate);

			currentQuestion = getNextQuestion();
			currentQuestion.setTimeOut(TIME_OUT);

			setRequestAttributes(request);

			RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
			rd.forward(request, response);

		} else {
			request.setAttribute("permission", "checked");
			request = SessionUtil.setThemeAttribute(request);

			String redirection = RequestUtil.getRedirection(request.getServletPath(), user.getNbQuestionsRestantes());

			if (redirection != null) {
				response.sendRedirect(redirection);
			} else {
				
				setRequestAttributes(request);
				RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
				rd.forward(request, response);
			}
		}

	}

	private User createUser() {
		User user = new User();
		UUID uuid = UUID.randomUUID();
		user.setUuid(uuid.toString());
		user.setScore(0);
		user.setNbQuestionsTotal(NB_QUESTIONS_TOTAL);
		user.setNbQuestionsRestantes(NB_QUESTIONS_TOTAL);
		Map<Question, Reponse> map = new HashMap<Question, Reponse>();
		user.setMap(map);
		return user;
	}

	private void setRequestAttributes(HttpServletRequest request) {

		request.setAttribute("question", currentQuestion);

		currentReponses = currentQuestion.getReponses();
		request.setAttribute("reponses", currentReponses);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		boolean checked = SessionUtil.checkSessionByUuid(user);

		if (!checked) {
			response.sendRedirect("error");
		} else {
			request.setAttribute("permission", "checked");
			request = SessionUtil.setThemeAttribute(request);

			String redirection = RequestUtil.getRedirection(request.getServletPath(), user.getNbQuestionsRestantes());

			if (redirection != null) {
				response.sendRedirect(redirection);
			} else {

				String idQuestionStr = request.getParameter("id-question");
				Long idQuestion = Long.parseLong(idQuestionStr);

				currentQuestion = getQuestionById(idQuestion);
				Integer timeOut = Integer.parseInt(request.getParameter("time-out"));
				PrintUtil.printInfo(this.getClass().getName(), request.getMethod(), "time-out", timeOut.toString());
				currentQuestion.setTimeOut(timeOut);

				String idReponseStr = request.getParameter("id-reponse");

				if (idReponseStr != null) {
					Long idReponse = Long.parseLong(idReponseStr);

					for (Reponse reponse : currentReponses) {
						if (reponse.getId().equals(idReponse)) {
							Reponse ancienneReponse = questionAlreadyExistsInMap(user.getMap(), currentQuestion);
							if (ancienneReponse != null) {
								if (!ancienneReponse.equals(reponse) && ancienneReponse.getIsTrue()) {
									user.setScore(user.getScore() - 1);
								}
							} else {
								if (reponse.getIsTrue()) {
									user.setScore(user.getScore() + 1);
								}
							}
							user.getMap().put(currentQuestion, reponse);
							break;
						}
					}

				} else {
					user.getMap().put(currentQuestion, null);
				}
				gameMap.put(currentQuestion, true);

				user.setNbQuestionsRestantes(getNbQuestionsRestantes());
				PrintUtil.printInfo(this.getClass().getName(), request.getMethod(), "user.getNbQuestionsRestantes()",
						user.getNbQuestionsRestantes());

				// Fin du quizz
				if (user.getNbQuestionsRestantes() == 0) {

					RequestDispatcher rd = request.getRequestDispatcher("fin");
					rd.forward(request, response);
				}
				// Continuer
				else {

					currentQuestion = getNextQuestion();
					currentQuestion.setTimeOut(TIME_OUT);

					setRequestAttributes(request);

					RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
					rd.forward(request, response);
				}
			}
		}

	}

	private Reponse questionAlreadyExistsInMap(Map<Question, Reponse> map, Question currentQuestion) {

		Reponse reponseTemp = null;
		for (Map.Entry<Question, Reponse> entry : map.entrySet()) {
			if (entry.getKey().equals(currentQuestion)) {
				reponseTemp = entry.getValue();
				break;
			}
		}

		return reponseTemp;
	}

	private Question getQuestionById(Long idQuestion) {

		Question questionToFind = null;
		for (Map.Entry<Question, Boolean> entry : gameMap.entrySet()) {
			if (entry.getKey().getId().equals(idQuestion)) {
				questionToFind = entry.getKey();
				break;
			}
		}

		return questionToFind;
	}

	private Question getNextQuestion() {

		Question nextQuestion = null;
		for (Map.Entry<Question, Boolean> entry : gameMap.entrySet()) {
			if (!entry.getValue()) {
				nextQuestion = entry.getKey();
				break;
			}
		}

		return nextQuestion;

	}

	private Integer getNbQuestionsRestantes() {

		Integer nbQuestionsRestantes = 0;
		for (Map.Entry<Question, Boolean> entry : gameMap.entrySet()) {
			if (!entry.getValue()) {
				nbQuestionsRestantes += 1;
			}
		}

		return nbQuestionsRestantes;

	}

}
