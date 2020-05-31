package com.douineau.utils;

public class RequestUtil {

	public static String getRedirection(String servletPath, Integer nbRestantes) {

		String redirection = null;

		if (ServletEnum.GAME.getServletPath().equals(servletPath)
				|| ServletEnum.GAME.getJspPath().equals(servletPath)) {
			if (nbRestantes == 0) {
				redirection = ServletEnum.END_GAME.getServletRelativePath();
			}

		} else if (ServletEnum.END_GAME.getServletPath().equals(servletPath)
				|| ServletEnum.END_GAME.getJspPath().equals(servletPath)) {
			if (nbRestantes > 0) {
				redirection = ServletEnum.GAME.getServletRelativePath();
			}
		}

		return redirection;
	}

}
