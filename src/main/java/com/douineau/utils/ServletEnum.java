package com.douineau.utils;

public enum ServletEnum {

	INDEX("/index", "index", "/index.jsp"), GAME("/game", "game", "/game.jsp"), END_GAME("/end-game", "end-game", "/end-game.jsp"),
	RESULTS("/results", "results", "/results.jsp"), ERROR(null, "error", null);
 
	private String servletPath;
	private String servletRelativePath;
	private String jspPath;

	ServletEnum(String servletPath, String servletRelativePath, String jspPath) {
		this.servletPath = servletPath;
		this.servletRelativePath = servletRelativePath;
		this.jspPath = jspPath;
	}

	public String getServletPath() {
		return servletPath;
	}

	public String getServletRelativePath() {
		return servletRelativePath;
	}

	public String getJspPath() {
		return jspPath;
	}

}
