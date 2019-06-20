package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

import quoridor.*;

public class GameGUI extends JPanel {
  Game game;

  BoardGUI bg;
  Border redLine;

  JLabel time;

  JPanel jP;
  JPanel nbBar1;
  JPanel nbBar2;
  JPanel top;
  JPanel currentPlayer;

  ImageIcon fenceBlue;
  ImageIcon pawnBlue;
  ImageIcon fenceOrange;
  ImageIcon pawnOrange;

  public GameGUI(Game game) {
    this.game = game;
    this.createAndShowGUI();
  }

  public Game getGame() {
    return this.game;
  }

  private void createAndShowGUI() {
  	this.bg = this.game.getBoardGUI();
  	this.redLine = BorderFactory.createLineBorder(new Color(149, 26, 0), 15);
  	bg.setBorder(redLine);

    this.setLayout(new BorderLayout(0,5));

    this.fenceBlue = new ImageIcon((new ImageIcon("../data/icons/blueFence.png")).getImage().getScaledInstance(8, 65,  java.awt.Image.SCALE_SMOOTH));
    this.pawnBlue = new ImageIcon((new ImageIcon("../data/icons/bluePlayer.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    this.fenceOrange = new ImageIcon((new ImageIcon("../data/icons/orangeFence.png")).getImage().getScaledInstance(8, 65,  java.awt.Image.SCALE_SMOOTH));
    this.pawnOrange = new ImageIcon((new ImageIcon("../data/icons/orangePlayer.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    this.top = new JPanel();
    this.top.setLayout(new GridLayout(1,0,50,0));
    this.top.add(new JLabel("Temps : 00:45"));

    this.currentPlayer = new JPanel();
    this.currentPlayer.setLayout(new GridLayout(1,0,0,0));
    this.currentPlayer.add(new JLabel("Joueur actuel :"));
    if (this.game.getActualPlayer() == this.game.getPlayer1()) this.currentPlayer.add(new JLabel(this.pawnBlue));
    else this.currentPlayer.add(new JLabel(this.pawnOrange));
    this.currentPlayer.setBackground(new Color(195, 195, 148));

    this.top.add(this.currentPlayer);
    this.top.setBackground(new Color(195, 195, 148));


    this.add(this.top, BorderLayout.NORTH);

    this.jP = new JPanel();
    this.jP.setLayout(new BorderLayout(0,10));
    this.add(this.jP, BorderLayout.CENTER);
    this.jP.setBackground(new Color(195, 195, 148));


    this.nbBar1 = new JPanel();
    this.nbBar1.setLayout(new GridLayout(0,10));
    this.nbBar1.setBackground(new Color(195, 195, 148));


    this.nbBar2 = new JPanel();
    this.nbBar2.setLayout(new GridLayout(0,10));
    this.nbBar2.setBackground(new Color(195, 195, 148));

    this.updateFences();


    this.jP.add(this.nbBar1, BorderLayout.NORTH);
    this.jP.add(this.bg, BorderLayout.CENTER);
    this.jP.add(this.nbBar2, BorderLayout.SOUTH);

    this.bg.displayBoardGUI();
    this.bg.addTmpPossibilities(this.game.getBoard().listOfPossibilitiesPawn(this.game.getActualPlayer()));
  }

  public void updateFences() {
    for (int i = 0; i < this.game.getActualPlayer().getNbRestingFences(); i++) {
      this.nbBar1.add(new JLabel(this.fenceOrange));
    }

    for (int i = 0; i < this.game.getOtherPlayer().getNbRestingFences(); i++) {
    	this.nbBar2.add(new JLabel(this.fenceBlue));
    }
  }
}
