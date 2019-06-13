package quoridor;

// import project
import quoridor.Square;
//import GUI.MainGUI;

// import java
import java.util.ArrayList;

/**
 * Class for the initializing of the board.
 * Contains the grid and calls the GUI package to show the game
 * @author
 */
public class Board {

	private int SIZE = 9;
	private Square[][] grid;

	/**
	 * Board constructor
	 * intiliaze a 81 squares board (9*9)
	 * Initialize walls and positionates players on their starting positions
	 */
	public Board() {
		this.grid = new Square[SIZE][SIZE]; 
		initializeBoard();
	}

	public int getSIZE() {
		return this.SIZE;
	}

	public Square[][] getGrid() {
		return this.grid;
	}

	public void initializeBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.grid[i][j] = new Square(i, j, Status.NONE);
			}
		}

		this.grid[0][4] = new Square(0, 4, Status.Player1);
		this.grid[8][4] = new Square(8, 4, Status.Player2);
	}

	public ArrayList<Square> listOfPossibilitiesFence() {
		ArrayList<Square> al = new ArrayList<Square>();
		return al;
	}

	/**
	 *
	 * @param player
	 */
	public ArrayList<Square> listOfPossibilitiesPawn(Player player) {
		ArrayList<Square> al = new ArrayList<Square>();
		return al;
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.grid[i][j].getStatus() == Status.NONE) System.out.print(" \u001B[37mX ");;
				if (this.grid[i][j].getStatus() == Status.Player1) System.out.print(" \u001B[32mX ");;
				if (this.grid[i][j].getStatus() == Status.Player2) System.out.print(" \u001B[31mX ");;
			}
			System.out.println("\u001B[0m");
		}
		return "";
	}

}
