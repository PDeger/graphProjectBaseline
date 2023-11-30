package graphProjectBaseline;

import java.util.Scanner;

public class InputHandler {
	
	private Scanner scn = new Scanner(System.in);
	
	public InputHandler() {
		
	}
	
	//Liest einen String ein und gibt ihn zurück
	public String getInput() {
		System.out.println("Einen Wert eingeben.");
		return scn.nextLine();
	}
	
	
	//Liest einen String ein und gibt ihn als Int zurück, wenn er eine gültige Zahl ist
	public int getNumericInput() {
		String s;
		do {
			System.out.println("Eine Zahl eingeben.");
			s = scn.nextLine();
		}
		while(!isNumeric(s));
		
		return Integer.parseInt((s));
	}
	
	private boolean isNumeric(String str) {
		boolean matches = str.matches("-?\\d+(\\.\\d+)?");
		if (!matches) System.out.println(str + " ist keine Zahl");
		return matches;
	}
}
