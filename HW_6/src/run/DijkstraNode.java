/*
 * TCSS 343 - Algorithms
 * Final Homework Coding Project: Dijkstra's algorithm
 * Group 3 - Steven Cozart, Brian Luger,  Michael Pitts
 */

package run;

/**
 * Stores the vertex, cost, and preceding step in a path leading up to the given
 * vertex.
 * 
 * @author Michael Pitts
 * @version Nov 29, 2011
 */
public class DijkstraNode implements Comparable<DijkstraNode> {

	/** The vertex stored at this Node. */
	private final Vertex my_vertex;

	/** The previous Node in the path leading to this Node and Vertex. */
	private DijkstraNode my_previous;

	/** The cost of the path up to this Node and Vertex. */
	private double my_cost;

	/** The location of the Node in the heap. */
	private int my_location;

	/**
	 * Creates a new Dijkstra Node.
	 * 
	 * @param the_vertex
	 *            is the Vertex at this Node.
	 * @param the_cost
	 *            is the cost of the path leading upto this Node.
	 * @param the_previous
	 *            is the previous Node in the path leading to this Node.
	 */
	public DijkstraNode(final Vertex the_vertex, final double the_cost,
			final DijkstraNode the_previous) {
		my_cost = the_cost;
		my_vertex = the_vertex;
		my_previous = the_previous;
	}

	/** {@inheritDoc} */
	public int hashCode() {
		return my_vertex.hashCode();
	}

	/** {@inheritDoc} */
	public boolean equals(final Object the_other) {
		return DijkstraNode.class.isInstance(the_other) &&
				((DijkstraNode) the_other).my_vertex.getName().equals(my_vertex.getName());
	}

	/** @return the cost to get to this Node. */
	public double getCost() {
		return my_cost;
	}

	/** @return the vertex for this Node. */
	public Vertex getVertex() {
		return my_vertex;
	}

	/** @return the previous Node in the path. */
	public DijkstraNode getPrevious() {
		return my_previous;
	}

	/**
	 * Allows the cost for this node to be changed.
	 * 
	 * @param the_cost
	 *            is the new cost.
	 */
	public void setCost(final double the_cost) {
		my_cost = the_cost;
	}

	/**
	 * Sets the location for this Node.
	 * 
	 * @param the_location
	 *            is the new location.
	 */
	public void setLocation(final int the_location) {
		my_location = the_location;
	}

	/**
	 * Gets the location for this Node.
	 * 
	 * @return the integer location for this Node.
	 */
	public int getLocation() {
		return my_location;
	}

	/** {@inheritDoc} */
	@Override
	public int compareTo(final DijkstraNode the_other) {
		if (my_cost - the_other.my_cost > 0.0) {
			return 1;
		} else if (my_cost - the_other.my_cost < 0.0) {
			return -1;
		}
		return 0;
	}

	/**
	 * Sets the previous node to this one in the path.
	 * 
	 * @param the_node
	 *            is the new previous node.
	 */
	public void setPrevious(final DijkstraNode the_node) {
		my_previous = the_node;
	}
}