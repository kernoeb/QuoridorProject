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
    // this.setLayout(new GridLayout(4, 0));
    this.add(new JLabel("Joueur actuel : " + this.game.getActualPlayer().getName()), BorderLayout.NORTH);
    // BorderLayout bl = new BorderLayout();
    JPanel jP = new JPanel();
    jP.setLayout(new BorderLayout());
    add(jP, BorderLayout.CENTER);
    jP.add(new JLabel("5 barrières"), BorderLayout.NORTH);
    jP.add(bg, BorderLayout.CENTER);
    jP.add(new JLabel("5 barrières"), BorderLayout.SOUTH);
    bg.displayBoardGUI(this.game.getBoard());
  }
}
