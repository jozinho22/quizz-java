package com.douineau.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douineau.dao.QuestionDao;
import com.douineau.entity.Question;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5384359235916855711L;

	private static List<Question> questions;
	
	private static Integer nbRestantes;

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
		
		questions = QuestionDao.getRandomQuestions(2);
		nbRestantes = questions.size();

		request.setAttribute("nbRestantes", nbRestantes);
		request.setAttribute("question", questions.get(0));
		
		RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		questions.remove(questions.get(0));
		nbRestantes -= 1 ;
		
		if (questions.size() == 0) {
			response.sendRedirect("fin.jsp");

		}
		
		request.setAttribute("nbRestantes", nbRestantes);
		request.setAttribute("question", questions.get(0));		
		
		RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
		rd.forward(request, response);

	}
}
