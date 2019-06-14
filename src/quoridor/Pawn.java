package quoridor;

/**
 * Pawn
 * @author
 */
public class Pawn {

	private Square currentSquare;
	private int x;
	private int y;

	/**
	 *
	 * @param x
	 * @param y
	 */
	public Pawn(int x, int y) {
		// TODO - implement Pawn.Pawn
	}

	public int getX() {
		// TODO - implement Pawn.getX
		return this.x;
	}

	public int getY() {
		// TODO - implement Pawn.getY
		return this.y;
	}

	/**
	 * Move direction in X and Y
	 * Possible moves : -1 / 1 / 0 / 2
	 * @param x
	 * @param y
	 */
	public void movePawn(int x, int y) {
		this.x = this.x + x;
		this.y = this.y + y;
	}

	public String toString() {
		// TODO - implement Pawn.toString
		return "";
	}

}
