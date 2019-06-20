package controller;

import javax.swing.*;
import java.awt.event.*;
import view.*;

public class SquareEcouteur implements ActionListener {
  BoardGUI boardGUI;

  public SquareEcouteur(BoardGUI boardGUI) {
    this.boardGUI = boardGUI;
  }

  public void actionPerformed(ActionEvent e) {
    JButton square = (JButton) e.getSource();
    System.out.println("Le joueur a cliqué en : " + this.boardGUI.getX(square)/2 + ", " + this.boardGUI.getY(square)/2);
  }
}
