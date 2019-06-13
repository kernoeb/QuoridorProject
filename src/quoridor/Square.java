package quoridor;

// import Project
import quoridor.StatusFence;
import quoridor.Status;

//import java


public class Square {

	private int x;
	private int y;

	private Status status; // if it contains player 1 / player 2 or none


	private StatusFence statusFence; // gets it from the enumeration statusFence

	// true if there is a fence, false otherwise
	private boolean fenceN;
	private boolean fenceE;
	private boolean fenceS;
	private boolean fenceW;


	/**
	 * Square constructor
	 * Square object which is a square on the board.
	 * It knows its coordinates, if there is a player or not and if their is fences around him
	 * @author
	 */
	public Square() {
		// TODO - implement Square.Square
		throw new UnsupportedOperationException();
	}

	public StatusFence getStatusFence() {
		return statusFence;
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

	public boolean getFenceN() {
		// TODO - implement Square.getFenceStatusN
		throw new UnsupportedOperationException();
	}

	public boolean getFenceE() {
		// TODO - implement Square.getFenceStatusE
		throw new UnsupportedOperationException();
	}

	public boolean getFenceS() {
		// TODO - implement Square.getFenceStatusS
		throw new UnsupportedOperationException();
	}

	public void setFenceE(boolean fenceE) {
		this.fenceE = fenceE;
	}

	public void setFenceN(boolean fenceN) {
		this.fenceN = fenceN;
	}

	public void setFenceS(boolean fenceS) {
		this.fenceS = fenceS;
	}

	public void setFenceW(boolean fenceW) {
		this.fenceW = fenceW;
	}
}
