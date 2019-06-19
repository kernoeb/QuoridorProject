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
    // System.out.println(this.game.getBoard());
    this.setLayout(new GridLayout(0,3));
    this.add(new JLabel("eiuhopihf"));
    this.add(this.game.getBoardGUI(), BorderLayout.CENTER);
    this.add(new JLabel("lzeuhefliuh"));
    // this.game.getBoardGUI().displayBoardGUI(this.game.getBoard());
  }
}
