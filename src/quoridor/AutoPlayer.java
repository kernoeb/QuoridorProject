package quoridor;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import view.*;

public class AutoPlayer extends Player implements Serializable {

	private static final long serialVersionUID = 5452854L;
	/**
	 * AutoPlayer constructor
	 * @param name
	 * @author
	 */
	public AutoPlayer(Game game, String name, Board board, int initX, int initY, boolean terminal) {
		super(game, name, board, initX, initY, terminal);
	}

	public AutoPlayer(Game game, String name, Board board, boolean terminal) {
		super(game, name, board, terminal);
	}

	/**
	 * AI for the AutoPlayer
	 * Choose between playFence and playPawn
	 * Only one level of difficulty for now
	 * @author
	 */
	public void play() {
		ArrayList<Square> al = this.board.listOfPossibilitiesPawn(this);
		int currentX = this.currentSquare.getX();
		int currentY = this.currentSquare.getY();
		// System.out.println("Vals : " + currentX + " " + currentY);
		this.board.printListOfPossibilitiesPawn(this);

		if (al.contains(this.board.getGrid()[(currentX+2)][currentY])) {
			super.movePawn((currentX+2), currentY);
		} else {
			Square s = al.get(new Random().nextInt(al.size()));
			super.movePawn(s.getX(), s.getY());
		}

	}

	/**
	 * Places a fence on the desired emplacement
	 * The overlaping validity is checked by the square object by fenceStatus
	 * The path validity is checked by the checkExistingPath method
	 * @author
	 */
	public void playFence() {
		// TODO - implement AutoPlayer.playFence
	}

	public void playPawn() {}

	public void play(Square square, BoardGUI boardGUI) {}

	public void playPawn(Square square) {}

	public void playFence(Square square, BoardGUI boardGUI) {}
}
