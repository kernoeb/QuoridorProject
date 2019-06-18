package utilitary;

import quoridor.*;
import java.util.ArrayDeque;
import java.util.Queue;

class Node {
	int x, y, dist;

	Node(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

public class Maze {
	private final int row[] = { -1, 0, 0, 1 };
	private final int col[] = { 0, -1, 1, 0 };	
	Board board;
	int sens;

	public Maze(Board board, int sens) {
		this.board = board;
		//this.sens = sens;
	}

	public void printSolution(boolean visited[][]) { 
		for (int i = 0; i < visited.length; i++) { 
			for (int j = 0; j < visited.length; j++) { 
				if (visited[i][j] == true) System.out.print(" " + "\u001B[32m1" + " ");
				else System.out.print(" " + "\u001B[0m0" + " ");
			} 
			System.out.println(); 
		} 
		System.out.println("\u001B[0m");
	}

	public void printMaze(int maze[][]) { 
		for (int i = 0; i < maze.length; i++) { 
			for (int j = 0; j < maze.length; j++) 
				System.out.print(" " + maze[i][j] + " "); 
			System.out.println(); 
		} 
		System.out.println();
	}

	private boolean isValid(int mat[][], boolean visited[][], int row, int col) {
		return (row >= 0) && (row < this.board.getTotalSize()) && (col >= 0) && (col < this.board.getTotalSize()) && mat[row][col] == 1 && !visited[row][col];
	}

	public boolean BFS(int mat[][], int i, int j, int x) {
		boolean[][] visited = new boolean[this.board.getTotalSize()][this.board.getTotalSize()];
		Queue<Node> q = new ArrayDeque<>();

		visited[i][j] = true;
		q.add(new Node(i, j, 0));

		int min_dist = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			Node node = q.poll();

			i = node.x;
			j = node.y;
			int dist = node.dist;

			if (i == x) {
				min_dist = dist;
				break;
			}

			for (int k = 0; k < 4; k++) {
				if (isValid(mat, visited, i + row[k], j + col[k])) {
					visited[i + row[k]][j + col[k]] = true;
					q.add(new Node(i + row[k], j + col[k], dist + 1));
				}
			}
		}

		if (min_dist != Integer.MAX_VALUE) {
			// System.out.print("The shortest path from source to destination " + "has length " + min_dist + "\n");
			// printSolution(visited);
			return true;
		} else {
			// System.out.print("Destination can't be reached from source\n");
			return false;
		}
	}

	private boolean checkValid(int x, int y) {
		return (x >= 0) && (x < this.board.getTotalSize()) && (y >= 0) && (y < this.board.getTotalSize());
	}

	private boolean fenceAroundX(int x, int y) {
		try {
			if (!this.board.getGrid()[x-1][y].isFencePossible()) 
				if (!this.board.getGrid()[x-2][y].isFencePossible()) 
					if(!this.board.getGrid()[x-3][y].isFencePossible())
						if (!this.board.getGrid()[x+1][y].isFencePossible())
							if (!this.board.getGrid()[x+2][y].isFencePossible())
								if (!this.board.getGrid()[x+3][y].isFencePossible()) 
									return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	private boolean fenceAroundY(int x, int y) {
		try {
			if (!this.board.getGrid()[x][y-1].isFencePossible()) 
				if (!this.board.getGrid()[x][y-2].isFencePossible()) 
					if(!this.board.getGrid()[x][y-3].isFencePossible())
						if (!this.board.getGrid()[x][y+2].isFencePossible())
							if (!this.board.getGrid()[x][y+2].isFencePossible())
								if (!this.board.getGrid()[x][y+3].isFencePossible())
									return true;
		} catch (ArrayIndexOutOfBoundsException e) {
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

				if (this.sens == 0) {
					if (fenceAroundX(x, y)) maze[x][y] = 0;

					else if (fenceAroundY(x, y)) maze[x][y] = 0;

					else if (temp.isPawn2() || temp.isFencePawn1() || temp.isFencePawn2()) {
						maze[x][y] = 0;
					} else {
						maze[x][y] = 1;
					}

				} else {
					if (temp.isPawn1() || temp.isFencePawn1() || temp.isFencePawn2()) {
						maze[x][y] = 0;
					} else maze[x][y] = 1;					
				}
			}
		}

		// printMaze(maze);
		return maze;
	}










}