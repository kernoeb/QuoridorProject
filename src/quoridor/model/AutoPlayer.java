package quoridor.model;

/** This will allow us to use elements of the class Serializable.
*/
import java.io.Serializable;

/** This will allow us to create an ArrayList.
*/
import java.util.ArrayList;

/** This will allow us to use elements of the classes Random.
*/
import java.util.Random;

/**
* Class representing an automatic player.
* @author Noéwen Boisnard, Sébastien Gavignet
*/
public class AutoPlayer extends Player implements Serializable {

	  /** This will allow us to serialize the object.
	  */
    private static final long serialVersionUID = 5452854L;

    /**
     * Constructor of an automatic player.
     * @param game The game he belongs to.
     * @param name The name of the automatic player.
     * @param board The board he belongs to.
     * @param initX The initial position of the automatic player on the board's X axis.
     * @param initY The initial position of the automatic player on the board's Y axis.
     * @param terminal Tells if the automatic player will play on a terminal or not.
     */
    public AutoPlayer(Game game, String name, Board board, int initX, int initY, boolean terminal) {
        super(game, name, board, initX, initY, terminal);
    }

    /**
     * Make the automatic player play by choosing randomly if it will place a fence or
	   * move its pawn.
	   * Only the first difficulty has been implemented.
     */
    public void play() {
        ArrayList<Square> al = this.board.listOfPossibilitiesPawn(this);
        Object[][] lopFence = this.board.listOfPossibilitiesFence(this);

        int currentX = this.currentSquare.getX();
        int currentY = this.currentSquare.getY();

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

    /**
     * Method which make the automatic player in the graphical interface.
   	 * As well as the one for the terminal, it selects randomly a square
   	 * and places a fence in it or move the pawn in it according to the status
   	 * of the square.
   	 * However, this method only calls the right method according if the square
   	 * choosed is a fence or a pawn.
     * @param  square   The square choosed in which the automatic player will play.
     * @return          True if something has been modified, false otherwise.
     */
    public boolean play(Square square) {
        boolean ret = false;

        if (!this.terminal) {
            if (square.isPawn()) {
                ret = this.playPawn(square);

                if (ret) {
                    this.game.setActualPlayer();
                }
            } else if (square.isFence()) {
                ret = this.playFence(square);

                if (ret) {
                    this.game.setActualPlayer();
                }
            }
        }

        return ret;
    }

    /**
     * Move the pawn according to the square given in parameters
     * @param  square The square in which the automatic player will move.
     * @return        True if something has been modified.
     */
    private boolean playPawn(Square square) {
        super.movePawn(square.getX(), square.getY());
        return true;
    }

    /**
     * Place a fence according to the square given in parameters.
     * @param  square The square in which the automatic player will place a fence.
     * @return        True if something has been modified.
     */
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

    /**
     * Selects randomly a square in the list of squares where it could play (fence or pawn)
     * and this square will be used by the automatic player to play in.
     * Corresponds to the first difficulty (Easy) of the automatic player's strategy.
     * @return The square which has been selected.
     */
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
