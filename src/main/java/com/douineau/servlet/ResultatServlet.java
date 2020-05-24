package com.douineau.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.douineau.entity.User;

/**
 * Servlet implementation class ResultatServlet
 */
@WebServlet("/resultats")
public class ResultatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("resultats.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");	
		
//		for (Map.Entry<Question, Reponse> entry : user.getMap().entrySet()) {
//			
//		}
//		
		request.setAttribute("user", user);
		
		RequestDispatcher rd = request.getRequestDispatcher("resultats.jsp");
		rd.forward(request, response);
		
		}

}
