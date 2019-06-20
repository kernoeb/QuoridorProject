package quoridor;

// import project
//import quoridor.Pawn;

// import java
import java.util.Scanner;
import java.io.*;

public class HumanPlayer extends Player implements Serializable {

	private static final long serialVersionUID = 5454574554L;

	private transient Scanner scan;

	/**
	 * HumanPlayer constructor
	 * @param name
	 * @author
	 */
	public HumanPlayer(Game game, String name, Board board, int initX, int initY, boolean terminal) {
		super(game, name, board, initX, initY, terminal);
		this.scan = new Scanner(System.in);
	}

	public HumanPlayer(Game game, String name, Board board, boolean terminal) {
		super(game, name, board, terminal);
		this.scan = new Scanner(System.in);
	}

    /**
     * Let the player choose between if he wants to play a fence or moves its pawn
     * @author
     */
    public void play() {
        if(this.terminal) {
            if(this.nbFences > 0) {
                String mode = this.askMode();
 
                if (mode.equalsIgnoreCase("pawn")) {
                    this.board.displayForPawn();
                    this.playPawn();
                }
 
                else if (mode.equalsIgnoreCase("fence")) {
                    this.board.displayForFence();
                    this.playFence();
                    System.out.println("Il vous reste " + super.nbFences + " barrières !");
                }
                else if (mode.equalsIgnoreCase("save")) {
                	throw new SaveGameException("");
                }
            }
            else {
                System.out.println("Vous n'avez plus de murs disponibles !");
 
                this.board.displayForPawn();
                this.playPawn();
            }
        }
    }

    public void play(Square sq) {
        if(!terminal) {
            if(this.nbFences > 0) {
                if (sq.isPawn()) {
                    this.playPawn(sq);
                }
 
                else if (sq.isFence()) {
                    this.playFence(sq);
                    // TODO - Réactualiser le nombre de barrières en ayant accès à GameGUI
                }
            }
            else {
                this.playPawn(sq);
            }
        }
    }

	private String askMode() {
		System.out.println("Quelle pièce voulez-vous jouer ? \n"
			+ "Pion (p) ou Mur (m)");

		String ret = null;
		this.scan = new Scanner(System.in);
		String s = this.scan.nextLine();


		while ((!s.equalsIgnoreCase("p")) && (!s.equalsIgnoreCase("pion")) &&
			(!s.equalsIgnoreCase("mur")) && (!s.equalsIgnoreCase("m")) &&
			((!s.equalsIgnoreCase("s")) && (!s.equalsIgnoreCase("save")))) {
				System.out.println("La chaîne de caractères est incorrecte !\n"
					+ "Veuillez écrire la pièce que vous voulez jouer : Pion (p) ou Mur (m)");
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

	/**
	 * Places a fence on the desired emplacement
	 * The overlaping validity is checked by the square object by fenceStatus
	 * The path validity is checked by the checkExistingPath method
	 * @author
	 */
	private void playFence() {
		// TODO - implement HumanPlayer.playFence
		this.listOfOldPositions.clear();

		System.out.println("Sur quelle case voulez-vous jouer ?");

		int x = this.askX(this.board.getSIZE() - 1);
		int y = this.askY(this.board.getSIZE() - 1);
		String dir = this.askDir();

		Square currentSquare = this.getCurrentSquare();

		while (!this.checkFencePossible(this.board.getGrid()[this.board.fenceCoord(x)][this.board.fenceCoord(y)], dir)
			|| (!this.checkExistingPath(this.game.getPlayer1(), x, y, dir)) || (!this.checkExistingPath(this.game.getPlayer2(), x, y, dir))) {
			System.out.println("Vous ne pouvez pas jouer sur cette case. \n"
								+ "Veuillez en choisir une autre !");

			x = this.askX(this.board.getSIZE() - 1);
			y = this.askY(this.board.getSIZE() - 1);
			dir = this.askDir();

			this.setCurrentSquare(currentSquare);
		}

		this.setCurrentSquare(currentSquare);

		this.board.setFence(this.board.fenceCoord(x), this.board.fenceCoord(y), dir, this);
		this.setNbFences(this.nbFences - 1);
	}

    private void playFence(Square sq) {
        // TODO - implement HumanPlayer.playFence
        this.listOfOldPositions.clear();
 
        Square currentSquare = this.getCurrentSquare();
 
        while (!this.checkFencePossible(this.board.getGrid()[this.board.fenceCoord(x)][this.board.fenceCoord(y)], dir)
            || (!this.checkExistingPath(this.game.getPlayer1(), x, y, dir)) || (!this.checkExistingPath(this.game.getPlayer2(), x, y, dir))) {
            System.out.println("Vous ne pouvez pas jouer sur cette case. \n"
                                + "Veuillez en choisir une autre !");
 
            x = this.askX(this.board.getSIZE() - 1);
            y = this.askY(this.board.getSIZE() - 1);
            dir = this.askDir();
 
            this.setCurrentSquare(currentSquare);
        }
 
        this.setCurrentSquare(currentSquare);
 
        this.board.setFence(this.board.fenceCoord(x), this.board.fenceCoord(y), dir, this);
        this.setNbFences(this.nbFences - 1);
    }

	/**
	 * Moves the pawns to the desired direction.
	 * The validity is checked by the current square fenceStatus
	 * @author
	 */
	private void playPawn() {
		System.out.print("Vous pouvez jouer un pion sur les cases : ");

		this.board.printListOfPossibilitiesPawn(this);
		this.game.getBoardGUI().addTmpPossibilities(this.board.listOfPossibilitiesPawn(this));

		System.out.println("Sur quelle case voulez-vous jouer ?");
		int x = this.askX(this.board.getSIZE());
		int y = this.askY(this.board.getSIZE());

		while (((this.board.pawnCoord(x) == this.currentSquare.getX()) && (this.board.pawnCoord(y) == this.currentSquare.getY()))
				|| (this.board.listOfPossibilitiesPawn(this).contains(this.board.getGrid()[this.board.pawnCoord(x)][this.board.pawnCoord(y)]) == false)) {
			System.out.println("Vous ne pouvez pas jouer sur cette case. \n"
								+ "Veuillez en choisir une autre !");

			x = this.askX(this.board.getSIZE());
			y = this.askY(this.board.getSIZE());
		}
		super.movePawn(this.board.pawnCoord(x), this.board.pawnCoord(y));
	}


	// Méthode pour Pawn pour l'instant ...
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
}
