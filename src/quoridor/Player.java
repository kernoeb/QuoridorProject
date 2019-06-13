package quoridor;

/**
 * Player
 * @author
 */
public abstract class Player {

	protected Board board;
	protected Pawn pawn;
	protected String name;
	private int nbFences;

	/**
	 * Player constructor
	 * it cans either be human or bot
	 * it has a dedidcated pawn and a number of fences left.
	 */
	public Player() {
		// TODO - implement Player.Player
		throw new UnsupportedOperationException();
	}

	public Pawn getPawn() {
		return this.pawn;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * @return the number of fences left playable by the player
	 * @author
	 */
	public int checkNbRestingFences() {
		// TODO - implement Player.checkNbRestingFences
		throw new UnsupportedOperationException();
	}


	public boolean checkHasFinished(){
		// TODO - Player.checkHasFinished
		throw new UnsupportedOperationException();
	}

	public void setNbFences(int nb) {
		this.nbFences = nb;
	}

	/**
	 *
	 * @param square
	 * @return
	 */
	public boolean checkFencePossible(Square square) {
		// TODO - implement Player.checkFencePossible
		throw new UnsupportedOperationException();
	}

	public boolean checkExistingPath(){
		// TODO - implement player.checkExistingPath
		throw new UnsupportedOperationException();
	}

}
