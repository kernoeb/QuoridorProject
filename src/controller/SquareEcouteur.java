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

  		// System.out.println(this.boardGUI.getGame().getActualPlayer().getNbRestingFences());

		// if (this.actualPlayer.getNbRestingFences() > 0) {
		if (this.boardGUI.getGame().getActualPlayer().getNbRestingFences() > 0) {
			if (square.isPawn()) {
				// this.actualPlayer.playPawn(square);
				this.boardGUI.getGame().getActualPlayer().playPawn(square);
				this.boardGUI.displayBoardGUI();
			}

			else if (square.isFence()) {
				// this.actualPlayer.playFence(square);
				this.boardGUI.getGame().getActualPlayer().playFence(square);
				// TODO - Réactualiser le nombre de barrières en ayant accès à GameGUI
			}
		}
		else {
			// this.actualPlayer.playPawn(square);
			this.boardGUI.getGame().getActualPlayer().playPawn(square);
		}
    
    // System.out.println("Le joueur a cliqué en : " + this.boardGUI.getX(square)/2 + ", " + this.boardGUI.getY(square)/2);
  }
}
