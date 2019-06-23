package quoridor.controller;

import javax.swing.*;
import java.awt.event.*;
import quoridor.view.*;
import quoridor.model.*;

public class SquareEcouteur implements ActionListener {
  BoardGUI boardGUI;
  Player actualPlayer;

  public SquareEcouteur(BoardGUI boardGUI) {
    this.boardGUI = boardGUI;
    this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
    System.out.println("SquareEcouteur : " + this.actualPlayer.getName());
  }

  public void actionPerformed(ActionEvent e) {
    System.out.println("actionPerformed() : " + this.actualPlayer.getName());
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

    if (this.actualPlayer.play(square, this.boardGUI)) {
      // this.boardGUI.getGame().nextPlayerGUI();
      this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
      this.boardGUI.setFencesEnabled(square);
      this.boardGUI.displayBoardGUI();
      this.boardGUI.addTmpPossibilities(this.boardGUI.getBoard().listOfPossibilitiesPawn(this.actualPlayer), this.actualPlayer);

      if (this.actualPlayer instanceof AutoPlayer) {
        square = this.actualPlayer.randomSquare();
        this.actualPlayer.play(square, this.boardGUI);
        this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
        this.boardGUI.setFencesEnabled(square);
        this.boardGUI.displayBoardGUI();
        this.boardGUI.addTmpPossibilities(this.boardGUI.getBoard().listOfPossibilitiesPawn(this.actualPlayer), this.actualPlayer);
      }

      if((this.boardGUI.getGame().checkHasFinished(this.boardGUI.getGame().getPlayer1())) || (this.boardGUI.getGame().checkHasFinished(this.boardGUI.getGame().getPlayer2()))) {
        this.endOfGame();
      }
    }

    // System.out.println("Le joueur a cliqué en : " + this.boardGUI.getX(square)/2 + ", " + this.boardGUI.getY(square)/2);
  }

  private void endOfGame() {
    QuoridorGUI quoridorGUI = this.boardGUI.getGameGUI().getQuoridorGUI();

    quoridorGUI.setBackgroundImage("../data/images/MenuBackground2.png");
    quoridorGUI.remove(quoridorGUI.getGameGUI());
    quoridorGUI.getEndText().setText("<html><head><style type='text/css'>body { font-family: Palatino Linotype; } </style></head><div align='center'>Félicitations ! <br> Le joueur " + this.actualPlayer.getName() + " a gagné la partie !</div></html>");
    quoridorGUI.add(quoridorGUI.getMenuEnd());

    quoridorGUI.setFocusableWindowState(true);
    quoridorGUI.getMenuEnd().setOpaque(false);
    quoridorGUI.revalidate();
    quoridorGUI.repaint();
  }
}
