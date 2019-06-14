package quoridor;

public class AutoPlayer extends Player {
	/**
	 * AutoPlayer constructor
	 * @param name
	 * @author
	 */
	public AutoPlayer(String name, Board board, int initX, int initY) {
		super(name, board, initX, initY);
	}

	public AutoPlayer(String name, Board board) {
		super(name, board);
	}

	/**
	 * AI for the AutoPlayer
	 * Choose between playFence and playPawn
	 * Only one level of difficulty for now
	 * @author
	 */
	public void play() {
		// TODO - implement AutoPlayer.play
	}

	/**
	 * Places a fence on the desired emplacement
	 * The overlaping validity is checked by the square object by fenceStatus
	 * The path validity is checked by the checkExistingPath method
	 * @author
	 */
	private void playFence() {
		// TODO - implement AutoPlayer.playFence
	}

	/**
	 * Moves the pawns to the desired direction.
	 * The validity is checked by the current square fenceStatus
	 * @author
	 */
	// private void playPawn() {
	// }
}
