package com.douineau.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ThemeServlet
 */
@WebServlet("/theme")
public class ThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThemeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("theme") != null) {
			if (session.getAttribute("theme").equals("dark")) {
				session.removeAttribute("theme");
				session.setAttribute("theme", "normal");
			} else if (session.getAttribute("theme").equals("normal")) {
				session.removeAttribute("theme");
				session.setAttribute("theme", "dark");
			}
			session.removeAttribute("theme");
		} else {
			session.setAttribute("theme", "normal");
		}
		
		System.out.println(request.getServletContext());
		System.out.println(request.getServletContext().getContextPath());

		System.out.println(request.getServletContext().getServerInfo());

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
