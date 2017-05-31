import java.util.ArrayList;
import java.util.List;

/**
 * @author z5060165
 */
public class AStarNode implements Comparable<AStarNode>, Cloneable {

	private List<Edge> path;
	private int cost;
	private int heuristic;

	/**
	 * Constructs a new AStarNode instance
	 * @param node
	 */
	public AStarNode(Edge node) {
		this.path = new ArrayList<Edge>();
		this.path.add(node);
		this.heuristic = 0;
	}
	
	/**
	 * @return AStarNode a clone of this class
	 */
	public AStarNode getClone() {
		try {
			AStarNode clone = (AStarNode) this.clone();
			clone.path = new ArrayList<Edge>(this.path);
			return clone;
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}

	/**
	 * @return Edge the current node
	 */
	public Edge getCurrent() {
		return path.get(path.size() - 1);
	}
	
	/**
	 * @return List<Edge> the path of the node
	 */
	public List<Edge> getPath() {
		return this.path;
	}
	
	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	/**
	 * @return the heuristic
	 */
	public int getHeuristic() {
		return heuristic;
	}

	/**
	 * @param heuristic the heuristic to set
	 */
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	
	@Override
	public int compareTo(AStarNode o) {
		int cmp = (this.cost + this.heuristic) - (o.cost + o.heuristic);
		if (cmp > 0)
			return 1;
		if (cmp < 0)
			return -1;
		return 0;
	}

}
