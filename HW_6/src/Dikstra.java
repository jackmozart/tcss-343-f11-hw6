import java.util.Iterator;
import java.util.LinkedList;


public class Dikstra {

	private Vertex my_startVertex;
	
	private Vertex my_endVertex;
	
	private SimpleGraph my_graph;
	
	private double my_minDistance;

	private LinkedList<Vertex> my_path;
	
	
	public Dikstra(SimpleGraph the_graph, Vertex the_start, Vertex the_end) {
		my_endVertex = the_end;
		my_graph = the_graph;
		my_startVertex = the_start;
		my_minDistance = Double.MAX_VALUE;
		my_path = new LinkedList<Vertex>();
		applyDikstra();
	}
	
	private void applyDikstra(){
		BinaryHeap heap = new BinaryHeap(); 
		DijkstraNode end_Node = null;
		DijkstraNode start_Node = null;
		Iterator<Vertex> graphIter = my_graph.vertices();
		
		//make every vertex into a dikstra node and set their cost to inf 
		//and the source to 0.
		while(graphIter.hasNext()){
			Vertex  a_vertex = graphIter.next();
			DijkstraNode a_dIjkstrNode = new DijkstraNode();
			a_vertex.setData(a_dIjkstrNode);
			a_dIjkstrNode.setMy_vertex(a_vertex);
			if(a_vertex == my_startVertex){
				a_dIjkstrNode.setMy_weight(0);
				a_dIjkstrNode.setVisited(true);
			}
//			else if(a_vertex == my_endVertex){
//				end_Node = a_dIjkstrNode;
//				a_dIjkstrNode.setMy_weight(Double.MAX_VALUE);
//			}
			else{
				a_dIjkstrNode.setMy_weight(Double.MAX_VALUE);
			}
			heap.insert(a_dIjkstrNode);
		}
		
		//set the ending point and the starting point for Dijkstra by making weight 0 and locked to true
		end_Node = (DijkstraNode) my_endVertex.getData();

		
		
		while(!heap.isEmpty()){
			DijkstraNode min_node = null;
			try {
				min_node = (DijkstraNode) heap.deleteMin();
				
				if(min_node == end_Node){
					System.out.print("done");
					break;
				}
				
			} catch (EmptyHeapException e) {
				e.printStackTrace();
			}
			
			Iterator<Edge> edges = my_graph.incidentEdges(min_node.getMy_vertex());
			
			while(edges.hasNext()){
				Edge a_edge = edges.next();
				DijkstraNode opositeVertex = (DijkstraNode) a_edge.getSecondEndpoint().getData();
				
				double novelWeight = min_node.getMy_weight() + (Double)a_edge.getData();
				if(opositeVertex.getMy_weight() > novelWeight){
					opositeVertex.setMy_weight(novelWeight);
					opositeVertex.setMy_previous_node(min_node);
					
					//to do heap operations
					heap.percolateUp(opositeVertex.getMy_heap_loc());
				}
			}
			System.out.println(min_node.getMy_vertex().getName()+  " " +  min_node.getMy_weight());
			min_node.setVisited(true);
			
		}
		//set the min distance to the distance of destion node
		DijkstraNode temp = (DijkstraNode) my_endVertex.getData();
		my_minDistance = temp.getMy_weight();
		DijkstraNode pathStart = temp;
		

		while(pathStart.getMy_previous_node() != null){
			my_path.add(pathStart.getMy_vertex());
			pathStart = pathStart.getMy_previous_node();
		}
		
		
		
	}
	

	public double getDistance(){
		return my_minDistance;
	}
	
	/**
	 * Returns the path and length from start to end.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Length: " + getDistance());
		for(Vertex a_verex: my_path){
			sb.append(" " + a_verex.getName()+ " <- ");
		}		
		sb.append(" Start ");
		return sb.toString();
	}

}
