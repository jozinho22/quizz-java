package com.douineau.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douineau.utils.RequestUtil;

/**
 * Servlet implementation class FinServlet
 */
@WebServlet("/fin")
public class FinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("fin.jsp");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(this.getClass().getName() + " doPost - theme = " + request.getParameter("theme"));
		System.out.println("--------------------------------");

		request = RequestUtil.setThemeAttribute(request);
		
		System.out.println(this.getClass().getName() + " doPost - nbQuestions = " + request.getAttribute("nbQuestions"));
		System.out.println("--------------------------------");
		request.setAttribute("nbQuestions", request.getAttribute("nbQuestions"));
		
		RequestDispatcher rd = request.getRequestDispatcher("fin.jsp");
		rd.forward(request, response);	
	}

}
