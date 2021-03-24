package hupman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class HupmanRunner {

	public static void main(String[] args) {
		
		try {
			File myFile = new File("C:\\Users\\elies\\maze2-3.txt");
			Scanner myReader = new Scanner(myFile);
			
			int rows = myReader.nextInt();
			int columns = myReader.nextInt();
			
			int[][] unorderedMaze = new int[2*rows+1][columns+1];
			
			getMazeFile(unorderedMaze, rows, columns);
			
			ArrayList<MazeSpace> maze = new ArrayList<MazeSpace>();
			
			makeMaze(unorderedMaze, maze, rows, columns);
			
			for (int i=0; i<2*rows+1; i++) {
				for (int j=0; j<columns+1; j++) {
					if (i%2 == 0 && j == columns) {
						
					}
					else {
						myReader.nextInt();
					}
				}
			}
			
			int pellets = myReader.nextInt();
			
			
			MazeSpace[] locations = new MazeSpace[pellets];
			
			makeLocations(locations, pellets, rows, columns);
			
			myReader.close();
			
			Scanner input = new Scanner(System.in);
			System.out.println("What is your start state's x coordinate?");
			int startX = input.nextInt();
			
			System.out.println("What is your start state's y coordinate?");
			int startY = input.nextInt();
			
			input.close();
			
			
			LimitedDepthFirstSearch(maze,locations,pellets,rows,columns,startX,startY);
		
		}
			
		catch (FileNotFoundException e) {
			System.out.println("An error occured.");
			e.printStackTrace();
		}
		
	}
		
		
	public static void BreadthFirstSearch(ArrayList<MazeSpace> maze, MazeSpace[] locations, int pellets, int rows, int columns, int startX, int startY) {

		boolean goalMet = false;
		MazeSpace goalSpace;
		
		for (int i=0; i<pellets; i++) {
			int x = locations[i].getX();
			int y = locations[i].getY();
			if (x == startX && y == startY) {				//because start state is at 0,0
				goalMet = true;
				System.out.println("Pellet found on start state.");
			}
		}
		
		int currentSpace = startX + (columns*startY);
		
		ArrayList<MazeSpace> queue = new ArrayList<MazeSpace>();
		
		maze.get(currentSpace).setAvailable(false);
		
		queue.add(maze.get(currentSpace));
		
		int cost = 0;
		int nodes = 0;
		int totalSpaces = maze.size();
		
		while(goalMet == false && queue.size() != 0) {				//while we haven't found the goal
			currentSpace = maze.indexOf(queue.get(0));	//number can be from 0 to maze.size()
			System.out.println("Index of space visited:" + currentSpace);
			int north = queue.get(0).getNorth();
			int east = queue.get(0).getEast();
			int south = queue.get(0).getSouth();
			int west = queue.get(0).getWest();
			if (north == 0 && maze.get(currentSpace-columns).getAvailable() && goalMet == false) {  	//if we can move north
				MazeSpace tempSpace = maze.get(currentSpace-columns);
				for (int i=0; i<pellets; i++) {  										
					if (locations[i].getX() == tempSpace.getX() && locations[i].getY() == tempSpace.getY()) {		//checking for pellet
						goalMet = true;
						goalSpace = tempSpace;
						System.out.println("Goal met at:" + goalSpace.getX() + " " + goalSpace.getY());
						System.out.println("Number of nodes explored:" + nodes);
						System.out.println("Cost of moves:" + cost);
					}
				}
				maze.get(currentSpace-columns).setAvailable(false);									//mark as reached (unavailable)
				nodes++;
				queue.add(maze.get(currentSpace-columns));
			}
			
			if (east == 0 && maze.get(currentSpace+1).getAvailable() && goalMet == false) {  	//if we can move east
				MazeSpace tempSpace = maze.get(currentSpace+1);
				for (int i=0; i<pellets; i++) {  										
					if (locations[i].getX() == tempSpace.getX() && locations[i].getY() == tempSpace.getY()) {		//checking for pellet
						goalMet = true;
						goalSpace = tempSpace;
						System.out.println("Goal met at:" + goalSpace.getX() + " " + goalSpace.getY());
						System.out.println("Number of nodes explored:" + nodes);
						System.out.println("Cost of moves:" + cost);
					}
				}
				maze.get(currentSpace+1).setAvailable(false);										//mark as reached (unavailable)
				nodes++;
				queue.add(maze.get(currentSpace+1));
			}
			if (south == 0 && maze.get(currentSpace+columns).getAvailable() && goalMet == false) {  	//if we can move south
				MazeSpace tempSpace = maze.get(currentSpace+columns);
				for (int i=0; i<pellets; i++) {  										
					if (locations[i].getX() == tempSpace.getX() && locations[i].getY() == tempSpace.getY()) {		//checking for pellet
						goalMet = true;
						goalSpace = tempSpace;
						System.out.println("Goal met at:" + goalSpace.getX() + " " + goalSpace.getY());
						System.out.println("Number of nodes explored:" + nodes);
						System.out.println("Cost of moves:" + cost);
					}
				}
				maze.get(currentSpace+columns).setAvailable(false);									//mark as reached (unavailable)
				nodes++;
				queue.add(maze.get(currentSpace+columns));
			}
			if (west == 0 && maze.get(currentSpace-1).getAvailable() && goalMet == false) {  	//if we can move west
				MazeSpace tempSpace = maze.get(currentSpace-1);
				for (int i=0; i<pellets; i++) {  										
					if (locations[i].getX() == tempSpace.getX() && locations[i].getY() == tempSpace.getY()) {		//checking for pellet
						goalMet = true;
						goalSpace = tempSpace;
						System.out.println("Goal met at:" + goalSpace.getX() + " " + goalSpace.getY());
						System.out.println("Number of nodes explored:" + nodes);
						System.out.println("Cost of moves:" + cost);
					}
				}
				maze.get(currentSpace-1).setAvailable(false);
				nodes++;
				queue.add(maze.get(currentSpace-1));
			}
			
			queue.remove(0);
		}
		
		System.out.println("Total number of spaces in maze: " + totalSpaces);
		
	}

	public static void LimitedDepthFirstSearch(ArrayList<MazeSpace> maze, MazeSpace[] locations, int pellets, int rows, int columns, int startX, int startY) {
		boolean goalMet = false;
		MazeSpace goalSpace;
		
		for (int i=0; i<pellets; i++) {
			int x = locations[i].getX();
			int y = locations[i].getY();
			if (x == startX && y == startY) {				//because start state is at 0,0
				goalMet = true;
				System.out.println("Pellet found on start state.");
			}
		}
		
		int currentSpace = startX + (columns*startY);
		
		Stack<MazeSpace> stack = new Stack<MazeSpace>();
		
		maze.get(currentSpace).setAvailable(false);
		
		stack.push(maze.get(currentSpace));								//dfs uses stack
		
		int nodes = 0;								//total number of nodes visited
		int depth = 0;
		int depthLimit = (rows*columns)/2;
		int totalSpaces = maze.size();
		
		while(goalMet == false && stack.size() != 0 && depth<depthLimit) {				//while we haven't found the goal
			boolean incDepth = false;
			MazeSpace node = stack.pop();
			currentSpace = maze.indexOf(node);				//number can be from 0 to maze.size()
			System.out.println("Index of space visited" + " " + currentSpace);
			int north = node.getNorth();
			int east = node.getEast();
			int south = node.getSouth();
			int west = node.getWest();
			
			if (north == 0 && maze.get(currentSpace-columns).getAvailable() && goalMet == false) {  	//if we can move north
				nodes++;
				MazeSpace tempSpace = maze.get(currentSpace-columns);
				for (int i=0; i<pellets; i++) {  										
					if (locations[i].getX() == tempSpace.getX() && locations[i].getY() == tempSpace.getY()) {		//checking for pellet
						goalMet = true;
						goalSpace = tempSpace;
						System.out.println("Goal met at:" + goalSpace.getX() + " " + goalSpace.getY());
						depth++;
						System.out.println("Number of nodes visited:" + nodes);
						System.out.println("Depth of tree:" + depth);
					}
				}
				maze.get(currentSpace-columns).setAvailable(false);									//mark as reached (unavailable)
				incDepth = true;
				stack.push(maze.get(currentSpace-columns));
			}
			if (east == 0 && maze.get(currentSpace+1).getAvailable() && goalMet == false) {  	//if we can move east
				nodes++;
				MazeSpace tempSpace = maze.get(currentSpace+1);
				for (int i=0; i<pellets; i++) {  										
					if (locations[i].getX() == tempSpace.getX() && locations[i].getY() == tempSpace.getY()) {		//checking for pellet
						goalMet = true;
						goalSpace = tempSpace;
						System.out.println("Goal met at:" + goalSpace.getX() + " " + goalSpace.getY());
						depth++;
						System.out.println("Number of nodes visited:" + nodes);
						System.out.println("Depth of tree:" + depth);
					}
				}
				maze.get(currentSpace+1).setAvailable(false);										//mark as reached (unavailable)
				incDepth = true;
				stack.push(maze.get(currentSpace+1));
			}
			if (south == 0 && maze.get(currentSpace+columns).getAvailable() && goalMet == false) {  	//if we can move south
				nodes++;
				MazeSpace tempSpace = maze.get(currentSpace+columns);
				for (int i=0; i<pellets; i++) {  										
					if (locations[i].getX() == tempSpace.getX() && locations[i].getY() == tempSpace.getY()) {		//checking for pellet
						goalMet = true;
						goalSpace = tempSpace;
						System.out.println("Goal met at:" + goalSpace.getX() + " " + goalSpace.getY());
						depth++;
						System.out.println("Number of nodes visited:" + nodes);
						System.out.println("Depth of tree:" + depth);
					}
				}
				maze.get(currentSpace+columns).setAvailable(false);									//mark as reached (unavailable)
				incDepth = true;
				stack.push(maze.get(currentSpace+columns));
			}
			if (west == 0 && maze.get(currentSpace-1).getAvailable() && goalMet == false) {  	//if we can move west
				nodes++;
				MazeSpace tempSpace = maze.get(currentSpace-1);
				for (int i=0; i<pellets; i++) {  										
					if (locations[i].getX() == tempSpace.getX() && locations[i].getY() == tempSpace.getY()) {		//checking for pellet
						goalMet = true;
						goalSpace = tempSpace;
						System.out.println("Goal met at:" + goalSpace.getX() + " " + goalSpace.getY());
						depth++;
						System.out.println("Number of nodes visited:" + nodes);
						System.out.println("Depth of tree:" + depth);
					}
				}
				maze.get(currentSpace-1).setAvailable(false);										//mark as reached (unavailable)
				incDepth = true;
				stack.push(maze.get(currentSpace-1));
			}
			if (incDepth) {								//only increases if node has children
				depth++;
			}
			if (depth==depthLimit) {
				System.out.println("Depth limit reached. Goal could not be found within depth limit.");
			}
		}
		System.out.println("Total number of spaces in maze:" + totalSpaces);
	}

	/*public static void IterativeDeepeningSearch(ArrayList<MazeSpace> maze, MazeSpace[] locations, int pellets, int rows, int columns, int startX, int startY, int depthLimit) {
		
		for (int i=0; i<depthLimit; i++) {
			LimitedDepthFirstSearch(maze,locations,pellets,rows,columns,startX,startY,i);
		}
			
	}*/

	
	public static void getMazeFile(int[][] emptyMaze, int rows, int columns) {
		try {
			File myFile = new File("C:\\Users\\elies\\maze2-3.txt");
			Scanner myReader = new Scanner(myFile);
			
			myReader.nextInt();
			myReader.nextInt();
			
			for (int i=0; i<2*rows+1;i++) {
				for (int j=0; j<columns+1; j++) {
					if (i%2 == 0 && j == columns) {
						emptyMaze[i][j] = 8;
					}
					else {
						emptyMaze[i][j] = myReader.nextInt();
					}
				}
			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occured.");
			e.printStackTrace();
		}
		
	}
	
	public static void makeMaze(int[][] filledMaze, ArrayList<MazeSpace> emptyMaze, int rows, int columns) {
		for (int i=0; i<2*rows-1; i++) {
			for (int j=0; j<columns; j++) {
				if (i%2==0) {
					emptyMaze.add(new MazeSpace(j, i/2, filledMaze[i][j], filledMaze[i+1][j+1],
							filledMaze[i+2][j], filledMaze[i+1][j], true));
				}
			}
		}
	
	}
	
	public static void makeLocations(MazeSpace[] emptyLocations, int pellets, int rows, int columns) {
		try {
			File myFile = new File("C:\\Users\\elies\\maze2-3.txt");
			Scanner myReader = new Scanner(myFile);
			
			myReader.nextInt();
			myReader.nextInt();
			myReader.nextInt();
			
			for (int i=0; i<2*rows+1; i++) {
				for (int j=0; j<columns+1; j++) {
					if (i%2 == 0 && j == columns) {
						
					}
					else {
						myReader.nextInt();
					}
				}
			}
			
		for (int i=0; i<pellets; i++) {
			int x = myReader.nextInt();
			int y = myReader.nextInt();
			emptyLocations[i] = new MazeSpace(y, x, 0, 0, 0, 0, false);
		}
		
		myReader.close();
	}
	catch (FileNotFoundException e) {
		System.out.println("An error occured.");
		e.printStackTrace();
	}
	
}
	
}
