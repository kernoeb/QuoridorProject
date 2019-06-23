package quoridor.model;

/** This will allow us to use elements of the class Serializable.
*/
import java.io.Serializable;

/** This will allow us to create an ArrayList.
*/
import java.util.ArrayList;

/** This will allow us to use elements of the class Random.
*/
import java.util.Random;

/**
 * Class representing a game.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class Game implements Serializable {

    /** This will allow us to serialize the object.
    */
    private static final long serialVersionUID = 5454564654L;

    /** The board of the game.
    */
    private final Board board;

    /** The first player of the game.
    */
    private Player player1;

    /** The second player of the game.
    */
    private Player player2;

    /** The mode of the game.
    */
    private final Mode mode;

    /** The actual player of the game.
    */
    private Player actualPlayer;

    /** The boolean terminal which tells if the game is in the terminal or not.
    */
    private final boolean terminal;

    /**
     * Constructor of a game.
     * Initialize the elements of the game (its board, its mode, its players and
     * the actual player, which means the player who will play at first and who is
     * selected randomly).
     * @param mode        The mode of the game.
     * @param namePlayer1 The name of the first player.
     * @param namePlayer2 The name of the second player.
     * @param terminal    The booelan terminal which tells if the game is in a terminal or not.
     */
    public Game(Mode mode, String namePlayer1, String namePlayer2, boolean terminal) {
        this.board = new Board();
        this.terminal = terminal;
        this.mode = mode;

        if (mode == Mode.HH) {
            this.player1 = new HumanPlayer(this, namePlayer1, this.board, this.board.getSIZE() - 1, this.board.getSIZE() / 2, terminal);
            this.player2 = new HumanPlayer(this, namePlayer2, this.board, 0, this.board.getSIZE() / 2, terminal);
        } else if (mode == Mode.HA) {
            this.player1 = new HumanPlayer(this, namePlayer1, this.board, this.board.getSIZE() - 1, this.board.getSIZE() / 2, terminal);
            this.player2 = new AutoPlayer(this, namePlayer2, this.board, 0, this.board.getSIZE() / 2, terminal);
        }

        this.initializeGame();
    }

    /**
     * Return the board of the game.
     * @return The board of the game.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Return the first player of the game.
     * @return The first player of the game.
     */
    public Player getPlayer1() {
        return this.player1;
    }

    /**
     * Return the second player of the game.
     * @return The second player of the game.
     */
    public Player getPlayer2() {
        return this.player2;
    }

    /**
     * Return the actual player of the game.
     * @return Return the actual player of the game.
     */
    public Player getActualPlayer() {
        return this.actualPlayer;
    }

    /**
     * Initialize tje actual plyaer, which means the player who will start to play.
     */
    private void initializeGame() {
        this.actualPlayer = this.whoStarts();
    }

    /**
     * Choose randomly one of the two player to know which one will start.
     * @return One of the two player selected randomly.
     */
    private Player whoStarts() {
        ArrayList<Player> list = new ArrayList<Player>();
        list.add(this.player1);
        list.add(this.player2);

        // If the game is in a graphical interface, we choosed to start always by
        // the HumanPlayer because we think that when the AutomaticPlayer starts,
        // it is not natural that we will have to play after.
        if (!this.terminal && this.mode == Mode.HA) {
            return this.player1;
        } else return list.get(new Random().nextInt(list.size()));
    }

    /**
     * Return true if the player given in parameters has finished.
     * @param  player The player for who we want to know if he has finished.
     * @return        True if the player has finished, false otherwise.
     */
    public boolean checkHasFinished(Player player) {
        boolean ret = false;

        if (player != null) {
            if (player == this.player1) {
                ret = (this.player1.getCurrentSquare().getX() == 0);
            } else if (player == this.player2) {
                ret = (this.player2.getCurrentSquare().getX() == (this.board.getTotalSize() - 1));
            }
        } else {
            System.err.println("checkHasFinished : Paramètre non valide.");
        }

        return ret;
    }

    /**
     * Update the actual player.
     */
    public void setActualPlayer() {
        if (this.actualPlayer == this.player1) {
            this.actualPlayer = this.player2;
        } else this.actualPlayer = this.player1;
    }

    /**
     * Return the player who has finished and so the one who won the game.
     * @return The player who won the game.
     */
    public Player getWinnerPlayer() {
        Player finishPlayer = null;

        if (this.checkHasFinished(this.player1)) {
            finishPlayer = this.player1;
        } else if (this.checkHasFinished(this.player2)) {
            finishPlayer = this.player2;
        }

        return finishPlayer;
    }

    /**
     * Return a representation of a game, with its board and the player supposed to play.
     * @return A string which represents the game with its board and the actual player.
     */
    public String toString() {
        String ret = "";

        ret += this.board;
        if (!this.board.getColor()) {
            if (this.actualPlayer == this.getPlayer1()) {
                ret += this.actualPlayer.getName() + " (X) :";
            } else ret += this.actualPlayer.getName() + " (O) :";
        } else ret += this.actualPlayer.getName() + " :";

        return ret;
    }

}
