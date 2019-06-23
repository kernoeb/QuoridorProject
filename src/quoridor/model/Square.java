package quoridor.model;

/** This will allow us to use elements of the class Serializable.
*/
import java.io.Serializable;

/**
 * Class representing a square.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class Square implements Serializable {

    /** This will allow us to serialize the object.
    */
    private static final long serialVersionUID = 5458954L;

    /** Coordinate x of the square.
    */
    private final int x;

    /** Coordinate y of the square.
    */
    private final int y;

    /** Status of the square.
    */
    private Status status;

    /**
     * Constructor of a square.
     * Initialize it with its coordinates and its status.
     * @param x The coordinate x of the square.
     * @param y The coordinate y of the square.
     * @param s The status of the square.
     */
    public Square(int x, int y, Status s) {
        this.x = x;
        this.y = y;
        this.status = s;
    }

    /**
     * Return the status of the square.
     * @return The status of the square.
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Modify the status of the square.
     * @param status The status of the square.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Return the coordinate x of the square.
     * @return The coordinate x of the square.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Return the coordinate y of the square.
     * @return The coordinate y of the square.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Return true if the square is a fence.
     * @return True if the fence is a fence, false otherwise.
     */
    public boolean isFence() {
        return (this.status == Status.FENCEPOSSIBLE) || (this.status == Status.FENCEPAWN1)
                || (this.status == Status.FENCEPAWN2);
    }

    /**
     * Return true if the square is a fence of the first player.
     * @return True if the square is a fence of the first player, false otherwise.
     */
    public boolean isFencePawn1() {
        return this.status == Status.FENCEPAWN1;
    }

    /**
     * Return true if the square is a fence of the second player.
     * @return True if the square is a fence of the second player, false otherwise.
     */
    public boolean isFencePawn2() {
        return this.status == Status.FENCEPAWN2;
    }

    /**
     * Return true if the square is a fence possible.
     * @return True if the square is a fence possible, false otherwise.
     */
    public boolean isFencePossible() {
        return this.status == Status.FENCEPOSSIBLE;
    }

    /**
     * Return true if the square is a pawn.
     * @return True if the square is a pawn, false otherwise.
     */
    public boolean isPawn() {
        return (this.status == Status.PAWN1) || (this.status == Status.PAWN2) || (this.status == Status.PAWNPOSSIBLE);
    }

    /**
     * Return true if the square is a pawn possible.
     * @return True if the square is a pawn possible, false otherwise.
     */
    public boolean isPawnPossible() {
        return this.status == Status.PAWNPOSSIBLE;
    }

    /**
     * Return true if the square is a pawn of the first player.
     * @return True if the square is a pawn of the first player, false otherwise.
     */
    public boolean isPawn1() {
        return this.status == Status.PAWN1;
    }

    /**
     * Return true if the square is a pawn of the second player.
     * @return True if the square is a pawn of the second player, false otherwise.
     */
    public boolean isPawn2() {
        return this.status == Status.PAWN2;
    }

}
