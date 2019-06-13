package quoridor;

// import project
import quoridor.Board;
import quoridor.Mode;
import quoridor.Player;

// import java
import java.util.Scanner;

/**
 * This class contains the methods to intiliaze the game
 * @author
 */
public class Game {

	private Board board;
	private Player player1;
	private Player player2;
	private Mode mode;

	private Scanner scan;

	/**
	 * Game constructor
	 * intiliaze the board, the two players and the mode
	 * The mode is choosen by the players with a scanner
	 * @author
	 */
	public Game(Mode mode, String namePlayer1, String namePlayer2) {
		this.board = new Board();

		if (mode == Mode.HH) {
			this.player1 = new HumanPlayer(namePlayer1, this.board);
			this.player2 = new HumanPlayer(namePlayer2, this.board);
		}

		else if (mode == Mode.HA) {
			this.player1 = new HumanPlayer(namePlayer1, this.board);
			this.player2 = new AutoPlayer(namePlayer2, this.board);
		}
	}

	public Board getBoard() {
		return this.board;
	}

	public Player getPlayer1() {
		return this.player1;
	}

	public Player getPlayer2() {
		return this.player2;
	}

	/**
	 * @param board the desired board to play the game with.
	 */
	public void setBoard(Board board) {
		if(board != null) {
			this.board = board;
		}
		else {
			System.err.println("setBoard : Paramètre non valide");
		}
	}

	/**
	 *
	 */
	public void initializeGame() {
		// TODO - implement Game.initializeGame
	}

	/**
	 * Choose randomly which player plays first
	 * @return the starting player
	 */
	public Player whoStarts() {
		// TODO - implement Game.whoStarts
		return this.player1; // à changer
	}

	/**
	 * Launch the game
	 */
	public void start() {
		// TODO - implement Game.start
	}

	/**
	 * End the game and launch the results procedure
	 */
	public void endOfGame() {
		// TODO - implement Game.endOfGame
	}

	public String toString() {
		// TODO - implement Game.toString
		return "";
	}

}
