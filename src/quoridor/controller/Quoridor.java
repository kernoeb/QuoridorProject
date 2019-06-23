package quoridor.model;

// project import
import quoridor.model.utilitary.RWFile;
import quoridor.model.*;
import quoridor.view.*;

import java.util.ArrayList;
import java.util.Scanner;

// java import

/**
* Permits to launch a session from which can be launch and saved games
* @author
*/
public class Quoridor {

	private Game game;
	private String fileName;
	private boolean terminal;
	private boolean load;
	private Scanner scan;
	private DisplayTerminal displayTerminal;

    /**
     * Quoridor constructor
     * Create an object Quoridor
     * Serve as a platform to load and launch games
     * @param fileName file used
     * @param namePlayer1 player 1's name
     * @param namePlayer2 player 2's name
     * @param terminal terminal mode or not
     */
	public Quoridor(String fileName, String namePlayer1, String namePlayer2, boolean terminal) {
		try {
			ArrayList<String> list = RWFile.readFile(fileName);
			Mode mode = Mode.valueOf(list.get(0));
			this.terminal = terminal;
			this.load = false;
			this.game = new Game(mode, namePlayer1, namePlayer2, this.terminal);
			this.scan = new Scanner(System.in);
			if (this.terminal) {
				boolean color = true;
				if (!this.askColor()) {
					color = false;
				}
				this.game.getBoard().setColor(color);
				this.displayTerminal = new DisplayTerminal();
				this.launchGame();
			}
		} catch (Exception e) {
			System.err.println("Quoridor 1 : " + e.getMessage());
		}
	}

    /**
     * Quoridor constructor
     * Create an object Quoridor
     * Serve as a platform to load and launch games
     * @param mode game mode (HH or HA)
     * @param namePlayer1 player 1's username
     * @param namePlayer2 player 2's username
     * @param terminal terminal mode or not
     */
	public Quoridor(Mode mode, String namePlayer1, String namePlayer2, boolean terminal) {
		try {
			this.terminal = terminal;
			this.load = false;
			this.game = new Game(mode, namePlayer1, namePlayer2, this.terminal);
			if (this.terminal) {
				boolean color = true;
				if (!this.askColor()) {
					color = false;
				}
				this.game.getBoard().setColor(color);
				this.displayTerminal = new DisplayTerminal();
				this.launchGame();
			}
		} catch (Exception e) {
			System.err.println("Quoridor 2 : " + e.getMessage());
		}
	}

	public Quoridor(String fileName, boolean terminal) {
		try {
			this.terminal = terminal;
			this.fileName = fileName;
			this.load = true;
			this.game = this.loadOldGame(this.fileName);
			// this.game.start();
			// if (this.terminal) this.launchGame(this.game);
			if (this.terminal) {
				boolean color = true;
				if (!this.askColor()) {
					color = false;
				}
				this.game.getBoard().setColor(color);
				this.displayTerminal = new DisplayTerminal();
				this.launchGame();
			}
	 	} catch (Exception e) {
			System.err.println("Quoridor 3 : " + e.getMessage());
		}
	}

	/**
	 * @return the Game of the current Quoridor.
	 * @author
	 */
	public Game getGame() {
		return this.game;
	}

	/**
	 * @return The previously saved game
	 * @author
	 */
	public Game loadOldGame(String fileName) {
		Game game = null;

		try {
			if (this.terminal) System.out.println("Reprise de la partie : " + fileName);
			game = RWFile.readObj(fileName);
		} catch (Exception e) {
			System.err.println("loadOldGame : " + e.getMessage());
		}

		return game;
	}

	/**
	 * Launch the chosen game
	 * @param game the desired game to launch
	 * @author
	 */
	public void launchGame() {
		if(game != null) {
			String mode = null;
			boolean save = false;
			while((!this.game.checkHasFinished(this.game.getPlayer1())) && (!this.game.checkHasFinished(this.game.getPlayer2())) && (!save)) {
		    this.displayTerminal.displayGame(game);
				if (this.game.getActualPlayer() instanceof HumanPlayer) {
					mode = this.askMode();
					if (!mode.equalsIgnoreCase("save")) {
			    	this.nextPlayer(mode);
					}
					else {
						this.saveGame(this.game);
						save = true;
					}
				}
				else {
					this.nextPlayer("");
				}
		  }

			if (!save) {
		  	this.displayTerminal.displayEndOfGame(this.game.getWinnerPlayer());
			}
			else {
				System.out.println("Partie sauvegardée !");
			}
		}

		else {
			System.err.println("launchGame : Paramètre non valide.");
		}
	}

