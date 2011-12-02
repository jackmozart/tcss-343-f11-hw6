package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import run.DijkstraMap;
import run.SimpleGraph;
import run.Vertex;

public class DijkstraMapTest {

	private Map<Character, Vertex> my_vertex_map;

	/**
	 * Tests a simple two vertex graph.
	 */
	@Test public void testTwo() {
		final SimpleGraph graph = new SimpleGraph();
		final Vertex a = graph.insertVertex("a", "A");
		final Vertex b = graph.insertVertex("b", "B");
		graph.insertEdge(a, b, 5.0, "A-B");
		final DijkstraMap map = new DijkstraMap(graph, a);
		assertEquals("A should be cost zero", 0.0, map.getNode(a).getCost(), 0.0);
		assertEquals("Path to a should be no step", 1, map.getPath(a).length);
		assertEquals("B should be cost five", 5.0, map.getNode(b).getCost(), 0.0);
		final Vertex[] path = map.getPath(b);
		assertTrue("First node in path should be A", path[0].equals(a));
		assertTrue("Last node in path should be B", path[1].equals(b));
	}

	@Test public void testThree() {
		final SimpleGraph graph = new SimpleGraph();
		final Vertex a = graph.insertVertex("a", "A");
		final Vertex b = graph.insertVertex("b", "B");
		final Vertex c = graph.insertVertex("c", "C");
		graph.insertEdge(a, b, 5.0, "A-B");
		graph.insertEdge(b, c, 4.0, "B-C");
		graph.insertEdge(c, a, 10.0, "C-A");
		final DijkstraMap map = new DijkstraMap(graph, a);
		assertEquals("A should be cost zero", 0, map.getNode(a).getCost(), 0.0);
		assertEquals("Path to a should be no step", 1, map.getPath(a).length);
		assertEquals("C should be cost nine", 9, map.getNode(c).getCost(), 0.0);
		final Vertex[] path = map.getPath(c);
		assertTrue("First node in path should be A", path[0].equals(a));
		assertTrue("Second node in path should be B", path[1].equals(b));
		assertTrue("Last node in path should be C", path[2].equals(c));
	}
	
	@Test public void testFour() {
		final SimpleGraph graph = new SimpleGraph();
		final Vertex a = graph.insertVertex("a", "A");
		final Vertex b = graph.insertVertex("b", "B");
		final Vertex c = graph.insertVertex("c", "C");
		final Vertex d = graph.insertVertex("d", "D");
		graph.insertEdge(a, b, 10.0, "A-B");
		graph.insertEdge(a, c, 4.0, "A-C");
		graph.insertEdge(b, c, 2.0, "B-C");
		graph.insertEdge(b, d, 1.0, "B-D");
		graph.insertEdge(c, d, 3.0, "C-D");
		final DijkstraMap map = new DijkstraMap(graph, a);
		assertEquals("Cost of B wrong", 6, map.getNode(b).getCost(), 0.0);
		assertEquals("Cost of C wrong", 4, map.getNode(c).getCost(), 0.0);
		assertEquals("Cost of D wrong", 7, map.getNode(d).getCost(), 0.0);
		final Vertex[] d_path = map.getPath(d);
		assertEquals("Path for D incorrect", 3, d_path.length);
		assertTrue("Did not start at A", d_path[0].equals(a));
		assertTrue("Did not have C next", d_path[1].equals(c));
		assertTrue("Did not end at D", d_path[2].equals(d));
		final Vertex[] b_path = map.getPath(b);
		assertEquals("Path for B incorrect", 3, b_path.length);
		assertTrue("Did not start at A", b_path[0].equals(a));
		assertTrue("Did not have C next", b_path[1].equals(c));
		assertTrue("Did not end at B", b_path[2].equals(b));
	}
	
	@Test public void testComplex() {
		final SimpleGraph graph = getComplexGraph();
		final DijkstraMap map = new DijkstraMap(graph, my_vertex_map.get('a'));
		assertEquals("Wrong cost for a", 0, 
				map.getNode(my_vertex_map.get('a')).getCost(), 0.0);
		assertEquals("Wrong cost for b", 3, 
				map.getNode(my_vertex_map.get('b')).getCost(), 0.0);
		assertEquals("Wrong cost for c", 4, 
				map.getNode(my_vertex_map.get('c')).getCost(), 0.0);
		assertEquals("Wrong cost for d", 8, 
				map.getNode(my_vertex_map.get('d')).getCost(), 0.0);
		assertEquals("Wrong cost for e", 10, 
				map.getNode(my_vertex_map.get('e')).getCost(), 0.0);
		assertEquals("Wrong cost for f", 2, 
				map.getNode(my_vertex_map.get('f')).getCost(), 0.0);
		assertEquals("Wrong cost for g", 2, 
				map.getNode(my_vertex_map.get('g')).getCost(), 0.0);
		assertEquals("Wrong cost for h", 5, 
				map.getNode(my_vertex_map.get('h')).getCost(), 0.0);
		assertEquals("Wrong cost for i", 11, 
				map.getNode(my_vertex_map.get('i')).getCost(), 0.0);
	}
	
	private SimpleGraph getComplexGraph() {
		final SimpleGraph graph = new SimpleGraph();
		final Map<Character, Vertex> map = new HashMap<Character, Vertex>();
		addEdge(graph, 'a', 'f', 2, map);
		addEdge(graph, 'a', 'c', 4, map);
		addEdge(graph, 'a', 'g', 2, map);
		addEdge(graph, 'a', 'b', 3, map);
		addEdge(graph, 'f', 'b', 1, map);
		addEdge(graph, 'b', 'g', 5, map);
		addEdge(graph, 'c', 'h', 1, map);
		addEdge(graph, 'h', 'd', 4, map);
		addEdge(graph, 'd', 'e', 2, map);
		addEdge(graph, 'h', 'i', 6, map);
		addEdge(graph, 'e', 'i', 7, map);
		addEdge(graph, 'g', 'd', 6, map);
		my_vertex_map = map;
		return graph;
	}
	
	private void addEdge(final SimpleGraph the_graph, final char the_first, 
			final char the_second, final double the_cost, Map<Character, Vertex> the_nodes) {
		if (!the_nodes.containsKey(the_first)) {
			the_nodes.put(the_first, the_graph.insertVertex(the_first, the_first));
		}
		if (!the_nodes.containsKey(the_second)) {
			the_nodes.put(the_second, the_graph.insertVertex(the_second, the_second));
		}
		the_graph.insertEdge(the_nodes.get(the_first), the_nodes.get(the_second), 
				the_cost, the_first + "-" + the_second);
	}
}
