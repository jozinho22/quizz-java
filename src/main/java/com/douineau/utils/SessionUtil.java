package com.douineau.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.douineau.entity.User;

public class SessionUtil {
	
	public static boolean checkSessionByUuid(User user) throws IOException {
		
		boolean checked = false;

		if(user != null) {
			checked = true;
		}
		
		return checked;
	}
	
	public static HttpServletRequest setThemeAttribute(HttpServletRequest request)  {
		HttpSession session = request.getSession();
		
		String theme = (String) request.getParameter("theme");
		if(theme != null) {
			session.setAttribute("theme", theme);
			PrintUtil.printInfo("SessionUtil", "setThemeAttribute", "theme", theme);
		}
		
		return request;
	}

}
