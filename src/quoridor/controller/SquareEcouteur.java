package quoridor.controller;

import quoridor.model.AutoPlayer;
import quoridor.model.Player;
import quoridor.model.Square;
import quoridor.view.BoardGUI;
import quoridor.view.QuoridorGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareEcouteur implements ActionListener {
    private final BoardGUI boardGUI;
    private Player actualPlayer;

    public SquareEcouteur(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
        this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
    }

    public void actionPerformed(ActionEvent e) {
        Square square = this.boardGUI.getSquare((JButton) e.getSource());

        if (this.actualPlayer.play(square, this.boardGUI)) {
            this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
            this.boardGUI.displayBoardGUI();
            this.boardGUI.getGameGUI().updateFences();
            this.boardGUI.getGameGUI().updateCurrentPlayer();
            this.boardGUI.getGameGUI().revalidate();
            this.boardGUI.getGameGUI().repaint();
            this.boardGUI.addTmpPossibilities(this.boardGUI.getBoard().listOfPossibilitiesPawn(this.actualPlayer), this.actualPlayer);

            if (this.actualPlayer instanceof AutoPlayer) {
                square = this.actualPlayer.randomSquare();
                this.actualPlayer.play(square, this.boardGUI);
                this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
                this.boardGUI.displayBoardGUI();
                this.boardGUI.getGameGUI().updateFences();
                this.boardGUI.getGameGUI().updateCurrentPlayer();
                this.boardGUI.getGameGUI().revalidate();
                this.boardGUI.getGameGUI().repaint();
                this.boardGUI.addTmpPossibilities(this.boardGUI.getBoard().listOfPossibilitiesPawn(this.actualPlayer), this.actualPlayer);
            }

            if ((this.boardGUI.getGame().checkHasFinished(this.boardGUI.getGame().getPlayer1())) || (this.boardGUI.getGame().checkHasFinished(this.boardGUI.getGame().getPlayer2()))) {
                this.endOfGame();
            }
        }
    }

    private void endOfGame() {
        QuoridorGUI quoridorGUI = this.boardGUI.getGameGUI().getQuoridorGUI();

        quoridorGUI.setBackgroundImage("../data/images/MenuBackground2.png");
        quoridorGUI.remove(quoridorGUI.getGameGUI());
        quoridorGUI.getEndText().setText("<html><head><style type='text/css'>body { font-family: Palatino Linotype; } </style></head><div align='center'>Félicitations ! <br> Le joueur " + this.actualPlayer.getName() + " a gagné la partie !</div></html>");
        quoridorGUI.add(quoridorGUI.getMenuEnd());

        quoridorGUI.setFocusableWindowState(true);
        quoridorGUI.getMenuEnd().setOpaque(false);
        quoridorGUI.revalidate();
        quoridorGUI.repaint();
    }
}
