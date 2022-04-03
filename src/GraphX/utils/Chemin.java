package GraphX.utils;

import java.util.ArrayList;
import java.util.Scanner;

public class Chemin {
	
	private static int nbIterations = 0;
	private static int[][] couts;
	private static String affTab = "";
	private static int[][] graphe;

	/**
	 * Constructeur de la classe pour générer le chemin et les algorithmes de Bellman-Ford et Dijkistra
	 * @param arcs Récupération de l'arraylist d'arcs contenant le graphe dans son ensemble (indexFrom, indexTo, costs)
	 */
	public Chemin(ArrayList<int[]> arcs){
		
		//Conversion en double tableau de int
		int[][] graphe;
		graphe = new int[arcs.size()][3];

		for (int i = 0; i < arcs.size(); i++) {
			for (int j = 0; j < 3; j++) {
				graphe[i][j] = arcs.get(i)[j];
			}
		}

		this.graphe = graphe;


		Scanner sc = new Scanner( System.in );

		// Récupération de l'algorithme à utiliser
		int algo = -1;

		do {
			System.out.print("Choisissez votre algorithme ( 0 pour Bellman-Ford ou 1 pour Dijkstra ) : ");
			algo = sc.nextInt();
		} while ( algo != 0 && algo != 1 );

		System.out.println(graphe[0][0]);


		// Appel de l'algorithme choisi avec le sommet source choisi
		switch( algo ) {
			case 0 : bellmanFord(graphe[0][0]); break;
			case 1 : dijkstra(graphe, couts, graphe[0][0]); break;
		}

		sc.close();

		System.out.println(affTab);
	}

	

	/**
	 * Initialise les coûts des sommets dans le tableau en fonction du sommet de départ.
	 * @param sommetDep
	 * @return
	 */
	public static int[][] initialiserCouts( int sommetDep )
	{
		int nbSommets = 0;

		for( int[] arc : graphe ) {
			if( nbSommets < arc[0] + 1 ) nbSommets = arc[0] + 1;
			if( nbSommets < arc[1] + 1 ) nbSommets = arc[1] + 1;
		}

        couts = new int[nbSommets][2];

        for( int i = 0; i < nbSommets; i++ ) {
            couts[i][0] = i;
            couts[i][1] = Integer.MAX_VALUE;
        }

        couts[sommetDep][1] = 0;

		return couts;
    }


	/**
	 * Initialise la chaîne de caractères contenant l'affichage du tableau.
	 */
	public static void initialiserAffichage()
	{
		// ligne 1
		affTab = "\n              |";
		for ( int[] sommet: couts )
			affTab += String.format( "%6s", (sommet[0]) + " |");
		
		// ligne 2 ( séparation )
		affTab += "\n" + "-".repeat( 15 + 6 * couts.length ) + "\n";
		
		// ligne 3
		affTab += "Init          |";

		for( int[] sommet: couts )
		{
			if( sommet[1] == Integer.MAX_VALUE )
				affTab += "  +∞ |";
			else
				affTab += String.format( "%4d", sommet[1] ) + " |";
		}

		// ligne 4 ( séparation )
		affTab += "\n" + "-".repeat( 15 + 6 * couts.length ) + "\n";
	}


	/**
	 * Ajoute une ligne à la chaîne de caractères contenant
	 * l'affichage du tableau.
	 */
	public static void ajouterLigneTableauAff()
	{
		// ligne 1 ( valeurs )
		affTab += "Iteration " + String.format( "%3d", ++nbIterations ) + " |";

		for( int[] sommet: couts )
		{
			if( sommet[1] == Integer.MAX_VALUE )
				affTab += "  +∞ |";
			else
				affTab += String.format( "%4d", sommet[1] ) + " |";
		}

		// ligne 2 ( séparation )
		affTab += "\n" + "-".repeat( 15 + 6 * couts.length ) + "\n";
	}


	/**
	 * Regarde si le tableau passé en paramètre est considéré comme vide.
	 * Il est considéré comme vide si le sous-tableau est null.
	 * @param tab Le tableau à tester.
	 * @return Un booléen indiquant si le tableau est vide ou non.
	 */
	public static boolean tableauVide( int[][] tab )
	{
		for( int[] sommet: tab )
			if( sommet != null ) return false;

		return true;
	}


	/**
	 * Fonction exécutant l'algorithme Dijkstra.
	 * @param graphe Le graphe sur lequel la fonction travaille.
	 * @param couts Le tableau contenant les sommets avec leurs coûts.
	 * @param sommetDepart Le sommet de départ.
	 */
	public static void dijkstra( int[][] graphe, int[][] couts, int sommetDepart )
	{
		// Initialisation des valeurs des sommets.			
		couts = initialiserCouts(sommetDepart);

		// Initialisation de l'affichage pour avoir les valeurs de bases dans le tableau.
		initialiserAffichage();

		int[][] E = new int[couts.length][couts[0].length];
		int[][] F = couts.clone();
		int[] u = new int[2];

		while( !tableauVide( F ) )
		{
			// Sélection du sommet avec la plus petite valeur d.
			int valMin = Integer.MAX_VALUE;
			for( int[] sommet: F )
			{
				if( sommet != null && sommet[1] < valMin )
				{
					u = sommet;
					valMin = sommet[1];
				}
			}
			
			F[u[0]] = null;
			E[u[0]] = u;
			
			// Relachement des arcs adjacents.
			for( int[] arc: graphe )
			{
				if( arc[0] == u[0] )
				{
					if( F[arc[1]] != null )
					{
						if( F[arc[1]][1] > u[1] + arc[2] ) 
							F[arc[1]][1] = u[1] + arc[2];
					}
				}
			}

			ajouterLigneTableauAff();
		}
	}

	/**
	 * Fonction exécutant l'algorithme Bellman-ford.
	 */
	public static void bellmanFord( int sommetSource ) {
        couts = initialiserCouts(sommetSource);

		initialiserAffichage();

        for( int i = 1; i < couts.length; i++ )
		{
            for( int[] arc : graphe )
			{
                if( couts[arc[1]][1] > couts[arc[0]][1] + arc[2] )
                    couts[arc[1]][1] = couts[arc[0]][1] + arc[2];
			}

            ajouterLigneTableauAff();
        }
    }


}