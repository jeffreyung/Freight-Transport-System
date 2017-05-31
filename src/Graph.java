import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author z5060165
 */
public class Graph<E> {

	private Map<E, Vertex> vertices;
	private List<Edge> edges;
	
	/**
	 * The constructor of the Graph class
	 */
	public Graph() {
		this.vertices = new HashMap<E, Vertex>();
		this.edges = new ArrayList<Edge>();
	}
	
	/**
	 * Adds an edge with a weight of one
	 * @param v1 The vertex associated with the edge
	 * @param v2 The vertex associated with the edge
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		addEdge(v1, v2, 1);
	}
	
	/**
	 * Adds an edge with a given weight
	 * @param v1 The vertex associated with the edge
	 * @param v2 The vertex associated with the edge
	 * @param weight The weight of the edge
	 */
	public void addEdge(Vertex v1, Vertex v2, int weight) {
		if (v1.equals(v2))
			return;
		Edge edge = new Edge(v1, v2, weight);
		edges.add(edge);
		v1.addEdge(edge);
	}
	
	/**
	 * Checks if the graph contains the edge
	 * @param edge The edge to check
	 * @return boolean If the graph contains the edge
	 */
	public boolean containsEdge(Edge edge) {
		if (edge.getV1() == null || edge.getV2() == null)
			return false;
		return this.edges.contains(edge);
	}
	
	/**
	 * Remvoes an edge from the graph
	 * @param edge The edge to be removed
	 */
	public void removeEdge(Edge edge) {
		this.edges.remove(edge);
		edge.getV1().removeEdge(edge);
		edge.getV2().removeEdge(edge);
	}
	
	/**
	 * Adds a vertex to the graph
	 * @param vertex The vertex to add
	 */
	@SuppressWarnings("unchecked")
	public void addVertex(Vertex vertex) {
		vertices.put((E) vertex.getData(), vertex);
	}
	
	/**
	 * Checks if the graph contains the vertex
	 * @param vertex The vertex to check
	 * @return boolean If the graph contains the vertex
	 */
	public boolean containsVertex(Vertex vertex) {
		return this.vertices.get(vertex.getData()) != null;
	}
	
	/**
	 * Removes the vertex from the graph
	 * @param vertex The vertex to remove
	 */
	public void removeVertex(Vertex vertex) {
		vertices.remove(vertex);
	}
	
	/**
	 * @param data The data associated with the vertex
	 * @return Vertex the vertex associated with the data
	 */
	public Vertex getVertex(Object data) {
		return this.vertices.get(data);
	}
	
	/**
	 * Gets the edge from given data
	 * @param data1 The data associated with the vertex
	 * @param data2 The data associated with the vertex
	 * @return Edge the edge associated with the data
	 */
	public Edge getEdge(Object data1, Object data2) {
		for (Edge edge : edges) {
			if (edge.getV1().getData().equals(data1) && edge.getV2().getData().equals(data2))
				return edge;
		}
		return null;
	}
	
	/**
	 * Gets the edge from given vertices
	 * @param vertex1 The vertex associated with the vertex
	 * @param vertex2 The vertex associated with the vertex
	 * @return Edge the edge associated with the vertices
	 */
	public Edge getEdge(Vertex vertex1, Vertex vertex2) {
		for (Edge edge : edges) {
			if (edge.getV1().equals(vertex1) && edge.getV2().equals(vertex2))
				return edge;
		}
		return null;
	}
	
	/**
	 * @return List The list of edges
	 */
	public List<Edge> getEdges() {
		return this.edges;
	}
	
	/**
	 * @return List The list of vertices
	 */
	public List<Vertex> getVertices() {
		List<Vertex> verticesList = new ArrayList<Vertex>();
		for (E data : vertices.keySet())
			verticesList.add(vertices.get(data));
		return verticesList;
	}

}
