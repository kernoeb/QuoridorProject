package quoridor;

import java.util.ArrayList;
import utilitary.Maze;
import java.io.*;
import view.*;

/**
 * Player
 * @author
 */
public abstract class Player implements Serializable {

	private static final long serialVersionUID = 52696565L;

	protected Board board;
	protected Square currentSquare;
	protected String name;
	protected int nbFences;
	protected Game game;
	protected ArrayList<Square> listOfOldPositions;
	protected boolean terminal;

	/**
	 * Player constructor
	 * it cans either be human or bot
	 * it has a dedidcated pawn and a number of fences left.
	 */
	public Player(Game game, String name, Board board, boolean terminal) {
		// TODO - implement Player.Player
		if ((game != null) && (name != null) && (board != null)) {
			this.name = name;
			this.board = board;
			this.nbFences = 10;
			this.currentSquare = null;
			this.game = game;
			this.listOfOldPositions = new ArrayList<Square>();
			this.terminal = terminal;
		}
		else {
			System.err.println("Player : Paramètre(s) non valide(s).");
		}
	}

	public Player(Game game, String name, Board board, int initX, int initY, boolean terminal) {
		if ((game != null) && (name != null) && (board != null)) {
			this.name = name;
			this.board = board;
			this.nbFences = 10;
			this.game = game;
			this.listOfOldPositions = new ArrayList<Square>();
			this.terminal = terminal;

			if ((initX >= 0) && (initX < this.board.getSIZE()) && (initY >= 0) && (initY < this.board.getSIZE())) {
				// this.currentSquare = this.board.getGrid()[initX][initY];
				this.currentSquare = this.board.getGrid()[this.board.pawnCoord(initX)][this.board.pawnCoord(initY)];
			} else {
				System.out.println("Player : Paramètre(s) x et/ou y non valide(s). \n"
					+ "L'attribut currentSquare sera mis à null par défaut.");
				this.currentSquare = null;
			}
		}
		else {
			System.err.println("Player : Paramètre(s) non valide(s).");
		}
	}

	public Game getGame() {
		return this.game;
	}

	public boolean checkExistingPath(Player player, int x, int y, String dir) {
		Board tmp = this.board;
		// tmp.setFence(this.board.fenceCoord(x), this.board.fenceCoord(y), dir, player);

		Maze maze = null;
		boolean ok = false;
		if (this.game.getPlayer1() == player) {
			maze = new Maze(tmp, 0);
			ok = maze.BFS(maze.convertToMaze(tmp), player.getCurrentSquare().getX(),
				player.getCurrentSquare().getY(), 0);
		} else {
			maze = new Maze(tmp, 1);
			ok = maze.BFS(maze.convertToMaze(tmp), player.getCurrentSquare().getX(),
						  player.getCurrentSquare().getY(), this.board.getTotalSize()-1);
		}
		if (!ok) System.out.println("Aucun chemin possible !");
		return ok;
	}

	// public boolean checkExistingPath(Player player) {
	// 	boolean ret = false;
	// 	ArrayList<Square> list = this.board.listOfPossibilitiesPawn(player);
	// 	int i = 0;
	// 	Square sq = null;

	// 	while((!ret) && (i < list.size())) {
	// 		// movePawn -> nouvelle cases (list.get(i))
	// 		// Si joueur a fini
	// 		// Alors ret = true
	// 		// Sinon ret = checkExistingPath(player)
	// 		// 	Si

	// 		sq = list.get(i);
	// 		player.movePawn(sq.getX(), sq.getY());
	// 		this.listOfOldPositions.add(this.board.getGrid()[sq.getX()][sq.getY()]);

	// 		System.out.println("x : " + sq.getX() + "y : " + sq.getY());

	// 		if (player.getGame().checkHasFinished(player)) {
	// 			ret = true;
	// 		}
	// 		else if(this.listOfOldPositions.contains(sq)) {

	// 		}
	// 		else {
	// 			ret = this.checkExistingPath(player);

	// 			if(!ret) {
	// 				player.movePawn(sq.getX(), sq.getY());
	// 			}
	// 		}

	// 		i++;
	// 	}

	// 	return ret;
	// }



	public void setCurrentSquare(int x, int y) {
		if ((x >= 0) && (x < this.board.getSIZE()) && (y >= 0) && (y < this.board.getSIZE())
			&& (this.board.getGrid()[this.board.pawnCoord(x)][this.board.pawnCoord(y)].isPawnPossible())) {

			this.currentSquare = this.board.getGrid()[this.board.pawnCoord(x)][this.board.pawnCoord(y)];
			// this.currentSquare = this.board.getGrid()[x][y];
		}

		else {
			System.err.println("setCurrentSquare : Paramètre(s) non valide(s).");
		}
	}

	public void setCurrentSquare(Square square) {
		if (square != null) {
			int x = square.getX();
			int y = square.getY();

			if((x >= 0) && (x < this.board.getSIZE()) && (y >= 0) && (y < this.board.getSIZE())
				&& (this.board.getGrid()[x][y].isPawnPossible())) {

					this.currentSquare = square;
			}
		}

		else {
			System.err.println("setCurrentSquare : Paramètre non valide.");
		}

	}

	public Square getCurrentSquare() {
		return this.currentSquare;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * @return the number of fences left playable by the player
	 * @author
	 */
	public int getNbRestingFences() {
		return this.nbFences;
	}

	public abstract void play() throws SaveGameException;

	public abstract void play(Square square, BoardGUI boardGUI);

	public abstract void playPawn();
	public abstract void playPawn(Square square);

	public abstract void playFence();
	public abstract void playFence(Square square, BoardGUI boardGUI);

	protected void movePawn(int x, int y) {
		if (this.currentSquare != null) {
			// System.out.println("X : " + this.currentSquare.getX() + " | Y : " + this.currentSquare.getY());
			// System.out.println(this.currentSquare.getStatus());
			this.board.getGrid()[x][y].setStatus(this.currentSquare.getStatus());
			this.board.getGrid()[this.currentSquare.getX()][this.currentSquare.getY()].setStatus(Status.PAWNPOSSIBLE);
			this.currentSquare = this.board.getGrid()[x][y];
		}

		else {
			System.err.println("movePawn : L'attribut currentSquare de Player ne doit pas être null.");
		}
	}

	public void setNbFences(int nb) {
		this.nbFences = nb;
	}

	/**
	 *
	 * @param square
	 * @return
	 */
	public boolean checkFencePossible(Square square, String dir) {
		boolean ret = false;

		if ((square != null) && (dir != null)) {
			int x = square.getX();
			int y = square.getY();

			if (dir.equalsIgnoreCase("h") || dir.equalsIgnoreCase("horizontal")) {
				ret = square.isFencePossible() && this.board.getGrid()[x][y-1].isFencePossible()
							&& this.board.getGrid()[x][y+1].isFencePossible();
			}

			else if (dir.equalsIgnoreCase("v") || dir.equalsIgnoreCase("vertical")) {
				ret = square.isFencePossible() && this.board.getGrid()[x-1][y].isFencePossible()
							&& this.board.getGrid()[x+1][y].isFencePossible();
			}

			ret = ret && this.checkExistingPath(this.game.getPlayer1(), x, y, dir) && this.checkExistingPath(this.game.getPlayer2(), x, y, dir);
		}

		else {
			System.err.println("checkFencePossible : Paramètre(s) non valide(s).");
		}

		return ret;
	}
}
