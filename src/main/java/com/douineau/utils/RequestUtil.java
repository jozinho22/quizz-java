package com.douineau.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	public static HttpServletRequest setThemeAttribute(HttpServletRequest request)  {
		
		String theme = (String) request.getParameter("theme");
		request.setAttribute("theme", theme);
		
		return request;
	}

}
