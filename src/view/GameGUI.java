package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

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
  	Border redLine = BorderFactory.createLineBorder(new Color(149, 26, 0), 15);
  	bg.setBorder(redLine);

    this.setLayout(new BorderLayout(0,5));

    ImageIcon fenceBlue = new ImageIcon((new ImageIcon("../data/icons/blueFence.png")).getImage().getScaledInstance(8, 65,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon pawnBlue = new ImageIcon((new ImageIcon("../data/icons/bluePlayer.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon fenceOrange = new ImageIcon((new ImageIcon("../data/icons/orangeFence.png")).getImage().getScaledInstance(8, 65,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon pawnOrange = new ImageIcon((new ImageIcon("../data/icons/orangePlayer.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    JPanel top = new JPanel();
    top.setLayout(new GridLayout(1,0,50,0));
    top.add(new JLabel("Temps : 00:45"));

    JPanel currentPlayer = new JPanel();
    currentPlayer.setLayout(new GridLayout(1,0,0,0));
    currentPlayer.add(new JLabel("Joueur actuel :"));
    if (this.game.getActualPlayer() == this.game.getPlayer1()) currentPlayer.add(new JLabel(pawnBlue));
    else currentPlayer.add(new JLabel(pawnOrange));
    currentPlayer.setBackground(new Color(195, 195, 148));

    top.add(currentPlayer);
    top.setBackground(new Color(195, 195, 148));
    

    this.add(top, BorderLayout.NORTH);

    JPanel jP = new JPanel();
    jP.setLayout(new BorderLayout(0,10));
    add(jP, BorderLayout.CENTER);
    jP.setBackground(new Color(195, 195, 148));


    JPanel nbBar1 = new JPanel();
    nbBar1.setLayout(new GridLayout(0,10));
    nbBar1.setBackground(new Color(195, 195, 148));

    for (int i = 0; i < this.game.getActualPlayer().getNbRestingFences(); i++) {
    	nbBar1.add(new JLabel(fenceOrange));
    }

    JPanel nbBar2 = new JPanel();
    nbBar2.setLayout(new GridLayout(0,10));
    nbBar2.setBackground(new Color(195, 195, 148));

    for (int i = 0; i < this.game.getOtherPlayer().getNbRestingFences(); i++) {
    	nbBar2.add(new JLabel(fenceBlue));
    }


    jP.add(nbBar1, BorderLayout.NORTH);
    jP.add(bg, BorderLayout.CENTER);
    jP.add(nbBar2, BorderLayout.SOUTH);

    bg.displayBoardGUI(this.game.getBoard());
    bg.addTmpPossibilities(this.game.getBoard().listOfPossibilitiesPawn(this.game.getActualPlayer()));

  }
}
