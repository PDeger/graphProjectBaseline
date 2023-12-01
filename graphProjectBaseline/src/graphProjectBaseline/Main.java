package graphProjectBaseline;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Name der Datei
		String fileName = "10x10Graph.txt";
		//Name des Verzeichnisses, in dem die Datei liegt
		String dir = "weightedDirectionalGraphs";
		
		/*
		 * Akzeptiert "Matrix" oder "List", gibt an, in welcher form der Graph ausgelesen wird. 
		 * Nur die Matrixdarstellung ist implementiert, die Listendarstellung müsst ihr selbst implementieren,
		 * wenn ihr sie verwenden wollt. 
		 * Hilfestellung für die Listendarstellung: Objekte für die Kanten und Knoten liegen schon vor -> Siehe "Node" und "Edge". 
		 * Es gibt auch ander Möglichekiten für die Implementierung, dies soll nur als Denkanstoß dienen
		 */
		String type = "Matrix";

		/*
		 * Den Input Handler könnt ihr einem Objekten oder Funktionen als Parameter mitgeben, 
		 * um eine Einfache Eingabefunktion zu haben
		 * Er hat 2 Funktionen: 
		 * get input: leist eine beliebige Zeichenfolge aus der Konsole ein und gibt sie als String wieder
		 * getNumericInput: Liest eine Zeile ein und gibt sie als Integer weider. Ist die Eingabe kein gültiger Int,
		 * wird die Eingabeaufforderung so lange wiederholt, bis man einen gültigen Int eingibt
		 * Verwendung:
		 * InputHandler in = new InputHandler();
		 * String s = in.getInput();
		 * int i = in.getNumericInput();
		 */
		InputHandler in = new InputHandler();
		
		Graph g = new Graph(fileName, dir);
		System.out.println();
	}

}
