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

	private final String ANSI_RESET = "\u001B[0m";
	private final String ANSI_BLACK = "\u001B[30m";
	private final String ANSI_RED = "\u001B[31m";
	private final String ANSI_GREEN = "\u001B[32m";
	private final String ANSI_YELLOW = "\u001B[33m";
	private final String ANSI_BLUE = "\u001B[34m";
	private final String ANSI_PURPLE = "\u001B[35m";
	private final String ANSI_CYAN = "\u001B[36m";
	private final String ANSI_WHITE = "\u001B[37m";

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

	public int getTotalSize() {
		return this.SIZE * 2 - 1;
	}

	public void initializeBoard() {
		Square temp = null;

		for (int i = 0; i <= SIZE+7; i++) {
			for (int j = 0; j <= SIZE+7; j++) {
				temp = this.grid[i][j];

				if (i % 2 == 0 && j % 2 != 0) {
					this.grid[i][j] = new Square(i, j, Status.FENCEPOSSIBLEV);
				}

				else if () {
					System.out.print(" \u001B[30m─ +");
				}

				else if (i % 2 != 0) {
					this.grid[i][j] = new Square(i, j, Status.FENCEPOSSIBLEH);
				}

				else {
					this.grid[i][j] = new Square(i, j, Status.PAWNPOSSIBLE);
				}
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
		for (int i = 0; i <= this.SIZE+7; i++) {
			for (int j = 0; j <= this.SIZE+7; j++) {
				if ((j > 8) && this.grid[i][j].getStatus() == Status.FENCEPOSSIBLEH) {
					System.out.print(this.ANSI_BLACK + "─ +");
				}

				if ((i % 2 != 0) && (j == 16)) {
					System.out.print(this.ANSI_BLACK + "─");
				}

				else {
					if (this.grid[i][j].getStatus() == Status.PAWNPOSSIBLE) System.out.print(this.ANSI_WHITE + "X ");
					// if (this.grid[i][j].getStatus() == Status.FENCEPOSSIBLEV) System.out.print("\u001B[37m|");
					if (this.grid[i][j].getStatus() == Status.FENCEPOSSIBLE) System.out.print(this.ANSI_BLACK + "|");
					if (this.grid[i][j].getStatus() == Status.PAWN1) System.out.print(this.ANSI_GREEN + "X ");
					if (this.grid[i][j].getStatus() == Status.PAWN2) System.out.print(thsi.ANSI_RED + "X ");
				}
			}
			System.out.println(this.ANSI_RESET);
		}
		return "";
	}

}
