package com.douineau.utils;

public class TagBuilder {
	
	public static String buildTag(String tag, String texte) {
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
	
	public static String buildTag(String tag, String texte, String style) {
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

}
