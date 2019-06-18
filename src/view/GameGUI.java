package view;

import javax.swing.*;
import java.awt.*;

import quoridor.*;

public class GameGUI extends JPanel {
  Game game;

  JLabel label;

  public GameGUI(Game game) {
    this.game = game;
    System.out.println("gamegui");
    this.createAndShowGUI();
  }

  private void createAndShowGUI() {
    label = new JLabel("Game");
  }
}
