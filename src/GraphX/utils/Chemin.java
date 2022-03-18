package GraphX.utils;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import java.util.ArrayList;
import java.util.HashMap;

import org.graphstream.algorithm.Toolkit ;

public class Chemin {
	private SingleGraph s;
	private HashMap<String,Integer> MapCount;

	public Chemin(SingleGraph s, HashMap<String,Integer> MapCount){
		this.s=s;
		this.MapCount = MapCount;
		HashMap<String,Integer> lstPoids = new HashMap<>();
	}

	public int[] Bellman(){
		ArrayList<Node> arrSomm = new ArrayList<Node>();
		Node n;

		// //met Le tableau de cout à +l'infini saufs pour l'origine
		// HashMap<String,Integer> tabVal = new HashMap();
		// for (int i = 0; i < tabVal.length; i++) {
		// 	tabVal[i] =99999;	
		// }
		// tabVal[0] = 0;


		// //itérations
		// for (int i = 0; i < tabVal.length-1; i++) {

		// 	//pour touts les arcs
		// 	for (Edge e : s.getEdgeSet()) {
		// 		if(){

		// 		}
		// 	}
		// }

		// //test si chemin absorbant


		// //renvois le tableau de couts
		return ;
	
	}

	/*public void Dijikstra(Node sommetDepart){
	}*/
}