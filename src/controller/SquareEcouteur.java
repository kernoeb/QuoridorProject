package controller;

import javax.swing.*;
import java.awt.event.*;
import view.*;
import quoridor.*;

public class SquareEcouteur implements ActionListener {
  BoardGUI boardGUI;
  Player actualPlayer;

  public SquareEcouteur(BoardGUI boardGUI) {
    this.boardGUI = boardGUI;
    this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
  }

  public void actionPerformed(ActionEvent e) {
  	Square square = this.boardGUI.getSquare((JButton) e.getSource());

		// if (square.isPawn()) {
		// 	if (this.boardGUI.getBoard().listOfPossibilitiesPawn(this.actualPlayer).contains(square)) {
    //     this.boardGUI.getGame().getActualPlayer().playPawn(square);
    //     this.boardGUI.getGame().nextPlayerGUI();
		// 	}
    //
		// }
    //
		// else if ((square.isFence()) && (this.actualPlayer.getNbRestingFences() > 0)) {
		// 	this.actualPlayer.playFence(square);
    //   this.boardGUI.getGame().nextPlayerGUI();
		// 	// TODO - Réactualiser le nombre de barrières en ayant accès à GameGUI
		// }

    this.actualPlayer.play(square, this.boardGUI);

    this.boardGUI.addTmpPossibilities(this.boardGUI.getBoard().listOfPossibilitiesPawn(this.actualPlayer));
    this.boardGUI.displayBoardGUI();

    if((this.boardGUI.getGame().checkHasFinished(this.boardGUI.getGame().getPlayer1())) || (this.boardGUI.getGame().checkHasFinished(this.boardGUI.getGame().getPlayer2()))) {
      this.endOfGame();
    }
    // System.out.println("Le joueur a cliqué en : " + this.boardGUI.getX(square)/2 + ", " + this.boardGUI.getY(square)/2);
  }

  private void endOfGame() {

  }
}
