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
import com.douineau.utils.PrintUtil;
import com.douineau.utils.RequestUtil;
import com.douineau.utils.SessionUtil;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
				RequestDispatcher rd = request.getRequestDispatcher("resultats.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
				
				// tri car les questions ne sont pas dans l'ordre
				Map<Question, Reponse> sortedMap = new HashMap<Question, Reponse>();

				List<Long> listIdQuestions = QuestionDao.getListIdQuestions();
				for (Long l : listIdQuestions) {
					for (Map.Entry<Question, Reponse> entry : user.getMap().entrySet()) {
						if (entry.getKey().getId().equals(l)) {
							sortedMap.put(entry.getKey(), entry.getValue());
							break;
						}
					}
				}

				user.setMap(sortedMap);

				session.removeAttribute("user");
				session.setAttribute("user", user);

				RequestDispatcher rd = request.getRequestDispatcher("resultats.jsp");
				rd.forward(request, response);
			}
		}
	}

}
