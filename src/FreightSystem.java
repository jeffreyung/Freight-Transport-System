import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author z5060165
 */
public class FreightSystem {

	public  final static String START_CITY = "Sydney";
	
	private Graph<String> jobGraph;
	private Map<Vertex, Integer> costMap;
	private List<Edge> jobList;
	private List<Edge> sourceEdges;

	/**
	 * The main of the program
	 * @param args Inputs that are initialized
	 */
	public static void main(String[] args) {
		FreightSystem freightSystem = new FreightSystem();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0])); 
			while (sc.hasNextLine())
				freightSystem.command(sc.nextLine().split(" "));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if (sc != null)
				sc.close();
		}
		freightSystem.process();
	}
	
	/**
	 * Constructs a new FreightSystem object
	 */
	public FreightSystem() {
		this.jobGraph = new Graph<String>();
		this.costMap = new HashMap<Vertex, Integer>();
		this.jobList = new ArrayList<Edge>();
		this.sourceEdges = new ArrayList<Edge>();
	}
	
	/**
	 * Handles all the paths that should be taken
	 */
	private void process() {
		try {
			AStarSearch search = new AStarSearch(jobGraph, jobList, costMap);
			List<Edge> path = search.getShortestPath(this.sourceEdges);
			System.out.println(search.getExpanded() + " nodes expanded");
			System.out.println("cost = " + search.getTotalCost());
			for (Edge edge : path) {
				boolean contains = jobList.contains(edge);
				System.out.println((contains ? "Job " : "Empty ") + edge.getV1().getData()
						+ " to " + edge.getV2().getData());
				if (contains)
					jobList.remove(edge);
			}
		} catch (Exception e) {
			//FreightSystem.teminate();
		}
	}
	
	/**
	 * Handles all the command input(s) from the console
	 * @param in argument(s) from the console
	 */
	private void command(String[] in) {
		switch (in[0]) {
		case "Unloading":
			unload(Integer.parseInt(in[1]), in[2]);
			break;
		case "Cost":
			addCost(Integer.parseInt(in[1]), in[2], in[3]);
			break;
		case "Job":
			addJob(in[1], in[2]);
			break;
		default:
			break;
		}
	}

	/**
	 * Adds a town to the job graph along with its unloading cost
	 * @param cost The unloading cost of the town
	 * @param name The name of the town
	 */
	public void unload(int cost, String name) {
		Vertex vertex = new Vertex(name);
		jobGraph.addVertex(vertex);
		costMap.put(vertex, cost);
	}
	
	/**
	 * Adds a job to the job graph
	 * @param t1 A town included in the job
	 * @param t2 A town included in the job
	 * @param cost The cost from traveling
	 */
	public void addCost(int cost, String name1, String name2) {
		Vertex v1 = jobGraph.getVertex(name1);
		Vertex v2 = jobGraph.getVertex(name2);
		jobGraph.addEdge(v1, v2, cost);
		jobGraph.addEdge(v2, v1, cost);
	}
	
	/**
	 * Adds a job to the list
	 * @param name1 The name of a town in the job list
	 * @param name2 The name of a town in the job list
	 */
	public void addJob(String name1, String name2) {
		Vertex v1 = jobGraph.getVertex(name1);
		Vertex v2 = jobGraph.getVertex(name2);
		Edge edge = jobGraph.getEdge(v1, v2);
		if (edge == null)
			FreightSystem.teminate();
		this.jobList.add(edge);
		if (name1.equals(START_CITY))
			this.sourceEdges.add(edge);
	}
	
	/**
	 * Exits the program called and produces a line output of 'No solution'
	 */
	public static void teminate() {
		System.out.println("No soluion");
		System.exit(0);
	}
}
