package com.douineau.servlet;

import java.io.IOException;
import java.util.List;

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
	
	private static Integer score;

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
		score = 0;
		String inputName = request.getParameter("input-name");
		HttpSession session = request.getSession();
		session.setAttribute("input-name", inputName);
		
		if(questions == null) {
			questions = QuestionDao.getRandomQuestions(5, 10);
			nbQuestions = questions.size();
			session.setAttribute("time-out", 10);
		}
		nbRestantes = questions.size();
		setAttributes(request);

		RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
		rd.forward(request, response);
		
	}

	private void setAttributes(HttpServletRequest request) {
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
		
		String idReponse = request.getParameter("reponse");
		System.out.println(idReponse);
		
		if(idReponse != null) {
			Long id = Long.parseLong(idReponse);
			
			for(Reponse reponse : reponses) {
				if(reponse.getId().equals(id)) {
					if(reponse.getIsTrue()) {
						score += 1;
						break;
					}
				}
			}
			
		}
			
		questions.remove(questions.get(0));
		nbRestantes = questions.size();
		
		if (questions.size() == 0) {
			questions = null;
			request.setAttribute("score", score);
			request.setAttribute("nbQuestions", nbQuestions);

			RequestDispatcher rd = request.getRequestDispatcher("fin");
			rd.forward(request, response);
		} else {
			setAttributes(request);

			RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
			rd.forward(request, response);
		}
	}
}
