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
	public Player(String name, Board board) {
		// TODO - implement Player.Player
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
		return 0;
	}


	public boolean checkHasFinished(){
		// TODO - Player.checkHasFinished
		return true;
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
		return true;
	}

	public boolean checkExistingPath(){
		// TODO - implement player.checkExistingPath
		return true;
	}
}
