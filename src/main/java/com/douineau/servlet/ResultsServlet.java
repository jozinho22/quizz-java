package com.douineau.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douineau.entity.User;
import com.douineau.utils.RequestUtil;
import com.douineau.utils.ServletEnum;
import com.douineau.utils.SessionUtil;

/**
 * Servlet implementation class ResultsServlet
 */
@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResultsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		boolean checked = SessionUtil.checkSessionByUuid(user);

		if (!checked) {
			response.sendRedirect(ServletEnum.ERROR.getServletRelativePath());
		} else {
			request.setAttribute("permission", "checked");
			if(request.getParameter("theme") != null) {
				session.setAttribute("theme", request.getParameter("theme"));
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(ServletEnum.RESULTS.getJspPath());
			rd.forward(request, response);
//			RequestUtil.redirect(request, response, user.getNbQuestionsRestantes());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
