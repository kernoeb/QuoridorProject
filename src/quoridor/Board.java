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
		this.grid = new Square[SIZE+8][SIZE+8];
		initializeBoard();
	}

	public int getSIZE() {
		return this.SIZE;
	}

	public Square[][] getGrid() {
		return this.grid;
	}

	public void initializeBoard() {
		for (int i = 0; i <= SIZE+7; i++) {
			for (int j = 0; j <= SIZE+7; j++) {
				if (i % 2 == 0 && j % 2 != 0) this.grid[i][j] = new Square(i, j, Status.FENCEPOSSIBLEV);
				else if (i % 2 != 0) this.grid[i][j] = new Square(i, j, Status.FENCEPOSSIBLEH);
				else this.grid[i][j] = new Square(i, j, Status.PAWNPOSSIBLE);
			}
		}

		this.grid[0*2][4*2] = new Square(0*2, 4*2, Status.PAWN1);
		this.grid[8*2][4*2] = new Square(8*2, 4*2, Status.PAWN2);
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

	public void printListOfPossibilitiesPawn() {
		
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i <= SIZE+7; i++) {
			for (int j = 0; j <= SIZE+7; j++) {
				// if ((j > 8) && this.grid[i][j].getStatus() == Status.FENCEPOSSIBLEH) System.out.print(" \u001B[37m─ +");
				if ((j > 8) && this.grid[i][j].getStatus() == Status.FENCEPOSSIBLEH) System.out.print(" \u001B[30m─ +");
				// if ((i % 2 != 0) && (j == 16)) System.out.print(" \u001B[37m─");
				if ((i % 2 != 0) && (j == 16)) System.out.print(" \u001B[30m─");
				else {
					if (this.grid[i][j].getStatus() == Status.PAWNPOSSIBLE) System.out.print(" \u001B[37mX ");
					// if (this.grid[i][j].getStatus() == Status.FENCEPOSSIBLEV) System.out.print("\u001B[37m|");
					if (this.grid[i][j].getStatus() == Status.FENCEPOSSIBLEV) System.out.print("\u001B[30m|");
					if (this.grid[i][j].getStatus() == Status.PAWN1) System.out.print(" \u001B[32mX ");
					if (this.grid[i][j].getStatus() == Status.PAWN2) System.out.print(" \u001B[31mX ");
				}
			}
			System.out.println("\u001B[0m");
		}
		return "";
	}

}
