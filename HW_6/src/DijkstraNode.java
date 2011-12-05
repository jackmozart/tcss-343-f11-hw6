
public class DijkstraNode implements Comparable {
	
	private boolean locked;
	
	private int my_label;
	
	private double my_weight;
	
	private Vertex my_vertex;
	
	private int my_heap_loc;
	
	private DijkstraNode my_previous_node;

	/**
	 * Compares by comparing weights of two objects.
	 * This object is considered greater if it has a higher weight. 
	 * @param arg0 The object to compare to.  
	 */
	@Override
	public int compareTo(Object arg0) {
		int result = 0;
		if(this.getClass().equals(arg0.getClass())){
			result = (int) (this.getMy_weight() - ((DijkstraNode) arg0).getMy_weight());
		}else{
			throw new IllegalArgumentException("Tried to compare two difrent objects");
		}
		return result;
	}

	/**
	 * @param my_weight the my_weight to set
	 */
	public void setMy_weight(double my_weight) {
		this.my_weight = my_weight;
	}

	/**
	 * @return the my_weight
	 */
	public double getMy_weight() {
		return my_weight;
	}

	/**
	 * @param my_label the my_label to set
	 */
	public void setMy_label(int my_label) {
		this.my_label = my_label;
	}

	/**
	 * @return the my_label
	 */
	public int getMy_label() {
		return my_label;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.locked = visited;
	}

	/**
	 * @return the visited
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param my_vertex the my_vertex to set
	 */
	public void setMy_vertex(Vertex my_vertex) {
		this.my_vertex = my_vertex;
	}

	/**
	 * @return the my_vertex
	 */
	public Vertex getMy_vertex() {
		return my_vertex;
	}

	/**
	 * @param my_heap_loc the my_heap_loc to set
	 */
	public void setMy_heap_loc(int my_heap_loc) {
		this.my_heap_loc = my_heap_loc;
	}

	/**
	 * @return the my_heap_loc
	 */
	public int getMy_heap_loc() {
		return my_heap_loc;
	}

	/**
	 * @param my_previous_node the my_previous_node to set
	 */
	public void setMy_previous_node(DijkstraNode my_previous_node) {
		this.my_previous_node = my_previous_node;
	}

	/**
	 * @return the my_previous_node
	 */
	public DijkstraNode getMy_previous_node() {
		return my_previous_node;
	}

}
