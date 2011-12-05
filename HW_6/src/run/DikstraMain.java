package run;

import java.util.Hashtable;


/**
 * 
 * @author Steven Cozart
 * @version 1.0
 */
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
		GraphDisplay display = new GraphDisplay(my_graph);

		
		
		
	}
	


}
