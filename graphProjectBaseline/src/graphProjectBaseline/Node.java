package graphProjectBaseline;

import java.util.ArrayList;

public class Node implements Comparable<Node>{

    //wird zu Verwaltung und Datenzugriffen in den Algorithmen verwendet
    private int id;
    //Hilfsvariable zur berechnung von kürzesten Wegen
    private int key;
    //Liste von Kanten, die von einem Knoten ausgehen
    private ArrayList<Edge> edges;
    //zum speichern von Vorgängerknoten im nützlich beim minimalenspannbaum 
    private Node prev;
    //Speichern des Knotennamens zur besseren Interpretation des Ergebnisses
    private String NodeName;

	public Node(String NodeName) {
		this.edges = new ArrayList<Edge>();
		this.NodeName = NodeName;
	}
	
    public void addEdge(int Pweight, Node Pdest) { 	
    	this.edges.add(new Edge(Pweight, this, Pdest));
    }


	public String getNodeName() {
		return NodeName;
	}


	public void setNodeName(String NodeName) {
		this.NodeName = NodeName;
	}

	public ArrayList<Edge> getedges() {
		return edges;
	}


	public void setedges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	public void addEdge(Edge e) {
		
		this.edges.add(e);
	}


	public int getKey() {
		return key;
	}


	public void setKey(int key) {
		this.key = key;
	}

	public Node getPrev() {
		return prev;
	}


	public void setPrev(Node prev) {
		this.prev = prev;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//Nützliche methode für manche Algorithmen 
	@Override
	public int compareTo(Node o) {
		if (key > o.key) {
			return 1;
		} else if(key < o.key) {
			return -1;
		}
		return 0;
	}
}