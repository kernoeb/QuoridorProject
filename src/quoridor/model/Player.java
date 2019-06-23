package quoridor.model;

/** This will allow us to use elements of the class Maze.
*/
import quoridor.model.utilitary.Maze;

/** This will allow us to use elements of the class Serializable.
*/
import java.io.Serializable;

/** This will allow us to create an ArrayList.
*/
import java.util.ArrayList;

/**
 * Class representing a player.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public abstract class Player implements Serializable {

    /** This will allow us to serialize the object.
    */
    private static final long serialVersionUID = 52696565L;

    /** The board the player belongs to.
    */
    Board board;

    /** The square where the player is at a specific moment.
    */
    Square currentSquare;

    /** The name of the player.
    */
    private String name;

    /** The number of resting fences of the player.
    */
    int nbFences;

    /** The game the player belongs to.
    */
    Game game;

    /** The boolean terminal which tells if the game is in the terminal or not.
    */
    boolean terminal;

    /**
     * Construct of a player.
     * @param game     The game the player belongs to.
     * @param name     The name of the player.
     * @param board    The board the player belongs to.
     * @param initX    The initial position of the automatic player on the board's X axis.
     * @param initY    The initial position of the automatic player on the board's Y axis.
     * @param terminal Tells if the player will play on a terminal or not.
     */
    Player(Game game, String name, Board board, int initX, int initY, boolean terminal) {
        if ((game != null) && (name != null) && (board != null)) {
            this.name = name;
            this.board = board;
            this.nbFences = 10;
            this.game = game;
            this.terminal = terminal;

            if ((initX >= 0) && (initX < this.board.getSIZE()) && (initY >= 0) && (initY < this.board.getSIZE())) {
                this.currentSquare = this.board.getGrid()[this.board.pawnCoord(initX)][this.board.pawnCoord(initY)];
            } else {
                System.out.println("Player : Paramètre(s) x et/ou y non valide(s). \n"
                        + "L'attribut currentSquare sera mis à null par défaut.");
                this.currentSquare = null;
            }
        } else {
            System.err.println("Player : Paramètre(s) non valide(s).");
        }
    }

    /**
     * Return true if the there is still an existing path if the fence with the
     * caracteristics given in parameters is set.
     * Uses the algorithm BFS of the class Maze.
     * @param  player The player for which the fence would be set.
     * @param  x      The coordinate x of the fence which would be set.
     * @param  y      The coordinate y of the fence which would be set.
     * @param  dir    The direction of the fence which would be set.
     * @return        Return true if there is still an existing path, false otherwise.
     */
    private boolean checkExistingPath(Player player, int x, int y, String dir) {
        Board tmp = this.board;
        tmp.setFence(x, y, dir, player);

        Maze maze;
        boolean ok;
        if (this.game.getPlayer1() == player) {
            maze = new Maze(tmp, 0);
            ok = maze.BFS(maze.convertToMaze(), player.getCurrentSquare().getX(),
                    player.getCurrentSquare().getY(), 0);
        } else {
            maze = new Maze(tmp, 1);
            ok = maze.BFS(maze.convertToMaze(), player.getCurrentSquare().getX(),
                    player.getCurrentSquare().getY(), this.board.getTotalSize() - 1);
        }
        if (!ok) {
            tmp.removeFence(x, y, dir);
            if (this.terminal) System.out.println("Aucun chemin possible !");
        }
        return ok;
    }

    /**
     * Abstract method to select a square randomly where a player could play.
     * @return Return a square selected randomly.
     */
    public abstract Square randomSquare();

    /**
     * Return the current square of the player.
     * @return The current square of the player.
     */
    public Square getCurrentSquare() {
        return this.currentSquare;
    }

    /**
     * Return the name of the player.
     * @return The name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the number of resting fences of the player.
     * @return The number of resting fences of the player.
     */
    public int getNbRestingFences() {
        return this.nbFences;
    }

    /**
     * Abstract method to allow the player to play in the terminal.
     */
    public abstract void play();

    /**
     * Abstract method to allow the player to play in the graphical interface by
     * playing in the square given in parameter.
     * @param  square The square in which the player wants to play.
     * @return        True if something has been modified.
     */
    public abstract boolean play(Square square);

    /**
     * Move the player's pawn in the square with the specified coordinates.
     * @param x The coordinate x of where to move the pawn.
     * @param y The coordinate y of where to move the pawn.
     */
    public void movePawn(int x, int y) {
        if (this.currentSquare != null) {
            this.board.getGrid()[x][y].setStatus(this.currentSquare.getStatus());
            this.board.getGrid()[this.currentSquare.getX()][this.currentSquare.getY()].setStatus(Status.PAWNPOSSIBLE);
            this.currentSquare = this.board.getGrid()[x][y];
        } else {
            System.err.println("movePawn : L'attribut currentSquare de Player ne doit pas être null.");
        }
    }

    /**
     * Modify the number of resting fences of the player.
     * @param nb The number of resting fences of the player.
     */
    public void setNbFences(int nb) {
        this.nbFences = nb;
    }

    /**
     * Return true if the fence with the caracteristics given in parameters could be placed.
     * @param  square The square of the fence which would be placed.
     * @param  dir    The direction of the fence which would be placed.
     * @return        True if the fence could be placed.
     */
    public boolean checkFencePossible(Square square, String dir) {
        boolean ret = false;

        if ((square != null) && (dir != null)) {
            int x = square.getX();
            int y = square.getY();

            if (dir.equalsIgnoreCase("h") || dir.equalsIgnoreCase("horizontal")) {
                ret = square.isFencePossible() && this.board.getGrid()[x][y - 1].isFencePossible()
                        && this.board.getGrid()[x][y + 1].isFencePossible();
            } else if (dir.equalsIgnoreCase("v") || dir.equalsIgnoreCase("vertical")) {
                ret = square.isFencePossible() && this.board.getGrid()[x - 1][y].isFencePossible()
                        && this.board.getGrid()[x + 1][y].isFencePossible();
            }

            ret = ret && this.checkExistingPath(this.game.getPlayer1(), x, y, dir) && this.checkExistingPath(this.game.getPlayer2(), x, y, dir);
        } else {
            System.err.println("checkFencePossible : Paramètre(s) non valide(s).");
        }

        return ret;
    }
}
