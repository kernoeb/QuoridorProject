package quoridor;

// import project
import quoridor.Pawn;

// import java
import java.util.Scanner;


public class HumanPlayer extends Player {

	private String name;
	private Scanner scan;

	/**
	 * HumanPlayer constructor
	 * @param name
	 * @author
	 */
	public HumanPlayer(String name) {
		// TODO - implement HumanPlayer.HumanPlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * Let the player choose between if he wants to play a fence or moves its pawn
	 * @author
	 */
	public void play() {
		// TODO - implement HumanPlayer.play
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	/**
	 * Moves the pawns to the desired direction.
	 * The validity is checked by the current square fenceStatus
	 * @param scan the player input depending on the graphical choosen mode
	 * @author
	 */
	private void playPawn(Scanner scan) {
		// TODO - implement HumanPlayer.playPawn
		throw new UnsupportedOperationException();
	}

	/**
	 * Check if the player has finished his turn
	 * @return true if the player has finished his turn, false otherwise
	 * @author
	 */
	public boolean checkHasFinished() {
		// TODO - implement Board.checkHasFinished
		throw new UnsupportedOperationException();
	}

	/**
	 * @return true if there is an existing path to the end, false otherwise
	 * @author
	 */
	public boolean checkExistingPath() {
		// TODO - implement HumanPlayer.java
		throw new UnsupportedOperationException();
	}

}
