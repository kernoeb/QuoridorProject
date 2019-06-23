package quoridor.model;

/** This will allow us to use elements of the class Serializable.
*/
import java.io.Serializable;

/**
 * Class representing a human player.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class HumanPlayer extends Player implements Serializable {

    /** This will allow us to serialize the object.
    */
    private static final long serialVersionUID = 52698554L;

    /**
     * Constructor of a human player.
     * Calls the constructor of the class Player.
     * @param game     The game the player belongs to.
     * @param name     The name of the player.
     * @param board    The board the player belongs to.
     * @param initX    The initial position of the automatic player on the board's X axis.
     * @param initY    The initial position of the automatic player on the board's Y axis.
     * @param terminal Tells if the human player will play on a terminal or not.
     */
    public HumanPlayer(Game game, String name, Board board, int initX, int initY, boolean terminal) {
        super(game, name, board, initX, initY, terminal);
    }

    /**
     * Method created to have the possibility to use the class abstract.
     */
    public void play() {
    }

    /**
     * Call the right method to allow the player to play in the graphical interface
     * in the square given in parameter.
     * @param  square   The square in which the player wants to play.
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
     * Mive the pawn according to the square given in parameters.
     * @param  square The square in which the player wants to move the pawn.
     * @return        True if something has been modified, false otherwise.
     */
    private boolean playPawn(Square square) {
        boolean ret = false;

        if (this.board.listOfPossibilitiesPawn(this).contains(square)) {
            super.movePawn(square.getX(), square.getY());
            ret = true;
        }

        return ret;
    }

    /**
     * Place a fence in the square given in parameter.
     * @param  square   The square in which the player wants to place a fence.
     * @return          True if something has been modified, false otherwise.
     */
    private boolean playFence(Square square) {
        boolean ret = false;

        if (this.getNbRestingFences() > 0) {
            int x = square.getX();
            int y = square.getY();
            Square squareFence;

            // Horizontal fence
            if (x % 2 != 0 && y % 2 == 0) {
                try {
                    if (y != this.board.getTotalSize() - 1) squareFence = this.board.getGrid()[x][y + 1];
                    else squareFence = this.board.getGrid()[x][y - 1];

                    if (this.checkFencePossible(squareFence, "h")) {
                        this.board.setFence(squareFence.getX(), squareFence.getY(), "h", this);
                        this.setNbFences(this.nbFences - 1);
                        ret = true;
                    }

                } catch (Exception ignored) {
                }
            }

            // Vertical fence
            else if (x % 2 == 0 && y % 2 != 0) {
                try {
                    if (y != this.board.getTotalSize() - 1) squareFence = this.board.getGrid()[x + 1][y];
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

    /**
     * Method created to have the possibility to use the abstract class.
     * @return Null
     */
    public Square randomSquare() {
        return null;
    }
}
