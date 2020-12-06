package com.douineau.utils;

public class TagBuilder {
	
	public static String buildTagResults(String tag, String texte) {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(tag);
		sb.append(">");
		sb.append(texte);
		sb.append("</");
		sb.append(tag);
		sb.append(">");
		
		return sb.toString();
	}
	
	public static String buildTagResults(String tag, String texte, String style) {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(tag);
		sb.append(" style=\"" + style + "\">");
		sb.append(texte);
		sb.append("</");
		sb.append(tag);
		sb.append(">");
		
		return sb.toString();
	}
	
	public static String buildTagImportCss(String theme) {
		StringBuilder sb = new StringBuilder();
		if("dark".equals(theme)) {
			sb.append("<link id=\"importCss\" rel=\"stylesheet\" href=\"css/stylesheet-dark.css\">");
		} else if("light".equals(theme)) {
			sb.append("<link id=\"importCss\" rel=\"stylesheet\" href=\"css/stylesheet-light.css\">");
		}	
		return sb.toString();
	}

	public static String buildTagThemeButton(String theme) {
		StringBuilder sb = new StringBuilder();
		if("dark".equals(theme)) {
			sb.append("<button class=\"btn\" type=\"submit\">Passer en mode light</button>");
		} else if("light".equals(theme)) {
			sb.append("<button class=\"btn\" type=\"submit\">Passer en mode dark</button>");
		}	
		return sb.toString();
	}
	
	public static String buildTagCheckBoxes(Boolean isDone) {
		StringBuilder sb = new StringBuilder();
		sb.append("<input name=\"id-reponse\" value=\"${reponse.id}\" type=\"checkbox\" class=\"form-check-input\"");
		
		if(isDone) {
			sb.append("disabled=\"disabled\"");
		} 
		
		sb.append(">");
		
		return sb.toString();
	}
	
	public static String buildTagTimer() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div id=\"timer-section\">");
		sb.append("Temps restant : ");
		sb.append("<input id=\"clock\" type=\"text\" name=\"clock\" disabled=\"disabled\">");
		sb.append("s.");
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static String buildTagJavaImage() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		sb.append("<img src=\"img/java.png\" width=\"100\" height=\"100\">");
		sb.append("</div>");
		
		return sb.toString();
	}
}
