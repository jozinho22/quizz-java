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

	private static List<Reponse> reponses;

	private static Integer nbRestantes;

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

		String uuid = (String) request.getParameter("uuid");

		if (uuid == null) {
			response.sendRedirect("test.jsp");
		} else {
			createUser(uuid);

			session.setAttribute("user", user);

			if (questions == null) {
				questions = QuestionDao.getRandomQuestionsJson(5, 75);
				nbQuestions = questions.size();
				session.setAttribute("time-out", 20);
			}

			nbRestantes = questions.size();
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

		reponses = questions.get(0).getReponses();

		request.setAttribute("nbRestantes", nbRestantes);
		request.setAttribute("question", questions.get(0));
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

		String idReponse = request.getParameter("reponse");

		if (idReponse != null) {
			Long id = Long.parseLong(idReponse);

			for (Reponse reponse : reponses) {
				if (reponse.getId().equals(id)) {
					user.getMap().put(questions.get(0), reponse);
					if (reponse.getIsTrue()) {
						user.setScore(user.getScore() + 1);
						break;
					}
				}
			}

		} else {
			user.getMap().put(questions.get(0), null);
		}

		questions.remove(questions.get(0));
		nbRestantes = questions.size();

		if (questions.size() == 0) {

			questions = null;
			session.setAttribute("nbQuestions", nbQuestions);

			RequestDispatcher rd = request.getRequestDispatcher("fin");
			rd.forward(request, response);
		} else {

			setRequestAttributes(request);

			RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
			rd.forward(request, response);
		}
	}

}
