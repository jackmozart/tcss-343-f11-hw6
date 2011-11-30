package test;

import static org.junit.Assert.*;

import org.junit.Test;

import run.DijkstraMap;
import run.SimpleGraph;
import run.Vertex;

public class DijkstraMapTest {

	/**
	 * Tests a simple two vertex graph.
	 */
	@Test public void testTwo() {
		final SimpleGraph graph = new SimpleGraph();
		final Vertex a = graph.insertVertex("a", "A");
		final Vertex b = graph.insertVertex("b", "B");
		graph.insertEdge(a, b, 5, "A-B");
		final DijkstraMap map = new DijkstraMap(graph, a);
		assertEquals("A should be cost zero", 0, map.getNode(a).getCost());
		assertEquals("Path to a should be no step", 1, map.getPath(a).length);
		assertEquals("B should be cost five", 5, map.getNode(b).getCost());
		final Vertex[] path = map.getPath(b);
		assertTrue("First node in path should be A", path[0].equals(a));
		assertTrue("Last node in path should be B", path[1].equals(b));
	}

	@Test public void testThree() {
		final SimpleGraph graph = new SimpleGraph();
		final Vertex a = graph.insertVertex("a", "A");
		final Vertex b = graph.insertVertex("b", "B");
		final Vertex c = graph.insertVertex("c", "C");
		graph.insertEdge(a, b, 5, "A-B");
		graph.insertEdge(b, c, 4, "B-C");
		graph.insertEdge(c, a, 10, "C-A");
		final DijkstraMap map = new DijkstraMap(graph, a);
		assertEquals("A should be cost zero", 0, map.getNode(a).getCost());
		assertEquals("Path to a should be no step", 1, map.getPath(a).length);
		assertEquals("C should be cost nine", 9, map.getNode(c).getCost());
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
		graph.insertEdge(a, b, 10, "A-B");
		graph.insertEdge(a, c, 4, "A-C");
		graph.insertEdge(b, c, 2, "B-C");
		graph.insertEdge(b, d, 1, "B-D");
		graph.insertEdge(c, d, 3, "C-D");
		final DijkstraMap map = new DijkstraMap(graph, a);
		assertEquals("Cost of B wrong", 6, map.getNode(b).getCost());
		assertEquals("Cost of C wrong", 4, map.getNode(c).getCost());
		assertEquals("Cost of D wrong", 7, map.getNode(d).getCost());
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
}
