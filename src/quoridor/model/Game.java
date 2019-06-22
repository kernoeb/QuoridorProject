package quoridor.model;

// import project

import quoridor.view.BoardGUI;


// import java
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * This class contains the methods to initialize the game
 * @author
 */
public class Game implements Serializable {

	private static final long serialVersionUID = 5454564654L;

	private Board board;
	private Player player1;
	private Player player2;
	private Mode mode;

	private BoardGUI boardGUI;

	private Player actualPlayer;

	private Scanner scan;
	private boolean terminal;

	/**
	 * Game constructor
	 * initialize the board, the two players and the mode
	 * The mode is chosen by the players with a scanner
	 * @author
	 */
	public Game(Mode mode, String namePlayer1, String namePlayer2, boolean terminal) throws SaveGameException {
		this.board = new Board();
		this.terminal = terminal;

		if (mode == Mode.HH) {
			this.player1 = new HumanPlayer(this, namePlayer1, this.board, this.board.getSIZE()-1, (int) (this.board.getSIZE() /2), terminal);
			this.player2 = new HumanPlayer(this, namePlayer2, this.board, 0, (int) (this.board.getSIZE() /2), terminal);
		}

		else if (mode == Mode.HA) {
			this.player1 = new HumanPlayer(this, namePlayer1, this.board, this.board.getSIZE()-1, (int) (this.board.getSIZE() /2), terminal);
			this.player2 = new AutoPlayer(this, namePlayer2, this.board, 0, (int) (this.board.getSIZE() /2), terminal);
		}

		this.initializeGame();
		this.boardGUI = new BoardGUI(this, this.board);
		runAutoPlayer();
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

	public Player getOtherPlayer() {
		if (this.actualPlayer == this.player1) return this.player2;
		else return this.player1;
	}

	public BoardGUI getBoardGUI() {
		return this.boardGUI;
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

	private void runAutoPlayer() {
		if (this.actualPlayer instanceof AutoPlayer) {
			Square square = this.actualPlayer.randomSquare();
			this.actualPlayer.play(square, this.boardGUI);
			// this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
			// this.boardGUI.setFencesEnabled(square);
			this.boardGUI.displayBoardGUI();
			this.boardGUI.addTmpPossibilities(this.boardGUI.getBoard().listOfPossibilitiesPawn(this.actualPlayer), this.actualPlayer);
		}
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

	public void nextPlayer() throws SaveGameException {
		if (this.actualPlayer == this.player1) {
			this.player1.play();

			this.actualPlayer = this.player2;
		}
		else if (this.actualPlayer == this.player2) {
			this.player2.play();

			this.actualPlayer = this.player1;
		}
	}

	public void nextPlayerGUI() {
		if (this.actualPlayer == this.player1) {
			this.actualPlayer = this.player2;
		}
		else this.actualPlayer = this.player1;
		System.out.println(this.actualPlayer);
	}

	/**
	 * Launch the game
	 */
	public void start() throws SaveGameException {
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

		// this.boardGUI.displayBoardGUI(this.board);

		ret += this.board;
		ret += this.actualPlayer.getName() + " :";

		return ret;
	}

}
