package quoridor;

// import project
import quoridor.Board;
import quoridor.Mode;
import quoridor.Player;

// import java
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class contains the methods to intiliaze the game
 * @author
 */
public class Game {

	private Board board;
	private Player player1;
	private Player player2;
	private Mode mode;

	private Player actualPlayer;

	private Scanner scan;

	/**
	 * Game constructor
	 * intiliaze the board, the two players and the mode
	 * The mode is choosen by the players with a scanner
	 * @author
	 */
	public Game(Mode mode, String namePlayer1, String namePlayer2) {
		this.board = new Board();

		if (mode == Mode.HH) {
			this.player1 = new HumanPlayer(this, namePlayer1, this.board, this.board.getSIZE()-1, (int) (this.board.getSIZE() /2));
			this.player2 = new HumanPlayer(this, namePlayer2, this.board, 0, (int) (this.board.getSIZE() /2));
		}

		else if (mode == Mode.HA) {
			this.player1 = new HumanPlayer(this, namePlayer1, this.board, this.board.getSIZE()-1, (int) (this.board.getSIZE() /2));
			this.player2 = new AutoPlayer(this, namePlayer2, this.board, 0, (int) (this.board.getSIZE() /2));
		}

		this.initializeGame();
		this.start();
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
	 * @param board the desired board to play the game with.
	 */
	public void setBoard(Board board) {
		if(board != null) {
			this.board = board;
		}
		else {
			System.err.println("setBoard : Paramètre non valide");
		}
	}

	/**
	 * Initialize the game
	 */
	private void initializeGame() {
		this.actualPlayer = this.whoStarts();
	}

	/**
	 * Choose randomly which player plays first
	 * @return the starting player
	 */
	public Player whoStarts() {
		ArrayList<Player> list = new ArrayList<Player>();
		list.add(this.player1);
		list.add(this.player2);

		return list.get(new Random().nextInt(list.size()));
	}

	public boolean checkHasFinished(Player player) {
		// TODO - Player.checkHasFinished
		boolean ret = false;

		if (player != null) {
			if (player == this.player1) {
				ret = (this.player1.getCurrentSquare().getX() == 0);
			}

			else if (player == this.player2) {
				ret = (this.player2.getCurrentSquare().getX() == (this.board.getTotalSize()-1));
			}
		}

		else {
			System.err.println("checkHasFinished : Paramètre non valide.");
		}

		return ret;
	}

	public void nextPlayer() {
		if (this.actualPlayer == this.player1) {
			this.player1.play();

			this.actualPlayer = this.player2;
		}
		else if (this.actualPlayer == this.player2) {
			this.player2.play();

			this.actualPlayer = this.player1;
		}
	}

	/**
	 * Launch the game
	 */
	public void start() {
		// TODO - implement Game.start
		while((!this.checkHasFinished(this.player1)) && (!this.checkHasFinished(this.player2))) {
			System.out.println(this);
			this.nextPlayer();
		}

		this.endOfGame();
	}

	/**
	 * End the game and launch the results procedure
	 */
	public void endOfGame() {
		Player finishPlayer = null;

		if(this.checkHasFinished(this.player1)) {
			finishPlayer = this.player1;
		}
		else if(this.checkHasFinished(this.player2)) {
			finishPlayer = this.player2;
		}

		System.out.println("Félicitations ! Le joueur " + this.player1.getName() + " a gagné la partie !");
	}

	public String toString() {
		// TODO - implement Game.toString
		String ret = "";

		ret += this.board;
		ret += this.actualPlayer.getName() + " :";

		return ret;
	}

}
