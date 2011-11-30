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
import run.Vertex;

/**
 * @author Michael Pitts
 *
 */
public class DijkstraNodeTest {
	
	private Vertex my_vertex = new Vertex("Data", "Vertex");
	
	private Vertex my_previous_vertex = new Vertex("data", "Previous");

	private DijkstraNode my_node;
	
	private DijkstraNode my_previous_node;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before public void setUp() {
		my_previous_node = new DijkstraNode(my_previous_vertex, 0, null);
		my_node = new DijkstraNode(my_vertex, 5, my_previous_node);
	}

	/**
	 * Test method for {@link DijkstraNode#hashCode()}.
	 */
	@Test public void testHashCode() {
		final DijkstraNode node = new DijkstraNode(my_vertex, 5, my_previous_node);
		assertEquals("Same data did not get same hash", 
				my_node.hashCode(), node.hashCode());
		assertTrue("Got same hash for different data", 
				my_node.hashCode() != my_previous_node.hashCode());
	}

	/**
	 * Test method for {@link DijkstraNode#equals(java.lang.Object)}.
	 */
	@Test public void testEqualsObject() {
		final DijkstraNode node = new DijkstraNode(my_vertex, 5, my_previous_node);
		assertTrue("Did not equal on same data", node.equals(my_node));
		assertFalse("Should not equal when different", 
				my_previous_node.equals(my_node));
	}

	/**
	 * Test method for {@link DijkstraNode#getVertex()}.
	 */
	@Test public void testGetVertex() {
		assertTrue("Did not get same vertex", my_node.getVertex().equals(my_vertex));
	}

	/**
	 * Test method for {@link DijkstraNode#getPrevious()}.
	 */
	@Test public void testGetPrevious() {
		assertTrue("Did not get previous node", 
				my_node.getPrevious().equals(my_previous_node));
		assertTrue("Did not get null value for previous", 
				my_previous_node.getPrevious() == null);
	}

	/**
	 * Test method for {@link DijkstraNode#setCost(int)}.
	 */
	@Test public void testSetCost() {
		assertEquals("Did not get correct original cost", 5, my_node.getCost());
		my_node.setCost(7);
		assertEquals("Did not get correct new cost", 7, my_node.getCost());
	}

	/**
	 * Test method for {@link DijkstraNode#setLocation(int)}.
	 */
	@Test public void testSetLocation() {
		my_node.setLocation(4);
		assertEquals("Did not get correct location", 4, my_node.getLocation());
		my_node.setLocation(7);
		assertEquals("Did not get correct new location", 7, my_node.getLocation());
	}

	/**
	 * Test method for {@link DijkstraNode#compareTo(DijkstraNode)}.
	 */
	@Test public void testCompareTo() {
		assertEquals("Did not get correct compare", 
				my_node.getCost() - my_previous_node.getCost(), 
				my_node.compareTo(my_previous_node));
		assertEquals("Did not get correct reverse compare",
				my_previous_node.getCost() - my_node.getCost(),
				my_previous_node.compareTo(my_node));
		final DijkstraNode node = new DijkstraNode(my_vertex, 5, my_previous_node);
		assertEquals("Should be equal", 0, my_node.compareTo(node));
		assertEquals("Should still be equal", 0, node.compareTo(my_node));
	}
	
	/**
	 * Test method for {@link DijkstraNode#setPrevious(DijkstraNode)}.
	 */
	@Test public void testSetPrevious() {
		my_node.setPrevious(null);
		assertTrue("Node previous should now be null", my_node.getPrevious() == null);
		my_node.setPrevious(my_previous_node);
		assertTrue("Previous should now be my_previous_node", 
				my_node.getPrevious().equals(my_previous_node));
	}
}
