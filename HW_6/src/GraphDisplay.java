

import java.util.Iterator;

/**
 * Used to display graph information.  
 * @author Steven Cozart
 * @version 1.0
 */
public class GraphDisplay {
	
	/**
	 * The graph to 
	 */
	private SimpleGraph my_graph;
	
	/**
	 * 
	 * @param the_graph The graph to display.
	 */
	public GraphDisplay(SimpleGraph the_graph){
		my_graph = the_graph;
	}
	
	/**
	 * 
	 * @return The number of vertices.  
	 */
	public  String numVertices(){
		return "" + my_graph.numVertices();
	}
	
	/**
	 * 
	 * @return The number of edges as a String.  
	 */
	public String numEdges(){
		return "" + my_graph.numEdges();
	}
	
	/**
	 * 
	 * @return All vertices name on a new line.
	 */
	public String allVertices(){
		StringBuilder sb = new StringBuilder();
		Iterator<Vertex> vertices = my_graph.vertices();
		
		while(vertices.hasNext()){
			sb.append(vertices.next().getName() + "\n");
		}
		return sb.toString();
	}
	
	public String allEdges(){
		StringBuilder sb = new StringBuilder();
		Iterator<Edge> edges = my_graph.edges();
		while(edges.hasNext()){
			Edge temp = edges.next();
			sb.append(temp.getFirstEndpoint().getName()+ " -> " + temp.getSecondEndpoint().getName() +" At cost:" + temp.getData()+ "\n");
		}
		return sb.toString();
	}
	
	
	

}
