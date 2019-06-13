package quoridor;

// import Project
import quoridor.Status;

//import java


public class Square {

	private int x;
	private int y;

	private Status status; // if it contains player 1 / player 2 or none

	/**
	 * Square constructor
	 * Square object which is a square on the board.
	 * It knows its coordinates, if there is a player or not and if their is fences around him
	 * @author
	 */
	public Square(int i, int j, Status s) {
		this.x = i;
		this.y = j;
		this.status = s;
		// TODO - implement Square.Square
	}

	public Status getStatus() {
		return this.status;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setAsFence() {
		this.status = Status.FENCE;
	}

	
}
