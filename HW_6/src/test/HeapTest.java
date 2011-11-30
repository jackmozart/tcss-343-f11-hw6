/*
 * TCSS 343 - Algorithms
 * Final Homework Coding Project: Dijkstra's algorithm
 * Group 3 - Steven Cozart, Brian Luger,  Michael Pitts
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import run.DijkstraNode;
import run.Heap;
import run.Vertex;

/**
 * Tests changes to heap.
 * @author Michael Pitts
 * @version Nov 29, 2011
 */
public class HeapTest {
	
	/** Vertices to create nodes. */
	private Vertex[] my_vertices;
	
	/** Some nodes to test on. */
	private DijkstraNode[] my_nodes;
	
	/** The heap to test. */
	private Heap my_heap;
	
	/**
	 * Sets up some nodes to test with.
	 */
	@Before public void setUp() {
		my_heap = new Heap();
		my_vertices = new Vertex[] {new Vertex("Three", "Three"),
				new Vertex("One", "One"), new Vertex("Four", "Four"),
				new Vertex("Zero", "Zero")};
		my_nodes = new DijkstraNode[] {new DijkstraNode(my_vertices[0], 3, null), 
				new DijkstraNode(my_vertices[1], 1, null), 
				new DijkstraNode(my_vertices[2], 4, null),
				new DijkstraNode(my_vertices[3], 0, null)};
	}
	
	/**
	 * Tests that the heap correctly outputs nodes in order.
	 */
	@Test public void testBasicOperation() {
		for (DijkstraNode insert : my_nodes) {
			my_heap.insert(insert);
		}
		assertTrue("Did not remove lowest node", my_nodes[3].equals(my_heap.deleteMin()));
		assertEquals("Did not correctly update node location", 
				1, my_nodes[3].getLocation());
		assertTrue("Did not remove second lowest node", 
				my_nodes[1].equals(my_heap.deleteMin()));
		assertEquals("Did not correctly update node location", 
				1, my_nodes[1].getLocation());
		assertTrue("Did not remove third lowest node", 
				my_nodes[0].equals(my_heap.deleteMin()));
		assertEquals("Did not correctly update node location", 
				1, my_nodes[0].getLocation());
		assertTrue("Did not remove last lowest node", 
				my_nodes[2].equals(my_heap.deleteMin()));
		assertEquals("Did not correctly update node location", 
				1, my_nodes[2].getLocation());
	}

	/**
	 * Tests that the insert() method updates Node location correctly.
	 */
	@Test public void testInsert() {
		my_heap.insert(my_nodes[0]);
		assertEquals("First insert did not go to top of heap", 
				1, my_nodes[0].getLocation());
		my_heap.insert(my_nodes[1]);
		assertEquals("New insert did not update correctly", 
				1, my_nodes[1].getLocation());
		assertEquals("Old node did not update correctly",
				2, my_nodes[0].getLocation());
		my_heap.insert(my_nodes[2]);
		assertEquals("Largest node should be at bottom",
				3, my_nodes[2].getLocation());
		my_heap.insert(my_nodes[3]);
		assertEquals("Smallest did not get right location at top",
				1, my_nodes[3].getLocation());
		assertEquals("One Node should have been next",
				2, my_nodes[1].getLocation());
		assertEquals("Four should have been next", 
				3, my_nodes[2].getLocation());
		assertEquals("Three should be last", 4, my_nodes[0].getLocation());
	}

	/**
	 * Tests that the deleteMin() operation correctly updates location.
	 */
	@Test public void testDeleteMin() {
		for (DijkstraNode insert : my_nodes) {
			my_heap.insert(insert);
		}
		my_heap.deleteMin();
		assertEquals("New min not at top", 1, my_nodes[1].getLocation());
		assertEquals("Four should be next", 2, my_nodes[0].getLocation());
		assertEquals("Three should be last", 3, my_nodes[2].getLocation());
		my_heap.deleteMin();
		assertEquals("Three should be new min", 1, my_nodes[0].getLocation());
		assertEquals("Four should be last", 2, my_nodes[2].getLocation());
		my_heap.deleteMin();
		assertEquals("Only Four should be left", 1, my_nodes[2].getLocation());
	}

	/**
	 * Tests that a node with updated cost is adjusted upward correctly.
	 */
	@Test public void testRePercolateGoingUp() {
		for (DijkstraNode insert : my_nodes) {
			my_heap.insert(insert);
		} // 3, 1, 4, 0
		my_nodes[2].setCost(0);
		my_heap.rePercolate(my_nodes[2]);
		assertEquals("Zero should still be at top" , 1, my_nodes[3].getLocation());
		assertEquals("\"Four\" should be next" , 2, my_nodes[2].getLocation());
		assertEquals("Three at the bottom" , 3, my_nodes[0].getLocation());
		assertEquals("One should be after" , 4, my_nodes[1].getLocation());
	}
	
	/**
	 * Tests that a node with updated cost is adjusted downward correctly.
	 */
	@Test public void testRePercolateGoingDown() {
		for (DijkstraNode insert : my_nodes) {
			my_heap.insert(insert);
		}
		my_nodes[1].setCost(5);
		my_heap.rePercolate(my_nodes[1]);
		assertEquals("Node \"Zero\" should be at bottom", 
				4, my_nodes[1].getLocation());
	}

}
