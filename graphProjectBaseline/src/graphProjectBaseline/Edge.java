package graphProjectBaseline;

public class Edge {
	
	//Kantengewicht
	private int weight;
	//Knoten, zu dem die Kante zeigt
	private Node src;
	//Knoten, von dem die Kante ausgeht
	private Node dest;
	
	public Edge(int weight, Node Psrc, Node Pdest) {
		this.weight = weight;
		this.src = Psrc;
		this.dest = Pdest;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Node getsrc() {
		return src;
	}

	public void setsrc(Node src) {
		this.src = src;
	}

	public Node getDest() {
		return dest;
	}

	public void setDest(Node dest) {
		this.dest = dest;
	}
}
