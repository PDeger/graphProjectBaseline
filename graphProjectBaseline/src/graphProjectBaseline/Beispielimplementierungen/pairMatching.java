package graphProjectBaseline.Beispielimplementierungen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ForkJoinWorkerThread;

//Hier wird der Ford-Fulkerson Algorithmus zum loesen des Bipartiten Matchings verwendet


public class pairMatching {
	
	private int quelle;
	private int senke;
	private int[][] graph;
	private int[] path;
	private int wholeNetworkflow;
	private int[][] flow;
	private ArrayList<String> nodeNames;
	
	public pairMatching(int Pquelle, int Psenke, int[][] Pgraph, ArrayList<String> PnodeNames) { //Objekt Konstruktor
		long startTime = System.currentTimeMillis();
		this.graph = Pgraph;
		this.quelle = Pquelle;
		this.senke = Psenke;
		this.nodeNames = PnodeNames;
		
		
		this.flow = fordFulkersonNetFlow(Pgraph, Psenke,Pquelle);
		//this.flow = buildFlowMatrix(flow);

		long endTime = System.currentTimeMillis();
		System.out.println("\nDas Flussnetzwerk wurde in " + (endTime-startTime) + "ms berechnet\n");
	}
	
	public int[][] fordFulkersonNetFlow(int[][] residual, int sink, int origin) { //Hauptalgorithmus
		
		int[][] finalFlowNumbers = new int[residual.length][residual.length];
		
		int maxNetworkFlow = 0;
		
		//Solange man noch einen neuen Pfad im Residualgraphen mit der Breitensuche findet
		while(BFSPathCheck(residual, origin, sink)) {
			
			//Maximalen Fluss als Maximalen Integer anlgen
			int maxPathFlow = Integer.MAX_VALUE;
			 
			//Durch das Rückwerts-traversieren des gefundenen Pfades im Residualgraphen von der Senke zur Quelle
			//den Maximalen Fluss des Graphen finden
			int n = sink;
			int e;
			while(n!=origin) {
				e = path[n];
				maxPathFlow = intMin(residual[e][n], maxPathFlow);
				n = e;
			}
			
			//Den Vorgang nach dem Selben Schema wiederholen und dabei den Fluss inerhalb des Residualgraphen 
			//entsprechend des vorher gefundenen maximalen Flusses Updaten
			n = sink;
			while(n!=origin) {
				e = path[n];
				
				//Durchfluss durch die Kanten des Graphen speichern
				finalFlowNumbers[e][n] += maxPathFlow;
				//Sicherstellen, dass eine Person immer nur eine Aufgabe erhält
				finalFlowNumbers[n][e] = 0;
				
				//Kaparität des Residualgraphen um dem Maximalen Fluss veringern
				residual[e][n] -= maxPathFlow;
				
				//Kapazität des Residualgraphen in die Gegenrichtung um den Maximalen Fluss des Pfades erhöhen
				residual[n][e] += maxPathFlow;	
				n = e;
			}
			//Gesamtdurchsatz des Netzwerks um den Maximalfluss des aktuellen Pfades erhöhen, wird als Zusatzinformation mit geführt
			wholeNetworkflow+= maxPathFlow;
		}
		
		System.out.println();
		for (int i = 0; i < finalFlowNumbers.length; i++) {
			for (int j = 0; j < finalFlowNumbers.length; j++) {
				System.out.print(residual[i][j] +" " );
			}
			System.out.print(" | "+nodeNames.get(i)+"\n");
		}
		
		return residual;
	}

	
	//Breitensuche, die im gegebenen Residualgraphen nach einem gueltingen Pfad sucht
	public boolean BFSPathCheck(int [][] residualGraph, int src, int dest) { 
		
		int [] searchPath = new int [residualGraph.length];
    	boolean pathFound = false;

    	//Array zum Speichern der in der Suche besuchten Knoten
        boolean [] visited = new boolean[residualGraph.length];

        //Queue fuer die Breitensuche anlegen
        Queue<Integer> queue = new LinkedList<>();

        //Quellknoten der Warteschlange hinzufuegen und als besucht markieren
        queue.add(src);
        searchPath[src] = -1;
        visited[src] = true;

        while(queue.isEmpty()==false){
        	//ersten Knotenindex aus der Warteschlange holen 
            int v = queue.poll();

            //Matrix horizontal durchlaufen und nach von Knoten u ausgehenden Kanten suchen
            for (int e = 0; e <residualGraph.length ; e++) {
            	//wenn der Knoten noch nicht besucht wurde und das Kantengewicht > 0 ist
                if(visited[e]==false && residualGraph[v][e]>0) {
                    queue.add(e);
                    searchPath[e] = v;
                    visited[e] = true;
                }
            }
        }
        //pruefen, ob man bei der Suche den Zielknoten erreicht hat, also ob es noch einen gueltigen Pfad im Residualraphen gibt
        pathFound = visited[dest];
        
        //gefundenen Pfad in Klassenvariable speichern, damit er auserhal der Methode verwendet werden kann
        path = searchPath;
        return pathFound;
    }
	
    public int intMin(int a, int b) { //liefert den kleineren der beiden Integer
    	if (a > b) {
    		return b;
    	} else {
    		return a;
    	} 
    }
    
    public void print() { //Ausgabemethode
    	int pairCounter = 0;
    	ArrayList<String> singles = new ArrayList<String>();
    	singles.addAll(nodeNames);
    	
    	//Superquelle und senke entfernen
    	singles.remove(singles.size()-1);
    	singles.remove(singles.size()-1);
    			
    	System.out.println("Die gefundenen Paare sehen wie folgt aus:\n");
    	for (int i = 0; i < (flow.length-2)/2; i++) {
			for (int j = 0; j < flow.length; j++) {
				
				if(flow[i][j] == 2) {
					pairCounter++;
					//Paarnamen von verheirateten Personen entfernen
					singles.remove(nodeNames.get(i));
					singles.remove(nodeNames.get(j));
					System.out.print(this.nodeNames.get(i) + " wird mit " + this.nodeNames.get(j) + " verheiratet.");
				}
			}
			System.out.println("\n---");
		}
    	
    	System.out.println("Mit den gegebenen Daten koennen <" + pairCounter + "> Paare gebildet werden ");

    	if(pairCounter != (graph.length-2)/2) {
    		System.out.println("Es konnte nicht fuer jeden ein Partner gefunden werden. :(");
    		for(String s : singles) {
    			System.out.println(s + " hat keinen Partner");
    		}
    	} else {

    		System.out.println("Es konnte fuer jeden ein passender Partner gefunden werden. :)");
    	}
    	

    }
}

