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
import com.douineau.utils.RequestUtil;
import com.douineau.utils.ServletEnum;
import com.douineau.utils.SessionUtil;

/**
 * Servlet implementation class EndGameServlet
 */
@WebServlet("/end-game")
public class EndGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndGameServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			user.setScore(calculateScore(user.getMap()));
			session.setAttribute("user", user);
			
			RequestDispatcher rd = request.getRequestDispatcher(ServletEnum.END_GAME.getJspPath());
			rd.forward(request, response);
//			RequestUtil.redirect(request, response, user.getNbQuestionsRestantes());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private Integer calculateScore(Map<Question, Reponse> map) {
		
		Integer score = 0;
		for (Map.Entry<Question, Reponse> entry : map.entrySet()) {
			if (entry.getValue() != null && entry.getValue().getIsTrue().booleanValue()) {
				score += 1;
			}
		}
		
		return score;
		
	}

}
