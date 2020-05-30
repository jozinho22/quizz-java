package com.douineau.utils;

public class PrintUtil {
	
	public static void printDebut() {
		System.out.println("");
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("");
		System.out.println("NOUVEAU TEST");
		System.out.println("");
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("");
	}
	
	public static void printInfo(String className, String methode, String parameter, Object valeur) {
		System.out.println("---------------------");
		System.out.println("INFO from : " + className + " - " + "methode : " + methode);
		System.out.println("parametre : " + parameter);
		System.out.println("valeur : " + valeur);
		System.out.println("---------------------");
	}

}
