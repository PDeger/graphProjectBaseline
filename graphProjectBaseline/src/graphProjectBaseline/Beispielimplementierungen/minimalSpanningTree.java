package graphProjectBaseline.Beispielimplementierungen;

import java.util.ArrayList;
import java.util.PriorityQueue;

import graphProjectBaseline.Node;

public class minimalSpanningTree {	

	private Node[] graph;
		
	//private ArrayList<Node> MSTgraph = new ArrayList<Node>();
	//private Node startingNode;
	
	public minimalSpanningTree(Node[] PGraph) {
		long startTime = System.currentTimeMillis();
		graph = Prim(PGraph);
		long endTime = System.currentTimeMillis();
		System.out.println("Der minimale Spannbaum wurde in " + (endTime-startTime) + "ms berechnet");
	}
	

	//Main Algorithm
	public Node[] Prim(Node[] graph) {
		
		//Rueckgabe als Arraylist initialisieren, wird später zur einfachern Traversierbarkeit
		//als Array zurückgegeben
		ArrayList<Node> MSTgraph = new ArrayList<Node>();
		
		//Keys Initialisieren
		initializeKeys(graph, graph[0]);
		
		//Queue Anlegen
		PriorityQueue<Node> Q = new PriorityQueue<Node>();
		
		//Nodes mit jetzt angelegten Key in die Queue Laden. Korrekte Reihnfolge wird durch die 
		//Compare Methode des Interfaces sichergestellt.
		for (int i = 0; i < graph.length; i++) {
			Q.add(graph[i]);
		}
		
		while (!Q.isEmpty()) {
			
			//Liefert automatisch den Knoten mit dem kleinsten Schlüssen und entfernt ihn aus der Queue
			Node n = Q.poll();
			updateAdjacentNodeKeys(n,Q);
			
			//Zu rückgabe hinzufügen
			MSTgraph.add(n);
		}
		
		//als Array zurueckgeben
		return MSTgraph.toArray(new Node[MSTgraph.size()]);
	}
	
	public void initializeKeys(Node [] graph, Node start) {
		
		for (int i = 0; i < graph.length; i++) {
			if (graph[i] != start) {
				graph[i].setKey(Integer.MAX_VALUE);
			} else {
				graph[i].setKey(0);
			}
		}		
	}
	
	public void  updateAdjacentNodeKeys(Node n, PriorityQueue<Node> Q) {
		Node current;
		for (int i = 0; i < n.getedges().size(); i++) {
			//current = n.relations[i].getTo_Node();
			current = n.getedges().get(i).getDest();
			
			//Wenn der aktuelle Nachbarknoten current noch in Q enthalten ist und der Schlüssel von 
			//diesem größer als das Kantengewicht vom aktuellen Knoten n zu diesem Knoten
			if(Q.contains(current) && current.getKey() > n.getedges().get(i).getWeight()) {
				current.setKey(n.getedges().get(i).getWeight());
				
				//Reinsert des aktuellen Objekts, damit die Position in der Warteschlange
				//aktualisiert wird.
				Q.remove(current);
				Q.add(current);
				
				current.setPrev(n);
			}
		}
	}
	
	//Printmethode
	public void print() {
		for (int i = 0; i < this.graph.length; i++) {
			System.out.println(this.graph[i].getPrev() != null ? this.graph[i].getPrev().getNodeName()+"<-"+ 
			this.graph[i].getKey()+"->" + this.graph[i].getNodeName():"Startknoten:" + this.graph[i].getNodeName());
		}
	}

	public Node[] getGraph() {
		return graph;
	}
}

