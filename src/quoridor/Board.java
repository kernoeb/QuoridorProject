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
	private ArrayList<Square> grid;

	/**
	 * Board constructor
	 * intiliaze a 81 squares board (9*9)
	 * Initialize walls and positionates players on their starting positions
	 */
	public Board() {
		// TODO - implement Board.Board
	}

	public int getSIZE() {
		// TODO - implement Board.getSIZE
		return this.SIZE;
	}

	public ArrayList<Square> getGrid() {
		// TODO - implement Board.getGrid
		return this.grid;
	}

	public void initializeBoard() {
		// TODO - implement Board.initializeBoard
	}

	public ArrayList<Square> listOfPossibilitiesFence() {
		ArrayList<Square> al = new ArrayList<Square>();
		// TODO - implement Board.listOfPossibilitiesFence
		return al;
	}

	/**
	 *
	 * @param player
	 */
	public ArrayList<Square> listOfPossibilitiesPawn(Player player) {
		ArrayList<Square> al = new ArrayList<Square>();
		// TODO - implement Board.listOfPossibilitiesPawn
		return al;
	}

	public String toString() {
		// TODO - implement Board.toString
		return "";
	}

}
