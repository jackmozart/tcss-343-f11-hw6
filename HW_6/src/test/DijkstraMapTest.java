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
		assertEquals("C should be cost nine", 9, map.getNode(b).getCost());
		final Vertex[] path = map.getPath(c);
		assertTrue("First node in path should be A", path[0].equals(a));
		assertTrue("Second node in path should be B", path[1].equals(b));
		assertTrue("Last node in path should be C", path[2].equals(c));
		
	}
}