	public void nextPlayer(String mode) {
		Player actualPlayer = this.game.getActualPlayer();

		if(this.terminal) {
			if(actualPlayer instanceof HumanPlayer) {
        if(actualPlayer.getNbRestingFences() > 0) {
            if (mode.equalsIgnoreCase("pawn")) {
                this.makePlayPawn(actualPlayer);
            }

            else if (mode.equalsIgnoreCase("fence")) {
							this.makePlayFence(actualPlayer);
            }
        }
        else {
            System.out.println("Vous n'avez plus de murs disponibles !");

            this.makePlayPawn(actualPlayer);
        }
			}
			else {
				actualPlayer.play();
			}
    }

		this.game.setActualPlayer();
	}

	public void makePlayPawn(Player actualPlayer) {
		Board board = this.game.getBoard();
		this.displayTerminal.displayForPawn(board);
		// actualPlayer.playPawn();
		this.displayTerminal.displayListOfPossibilitiesPawn(board.listOfPossibilitiesPawn(actualPlayer));
		// this.game.getBoardGUI().addTmpPossibilities(this.board.listOfPossibilitiesPawn(this));

		System.out.println("Sur quelle case voulez-vous jouer ?");
		int x = this.askX(board.getSIZE());
		int y = this.askY(board.getSIZE());

		while (((board.pawnCoord(x) == actualPlayer.getCurrentSquare().getX()) && (board.pawnCoord(y) == actualPlayer.getCurrentSquare().getY()))
				|| (!board.listOfPossibilitiesPawn(actualPlayer).contains(board.getGrid()[board.pawnCoord(x)][board.pawnCoord(y)]))) {
			System.out.println("Vous ne pouvez pas jouer sur cette case. \n"
								+ "Veuillez en choisir une autre !");

			x = this.askX(board.getSIZE());
			y = this.askY(board.getSIZE());
		}
		actualPlayer.movePawn(board.pawnCoord(x), board.pawnCoord(y));
	}

	public void makePlayFence(Player actualPlayer) {

		Board board = this.game.getBoard();
		this.displayTerminal.displayForFence(board);

		actualPlayer.listOfOldPositions.clear();

		System.out.println("Sur quelle case voulez-vous jouer ?");

		int x = this.askX(board.getSIZE() - 1);
		int y = this.askY(board.getSIZE() - 1);
		String dir = this.askDir();

		// Square currentSquare = actualPlayer.getCurrentSquare();

		while (!actualPlayer.checkFencePossible(board.getGrid()[board.fenceCoord(x)][board.fenceCoord(y)], dir)) {
			System.out.println("Vous ne pouvez pas jouer sur cette case. \n"
								+ "Veuillez en choisir une autre !");

			x = this.askX(board.getSIZE() - 1);
			y = this.askY(board.getSIZE() - 1);
			dir = this.askDir();

			// actualPlayer.setCurrentSquare(currentSquare);
		}

		// actualPlayer.setCurrentSquare(currentSquare);

		board.setFence(board.fenceCoord(x), board.fenceCoord(y), dir, actualPlayer);
		actualPlayer.setNbFences(actualPlayer.getNbRestingFences() - 1);

		System.out.println("Il vous reste " + actualPlayer.getNbRestingFences() + " barrières !");
	}

	/**
	 * Save the desired game into the saving file of the current Quoridor object
	 * @param game The desired game to save
	 * @author
	 */
	public void saveGame(Game game) {
		if (game != null) {
			if(this.load) {
				RWFile.writeFile(game, this.fileName);
			}
			else {
				RWFile.writeFile(game, "");
			}
		}

		else {
			System.err.println("saveGame : Paramètre non valide.");
		}
	}

	private String askMode() {
		System.out.println("Quelle pièce voulez-vous jouer ? \n"
			+ "Pion (p) ou Mur (m) | Arrêter et sauvegarder la partie (s)");

		String ret = null;
		this.scan = new Scanner(System.in);
		String s = this.scan.nextLine();


		while ((!s.equalsIgnoreCase("p")) && (!s.equalsIgnoreCase("pion")) &&
			(!s.equalsIgnoreCase("mur")) && (!s.equalsIgnoreCase("m")) &&
			((!s.equalsIgnoreCase("s")) && (!s.equalsIgnoreCase("save")))) {
				System.out.println("La chaîne de caractères est incorrecte !\n"
					+ "Veuillez écrire la pièce que vous voulez jouer : Pion (p) ou Mur (m) | Arrêter et sauvegarder la partie (s)");
				s = this.scan.nextLine();
		}

		if ((s.equalsIgnoreCase("p")) || (s.equalsIgnoreCase("pion"))) {
			ret = "pawn";
		}
		else if ((s.equalsIgnoreCase("m")) || (s.equalsIgnoreCase("mur"))) {
			ret = "fence";
		}
		else if ((s.equalsIgnoreCase("s")) || (s.equalsIgnoreCase("save"))) {
			ret = "save";
		}

		return ret;
	}

