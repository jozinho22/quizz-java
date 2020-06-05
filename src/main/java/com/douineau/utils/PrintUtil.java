package com.douineau.utils;

public class PrintUtil {
	
	public static void printDebut() {
		System.out.println("");
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("");
		System.out.println("NOUVEAU QUIZZ");
		System.out.println("");
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("");
	}
	
	public static void printNext() {
		System.out.println("");
		System.out.println("------------------------------");
		System.out.println("Question suivante");
		System.out.println("------------------------------");
		System.out.println("");
	}
	
	public static void printFin() {
		System.out.println("");
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("");
		System.out.println("FIN DU QUIZZ");
		System.out.println("");
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("");
	}
	
	public static void printInfo(String className, String methode, String parameter, Object valeur) {
		System.out.println("---------------------");
		System.out.println(className + " - " + methode);
		System.out.println(parameter + " = " + valeur);
		System.out.println("---------------------");
	}

}
