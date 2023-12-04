package graphProjectBaseline;

import java.util.Scanner;
import java.util.ArrayList;

public class Dictionary {
	
	//Nimmt als Input eine Arrayliste auf String Arrays
	public Dictionary(ArrayList<String[]> list) {
		//hier muss die Datenstruktur, mit der das Wörterbuch dargestellt werden soll implementiert werden
	}

	public void insert(String[] pair) {
		
	}
	
	public void search(String str) {
		
	}
	
	public void delete(String str) {
		
	}
	
	
	public String[] collectSingleInput() {
        Scanner scanner = new Scanner(System.in);
        String[] inputs = new String[2];

        System.out.println("Erste Eingabe eingeben:");
        inputs[0] = scanner.nextLine();

        System.out.println("Zweite Eingabe eingeben:");
        inputs[1] = scanner.nextLine();

        return inputs;
	}
		
}