	private int askX(int maxSize) {
		int x = 0;

		System.out.print("\nCoordonnée x : ");

		boolean isNumeric = false;
		while (!isNumeric) {
			try {
				x = this.scan.nextInt();
				isNumeric = true;
			} catch (java.util.InputMismatchException e) {
				System.out.println("La chaîne de caractère est incorrecte !");
				this.scan.nextLine();
				System.out.print("Coordonnée x : ");
			}
		}

		while ((x < 0) || (x >= maxSize)) {
			System.out.println("La coordonnée n'est pas valide. Veuillez en saisir une nouvelle !");
			System.out.print("\nCoordonnée x : ");
			isNumeric = false;
			while (!isNumeric) {
				try {
					x = this.scan.nextInt();
					isNumeric = true;
				} catch (java.util.InputMismatchException e) {
					System.out.println("La chaîne de caractère est incorrecte !");
					this.scan.nextLine();
					System.out.print("Coordonnée x : ");
				}
			}
		}

		return x;
	}

	// Méthode pour Pawn pour l'instant ...
	private int askY(int maxSize) {
		int y = 0;

		System.out.print("Coordonnée y : ");
		boolean isNumeric = false;
		while (!isNumeric) {
			try {
				y = this.scan.nextInt();
				isNumeric = true;
			} catch (java.util.InputMismatchException e) {
				System.out.println("La chaîne de caractère est incorrecte !");
				this.scan.nextLine();
				System.out.print("Coordonnée y : ");
			}
		}

		while((y < 0) || (y >= maxSize)) {
			System.out.println("La coordonnée n'est pas valide. Veuillez en saisir une nouvelle !");
			System.out.print("\nCoordonnée y : ");
			isNumeric = false;
			while (!isNumeric) {
				try {
					y = this.scan.nextInt();
					isNumeric = true;
				} catch (java.util.InputMismatchException e) {
					System.out.println("La chaîne de caractère est incorrecte !");
					this.scan.nextLine();
					System.out.print("Coordonnée y : ");
				}
			}
		}

		return y;
	}

	private String askDir() {
		String ret = null;

		System.out.println("Dans quelle direction voulez-vous mettre le mur ? \n"
			+"'H' (Horizontal) ou 'V' (Vertical)");

		this.scan = new Scanner(System.in);
		ret = this.scan.nextLine();

		while ((!ret.equalsIgnoreCase("h")) && (!ret.equalsIgnoreCase("horizontal")) &&
			(!ret.equalsIgnoreCase("v")) && (!ret.equalsIgnoreCase("vertical"))) {
				System.out.println("La chaîne de caractères est incorrecte !\n"
					+ "Veuillez écrire la direction du mur que vous voulez jouer : \n"
					+ "'H' (Horizontal) ou 'V' (Vertical)");
				ret = this.scan.nextLine();
		}

		if ((ret.equalsIgnoreCase("h")) || (ret.equalsIgnoreCase("horizontal"))) {
			ret = "h";
		}
		else {
			ret = "v";
		}

		return ret;
	}

	private boolean askColor() {
		boolean ret = false;
		String line = null;

		System.out.println("Votre terminal accepte t-il les couleurs ? \n"
			+"'O' (Oui) ou 'N' (Non)");

		this.scan = new Scanner(System.in);
		line = this.scan.nextLine();

		while ((!line.equalsIgnoreCase("o")) && (!line.equalsIgnoreCase("oui")) &&
			(!line.equalsIgnoreCase("n")) && (!line.equalsIgnoreCase("non"))) {
				System.out.println("La chaîne de caractères est incorrecte !\n"
					+ "Veuillez écrire si votre terminal accepte les couleurs : \n"
					+ "'O' (Oui) ou 'N' (Non)");
				line = this.scan.nextLine();
		}

		if ((line.equalsIgnoreCase("o")) || (line.equalsIgnoreCase("oui"))) {
			ret = true;
		}

		return ret;
	}
}
