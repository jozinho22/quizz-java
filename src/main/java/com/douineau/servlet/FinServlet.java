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
import com.douineau.utils.PrintUtil;
import com.douineau.utils.RequestUtil;
import com.douineau.utils.SessionUtil;

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
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		PrintUtil.printInfo(this.getClass().getName(), request.getMethod(), "uuid de session", user.getUuid());

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
				RequestDispatcher rd = request.getRequestDispatcher("fin.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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

				RequestDispatcher rd = request.getRequestDispatcher("fin.jsp");
				rd.forward(request, response);
			}
		}
	}

}
