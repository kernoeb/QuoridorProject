package quoridor.model;

import quoridor.view.BoardGUI;

import java.io.Serializable;

public class HumanPlayer extends Player implements Serializable {

    private static final long serialVersionUID = 52698554L;

    /**
     * HumanPlayer constructor
     *
     * @param name
     * @author
     */
    public HumanPlayer(Game game, String name, Board board, int initX, int initY, boolean terminal) {
        super(game, name, board, initX, initY, terminal);
    }

    /**
     * Let the player choose between if he wants to play a fence or moves its pawn
     *
     * @author
     */
    public void play() {
    }

    public boolean play(Square square, BoardGUI boardGUI) {
        boolean ret = false;

        if (!this.terminal) {
            if (square.isPawn()) {
                ret = this.playPawn(square);
                if (ret) {
                    this.game.setActualPlayer();
                }
            } else if (square.isFence()) {
                ret = this.playFence(square, boardGUI);
                if (ret) {
                    this.game.setActualPlayer();
                }
            }
        }
        return ret;
    }


    private boolean playPawn(Square square) {
        boolean ret = false;

        if (this.board.listOfPossibilitiesPawn(this).contains(square)) {
            super.movePawn(square.getX(), square.getY());
            ret = true;
        }

        return ret;
    }

    private boolean playFence(Square square, BoardGUI boardGUI) {
        boolean ret = false;

        if (this.getNbRestingFences() > 0) {
            int x = square.getX();
            int y = square.getY();
            Square squareFence;
            // Barrière horizontale
            if (x % 2 != 0 && y % 2 == 0) {
                try {
                    if (y != boardGUI.getSquares().length - 1) squareFence = this.board.getGrid()[x][y + 1];
                    else squareFence = this.board.getGrid()[x][y - 1];

                    if (this.checkFencePossible(squareFence, "h")) {
                        this.board.setFence(squareFence.getX(), squareFence.getY(), "h", this);
                        this.setNbFences(this.nbFences - 1);
                        ret = true;
                    }

                } catch (Exception ignored) {
                }
            }
            // Barrière verticale
            else if (x % 2 == 0 && y % 2 != 0) {
                try {
                    if (y != boardGUI.getSquares().length - 1) squareFence = this.board.getGrid()[x + 1][y];
                    else squareFence = this.board.getGrid()[x - 1][y];

                    if (this.checkFencePossible(squareFence, "v")) {
                        this.board.setFence(squareFence.getX(), squareFence.getY(), "v", this);
                        this.setNbFences(this.nbFences - 1);
                        ret = true;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return ret;
    }

    public Square randomSquare() {
        return null;
    }
}
