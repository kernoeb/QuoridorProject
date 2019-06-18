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
      this.quoridor.getMenuMain().setVisible(false);
      this.quoridor.add(this.quoridor.getMenuMode());
      this.quoridor.getMenuMode().setVisible(true);
    }

    else if (button == this.quoridor.getButtonLoad()) {

    }

    else if (button == this.quoridor.getButtonQuit()) {
      System.exit(0);
    }

    else if (button == this.quoridor.getButtonModeHH()) {
      this.quoridor.getMenuMode().setVisible(false);
      this.quoridor.addGameGUI(new GameGUI(new Game(Mode.HH, "Red user", "Green user")));
      this.quoridor.getGameGUI().setVisible(true);
    }

    else if (button == this.quoridor.getButtonModeHA()) {
      this.quoridor.getMenuMode().setVisible(false);
      this.quoridor.addGameGUI(new GameGUI(new Game(Mode.HA, "Red user", "Green user")));
      this.quoridor.getGameGUI().setVisible(true);
    }
  }

}
