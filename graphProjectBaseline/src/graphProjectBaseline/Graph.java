package graphProjectBaseline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
	private ArrayList<Node> nodeList;
	private int[][] adjacencyMatrix;
	private String[] nodeNameList;
	private String displayType = "Matrix";

	public Graph(String srcFile, String dir) {
		//nodeList = makeNodeList(srcFile);
		adjacencyMatrix = readGraphData(srcFile, dir);
	}
	
	
	private ArrayList<Node> makeNodeList(String fileName, String dir) {
	//Adjazenzliste kann hier implementiert werden
        return null;
	}
	
	private int[][] readGraphData(String fileName, String dir) {
		
		int graphsize = getGraphSize(fileName);
		
		int[][] graphData = new int[graphsize][graphsize];
		
        String path = "./Graph Data/" + dir + "/" + fileName;
        
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
        	
        	ArrayList<Node> list = new ArrayList<Node>();
            String line;
            
            //Knotennamen aus der ersten Zeile in der Datei holen und in Objektvariable ablegen
            line = reader.readLine();
            String[] headersNames = line.split(" ");
            headersNames = Arrays.copyOfRange(headersNames, 1, headersNames.length);
            setNodeNameList(headersNames);
            
            for(int i = 1; i <= graphsize-1; i++) {
            	line = reader.readLine();

            	//Erstes Zeichen in einer Zeile enthällt immer den Knotennamen, desshalb muss man diesen hier entfernen
                String[] numbers = line.split(" ");
                numbers = Arrays.copyOfRange(numbers, 1, numbers.length);

                int[] intArray = new int[numbers.length];

                // Umwandlung der String-Werte in Integers
                for (int j = 1; j <= graphsize-1; j++) {
                	graphData[i-1][j-1] = Integer.parseInt(numbers[j]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Datei wurde nicht gefunden: " + path);
        } catch (IOException e) {
            System.out.println("Ein Fehler ist beim Lesen der Datei aufgetreten: " + path);
        } catch (NumberFormatException e) {
            System.out.println("Ein Fehler ist beim erstellen des Knoten Liste aufgetreten.");
        }
		return graphData;
	}
	
	public ArrayList<Node> getNodeList() {
		return nodeList;
	}


	public void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}


	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}


	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}


	public String[] getNodeNameList() {
		return nodeNameList;
	}


	public void setNodeNameList(String[] nodeNameList) {
		this.nodeNameList = nodeNameList;
	}


	//holt die Graph Größe aus dem Dateinamen
	private int getGraphSize(String s) {
		String[] stringParts = s.split("x");
		return Integer.parseInt(stringParts[0]);
	}
	
	//erstelle so viele neue Knoten, wie für den Graphen gebraucht werden
	private ArrayList<Node> generateEmptyNodes(int count) {
		ArrayList<Node> list = new ArrayList<Node>();
		
		for(int i =0; i<= count-1; i++) {
			list.add(new Node(Integer.toString(i)));
		}
		return list;
	}
}
