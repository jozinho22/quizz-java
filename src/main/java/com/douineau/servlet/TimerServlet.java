package com.douineau.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TimerServlet
 */
@WebServlet("/timer")
public class TimerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public final static Integer TIME_OUT = 300;
	
	public static String clock;
	
	public static boolean init;
	public static boolean endQuizz;

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TimerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(!init) {
			clock = TIME_OUT.toString();
			init = true;
		} else if(endQuizz) {
			Integer k = 0;
			clock = k.toString();
		} 
		
		response.getWriter().write(clock.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		clock = request.getParameter("clock");
		
		if(Integer.parseInt(clock) == 0) {
			endQuizz = true;
		}

	}

}
