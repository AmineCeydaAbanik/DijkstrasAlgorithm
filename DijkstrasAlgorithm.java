import java.lang.Math; 
import java.util.*;  

class DijkstrasAlgorithm { 

	private static final int LOCATION_COUNT = 7; 

    private static double[][] adjacencyMatrix;
    private static int[] parents;

	// dijkstra algorithm
	private static void dijkstraAlgorithm( int src, int dst) 
	{ 
		// keeps the shortest distances to node i
		double[] shortests = new double[LOCATION_COUNT]; 
		// keeps the parents in SPT
		parents = new int[LOCATION_COUNT]; 
		// is used to detect whether the location visited or not.
		boolean[] isVisited = new boolean[LOCATION_COUNT]; 

		// initialization.
		for (int i = 0; i < LOCATION_COUNT; i++) 
		{ 
			shortests[i] = Integer.MAX_VALUE; 
			isVisited[i] = false; 
		} 
		
		shortests[src] = 0; // distance from itself
		parents[src] = -1;  // no parent

		// loop for all nodes
		for (int i = 0; i < LOCATION_COUNT; i++) 
		{ 
			int nearestNode = -1; 
			double shortestDistance = Integer.MAX_VALUE; 

			// find the minimum distance in non-visited nodes.
			for (int locationIndex = 0; locationIndex < LOCATION_COUNT; locationIndex++) 
			{ 
				if (!isVisited[locationIndex] && shortests[locationIndex] < shortestDistance) 
				{ 
					nearestNode = locationIndex; 
					shortestDistance = shortests[locationIndex]; 
				} 
			} 

			// mark the node as visited.
			isVisited[nearestNode] = true; 

			// updating adjacent nodes
			for (int locationIndex = 0; locationIndex < LOCATION_COUNT; locationIndex++) 
			{ 
				double edgeDistance = adjacencyMatrix[nearestNode][locationIndex]; 
				
				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortests[locationIndex])) 
				{ 
					parents[locationIndex] = nearestNode; 
					shortests[locationIndex] = shortestDistance + edgeDistance; 
				} 
			} 
		} 

		System.out.println("Total: " + shortests[dst]);
		System.out.print("Path: ");
		printPath(dst); 
	} 

	private static void printPath( int dst) 
	{ 
		String printedText = "";

		while( dst != -1 ){

			printedText = dst + " " + printedText;
			dst = parents[dst];
		}
		System.out.print(printedText); 
		
	} 

		// Driver Code 
	public static void main(String[] args) 
	{ 
	    // locations
	    double AA_x    = 350;   
	    double AA_y    = 165.5; 
	    double FM_x    = 102.3; 
	    double FM_y    = 10.26;
	    double BFNM_x  = 256.6; 
	    double BFNM_y  = 92.3;
	    double GOOCH_x = 120.5;
	    double GOOCH_y = 5.5;
	    double CH_x    = 92.5; 
	    double CH_y    = 86.4;
	    double EP_x    = 90.5;  
	    double EP_y    = 302.1;
	    double DOMT_x  = 40.8;  
	    double DOMT_y  = 72.35;

		//index of locations in adjacency matrix
	    int EP    = 0;
	    int BFNM  = 1;
	    int AA    = 2;
	    int DOMT  = 3;
	    int FM    = 4;
	    int CH    = 5;
	    int GOOCH = 6;

		adjacencyMatrix = new double[LOCATION_COUNT][LOCATION_COUNT];

		// fill the adjacency matrix by calculating the distances between each location.
	    adjacencyMatrix[EP][FM] 	 = Math.sqrt( Math.pow(EP_x - FM_x, 2) + Math.pow(EP_y - FM_y, 2) );
	    adjacencyMatrix[EP][CH]		 = Math.sqrt( Math.pow(EP_x - CH_x, 2) + Math.pow(EP_y - CH_y, 2) );

	    adjacencyMatrix[BFNM][GOOCH] = Math.sqrt( Math.pow(BFNM_x - GOOCH_x, 2) + Math.pow(BFNM_y - GOOCH_y, 2) );
	    adjacencyMatrix[BFNM][AA]    = Math.sqrt( Math.pow(AA_x - BFNM_x, 2) + Math.pow(AA_y - BFNM_y, 2) );

		adjacencyMatrix[AA][BFNM] 	 = Math.sqrt( Math.pow(AA_x - BFNM_x, 2) + Math.pow(AA_y - BFNM_y, 2) );
	    adjacencyMatrix[AA][FM] 	 = Math.sqrt( Math.pow(AA_x - FM_x, 2) + Math.pow(AA_y - FM_y, 2) );

	    adjacencyMatrix[DOMT][CH]    = Math.sqrt( Math.pow(DOMT_x - CH_x, 2) + Math.pow(DOMT_y - CH_y, 2) );
	    adjacencyMatrix[DOMT][FM]    = Math.sqrt( Math.pow(DOMT_x - FM_x, 2) + Math.pow(DOMT_y - FM_y, 2) );
	    adjacencyMatrix[DOMT][EP]    = Math.sqrt( Math.pow(DOMT_x - EP_x, 2) + Math.pow(DOMT_y - EP_y, 2) );

	    adjacencyMatrix[FM][AA]      = Math.sqrt( Math.pow(AA_x - FM_x, 2) + Math.pow(AA_y - FM_y, 2) );
	    adjacencyMatrix[FM][BFNM] 	 = Math.sqrt( Math.pow(FM_x - BFNM_x, 2) + Math.pow(FM_y - BFNM_y, 2) );
	    adjacencyMatrix[FM][EP] 	 = Math.sqrt( Math.pow(EP_x - FM_x, 2) + Math.pow(EP_y - FM_y, 2) );
	    adjacencyMatrix[FM][CH] 	 = Math.sqrt( Math.pow(CH_x - FM_x, 2) + Math.pow(CH_y - FM_y, 2) );
	    adjacencyMatrix[FM][GOOCH] 	 = Math.sqrt( Math.pow(GOOCH_x - FM_x, 2) + Math.pow(GOOCH_y - FM_y, 2) );
	    adjacencyMatrix[FM][DOMT] 	 = Math.sqrt( Math.pow(FM_x - DOMT_x, 2) + Math.pow(FM_y - DOMT_y, 2) );

	    adjacencyMatrix[CH][EP] 	 = Math.sqrt( Math.pow(EP_x - CH_x, 2) + Math.pow(EP_y - CH_y, 2) );
	    adjacencyMatrix[CH][FM]		 = Math.sqrt( Math.pow(CH_x - FM_x, 2) + Math.pow(CH_y - FM_y, 2) );
	    adjacencyMatrix[CH][DOMT] 	 = Math.sqrt( Math.pow(CH_x - DOMT_x, 2) + Math.pow(CH_y - DOMT_y, 2) );

	    adjacencyMatrix[GOOCH][BFNM] = Math.sqrt( Math.pow(GOOCH_x - BFNM_x, 2) + Math.pow(GOOCH_y - BFNM_y, 2) );
	    adjacencyMatrix[GOOCH][FM]   = Math.sqrt( Math.pow(GOOCH_x - FM_x, 2) + Math.pow(GOOCH_y - FM_y, 2) );

	    Scanner scan = new Scanner(System.in); 

	    System.out.println("Locations:\n0) Elder Park\n1) Big Fish Natural Museum\n2) Aquaria Aquarium\n3) Deep Ones Movie Theater\n4) Fhtagn Mall\n5) City Hall\n6) Great Old Ones Concert Hall\n");
	    System.out.print("Enter starting location: ");  
		int src = scan.nextInt();  
		System.out.print("Enter arrival location: ");
		int dst = scan.nextInt();  

		dijkstraAlgorithm( src, dst); 

		System.out.println();
	} 
} 

