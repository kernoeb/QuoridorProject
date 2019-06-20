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


		if(this.actualPlayer.getNbRestingFences() > 0) {
			if (square.isPawn()) {
				this.actualPlayer.playPawn(square);
			}

			else if (square.isFence()) {
				this.actualPlayer.playFence(square);
				// TODO - Réactualiser le nombre de barrières en ayant accès à GameGUI
			}
		}
		else {
			this.actualPlayer.playPawn(square);
		}
    
    // System.out.println("Le joueur a cliqué en : " + this.boardGUI.getX(square)/2 + ", " + this.boardGUI.getY(square)/2);
  }
}
