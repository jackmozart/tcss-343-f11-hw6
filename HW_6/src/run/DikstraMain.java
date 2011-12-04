package run;

import java.util.Hashtable;

public class DikstraMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String file;
		if (args != null && args.length == 1) {
			file = args[0];
		} else {
			file = "src/test/test2.txt";
		}
		SimpleGraph my_graph = new SimpleGraph();
		Hashtable my_table = GraphInput.LoadSimpleGraph(my_graph, file);
		DijkstraMap dike = new DijkstraMap(my_graph, my_graph.aVertex());
		GraphDisplay display = new GraphDisplay(my_graph);
		Vertex [] path = dike.getPath(my_graph.aVertex());
		for(Vertex a_vertex : path ){
			System.out.println(a_vertex.getName());
			System.out.println(a_vertex.getData());
		}
//		DikstraGui gui = new DikstraGui();
//		gui.start();
		
//		System.out.print(display.allEdges());
//		System.out.print(display.allVertices());
//		System.out.print(display.numEdges());
//		System.out.print(display.numVertices());
		
		
		
	}
	
	public static void displayMyGraph(){
		
	}
	


}
