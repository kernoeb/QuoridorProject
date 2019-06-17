import quoridor.*;

public class Maze {
	Board board;

	public Maze(Board board) {
		this.board = board;
	}

	public void printSolution(int sol[][]) { 
		for (int i = 0; i < sol.length; i++) { 
			for (int j = 0; j < sol.length; j++) 
				System.out.print(" " + sol[i][j] + " "); 
			System.out.println(); 
		} 
	}

	public void printMaze(int maze[][]) { 
		for (int i = 0; i < maze.length; i++) { 
			for (int j = 0; j < maze.length; j++) 
				System.out.print(" " + maze[i][j] + " "); 
			System.out.println(); 
		} 
	}

	private boolean isSafe(int maze[][], int x, int y) { 
		return (x >= 0 && x < maze.length && y >= 0 && y < maze.length && maze[x][y] == 1); 
	} 

	private boolean solveMaze() {
		int[][] maze = convertToMaze(this.board);
		int[][] sol = new int[maze.length][maze.length]; 

		if (solveMazeUtil(maze, 0, 0, sol) == false) { 
			System.out.print("Aucune solution"); 
			return false; 
		} 

		printSolution(sol); 
		return true; 
	} 

	private boolean solveMazeUtil(int maze[][], int x, int y, int sol[][]) { 
		if (x == 0) { 
			sol[x][y] = 1; 
			return true; 
		} 

		if (isSafe(maze, x, y) == true) { 
			sol[x][y] = 1; 

			if (solveMazeUtil(maze, x + 1, y, sol)) return true;

			if (solveMazeUtil(maze, x, y + 1, sol)) return true;

			sol[x][y] = 0; 
			return false; 
		} 
		return false; 
	}

	public int[][] convertToMaze(Board board) {
		Square temp = null;
		int[][] maze = new int[this.board.getTotalSize()][this.board.getTotalSize()];

		for (int x = 0; x < this.board.getTotalSize(); x++) {
			for (int y = 0; y < this.board.getTotalSize(); y++) {
				temp = this.board.getGrid()[x][y];

				if (temp.isPawn1() || temp.isPawn2() || temp.isFencePawn1() || temp.isFencePawn2() || temp == null) {
					maze[x][y] = 0;
				} else maze[x][y] = 1;
			}
		}

		printMaze(maze);
		return maze;
	}
}