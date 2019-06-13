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
	public Game() {
		// TODO - implement Game.Game
		throw new UnsupportedOperationException();
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
		this.board = board;
	}

	/**
	 *
	 */
	public void initializeGame() {
		// TODO - implement Game.initializeGame
		throw new UnsupportedOperationException();
	}

	/**
	 * Choose randomly which player plays first
	 * @return the starting player
	 */
	public Player whoStarts() {
		// TODO - implement Game.whoStarts
		throw new UnsupportedOperationException();
	}

	/**
	 * Launch the game
	 */
	public void start() {
		// TODO - implement Game.start
		throw new UnsupportedOperationException();
	}

	/**
	 * End the game and launch the results procedure
	 */
	public void endOfGame() {
		// TODO - implement Game.endOfGame
		throw new UnsupportedOperationException();
	}

	public String toString() {
		// TODO - implement Game.toString
		throw new UnsupportedOperationException();
	}

}
