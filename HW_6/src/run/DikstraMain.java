package run;

import java.util.Hashtable;

public class DikstraMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleGraph my_graph = new SimpleGraph();
		Hashtable my_table = GraphInput.LoadSimpleGraph(my_graph, "src/run/test2.txt");
		DijkstraMap DijkMap = new DijkstraMap(my_graph, my_graph.aVertex());
		
	}

}
