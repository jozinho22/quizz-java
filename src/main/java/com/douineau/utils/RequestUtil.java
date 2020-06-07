package com.douineau.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestUtil {

	public static String getRedirection(String servletPath, Integer nbRestantes) {

		String redirection = null;

		if (ServletEnum.GAME.getServletPath().equals(servletPath)
				|| ServletEnum.GAME.getJspPath().equals(servletPath)) {
			if (nbRestantes == 0) {
				redirection = ServletEnum.END_GAME.getServletRelativePath();
			} else {
				redirection = ServletEnum.GAME.getJspPath();
			}

		} else if (ServletEnum.END_GAME.getServletPath().equals(servletPath)
				|| ServletEnum.END_GAME.getJspPath().equals(servletPath)) {
				redirection = ServletEnum.END_GAME.getJspPath();
				
		} else if ( ServletEnum.RESULTS.getServletPath().equals(servletPath)
				|| ServletEnum.RESULTS.getJspPath().equals(servletPath)) {
			if (nbRestantes > 0) {
				redirection = ServletEnum.GAME.getServletRelativePath();
			} else {
				redirection = ServletEnum.RESULTS.getJspPath();
			}
		}

		return redirection;
	}

	public static void redirect(HttpServletRequest request, HttpServletResponse response, Integer nbRestantes)
			throws IOException, ServletException {
		String redirection = getRedirection(request.getServletPath(), nbRestantes);

		RequestDispatcher rd = request.getRequestDispatcher(redirection);
		rd.forward(request, response);
	}

}
