package controller;

import javax.swing.*;
import java.awt.event.*;

import quoridor.Game;
import quoridor.Mode;

import view.QuoridorGUI;
import view.GameGUI;

public class ActionEcouteur implements ActionListener {

  QuoridorGUI quoridor;

  public ActionEcouteur(QuoridorGUI quoridor) {
    this.quoridor = quoridor;
  }

  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton) e.getSource();

    if (button == this.quoridor.getButtonPlay()) {
      this.quoridor.setBackgroundImage("../data/images/MenuBackground2.png");
      // this.quoridor.getContentPane().revalidate();
      // this.quoridor.getContentPane().repaint();

      this.quoridor.remove(this.quoridor.getMenuMain());
      this.quoridor.add(this.quoridor.getMenuMode());
      this.quoridor.getMenuMode().setOpaque(false);
      this.quoridor.revalidate();
      this.quoridor.repaint();
    }

    else if (button == this.quoridor.getButtonLoad()) {
      // Ne marche pas car cela enlève l'élément BackgroundImage dans le nouveau layout de la frame
      // En gros c'est comme si l'élément n'était plus accessible ...
      // Essayer d'ajouter tous les éléments dans le JPanel BackgroundImage
      //this.quoridor.getContentPane().remove(this.quoridor.getBackgroundImage());
      this.quoridor.setBackgroundImage("../data/images/MenuBackground2.png");

      this.quoridor.remove(this.quoridor.getMenuMain());
      this.quoridor.add(this.quoridor.getMenuMode());
      this.quoridor.getMenuLoad().setOpaque(false);
      this.quoridor.revalidate();
      this.quoridor.repaint();
    }

    else if (button == this.quoridor.getButtonQuit()) {
      System.exit(0);
    }

    else if (button == this.quoridor.getButtonModeHH()) {
      this.quoridor.getMenuMode().setVisible(false);
      this.quoridor.setGameGUI(new GameGUI(new Game(Mode.HH, "Red user", "Green user", false)));
      this.quoridor.add(this.quoridor.getGameGUI());
      this.quoridor.getGameGUI().setVisible(true);
      this.quoridor.setFocusableWindowState(true);
    }

    else if (button == this.quoridor.getButtonModeHA()) {
      this.quoridor.getMenuMode().setVisible(false);
      this.quoridor.setGameGUI(new GameGUI(new Game(Mode.HA, "Red user", "Green user", false)));
      this.quoridor.add(this.quoridor.getGameGUI());
      this.quoridor.getGameGUI().setVisible(true);
    }
  }

}
