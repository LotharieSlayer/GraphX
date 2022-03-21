package GraphX.utils;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.algorithm.Toolkit ;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Lecture{
	private HashMap<String,Integer> lstPoids;
	private SingleGraph graph;

	public Lecture(){
		lstPoids = new HashMap<>();
		graph = new SingleGraph("Graphe utilisareur");
		creerGraph();
	}

	private void creerGraph(){
		for (String s : getLstPoint()) {
			graph.addNode(""+s);
		}
		for (String[] tabs : getLstLiaison()) {
			graph.addEdge(tabs[0]+tabs[1],tabs[0],tabs[1],true);
			lstPoids.put(tabs[0]+tabs[1],Integer.parseInt(tabs[2]));

		}
		for(Node n:graph.getNodeSet()) 
		{
			n.setAttribute("ui.label",n.getId());
		}
		graph.display();
	}

	private ArrayList<String> getLstPoint(){
		ArrayList<String> lstPoint = new ArrayList<String>();
		boolean continu = true;
		String lig;
		try {
			Scanner sc = new Scanner(new FileInputStream("../examples/properties.graph"));
			sc.nextLine();
			sc.nextLine();
			sc.nextLine();
			sc.nextLine();
			while(continu){
				lig=sc.nextLine();
				if(lig.startsWith("/")){
					continu = false;
				} else {
				lstPoint.add(lig.substring(0,lig.indexOf(';')));
				}
			}
		}catch (Exception e) {
			System.out.println("err 0\n");
			e.printStackTrace();
		}
		return lstPoint;
	}

	private ArrayList<String[]> getLstLiaison(){
		ArrayList<String[]> lstLiaison = new ArrayList<String[]>();
		String lig;
		try {
			Scanner sc = new Scanner(new FileInputStream("../examples/properties.graph"));
			while(!sc.nextLine().equals("links:")){}
			while(sc.hasNextLine()){
				String[] tabS = new String[3];
				lig=sc.nextLine();
				tabS=lig.substring(0,lig.indexOf(';')).split(",");
				lstLiaison.add(tabS);
				
			}
		}catch (Exception e) {
			System.out.println("err 1\n");
			e.printStackTrace();
		}
		return lstLiaison;
	}

	public SingleGraph getGraph(){
		return this.graph;
	}

	private HashMap<String,Integer> getLstPoids(){
		return this.lstPoids;
	}

}