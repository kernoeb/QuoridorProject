package quoridor.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import quoridor.model.*;
import quoridor.view.textured.JTexturedButton;
import quoridor.controller.ActionEcouteur;

public class GameGUI extends JPanel {
  QuoridorGUI quoridorGUI;
  Game game;

  BoardGUI boardGUI;
  Border redLine;

  JTexturedButton pauseButton;

  JLabel time;
  JLabel currentPlayerText;
  JLabel currentPlayerColor;

  JPanel board;
  JPanel nbBar1;
  JPanel nbBar2;
  JPanel top;
  JPanel currentPlayer;

  ImageIcon fenceBlue;
  ImageIcon pawnBlue;
  ImageIcon fenceOrange;
  ImageIcon pawnOrange;

  public GameGUI(Game game, QuoridorGUI quoridorGUI) {
    this.game = game;
    this.quoridorGUI = quoridorGUI;
    this.createAndShowGUI();
  }

  public Game getGame() {
    return this.game;
  }

  public JTexturedButton getButtonPause() {
    return this.pauseButton;
  }

  private void createAndShowGUI() {
  	this.boardGUI = this.game.getBoardGUI();
  	this.redLine = BorderFactory.createLineBorder(new Color(149, 26, 0), 15);
  	this.boardGUI.setBorder(redLine);

    this.setLayout(new BorderLayout(0,5));

    this.fenceBlue = new ImageIcon((new ImageIcon("../data/icons/blueFence.png")).getImage().getScaledInstance(8, 65,  java.awt.Image.SCALE_SMOOTH));
    this.pawnBlue = new ImageIcon((new ImageIcon("../data/icons/bluePlayer.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    this.fenceOrange = new ImageIcon((new ImageIcon("../data/icons/orangeFence.png")).getImage().getScaledInstance(8, 65,  java.awt.Image.SCALE_SMOOTH));
    this.pawnOrange = new ImageIcon((new ImageIcon("../data/icons/orangePlayer.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    this.top = new JPanel();
    this.top.setLayout(new GridLayout(1,3,-50,0));

    this.pauseButton = new JTexturedButton("../data/images/ButtonPause.png", "../data/images/ButtonPause.png");
    this.top.add(this.pauseButton);
    this.pauseButton.addActionListener(new ActionEcouteur(this.quoridorGUI));

    this.time = new JLabel("Temps : 00:45");
    this.quoridorGUI.setFontPalatino(this.time, 25);
    this.top.add(this.time);

    this.currentPlayer = new JPanel();
    this.currentPlayer.setLayout(new GridLayout(1,0,-150,0));
    this.currentPlayerText = new JLabel("Joueur actuel :");
    this.quoridorGUI.setFontPalatino(this.currentPlayerText, 25);
    this.currentPlayer.add(this.currentPlayerText);
    this.currentPlayerColor = new JLabel();
    this.currentPlayer.setBackground(new Color(195, 195, 148));

    this.updateCurrentPlayer();

    this.top.add(this.currentPlayer);
    this.top.setBackground(new Color(195, 195, 148));

    this.add(this.top, BorderLayout.NORTH);

    this.board = new JPanel();
    this.board.setLayout(new BorderLayout(0,10));
    this.add(this.board, BorderLayout.CENTER);
    this.board.setBackground(new Color(195, 195, 148));
    this.board.setBorder(new EmptyBorder(50, 200, 0, 200));

    this.nbBar1 = new JPanel();
    this.nbBar1.setLayout(new GridLayout(0,10));
    this.nbBar1.setBackground(new Color(195, 195, 148));


    this.nbBar2 = new JPanel();
    this.nbBar2.setLayout(new GridLayout(0,10));
    this.nbBar2.setBackground(new Color(195, 195, 148));

    this.updateFences();


    this.board.add(this.nbBar1, BorderLayout.NORTH);
    this.board.add(this.boardGUI, BorderLayout.CENTER);
    this.board.add(this.nbBar2, BorderLayout.SOUTH);

    this.boardGUI.displayBoardGUI();
    this.boardGUI.addTmpPossibilities(this.game.getBoard().listOfPossibilitiesPawn(this.game.getActualPlayer()), this.game.getActualPlayer());
  }

  public void updateCurrentPlayer() {
    this.currentPlayer.remove(this.currentPlayerColor);
    if (this.game.getActualPlayer() == this.game.getPlayer1()) this.currentPlayerColor = new JLabel(this.pawnBlue);
    else this.currentPlayerColor = new JLabel(this.pawnOrange);

    this.currentPlayer.add(this.currentPlayerColor);
  }

  public void updateFences() {
    for (int i = 0; i < this.game.getPlayer2().getNbRestingFences(); i++) {
      this.nbBar1.add(new JLabel(this.fenceOrange));
    }

    for (int i = 0; i < this.game.getPlayer1().getNbRestingFences(); i++) {
    	this.nbBar2.add(new JLabel(this.fenceBlue));
    }
  }
}
