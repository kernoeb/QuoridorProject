package quoridor.controller;

import quoridor.model.AutoPlayer;
import quoridor.model.Player;
import quoridor.model.Square;
import quoridor.view.BoardGUI;
import quoridor.view.QuoridorGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class representing a square listener.
 * Listen and make actions chen the user click on a square of the board of the graphical interface.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class SquareEcouteur implements ActionListener {

    /** The board of the graphical interface.
    */
    private final BoardGUI boardGUI;

    /** The actual player.
    */
    private Player actualPlayer;

    /**
     * Constructor of a square listener.
     * @param boardGUI The board of the graphical interface.
     */
    public SquareEcouteur(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
        this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
    }

    /**
     * Method launched each time the user click on a square of the board of the graphical interface.
     * @param e The event raised which allow notably to determine which button has been clicked.
     */
    public void actionPerformed(ActionEvent e) {
        Square square = this.boardGUI.getSquare((JButton) e.getSource());

        if (this.actualPlayer.play(square)) {
            this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
            this.boardGUI.displayBoardGUI();
            this.boardGUI.getGameGUI().updateFences();
            this.boardGUI.getGameGUI().updateCurrentPlayer();
            this.boardGUI.getGameGUI().revalidate();
            this.boardGUI.getGameGUI().repaint();
            this.boardGUI.addTmpPossibilities(this.boardGUI.getBoard().listOfPossibilitiesPawn(this.actualPlayer), this.actualPlayer);

            if (this.actualPlayer instanceof AutoPlayer) {
                square = this.actualPlayer.randomSquare();
                this.actualPlayer.play(square);
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

    /**
     * Launch the endMenu when one of the two players has won the game.
     */
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
