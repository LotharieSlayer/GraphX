package GraphX.utils;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Lecture{
	private SingleGraph graph;
	private String fichier;
	private ArrayList<int[]> arcs = new ArrayList<>();

	public Lecture(String fichier){
		this.fichier = fichier;
		graph = new SingleGraph("Graphe utilisateur");
		creerGraph();
	}

	/**
	 * Méthode générant le graphe et l'affichant
	 */
	private void creerGraph(){
		for (String s : getLstPoint()) {
			graph.addNode(""+s);
		}
		for (String[] tabs : getLstArcs()) {
			graph.addEdge(tabs[0]+tabs[1],tabs[0],tabs[1],true);
		}
		for(Node n:graph.getNodeSet()) 
		{
			n.setAttribute("ui.label",n.getId());
		}
		graph.display();
	}

	/**
	 * Lecture de la liste des points
	 * @return Une arraylist contenant tous les points 
	 */
	private ArrayList<String> getLstPoint(){
		ArrayList<String> lstPoint = new ArrayList<String>();
		boolean continu = true;
		String lig;
		try {
			Scanner sc = new Scanner(new FileInputStream("../files/"+ fichier));
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

	/**
	 * Lecture de la liste des arcs dans le graphe
	 * @return Une arraylist contenant tous les arcs
	 */
	public ArrayList<String[]> getLstArcs(){
		ArrayList<String[]> lstLiaison = new ArrayList<String[]>();
		String lig;
		try {
			Scanner sc = new Scanner(new FileInputStream("../files/" + fichier));
			while(!sc.nextLine().equals("links:")){}
			while(sc.hasNextLine()){
				String[] tabS = new String[3];
				lig=sc.nextLine();
				tabS=lig.substring(0,lig.indexOf(';')).split(",");
				int[] tabInt = {Integer.parseInt(tabS[0]), Integer.parseInt(tabS[1]), Integer.parseInt(tabS[2])};
				arcs.add(tabInt);
				lstLiaison.add(tabS);
				
			}
		}catch (Exception e) {
			System.out.println("err 1\n");
			e.printStackTrace();
		}
		return lstLiaison;
	}

	/**
	 * Retourne le graph en cours d'exécution
	 * @return le graph
	 */
	public SingleGraph getGraph(){
		return this.graph;
	}

	/**
	 * Retourne une arraylist d'arcs mais sous forme d'entier primitifs
	 * @return
	 */
	public ArrayList<int[]> getLstArcsInt(){
		return this.arcs;
	}

}