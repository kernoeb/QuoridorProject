package quoridor;

// import project
//import quoridor.Pawn;

// import java
import java.util.Scanner;


public class HumanPlayer extends Player {
	private Scanner scan;

	/**
	 * HumanPlayer constructor
	 * @param name
	 * @author
	 */
	public HumanPlayer(String name, Board board, int initX, int initY) {
		super(name, board, initX, initY);
		this.scan = new Scanner(System.in);
	}

	public HumanPlayer(String name, Board board) {
		super(name, board);
		this.scan = new Scanner(System.in);
	}

	/**
	 * Let the player choose between if he wants to play a fence or moves its pawn
	 * @author
	 */
	public void play() {
		String mode = this.askMode();

		if (mode.equalsIgnoreCase("pawn")) {
			this.playPawn();
		}

		else if (mode.equalsIgnoreCase("fence")) {
			this.playFence();
		}
	}

	private String askMode() {
		System.out.println("Quelle pièce voulez-vous jouer ? \n"
			+ "Pion (p) ou Mur (m)");

		String ret = null;
		String s = this.scan.nextLine();

		while ((!s.equalsIgnoreCase("p")) && (!s.equalsIgnoreCase("pion")) &&
			(!s.equalsIgnoreCase("mur")) && (!s.equalsIgnoreCase("m"))) {
				System.out.println("La chaîne de caractères est incorrecte !"
					+ "Veuillez écrire la pièce que vous voulez jouée : Pion (p) ou Mur (m)");
				s = this.scan.nextLine();
		}

		if ((s.equalsIgnoreCase("p")) || (s.equalsIgnoreCase("pion"))) {
			ret = "pawn";
		}
		else {
			ret = "fence";
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

	}

	/**
	 * Moves the pawns to the desired direction.
	 * The validity is checked by the current square fenceStatus
	 * @author
	 */
	private void playPawn() {
		System.out.println("Vous pouvez jouer un pion sur les cases :");

		this.board.printListOfPossibilitiesPawn();

		System.out.println("Sur quelle case voulez-vous jouer ?");
		int x = this.askX();
		int y = this.askY();

		while ((x == this.currentSquare.getX()) && (y == this.currentSquare.getY())) {
			System.out.println("Vous ne pouvez pas jouer sur la même case que votre pion. \n"
				+"Veuillez choisir une autre case !");

			x = this.askX();
			y = this.askY();
		}

		super.movePawn(x, y);
	}

	// Méthode pour Pawn pour l'instant ...
	private int askX() {
		int x = 0;

		// System.out.print(this.name + "\nCoordonnée x : ");
		System.out.print("\nCoordonnée x : ");
		x = this.scan.nextInt();

		while ((x < 0) || (x >= this.board.getSIZE())) {
			System.out.println("La coordonnée n'est pas valide. Veuillez en saisir une nouvelle !");
			// System.out.print(this.name + "Coordonnée x : ");
			System.out.print("\nCoordonnée x : "); 
			x = this.scan.nextInt();
		}

		return this.board.pawnCoord(x);
	}

	// Méthode pour Pawn pour l'instant ...
	private int askY() {
		int y = 0;

		// System.out.print(this.name + "\nCoordonnée y : ");
		System.out.print("\nCoordonnée y : ");
		y = this.scan.nextInt();

		while((y < 0) || (y >= this.board.getSIZE())) {
			System.out.println("La coordonnée n'est pas valide. Veuillez en saisir une nouvelle !");
			// System.out.print(this.name + "Coordonnée y : ");
			System.out.print("\nCoordonnée y : "); 
			y = this.scan.nextInt();
		}

		return this.board.pawnCoord(y);
	}
}
