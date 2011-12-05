package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

import run.DijkstraMap;
import run.DijkstraNode;
import run.GraphInput;
import run.SimpleGraph;
import run.Vertex;

public class OverAllTest {

	@Test
	public void test3() {
		final String file = "src/test/test3.txt";
		final SimpleGraph my_graph = new SimpleGraph();
		final Hashtable<String, Vertex> my_table = GraphInput.LoadSimpleGraph(my_graph, file);
		final Vertex start = my_table.get("A");
		final DijkstraMap map = new DijkstraMap(my_graph, start);
		assertEquals("Start node should have no cost", 
				0, map.getNode(start).getCost(), 0.0);
		assertTrue("Start should have no previous", 
				null == map.getNode(start).getPrevious());
		final Map<String, DijkstraNode> answer_map = getSolution3();
		for (Vertex vert : my_table.values()) {
			assertTrue("Problem with: " + vert.getName().toString(), 
					checkNode(map.getNode(vert), answer_map.get(vert.getName())));
		}
	}
	
	private boolean checkNode(final DijkstraNode the_first, 
			final DijkstraNode the_second) {
		boolean equal = the_first.getCost() == the_second.getCost() && 
				the_first.equals(the_second);
		if (equal) {
			equal &= (the_first.getPrevious() == null) == 
					(the_second.getPrevious() == null);
			if (equal && the_first.getPrevious() != null) {
				equal &= the_first.getPrevious().equals(the_second.getPrevious());
			}
		}
		return equal;
	}
	
	private Map<String, DijkstraNode> getSolution3() {
		final Map<String, DijkstraNode> map = new HashMap<String, DijkstraNode>();
		final Vertex[] verts = {
				new Vertex(null, "A"), new Vertex(null, "B"), new Vertex(null, "C"), 
				new Vertex(null, "D"), new Vertex(null, "E"), new Vertex(null, "F"), 
				new Vertex(null, "G"), new Vertex(null, "H"), new Vertex(null, "I"), 
				new Vertex(null, "J"), new Vertex(null, "K"), new Vertex(null, "L"), 
				new Vertex(null, "M"), new Vertex(null, "N"), new Vertex(null, "O"), 
				new Vertex(null, "P"), new Vertex(null, "Q"), new Vertex(null, "R")};
		map.put(verts[0].getName().toString(), 
				new DijkstraNode(verts[0], 0, null));
		map.put(verts[1].getName().toString(), 
				 new DijkstraNode(verts[1], 4, map.get(verts[0].getName().toString())));
		map.put(verts[4].getName().toString(), 
				 new DijkstraNode(verts[4], 7, map.get(verts[0].getName().toString())));
		map.put(verts[10].getName().toString(), 
				 new DijkstraNode(verts[10], 7, map.get(verts[1].getName().toString())));
		map.put(verts[2].getName().toString(), 
				 new DijkstraNode(verts[2], 8, map.get(verts[1].getName().toString())));
		map.put(verts[9].getName().toString(), 
				 new DijkstraNode(verts[9], 8, map.get(verts[10].getName().toString())));
		map.put(verts[3].getName().toString(), 
				 new DijkstraNode(verts[3], 9, map.get(verts[4].getName().toString())));
		map.put(verts[8].getName().toString(), 
				 new DijkstraNode(verts[8], 9, map.get(verts[9].getName().toString())));
		map.put(verts[11].getName().toString(), 
				 new DijkstraNode(verts[11], 10, map.get(verts[10].getName().toString())));
		map.put(verts[6].getName().toString(), 
				 new DijkstraNode(verts[6], 12, map.get(verts[3].getName().toString())));
		map.put(verts[12].getName().toString(), 
				 new DijkstraNode(verts[12], 12, map.get(verts[11].getName().toString())));
		map.put(verts[5].getName().toString(), 
				 new DijkstraNode(verts[5], 13, map.get(verts[4].getName().toString())));
		map.put(verts[7].getName().toString(), 
				 new DijkstraNode(verts[7], 13, map.get(verts[6].getName().toString())));
		map.put(verts[13].getName().toString(), 
				 new DijkstraNode(verts[13], 13, map.get(verts[12].getName().toString())));
		map.put(verts[15].getName().toString(), 
				 new DijkstraNode(verts[15], 15, map.get(verts[12].getName().toString())));
		map.put(verts[14].getName().toString(), 
				 new DijkstraNode(verts[14], 17, map.get(verts[13].getName().toString())));
		map.put(verts[17].getName().toString(), 
				 new DijkstraNode(verts[17], 17, map.get(verts[12].getName().toString())));
		map.put(verts[16].getName().toString(), 
				 new DijkstraNode(verts[16], 22, map.get(verts[15].getName().toString())));
		return map;
	}

}
