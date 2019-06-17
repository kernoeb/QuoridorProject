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
		if(this.nbFences > 0) {
			String mode = this.askMode();

			if (mode.equalsIgnoreCase("pawn")) {
				this.board.displayForPawn();
				this.playPawn();
			}

			else if (mode.equalsIgnoreCase("fence")) {
				this.board.displayForFence();
				this.playFence();
			}
		}
		else {
			System.out.println("Vous n'avez plus de murs disponibles !");

			this.board.displayForPawn();
			this.playPawn();
		}
	}

	private String askMode() {
		System.out.println("Quelle pièce voulez-vous jouer ? \n"
			+ "Pion (p) ou Mur (m)");

		String ret = null;
		this.scan = new Scanner(System.in);
		String s = this.scan.nextLine();


		while ((!s.equalsIgnoreCase("p")) && (!s.equalsIgnoreCase("pion")) &&
			(!s.equalsIgnoreCase("mur")) && (!s.equalsIgnoreCase("m"))) {
				System.out.println("La chaîne de caractères est incorrecte !\n"
					+ "Veuillez écrire la pièce que vous voulez jouer : Pion (p) ou Mur (m)");
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
		System.out.println("Sur quelle case voulez-vous jouer ?");

		int x = this.askX(this.board.getSIZE() - 1);
		int y = this.askY(this.board.getSIZE() - 1);
		String dir = this.askDir();

		while(!this.board.getGrid()[x][y].isFencePossible()) {
			System.out.println("Vous ne pouvez pas jouer sur cette case. \n"
				+"Veuillez en choisir une autre !");

			x = this.askX();
			y = this.askY();
		}

		this.board.setFence(this.board.fenceCoord(x), this.board.fenceCoord(y), dir);
		this.board.setNbFences(this.nbFences - 1);
	}

	/**
	 * Moves the pawns to the desired direction.
	 * The validity is checked by the current square fenceStatus
	 * @author
	 */
	private void playPawn() {
		System.out.print("Vous pouvez jouer un pion sur les cases : ");

		this.board.printListOfPossibilitiesPawn(this);

		System.out.println("Sur quelle case voulez-vous jouer ?");
		int x = this.askX(this.board.getSIZE());
		int y = this.askY(this.board.getSIZE());

		while ((x == this.currentSquare.getX()) && (y == this.currentSquare.getY())) {
			System.out.println("Vous ne pouvez pas jouer sur la même case que votre pion. \n"
				+"Veuillez choisir une autre case !");

			x = this.askX();
			y = this.askY();
		}

		super.movePawn(x, y);
	}

	// Méthode pour Pawn pour l'instant ...
	private int askX(int maxSize) {
		int x = 0;

		// System.out.print(this.name + "\nCoordonnée x : ");
		System.out.print("\nCoordonnée x : ");
		x = this.scan.nextInt();

		while ((x < 0) || (x >= maxSize)) {
			System.out.println("La coordonnée n'est pas valide. Veuillez en saisir une nouvelle !");
			// System.out.print(this.name + "Coordonnée x : ");
			System.out.print("\nCoordonnée x : ");
			x = this.scan.nextInt();
		}

		return this.board.pawnCoord(x);
	}

	// Méthode pour Pawn pour l'instant ...
	private int askY(int maxSize) {
		int y = 0;

		// System.out.print(this.name + "\nCoordonnée y : ");
		System.out.print("Coordonnée y : ");
		y = this.scan.nextInt();

		while((y < 0) || (y >= maxSize)) {
			System.out.println("La coordonnée n'est pas valide. Veuillez en saisir une nouvelle !");
			// System.out.print(this.name + "Coordonnée y : ");
			System.out.print("\nCoordonnée y : ");
			y = this.scan.nextInt();
		}

		return this.board.pawnCoord(y);
	}

	private String askDir() {
		String ret = null;

		System.out.println("Dans quelle direction voulez-vous mettre le mur ? \n"
			+"'H' (Horizontal) ou 'V' (Vertical)");

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
