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
import com.douineau.utils.ServletEnum;
import com.douineau.utils.SessionUtil;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5384359235916855711L;

	private static Map<Question, Boolean> gameMap;
	private static Question currentQuestion;
	
	private static Integer clock;

	private static User user;

	private final static Integer NB_QUESTIONS_TOTAL = 2;
	private final static Integer NB_QUESTIONS_POSSIBLES = 2;

	public static final int TIME_OUT = 12;

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GameServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		user = (User) session.getAttribute("user");
		boolean checked = SessionUtil.checkSessionByUuid(user);
		
		if (!checked) {
			response.sendRedirect(ServletEnum.ERROR.getServletRelativePath());
		} else {
			request.setAttribute("permission", "checked");
			request.setAttribute("question", currentQuestion);
			if(request.getParameter("theme") != null) {
				session.setAttribute("theme", request.getParameter("theme"));
			}
			
			String redirection = RequestUtil.getRedirection(request.getServletPath(), user.getNbQuestionsRestantes());

			if (redirection != null) {
				response.sendRedirect(redirection);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(ServletEnum.GAME.getJspPath());
				rd.forward(request, response);
			}
		}

	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// if init
		if(session.getAttribute("user") == null) {
			PrintUtil.printDebut();
			
			List<Question> questions = QuestionDao.getRandomQuestionsJson(NB_QUESTIONS_TOTAL, NB_QUESTIONS_POSSIBLES);
			gameMap = new HashMap<Question, Boolean>();

			for (Question q : questions) {
				gameMap.put(q, false);
			}

			user = createUser();

			session.setAttribute("user", user);

			currentQuestion = getNextQuestion();
			currentQuestion.setClock(TIME_OUT);

			doGet(request, response);
		} else {
			if(request.getParameter("id-question") != null) {
				currentQuestion = getQuestionById(request.getParameter("id-question"));
			}

			if (request.getParameter("id-reponse") != null) {
				Long idReponse = Long.parseLong(request.getParameter("id-reponse"));

				for (Reponse reponse : currentQuestion.getReponses()) {
					if (reponse.getId().equals(idReponse)) {
						user.getMap().put(currentQuestion, reponse);
						break;
					} else {
						user.getMap().put(currentQuestion, null);
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
				RequestDispatcher rd = request.getRequestDispatcher(ServletEnum.END_GAME.getServletRelativePath());
				rd.forward(request, response);
			} else {
				currentQuestion = getNextQuestion();
				currentQuestion.setClock(TIME_OUT);

				doGet(request, response);
			}
		}

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String clockStr = request.getParameter("clock");
		clock = Integer.parseInt(clockStr);
		currentQuestion.setClock(clock);

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

	private Question getQuestionById(String idQuestionStr) {

		Question question = null;
		for (Map.Entry<Question, Boolean> entry : gameMap.entrySet()) {
			Long idQuestion = Long.parseLong(idQuestionStr);
			if (entry.getKey().getId().equals(idQuestion)) {
				question = entry.getKey();
				break;
			}
		}

		return question;
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
