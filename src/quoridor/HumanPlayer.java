package quoridor;

// import project
import quoridor.Pawn;

// import java
import java.util.Scanner;


public class HumanPlayer extends Player {
	private Scanner scan;

	/**
	 * HumanPlayer constructor
	 * @param name
	 * @author
	 */
	public HumanPlayer(String name, Board board) {
		super(name, board);
		this.scan = new Scanner(System.in);
	}

	/**
	 * Let the player choose between if he wants to play a fence or moves its pawn
	 * @author
	 */
	public void play() {
		// TODO - implement HumanPlayer.play
	}

	/**
	 * Places a fence on the desired emplacement
	 * The overlaping validity is checked by the square object by fenceStatus
	 * The path validity is checked by the checkExistingPath method
	 * @param scan the player imput
	 * @author
	 */
	private void playFence(Scanner scan) {
		// TODO - implement HumanPlayer.playFence
	}

	/**
	 * Moves the pawns to the desired direction.
	 * The validity is checked by the current square fenceStatus
	 * @param scan the player input depending on the graphical choosen mode
	 * @author
	 */
	private void playPawn(Scanner scan) {
		// TODO - implement HumanPlayer.playPawn
	}

	/**
	 * Check if the player has finished his turn
	 * @return true if the player has finished his turn, false otherwise
	 * @author
	 */
	public boolean checkHasFinished() {
		// TODO - implement Board.checkHasFinished
		return true;
	}

	/**
	 * @return true if there is an existing path to the end, false otherwise
	 * @author
	 */
	public boolean checkExistingPath() {
		// TODO - implement HumanPlayer.java
		return true;
	}

}
