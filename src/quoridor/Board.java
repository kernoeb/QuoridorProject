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

	private String ANSI_RESET = "\u001B[0m";
	private String ANSI_GREY = "\u001B[30m";
	private String ANSI_RED = "\u001B[31m";
	private String ANSI_GREEN = "\u001B[32m";
	private String ANSI_YELLOW = "\u001B[33m";
	private String ANSI_BLUE = "\u001B[34m";
	private String ANSI_PURPLE = "\u001B[35m";
	private String ANSI_CYAN = "\u001B[36m";
	private String ANSI_WHITE = "\u001B[37m";

	/**
	 * Board constructor
	 * intiliaze a 81 squares board (9*9)
	 * Initialize walls and positionates players on their starting positions
	 */
	public Board() {
		this.grid = new Square[this.getTotalSize()][this.getTotalSize()];
		this.initializeBoard();
		// stopColors();
	}

	public void initializeBoard() {
		Square temp = null;

		for (int x = 0; x < this.getTotalSize(); x++) {
			for (int y = 0; y < this.getTotalSize(); y++) {
				temp = this.grid[x][y];

				if (this.isEvenNumber(x) && this.isOddNumber(y)) {
					this.grid[x][y] = new Square(x, y, Status.FENCEPOSSIBLE);
				}

				else if (this.isOddNumber(x)) {
					this.grid[x][y] = new Square(x, y, Status.FENCEPOSSIBLE);
				}

				else {
					this.grid[x][y] = new Square(x, y, Status.PAWNPOSSIBLE);
				}
			}
		}

		this.grid[this.pawnCoord(8)][this.pawnCoord(4)] = new Square(this.pawnCoord(8), this.pawnCoord(4), Status.PAWN1);
		this.grid[this.pawnCoord(0)][this.pawnCoord(4)] = new Square(this.pawnCoord(0), this.pawnCoord(4), Status.PAWN2);
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

	public int pawnCoord(int x) {
		return x*2;
	}

	public int fenceCoord(int x) {
		return (x*2) + 1;
	}

	public ArrayList<Square> listOfPossibilitiesFence() {
		ArrayList<Square> list = new ArrayList<Square>();

	//	for(int x=0; x < )
		return list;
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

		Square temp = null;

		for (int x = 0; x < this.getTotalSize(); x++) {
			for (int y = 0; y < this.getTotalSize(); y++) {
				temp = this.grid[x][y];

				if (temp.isPawn()) {
					if (temp.isPawnPossible()) {
						ret += this.ANSI_WHITE;
					}

					else if (temp.isPawn1()) {
						ret += this.ANSI_GREEN;
					}

					else if (temp.isPawn2()) {
						ret += this.ANSI_RED;
					}

					ret += "X ";
				}


				else if (temp.isFence()) {
					if (temp.isFencePossible()) {
						ret += this.ANSI_GREY;
					}

					else if (temp.isFencePawn1()) {
						ret += this.ANSI_GREEN;
					}

					else if (temp.isFencePawn2()) {
						ret += this.ANSI_RED;
					}

					if (this.isEvenNumber(x)) {
						ret += "| ";
					}

					else if (this.isEvenNumber(y)) {
						ret += "─ ";
					}

					else {
						ret += "+ ";
					}
				}
			}

			ret += this.ANSI_RESET + "\n";
		}

		return ret;
	}


	public void stopColors() {
		this.ANSI_RESET = "";
		this.ANSI_GREY = "";
		this.ANSI_RED = "";
		this.ANSI_GREEN = "";
		this.ANSI_YELLOW = "";
		this.ANSI_BLUE = "";
		this.ANSI_PURPLE = "";
		this.ANSI_CYAN = "";
		this.ANSI_WHITE = "";		
	}

}
