package view;

import javax.swing.*;
import java.awt.*;

import quoridor.*;

public class GameGUI extends JPanel {
  Game game;

  JLabel label;

  public GameGUI(Game game) {
    this.game = game;
    this.createAndShowGUI();
  }

  private void createAndShowGUI() {
    label = new JLabel("Game");
  }
}
