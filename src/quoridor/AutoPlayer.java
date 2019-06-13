package quoridor;

public class AutoPlayer extends Player {

	private String name;

	/**
	 * AutoPlayer constructor
	 * @param name
	 * @author
	 */
	public AutoPlayer(String name) {
		// TODO - implement AutoPlayer.AutoPlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * AI for the AutoPlayer
	 * Choose between playFence and playPawn
	 * Only one level of difficulty for now
	 * @author
	 */
	public void play() {
		// TODO - implement AutoPlayer.play
		throw new UnsupportedOperationException();
	}

	/**
	 * Places a fence on the desired emplacement
	 * The overlaping validity is checked by the square object by fenceStatus
	 * The path validity is checked by the checkExistingPath method
	 * @author
	 */
	private void playFence() {
		// TODO - implement AutoPlayer.playFence
		throw new UnsupportedOperationException();
	}

	/**
	 * Moves the pawns to the desired direction.
	 * The validity is checked by the current square fenceStatus
	 * @author
	 */
	private void playPawn() {
		// TODO - implement AutoPlayer.playPawn
		throw new UnsupportedOperationException();
	}

	/**
	 * Check if the player has finished his turn
	 * @return true if the player has finished his turn, false otherwise
	 * @author
	 */
	public boolean checkHasFinished(Player player) {
		// TODO - implement Board.checkHasFinished
		throw new UnsupportedOperationException();
	}


	/**
	 * @return true if there is an existing path to the end, false otherwise
	 * @author
	 */
	public boolean checkExistingPath() {
		// TODO - implement Board.checkExistingPath
		throw new UnsupportedOperationException();
	}
}
