package quoridor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class contains the methods to initialize the game
 *
 * @author
 */
public class Game implements Serializable {

    private static final long serialVersionUID = 5454564654L;

    private final Board board;
    private Player player1;
    private Player player2;
    private final Mode mode;

    private Player actualPlayer;

    private final boolean terminal;

    /**
     * Game constructor
     * initialize the board, the two players and the mode
     * The mode is chosen by the players with a scanner
     *
     * @author
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

    public Board getBoard() {
        return this.board;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public Player getActualPlayer() {
        return this.actualPlayer;
    }

    /**
     * Initialize the game
     */
    private void initializeGame() {
        this.actualPlayer = this.whoStarts();
    }

    /**
     * Choose randomly which player plays first
     *
     * @return the starting player
     */
    private Player whoStarts() {
        ArrayList<Player> list = new ArrayList<Player>();
        list.add(this.player1);
        list.add(this.player2);
        if (!this.terminal && this.mode == Mode.HA) {
            return this.player1;
        } else return list.get(new Random().nextInt(list.size()));
    }

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

    public void setActualPlayer() {
        if (this.actualPlayer == this.player1) {
            this.actualPlayer = this.player2;
        } else this.actualPlayer = this.player1;
    }

    /**
     * End the game and launch the results procedure
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

    public String toString() {
        // TODO - implement Game.toString
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
