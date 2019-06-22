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

	public boolean play(Square square, BoardGUI boardGUI) {
		boolean ret = false;

		if(!this.terminal) {
			if (square.isPawn()) {
				ret = this.playPawn(square);

				if (ret) {
					this.game.nextPlayerGUI();
				}
			}

			else if (square.isFence()) {
				ret = this.playFence(square, boardGUI);

				if (ret) {
					this.game.nextPlayerGUI();
				}
			}
    }

		return ret;
	}

	public boolean playPawn(Square square) {
		super.movePawn(square.getX(), square.getY());
		return true;
	}

	public boolean playFence(Square square, BoardGUI boardGUI) {
		boolean ret = false;

		if (this.getNbRestingFences() > 0) {
			int x = square.getX();
			int y = square.getY();

			try {

				if (this.checkFencePossible(square, "h")) {
					this.board.setFence(square.getX(), square.getY(), "h", this);
		      this.setNbFences(this.nbFences - 1);
					ret = true;
				}

				else if (this.checkFencePossible(square, "v")) {
					this.board.setFence(square.getX(), square.getY(), "v", this);
		      this.setNbFences(this.nbFences - 1);
					ret = true;
				}

			} catch (Exception ex) {}
		}
		
		return ret;
	}

	public Square randomSquare() {
		ArrayList<Square> lopPawn = this.board.listOfPossibilitiesPawn(this);
		Object[][] lopFence = this.board.listOfPossibilitiesFence(this);
		Square s = null;

		boolean val = new Random().nextInt(3) == 0;

		if (getNbRestingFences() > 0) {
			if (val) {
				s = (Square)lopFence[new Random().nextInt(lopFence.length)][0];
				while (s == null) {
					s = (Square)lopFence[new Random().nextInt(lopFence.length)][0];
				}
			} else s = lopPawn.get(new Random().nextInt(lopPawn.size()));
		} else s = lopPawn.get(new Random().nextInt(lopPawn.size()));


		return s;
	}
}
