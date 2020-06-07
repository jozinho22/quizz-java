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
			
//			String redirection = RequestUtil.getRedirection(request.getServletPath(), user.getNbQuestionsRestantes());
//
//			if (redirection != null) {
//				response.sendRedirect(redirection);
//			} else {
				RequestDispatcher rd = request.getRequestDispatcher(ServletEnum.RESULTS.getJspPath());
				rd.forward(request, response);
//			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");

		// tri car les questions ne sont pas dans l'ordre
//		Map<Question, Reponse> sortedMap = new HashMap<Question, Reponse>();
//
//		List<Long> listIdQuestions = QuestionDao.getListIdQuestions();
//		for (Long l : listIdQuestions) {
//			for (Map.Entry<Question, Reponse> entry : user.getMap().entrySet()) {
//				if (entry.getKey().getId().equals(l)) {
//					sortedMap.put(entry.getKey(), entry.getValue());
//					break;
//				}
//			}
//		}
//
//		user.setMap(sortedMap);
//
//		System.out.println(sortedMap);
//		session.removeAttribute("user");
//		session.setAttribute("user", user);

		doGet(request, response);

	}

}
