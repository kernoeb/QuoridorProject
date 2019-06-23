package quoridor.view;

import quoridor.controller.ActionEcouteur;
import quoridor.model.Game;
import quoridor.view.textured.JTexturedButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameGUI extends JPanel {
    private final QuoridorGUI quoridorGUI;
    private final Game game;

    private final BoardGUI boardGUI;

    private JTexturedButton pauseButton;

    private JLabel currentPlayerColor;

    private JPanel nbBar1;
    private JPanel nbBar2;
    private JPanel currentPlayer;

    private ImageIcon fenceBlue;
    private ImageIcon pawnBlue;
    private ImageIcon fenceOrange;
    private ImageIcon pawnOrange;

    private final Color colorRed = new Color(149, 26, 0);
    private final Color colorBackground = new Color(195, 195, 148);

    public GameGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        this.game = this.quoridorGUI.getQuoridor().getGame();
        this.boardGUI = new BoardGUI(this, this.game, this.game.getBoard());
        this.createAndShowGUI();
    }

    public Game getGame() {
        return this.game;
    }

    public QuoridorGUI getQuoridorGUI() {
        return this.quoridorGUI;
    }

    public BoardGUI getBoardGUI() {
        return this.boardGUI;
    }

    public JTexturedButton getButtonPause() {
        return this.pauseButton;
    }

    private void createAndShowGUI() {
        Border redLine = BorderFactory.createLineBorder(this.colorRed, 15);
        this.boardGUI.setBorder(redLine);

        this.setLayout(new BorderLayout(0, 5));

        this.fenceBlue = new ImageIcon((new ImageIcon("../data/icons/blueFence.png")).getImage().getScaledInstance(8, 65, java.awt.Image.SCALE_SMOOTH));
        this.pawnBlue = new ImageIcon((new ImageIcon("../data/icons/bluePlayer.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        this.fenceOrange = new ImageIcon((new ImageIcon("../data/icons/orangeFence.png")).getImage().getScaledInstance(8, 65, java.awt.Image.SCALE_SMOOTH));
        this.pawnOrange = new ImageIcon((new ImageIcon("../data/icons/orangePlayer.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 3, -50, 0));

        this.pauseButton = new JTexturedButton("../data/images/ButtonPause.png", "../data/images/ButtonPause.png");
        top.add(this.pauseButton);
        this.pauseButton.addActionListener(new ActionEcouteur(this.quoridorGUI));

        JLabel time = new JLabel("Temps : 00:45");
        this.quoridorGUI.setFontPalatino(time, 25);
        top.add(time);

        this.currentPlayer = new JPanel();
        this.currentPlayer.setLayout(new GridLayout(1, 0, -150, 0));
        JLabel currentPlayerText = new JLabel("Joueur actuel :");
        this.quoridorGUI.setFontPalatino(currentPlayerText, 25);
        this.currentPlayer.add(currentPlayerText);
        this.currentPlayerColor = new JLabel();
        this.currentPlayer.setBackground(this.colorBackground);

        this.updateCurrentPlayer();

        top.add(this.currentPlayer);
        top.setBackground(this.colorBackground);

        this.add(top, BorderLayout.NORTH);

        JPanel board = new JPanel();
        board.setLayout(new BorderLayout(0, 10));
        this.add(board, BorderLayout.CENTER);
        board.setBackground(this.colorBackground);
        board.setBorder(new EmptyBorder(50, 200, 0, 200));

        this.nbBar1 = new JPanel();
        this.nbBar1.setLayout(new GridLayout(0, 10));
        this.nbBar1.setBackground(this.colorBackground);


        this.nbBar2 = new JPanel();
        this.nbBar2.setLayout(new GridLayout(0, 10));
        this.nbBar2.setBackground(this.colorBackground);

        this.updateFences();


        board.add(this.nbBar1, BorderLayout.NORTH);
        board.add(this.boardGUI, BorderLayout.CENTER);
        board.add(this.nbBar2, BorderLayout.SOUTH);

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
        this.nbBar1.removeAll();
        this.nbBar2.removeAll();

        for (int i = 0; i < this.game.getPlayer2().getNbRestingFences(); i++) {
            this.nbBar1.add(new JLabel(this.fenceOrange));
        }

        for (int i = 0; i < this.game.getPlayer1().getNbRestingFences(); i++) {
            this.nbBar2.add(new JLabel(this.fenceBlue));
        }
    }
}
