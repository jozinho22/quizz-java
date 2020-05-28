package com.douineau.utils;

public class TagBuilder {
	
	public static String buildTagResultats(String tag, String texte) {
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
	
	public static String buildTagResultats(String tag, String texte, String style) {
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
		if(theme == null || "dark".equals(theme)) {
			sb.append("<link id=\"importCss\" rel=\"stylesheet\" href=\"css/stylesheet-dark.css\">");
		} else if("light".equals(theme)) {
			sb.append("<link id=\"importCss\" rel=\"stylesheet\" href=\"css/stylesheet-light.css\">");
		}	
		return sb.toString();
	}

	public static String buildTagThemeButton(String theme) {
		StringBuilder sb = new StringBuilder();
		if(theme == null || "dark".equals(theme)) {
			sb.append("<button class=\"btn\" type=\"submit\">Passer en mode light</button>");
		} else if("light".equals(theme)) {
			sb.append("<button class=\"btn\" type=\"submit\">Passer en mode dark</button>");
		}	
		return sb.toString();
	}
}
