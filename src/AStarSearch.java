import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * @author z5060165
 */
public class AStarSearch {

	private Queue<AStarNode> queue;
	private Graph<String> jobGraph;
	private List<Edge> jobList;
	private Map<Vertex, Integer> costMap;
	private int expanded = 0;
	private int totalCost;
	private Heuristic heuristic;
	private List<Heuristic> heuristicList;
	
	/**
	 * Constructs a new AStarSearch object
	 */
	public AStarSearch(Graph<String> jobGraph, List<Edge> jobList, Map<Vertex, Integer> costMap) {
		this.queue = new PriorityQueue<AStarNode>();
		this.jobGraph = jobGraph;
		this.jobList = jobList;
		this.heuristicList = new ArrayList<Heuristic>();
		this.costMap = costMap;
		this.initHeuristics();
	}
	
	/**
	 * Initializes the heuristics
	 */
	public void initHeuristics() {
		//this.heuristicList.add(new MinHeuristic(this.jobList));
		this.heuristicList.add(new RemainingJobsHeuristic(jobList));
	}
	
	/**
	 * Gets the shortest path from completing a list of jobs
	 * @param source The source edge
	 * @param jobList The job list
	 * @param costMap The map which carries all the unloading costs
	 * @return List<Edge> The path
	 */
	public List<Edge> getShortestPath(List<Edge> sourceEdges) {
		for (Edge sourceNode : sourceEdges) {
			AStarNode node = new AStarNode(sourceNode);
			node.setCost(sourceNode.getWeight());
			node.setHeuristic(this.getHeuristic(node));
			this.queue.add(node);
		}
		while (!this.queue.isEmpty()) {
			AStarNode currNode = this.queue.poll();
			this.expanded++;
			if (currNode.getPath().containsAll(jobList)) {
				this.totalCost = currNode.getCost() + getUnloadCost();
				return currNode.getPath();
			}
			Vertex curr = currNode.getCurrent().getV2();
			for (Edge edge : getEdges(curr)) {
				AStarNode clone = currNode.getClone();
				clone.getPath().add(edge);
				clone.setHeuristic(this.getHeuristic(clone));
				clone.setCost(clone.getCost() + edge.getWeight());
				this.queue.add(clone);
			}
		}
		return null;
	}
	
	/**
	 * @return int The unload cost derived from the job list
	 */
	public int getUnloadCost() {
		int cost = 0;
		for (Edge edge : jobList) {
			if (costMap.get(edge.getV2()) != null)
				cost += costMap.get(edge.getV2());
		}
		return cost;
	}
	
	/**
	 * Gets the list of edges from given connect vertices and a node
	 * @param node The start vertex
	 * @return List<Edge> The from the given parameters
	 */
	public List<Edge> getEdges(Vertex node) {
		List<Edge> result = new ArrayList<Edge>();
		for (Vertex vertex : node.getConnected())
			result.add(jobGraph.getEdge(node, vertex));
		return result;
	}
	
	/**
	 * Gets the heuristic value i.e., estimated distance
	 * @param node The node to get the heuristic
	 * @return int The heuristic value
	 */
	public int getHeuristic(AStarNode node) {
		int cost = 0;
		for (Heuristic h : heuristicList) {
			int val = h.estimateCost(node);
			if (node.getHeuristic() <= val)
			cost = cost < val ? val : cost;
		}
		return cost;
	}

	/**
	 * @return the expanded
	 */
	public int getExpanded() {
		return expanded;
	}

	/**
	 * @param expanded the expanded to set
	 */
	public void setExpanded(int expanded) {
		this.expanded = expanded;
	}

	/**
	 * @return int The total cost
	 */
	public int getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the total cost to set
	 */
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * @return the heuristic
	 */
	public Heuristic getHeuristic() {
		return heuristic;
	}

	/**
	 * @param heuristic the heuristic to set
	 */
	public void setHeuristic(Heuristic heuristic) {
		this.heuristic = heuristic;
	}
}
