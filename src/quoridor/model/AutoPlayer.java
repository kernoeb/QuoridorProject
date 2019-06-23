package quoridor.model;

import quoridor.view.BoardGUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class AutoPlayer extends Player implements Serializable {

    private static final long serialVersionUID = 5452854L;

    /**
     * AutoPlayer constructor
     * @param game
     * @param name
     * @param board
     * @param initX
     * @param initY
     * @param terminal
     */
    public AutoPlayer(Game game, String name, Board board, int initX, int initY, boolean terminal) {
        super(game, name, board, initX, initY, terminal);
    }

    /**
     * AI for the AutoPlayer
     * Choose between playFence and playPawn
     * Only one level of difficulty for now
     *
     */
    public void play() {
        ArrayList<Square> al = this.board.listOfPossibilitiesPawn(this);
        Object[][] lopFence = this.board.listOfPossibilitiesFence(this);

        int currentX = this.currentSquare.getX();
        int currentY = this.currentSquare.getY();
        // System.out.println("Vals : " + currentX + " " + currentY);
        //this.board.printListOfPossibilitiesPawn(this);

        boolean val = new Random().nextInt(3) == 0;

        if (getNbRestingFences() > 0) {
            if (!val) {

                if (al.contains(this.board.getGrid()[(currentX + 2)][currentY])) {
                    super.movePawn((currentX + 2), currentY);
                } else {
                    Square s = al.get(new Random().nextInt(al.size()));
                    super.movePawn(s.getX(), s.getY());
                }

            } else {


                Square s = (Square) lopFence[new Random().nextInt(lopFence.length)][0];
                while (s == null) {
                    s = (Square) lopFence[new Random().nextInt(lopFence.length)][0];
                }
                //this.board.setFence(s.getX(), s.getY(), "h", this);
                try {
                    if (this.checkFencePossible(s, "h")) {
                        this.board.setFence(s.getX(), s.getY(), "h", this);
                        this.setNbFences(this.nbFences - 1);
                    } else if (this.checkFencePossible(s, "v")) {
                        this.board.setFence(s.getX(), s.getY(), "v", this);
                        this.setNbFences(this.nbFences - 1);
                    }
                } catch (Exception ignored) {
                }
            }
        } else {
            Square s = al.get(new Random().nextInt(al.size()));
            super.movePawn(s.getX(), s.getY());
        }

    }

    public boolean play(Square square, BoardGUI boardGUI) {
        boolean ret = false;

        if (!this.terminal) {
            if (square.isPawn()) {
                ret = this.playPawn(square);

                if (ret) {
                    // System.out.println("AutoPlayer - play() isPawn -> nextPlayerGUI");
                    this.game.setActualPlayer();
                }
            } else if (square.isFence()) {
                ret = this.playFence(square);

                if (ret) {
                    // System.out.println("AutoPlayer - play() isFence -> nextPlayerGUI");
                    this.game.setActualPlayer();
                }
            }
        }

        return ret;
    }

    private boolean playPawn(Square square) {
        super.movePawn(square.getX(), square.getY());
        return true;
    }

    private boolean playFence(Square square) {
        boolean ret = false;

        if (this.getNbRestingFences() > 0) {

            try {

                if (this.checkFencePossible(square, "h")) {
                    this.board.setFence(square.getX(), square.getY(), "h", this);
                    this.setNbFences(this.nbFences - 1);
                    ret = true;
                } else if (this.checkFencePossible(square, "v")) {
                    this.board.setFence(square.getX(), square.getY(), "v", this);
                    this.setNbFences(this.nbFences - 1);
                    ret = true;
                }

            } catch (Exception ignored) {
            }
        }

        return ret;
    }

    public Square randomSquare() {
        ArrayList<Square> lopPawn = this.board.listOfPossibilitiesPawn(this);
        Object[][] lopFence = this.board.listOfPossibilitiesFence(this);
        Square s;

        boolean val = new Random().nextInt(3) == 0;

        if (getNbRestingFences() > 0) {
            if (val) {
                s = (Square) lopFence[new Random().nextInt(lopFence.length)][0];
                while (s == null) {
                    s = (Square) lopFence[new Random().nextInt(lopFence.length)][0];
                }
            } else s = lopPawn.get(new Random().nextInt(lopPawn.size()));
        } else s = lopPawn.get(new Random().nextInt(lopPawn.size()));


        return s;
    }
}
