package com.douineau.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
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

	private static List<Question> questions;

//	@Named
	private static Question currentQuestion;

	private static User user;

	private final static Integer NB_QUESTIONS_TOTAL = 10;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GameServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintUtil.printFin();
		RequestDispatcher rd = request.getRequestDispatcher(ServletEnum.END_GAME.getServletRelativePath());
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// if init
		if (session.getAttribute("user") == null) {
			PrintUtil.printDebut();

			questions = QuestionDao.getRandomQuestionsJson(NB_QUESTIONS_TOTAL);

			user = createUser();

			TimerServlet.init = false;
			TimerServlet.endQuizz = false;

			session.setAttribute("user", user);

			currentQuestion = getNextQuestion();

			display(request, response);
		} else {

			if (TimerServlet.clock != null && Integer.parseInt(TimerServlet.clock) == 0) {
				doPost(request, response);
			} else {

				String idQuestionStr = request.getParameter("id-question");
				Long idQuestion = null;
				if (idQuestionStr != null) {
					idQuestion = Long.parseLong(idQuestionStr);
				}

				if (currentQuestion.getId().equals(idQuestion)) {
					System.out.println(request.getParameter("id-reponse"));
					if (request.getParameter("id-reponse") != null) {

						Long idReponse = Long.parseLong(request.getParameter("id-reponse"));

						for (Reponse reponse : currentQuestion.getReponses()) {
							if (reponse.getId().equals(idReponse)) {
								user.getMap().put(currentQuestion, reponse);
								break;
							}
						}
					} else {
						user.getMap().put(currentQuestion, null);
					}

					currentQuestion.setIsDone(true);
					user.setNbQuestionsRestantes(getNbQuestionsRestantes());

					// Fin du quizz
					if (user.getNbQuestionsRestantes() == 0) {
						doPost(request, response);
					} else {
						currentQuestion = getNextQuestion();
						PrintUtil.printNext();
						display(request, response);
					}
					
				} else {
					// F5
					display(request, response);
				}

			}

		}

	}

	private void display(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		user = (User) session.getAttribute("user");
		boolean checked = SessionUtil.checkSessionByUuid(user);

		if (!checked) {
			response.sendRedirect(ServletEnum.ERROR.getServletRelativePath());
		} else {
			request.setAttribute("permission", "checked");
			request.setAttribute("question", currentQuestion);
			if (request.getParameter("theme") != null) {
				session.setAttribute("theme", request.getParameter("theme"));
			}

			RequestUtil.redirect(request, response, user.getNbQuestionsRestantes());
		}
	}

	private User createUser() {
		User user = new User();
		UUID uuid = UUID.randomUUID();
		user.setUuid(uuid.toString());
		user.setScore(0);
		user.setNbQuestionsTotal(NB_QUESTIONS_TOTAL);
		user.setNbQuestionsRestantes(NB_QUESTIONS_TOTAL);
		Map<Question, Reponse> map = new LinkedHashMap<Question, Reponse>();
		user.setMap(map);
		return user;
	}

	public static Question getQuestionById(String idQuestionStr) {

		Question question = null;

		if (idQuestionStr != null) {
			for (Question q : questions) {
				Long idQuestion = Long.parseLong(idQuestionStr);
				if (q.getId().equals(idQuestion)) {
					question = q;
					break;
				}
			}
		} else {
			question = currentQuestion;
		}

		return question;
	}

	private Question getNextQuestion() {

		Question nextQuestion = null;
		for (Question q : questions) {
			if (!q.getIsDone().booleanValue()) {
				nextQuestion = q;
				break;
			}
		}

		return nextQuestion;
	}

	private Integer getNbQuestionsRestantes() {

		Integer nbQuestionsRestantes = 0;
		for (Question q : questions) {
			if (!q.getIsDone().booleanValue()) {
				nbQuestionsRestantes += 1;
			}
		}

		return nbQuestionsRestantes;
	}

}
