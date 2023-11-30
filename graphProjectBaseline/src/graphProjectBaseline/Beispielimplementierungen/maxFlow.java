package graphProjectBaseline.Beispielimplementierungen;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import graphProjectBaseline.Node;

//Diese Klasse Impelemtiert einen Maximalen Flussgraphen mit dem Ford-Fulkerson Algorithmus
//Der Algorithmus wird direkt im Konstruktor ausgeführt

public class maxFlow {
	
	private int quelle;
	private int senke;
	private int[][] graph;
	private int[] path;
	private int flow;
	private ArrayList<String> nodeNames;
	
	public maxFlow(int Pquelle, int Psenke, int[][] Pgraph, ArrayList<String> PnodeNames) { //Objekt Konstruktor
		long startTime = System.currentTimeMillis();
		this.graph = Pgraph;
		this.quelle = Pquelle;
		this.senke = Psenke;
		this.nodeNames = PnodeNames;

		this.flow = fordFulkersonNetFlow(Pgraph, Psenke,Pquelle);

		long endTime = System.currentTimeMillis();
		System.out.println("Das Flussnetzwerk wurde in " + (endTime-startTime) + "ms berechnet");
	}
	
	public int fordFulkersonNetFlow(int[][] residual, int sink, int origin) { //Hauptalgorithmus
		
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
				//Kaparität um dem Maximalen Fluss veringern
				residual[e][n] -= maxPathFlow;
				
				//Kapazität in die Gegenrichtung im den Maximalen Fluss des Pfades erhöhen
				residual[n][e] += maxPathFlow;	
				n = e;
			}
			//Gesamtdurchsatz des Netzwerks um den Maximalfluss des aktuellen Pfades erhöhen
			maxNetworkFlow+= maxPathFlow;
		}
		
		return maxNetworkFlow;
	}
	
	public Node[] getNeighbours(Node n) {	//Liefert alle Nachbarknoten von n als node array
		Node[] rtn = new Node[n.getedges().size()];
		
		for (int i = 0; i < rtn.length; i++) {
			rtn[i] = n.getedges().get(i).getDest();
		}
		return rtn;
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
    	System.out.println("Der maximale Durchfluss des Graphen von Knoten "+ this.nodeNames.get(quelle) + " nach "+
        this.nodeNames.get(senke)+" ist: " + this.flow);
    }


    
    
}
