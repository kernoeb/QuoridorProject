package quoridor;

/**
 * Player
 * @author
 */
public abstract class Player {

	protected Board board;
	protected Square currentSquare;
	protected String name;
	protected int nbFences;

	/**
	 * Player constructor
	 * it cans either be human or bot
	 * it has a dedidcated pawn and a number of fences left.
	 */
	public Player(String name, Board board) {
		// TODO - implement Player.Player
		if((name != null) && (board != null)) {
			this.name = name;
			this.board = board;
			this.nbFences = 0;
			this.currentSquare = null;
		}
		else {
			System.err.println("Player : Paramètre(s) non valide(s).");
		}
	}

	public Player(String name, Board board, int initX, int initY) {
		// TODO - implement Player.Player
		if((name != null) && (board != null)) {
			this.name = name;
			this.board = board;
			this.nbFences = 0;
			this.setCurrentSquare(initX, initY);
		}
		else {
			System.err.println("Player : Paramètre(s) non valide(s).");
		}
	}

	public void setCurrentSquare(int x, int y) {
		if((x > 0) && (x < this.board.getTotalSize()) && (y > 0) && (y < this.board.getTotalSize())
			&& (this.board.getGrid()[x][y].isPawnPossible())) {

			this.currentSquare = this.board.getGrid()[x][y];
		}

		else {
			System.err.println("setCurrentSquare : Paramètre(s) non valide(s).");
		}
	}

	public Square getCurrentSquare() {
		return this.currentSquare;
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

	public abstract void play();

	protected void movePawn(int x, int y) {
		if(this.currentSquare != null) {
			this.board.getGrid()[x][y].setStatus(this.currentSquare.getStatus());
			this.board.getGrid()[this.currentSquare.getX()][this.currentSquare.getY()].setStatus(Status.PAWNPOSSIBLE);
		}

		else {
			System.err.println("movePawn : L'attribut currentSquare de Player ne doit pas être null.");
		}
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
