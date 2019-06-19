package view;

import javax.swing.*;
import java.awt.*;

import quoridor.*;

public class GameGUI extends JPanel {
  Game game;

  JLabel time;

  public GameGUI(Game game) {
    this.game = game;
    this.createAndShowGUI();
  }

  public Game getGame() {
    return this.game;
  }

  private void createAndShowGUI() {
  	BoardGUI bg = this.game.getBoardGUI();
    this.setLayout(new BorderLayout());
    this.add(bg, BorderLayout.CENTER);
    bg.displayBoardGUI(this.game.getBoard());
  }
}
