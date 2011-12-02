package test;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.Test;

import run.DijkstraMap;
import run.GraphInput;
import run.SimpleGraph;
import run.Vertex;

public class OverAllTest {

	@Test
	public void test3() {
		final String file = "src/test/test3.txt";
		final SimpleGraph my_graph = new SimpleGraph();
		final Hashtable<String, Vertex> my_table = GraphInput.LoadSimpleGraph(my_graph, file);
		final Vertex start = my_graph.aVertex();
		final DijkstraMap map = new DijkstraMap(my_graph, start);
		assertEquals("Start node should have no cost", 
				0, map.getNode(start).getCost(), 0.0);
		assertTrue("Start should have no previous", 
				null == map.getNode(start).getPrevious());
	}

}
