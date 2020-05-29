package com.douineau.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douineau.entity.User;

public class SessionUtil {
	
	public static User checkSessionByUuidAndGetUser(HttpServletResponse response, HttpSession session) throws IOException {
		
		User user = null;
		String uuid = (String) session.getAttribute("uuid");
		
		if (uuid == null) {
			response.sendRedirect("error");
		} else {
			user = (User) session.getAttribute("user");
		}
		return user;
		
	}

}
