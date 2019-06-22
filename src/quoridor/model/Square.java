package quoridor.model;

// import Project

import java.io.*;
//import java


public class Square implements Serializable {

	private static final long serialVersionUID = 5458954L;

	private int x;
	private int y;

	private Status status; // if it contains player 1 / player 2 or none

	/**
	 * Square constructor
	 * Square object which is a square on the board.
	 * It knows its coordinates, if there is a player or not and if their is fences around him
	 * @author
	 */
	public Square(int x, int y, Status s) {
		this.x = x;
		this.y = y;
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

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isFence() {
		return (this.status == Status.FENCEPOSSIBLE) || (this.status == Status.FENCEPAWN1)
			|| (this.status == Status.FENCEPAWN2);
	}

	public boolean isFencePawn1() {
		return this.status == Status.FENCEPAWN1;
	}

	public boolean isFencePawn2() {
		return this.status == Status.FENCEPAWN2;
	}

	public boolean isFencePossible() {
		return this.status == Status.FENCEPOSSIBLE;
	}

	public boolean isFenceFixed() {
		return (this.status == Status.FENCEPAWN1) || (this.status == Status.FENCEPAWN2);
	}

	public boolean isPawn() {
		return (this.status == Status.PAWN1) || (this.status == Status.PAWN2) || (this.status == Status.PAWNPOSSIBLE);
	}

	public boolean isPawnPossible() {
		return this.status == Status.PAWNPOSSIBLE;
	}

	public boolean isPawn1() {
		return this.status == Status.PAWN1;
	}

	public boolean isPawn2() {
		return this.status == Status.PAWN2;
	}

	public boolean isPawnFixed() {
		return (this.status == Status.PAWN1) || (this.status == Status.PAWN2);
	}
}
