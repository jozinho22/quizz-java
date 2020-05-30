package com.douineau.utils;

public class RequestUtil {
	
	public static String getRedirection(String servletPath, Integer nbRestantes)  {
		String redirection = null;
		if("/test".equals(servletPath) || "/test.jsp".equals(servletPath)) {
			if(nbRestantes == 0) {
				redirection = "fin";
			}
			
		} else if("/fin".equals(servletPath) || "/resultats".equals(servletPath)) {
			if(nbRestantes > 0) {
				redirection = "test";
			}
		} 
//		PrintUtil.printInfo("RequestUtil", "getRedirection", redirection, redirection);
		return redirection;
	}

}
