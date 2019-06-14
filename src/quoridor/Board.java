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

	public boolean isEvenNumber(int x) {
		return (x % 2) == 0;
	}

	public boolean isOddNumber(int x) {
		return (x % 2) != 0;
	}

	public void initializeBoard() {
		Square temp = null;

		for (int i = 0; i <= SIZE+7; i++) {
			for (int j = 0; j <= SIZE+7; j++) {
				temp = this.grid[i][j];

				if (this.isEvenNumber(i) && this.isOddNumber(j)) {
					this.grid[i][j] = new Square(i, j, Status.FENCEPOSSIBLE);
				}

				else if (this.isOddNumber(i)) {
					this.grid[i][j] = new Square(i, j, Status.FENCEPOSSIBLE);
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
	//
	// 	for (int i = 0; i <= this.getTotalSize(); i++) {
	// 		for (int j = 0; j <= this.getTotalSize(); j++) {
	// 			if ((j > (this.SIZE - 1)) && this.grid[i][j].getStatus() == Status.FENCEPOSSIBLEH) {
	// 				ret += this.ANSI_BLACK + "─ +";
	// 			}
	//
	// 			if (this.isOddNumber(i) && (j == (this.getTotalSize() - 1))) {
	// 				ret += this.ANSI_BLACK + "─";
	// 			}
	//
	// 			else {
	// 				if (this.grid[i][j].getStatus() == Status.PAWNPOSSIBLE) {
	// 					System.out.print(this.ANSI_WHITE + "X ");
	// 				}
	// 				else if (this.grid[i][j].getStatus() == Status.FENCEPOSSIBLE) {
	// 					ret += this.ANSI_BLACK + "|";
	// 				}
	//
	// 				else if (this.grid[i][j].getStatus() == Status.PAWN1) {
	// 					ret += this.ANSI_GREEN + "X ";
	// 				}
	//
	// 				else if (this.grid[i][j].getStatus() == Status.PAWN2) {
	// 					ret += this.ANSI_RED + "X ";
	// 			}
	// 		}
	//
	// 		ret += this.ANSI_RESET + "\n";
	// 	}
	//
		return ret;
	}

}
