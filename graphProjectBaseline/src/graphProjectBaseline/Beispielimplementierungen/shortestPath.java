package graphProjectBaseline.Beispielimplementierungen;

import java.util.ArrayList;
import java.util.PriorityQueue;

import graphProjectBaseline.Node;

//Diese Klasse Impelemtiert einen das Finden eines kuerzesten Pfades mit dem Dijkstra Algorithmus
//Wichtig: Diese Implementierung Arbeitet mit eine Adjazenzliste mit "Node" Objekten und verwendet entsprechend deren Methoden
//Der Algorithmus wird direkt im Konstruktor ausgeführt


public class shortestPath {
	private Node[] graph;
	private Node start;
	
	public shortestPath(Node[] Pgraph, Node Pstart) {
		long startTime = System.currentTimeMillis();
		
		this.start = Pstart;
		this.graph = dijkstra(Pgraph, Pstart);
		
		long endTime = System.currentTimeMillis();
		System.out.println("Der kuerzeste Pfad wurde in " + (endTime-startTime) + "ms berechnet");
	}

	//Hauptalgorithmus
	public Node[] dijkstra(Node[] graph, Node Pstart) {
		
		//Rueckgabe als Arraylist initialisieren, wird später zur einfachern Traversierbarkeit
		//als Array zurückgegeben
		ArrayList<Node> shortestPath = new ArrayList<Node>();
		
		//Queue Anlegen
		PriorityQueue<Node> Q = new PriorityQueue<Node>();
		
		//Keys Initialisieren
		initializeKeys(graph, Pstart);
		
		//Nodes mit jetzt angelegten Key in die Queue Laden. Korrekte Reihnfolge wird durch die 
		//Compare Methode des Interfaces sichergestellt.
		for (int i = 0; i < graph.length; i++) {
			Q.add(graph[i]);
		}
		
		while (!Q.isEmpty()) {
			
			//Liefert automatisch den Knoten mit dem kleinsten Schlüssen und entfernt ihn aus der Queue
			Node n = Q.poll();
			updateAdjacentNodeKeysDijkstra(n,Q);
			
			//Zu Rückgabe hinzufügen
			shortestPath.add(n);
		}
		
		//als Array zurueckgeben
		return shortestPath.toArray(new Node[shortestPath.size()]);
	}
	
	
	//Setzte die Schlüssel aller Knoten auf den Maximalen Intergerwert, nur der Startknoten erhällt die 0
	public void initializeKeys(Node [] graph, Node start) {
		
		for (int i = 0; i < graph.length; i++) {
			if (graph[i] != start) {
				graph[i].setKey(Integer.MAX_VALUE);
			} else {
				graph[i].setKey(0);
			}
		}		
	}
	
	public void  updateAdjacentNodeKeysDijkstra(Node n, PriorityQueue<Node> Q) {
		Node current;
		int distanceFromN;
		for (int i = 0; i < n.getedges().size(); i++) {
						
			//Momentan betrachteter Nachbarknoten von n in current speichern
			current = n.getedges().get(i).getDest();
			
			//Wenn der "Nachbarknoten noch in Q enthalten ist und das Kantengewicht kleiner ist als der Schluessel des benachbarten Knotens
			if(Q.contains(current) && n.getedges().get(i).getWeight() < current.getKey()) {
				
				current.setPrev(n);
				distanceFromN = n.getedges().get(i).getWeight();
				
				//Wenn der Schluessel von current groesser ist als der Schluessel von n + dem Weg von n nach Current 
				if (current.getKey() > current.getPrev().getKey()+distanceFromN) {
					//Key von current auf den Weg von n nach current + schluessel von n setzen
					current.setKey(current.getPrev().getKey()+distanceFromN);
				}

				//Reinsert des aktuellen Objekts, damit die Position in der Warteschlange
				//aktualisiert wird.
				Q.remove(current);
				Q.add(current);
			}
		}
	}
	
	
	public void print() {
		System.out.println("Start bei Knoten " + this.start.getNodeName() + " mit der ID " + this.start.getId());
		System.out.println("Die Knoten werden in folgender Reinfolge erreicht:");
		for (int i = 1; i < this.graph.length; i++) {
			System.out.println(i + ". " +this.graph[i].getNodeName() + " (Key: " + this.graph[i].getKey() + ")");
		}
	}
	
}
