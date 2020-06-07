package com.douineau.utils;

import java.io.IOException;

import com.douineau.entity.User;

public class SessionUtil {
	
	public static boolean checkSessionByUuid(User user) throws IOException {
		
		boolean checked = false;

		if(user != null) {
			checked = true;
		}
		
		return checked;
	}

}
