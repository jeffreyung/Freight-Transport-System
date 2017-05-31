import java.util.ArrayList;
import java.util.List;

/**
 * @author z5060165
 */
public class Vertex {

	private List<Edge> edges;
	private Object data;
	
	/**
	 * The constructor of the Vertex class
	 * @param data The data associated with the vertex
	 */
	public Vertex(Object data) {
		this.data = data;
		this.edges = new ArrayList<Edge>();
	}
	
	/**
	 * @return List<Vertex> The vertices connected to the node
	 */
	public List<Vertex> getConnected() {
		List<Vertex> vertices = new ArrayList<Vertex>();
		for (Edge edge : this.edges) 
			vertices.add(edge.getV2());
		return vertices;
	}

	/**
	 * Adds an edge to the vertex and does nothing if it already exist
	 * @param edge The edge to be added to the vertex
	 */
	public void addEdge(Edge edge) {
		if (this.edges.contains(edge))
			return;
		this.edges.add(edge);
	}
	
	/**
	 * Removes an edge from the vertex
	 * @param edge The edge to be removed
	 */
	public void removeEdge(Edge edge) {
		this.edges.remove(edge);
	}
	
	/**
	 * @return int The number of edges included in the vertex
	 */
	public int getEdgeCount() {
		return this.edges.size();
	}
	
	/**
	 * @return Object The data of the vertex
	 */
	public Object getData() {
		return data;
	}

}
