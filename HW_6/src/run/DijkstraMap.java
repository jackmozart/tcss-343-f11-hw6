/*
 * TCSS 343 - Algorithms
 * Final Homework Coding Project: Dijkstra's algorithm
 * Group 3 - Steven Cozart, Brian Luger,  Michael Pitts
 */

package run;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * A map storing the paths from one Vertex in a Graph to all other Vertices.
 * @author Michael Pitts
 * @version Nov 29, 2011
 */
public class DijkstraMap {
	
	/** The map storing the Nodes for each Vertex. */
	private final Map<Vertex, DijkstraNode> my_map;
	
	/** The heap storing shortest length path node remaining. */
	private final Heap my_heap;
	
	/** Set of visited nodes. */
	private final Set<Vertex> my_visited;
	
	/**
	 * Creates a new DijstraMap, which stores the shortest path from the given vertex
	 * to every other vertex.
	 * @param the_graph is the graph to path over.
	 * @param the_start is the vertex that all the paths will start from.
	 */
	public DijkstraMap(final SimpleGraph the_graph, final Vertex the_start) {
		my_map = new HashMap<Vertex, DijkstraNode>();
		my_heap = new Heap();
		my_visited = new HashSet<Vertex>();
		final int max = setUpMap(the_graph);
		my_map.put(the_start, new DijkstraNode(the_start, 0, null));
		my_heap.insert(my_map.get(the_start));
		while (!my_heap.isEmpty() && my_visited.size() <= max) {
			visitNode(the_graph, my_heap.deleteMin());
		}
	}
	
	/**
	 * Populates the map with "infinite" Nodes in preparation for later use of 
	 * Dijkstra's method.  Also calculates total number of vertices in graph.
	 * @param the_graph is the graph the Map will work on.
	 * @return the number of vertices in the graph.
	 */
	private int setUpMap(final SimpleGraph the_graph) {
		@SuppressWarnings("unchecked") // should be vertices
		final Iterator<Vertex> iterator = the_graph.vertices();
		Vertex vertex;
		int count = 0;
		while (iterator.hasNext()) {
			count++;
			vertex = iterator.next();
			my_map.put(vertex, new DijkstraNode(vertex, Integer.MAX_VALUE, null));
		}
		return count;
	}

	/**
	 * Populates this map with DijstraNodes using Dijkstra's method.
	 * @param the_graph is the graph the over which to path.
	 * @param the_node is the node this recursive instance is using as the base.
	 * @param the_visited is the set of visited Nodes.
	 */
	private void visitNode(final SimpleGraph the_graph, 
			final DijkstraNode the_node) {
		//final List<Vertex> queue = new ArrayList<Vertex>(); // stores un-visited vertices
		my_visited.add(the_node.getVertex()); // mark the passed vertex as visited
		@SuppressWarnings("unchecked") // should be edges
		final Iterator<Edge> iterator = the_graph.incidentEdges(the_node.getVertex());
		Edge edge;
		Vertex vertex;
		Double cost;
		while (iterator.hasNext()) { // go over every adjacent vertex to this one
			edge = iterator.next();
			vertex = the_graph.opposite(the_node.getVertex(), edge);
			if (!my_visited.contains(vertex)) { // only check unvisited
				cost = the_node.getCost() + ((Double) edge.getData()).doubleValue();
				if (cost < my_map.get(vertex).getCost()) { // if found better path
					my_map.get(vertex).setCost(cost.doubleValue());
					my_map.get(vertex).setPrevious(the_node);
					if (my_map.get(vertex).getLocation() == 0) {
						my_heap.insert(my_map.get(vertex)); // not yet in heap, insert
					} else {
						my_heap.rePercolate(my_map.get(vertex)); // reorder the heap.
					}
				}
			}
		}
	}
	
	/**
	 * Gets the DijkstraNode for the given vertex, which can be used to find the 
	 * length of the path from this Map's start to the given vertex.
	 * @param the_vertex is the ending vertex if the path.
	 * @return the DijkstraNode for the given Vertex.
	 */
	public DijkstraNode getNode(final Vertex the_vertex) {
		return my_map.get(the_vertex);
	}
	
	/**
	 * Given a vertex, returns the shortest path starting form this Map's start
	 * and ending at the given vertex, or null if there is no such path.
	 * @param the_vertex is the vertex the path should end at.
	 * @return an array of Vertex Objects in order from start to the given vertex, or
	 * null if no path exists between this Map's start and the given vertex.
	 */
	public Vertex[] getPath(final Vertex the_vertex) {
		DijkstraNode node = getNode(the_vertex);
		if (node.getCost() == Integer.MAX_VALUE) {
			return null; // no path exists
		}
		final Deque<Vertex> stack = new ArrayDeque<Vertex>();
		stack.push(node.getVertex());
		while (node.getPrevious() != null) {
			node = node.getPrevious();
			stack.push(node.getVertex());
		}
		return stack.toArray(new Vertex[stack.size()]);
	}	
}
