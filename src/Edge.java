/**
 * @author z5060165
 */

public class Edge implements Comparable<Edge> {

	private int weight;
	private Vertex v1;
	private Vertex v2;
	
	/**
	 * The constructor for the Edge class
	 * @param v1 The vertex associated with the edge
	 * @param v2 The other vertex that is associated with the edge
	 */
	public Edge(Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	/**
	 * The constructor for the Edge class
	 * @param v1 The vertex associated with the edge
	 * @param v2 The other vertex that is associated with the edge
	 * @param weight The weight of the vertex
	 */
	public Edge(Vertex v1, Vertex v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * @param weight the weight of the edge
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return Vertex the vertex associated with the edge
	 */
	public Vertex getV1() {
		return v1;
	}
	
	/**
	 * @return Vertex the vertex associated with the edge
	 */
	public Vertex getV2() {
		return v2;
	}

	@Override
	public int compareTo(Edge o) {
		int cmp = this.weight - o.weight;
		if (cmp > 0)
			return 1;
		if (cmp < 0)
			return -1;
		return 0;
	}
	
}
