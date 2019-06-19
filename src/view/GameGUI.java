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

    this.add(new JLabel("Joueur actuel : " + this.game.getActualPlayer().getName()), BorderLayout.NORTH);
    JPanel jP = new JPanel();
    jP.setLayout(new BorderLayout(0,10));
    add(jP, BorderLayout.CENTER);
    jP.setBackground(new Color(195, 195, 148));

    ImageIcon fenceBlue = new ImageIcon((new ImageIcon("../data/icons/blueFence.png")).getImage().getScaledInstance(5, 50,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon fenceOrange = new ImageIcon((new ImageIcon("../data/icons/orangeFence.png")).getImage().getScaledInstance(5, 50,  java.awt.Image.SCALE_SMOOTH));

    JPanel nbBar1 = new JPanel();
    nbBar1.setLayout(new GridLayout(0,10));
    nbBar1.setBackground(new Color(195, 195, 148));

    for (int i = 0; i < this.game.getActualPlayer().getNbRestingFences(); i++) {
    	nbBar1.add(new JLabel(fenceBlue));
    }

    JPanel nbBar2 = new JPanel();
    nbBar2.setLayout(new GridLayout(0,10));
    nbBar2.setBackground(new Color(195, 195, 148));

    for (int i = 0; i < this.game.getOtherPlayer().getNbRestingFences(); i++) {
    	nbBar2.add(new JLabel(fenceOrange));
    }


    jP.add(nbBar1, BorderLayout.NORTH);
    jP.add(bg, BorderLayout.CENTER);
    jP.add(nbBar2, BorderLayout.SOUTH);
    bg.displayBoardGUI(this.game.getBoard());
  }
}
