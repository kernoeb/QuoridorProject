package quoridor;

import java.util.ArrayList;
import java.util.Random; 

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
	private void playFence() {
		// TODO - implement AutoPlayer.playFence
	}
}
