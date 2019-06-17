package quoridor;

// import project
import quoridor.Square;
//import GUI.MainGUI;

// import java
import java.util.ArrayList;

/**
 * Class for the initializing of the board.
 * Contains the grid and calls the GUI package to show the game
 * @author
 */
public class Board {

	private int SIZE = 9;
	private Square[][] grid;

	private String ANSI_RESET = "\u001B[0m";
	private String ANSI_GREY = "\u001B[30m";
	private String ANSI_RED = "\u001B[31m";
	private String ANSI_GREEN = "\u001B[32m";
	private String ANSI_YELLOW = "\u001B[33m";
	private String ANSI_BLUE = "\u001B[34m";
	private String ANSI_PURPLE = "\u001B[35m";
	private String ANSI_CYAN = "\u001B[36m";
	private String ANSI_WHITE = "\u001B[37m";

	/**
	 * Board constructor
	 * intiliaze a 81 squares board (9*9)
	 * Initialize walls and positionates players on their starting positions
	 */
	public Board() {
		this.grid = new Square[this.getTotalSize()][this.getTotalSize()];
		this.initializeBoard();
		// stopColors();
	}

	public void initializeBoard() {
		Square temp = null;

		for (int x = 0; x < this.getTotalSize(); x++) {
			for (int y = 0; y < this.getTotalSize(); y++) {
				temp = this.grid[x][y];

				if (this.isEvenNumber(x) && this.isOddNumber(y)) {
					this.grid[x][y] = new Square(x, y, Status.FENCEPOSSIBLE);
				}

				else if (this.isOddNumber(x)) {
					this.grid[x][y] = new Square(x, y, Status.FENCEPOSSIBLE);
				}

				else {
					this.grid[x][y] = new Square(x, y, Status.PAWNPOSSIBLE);
				}
			}
		}

		this.grid[this.pawnCoord(8)][this.pawnCoord(4)] = new Square(this.pawnCoord(8), this.pawnCoord(4), Status.PAWN1);
		this.grid[this.pawnCoord(0)][this.pawnCoord(4)] = new Square(this.pawnCoord(0), this.pawnCoord(4), Status.PAWN2);

		// this.grid[15][8] = new Square(15, 8, Status.FENCEPAWN1);
		// this.grid[15][10] = new Square(15, 10, Status.FENCEPAWN1);

	}

	public int getSIZE() {
		return this.SIZE;
	}

	public Square[][] getGrid() {
		return this.grid;
	}

	public int getTotalSize() {
		return this.SIZE * 2 - 1;
	}

	public boolean isEvenNumber(int x) {
		return (x % 2) == 0;
	}

	public boolean isOddNumber(int x) {
		return (x % 2) != 0;
	}

	public int pawnCoord(int x) {
		return x*2;
	}

	public int fenceCoord(int x) {
		return (x*2) + 1;
	}

	public void setFence(int x, int y, String dir, Player player) {
		Status status = null;

		if (player.getCurrentSquare().isPawn1()) {
			status = Status.FENCEPAWN1;
		}
		else if (player.getCurrentSquare().isPawn2()) {
			status = Status.FENCEPAWN2;
		}

		this.grid[x][y].setStatus(status);

		if (dir.equalsIgnoreCase("h")) {
			this.grid[x][y-1].setStatus(status);
			this.grid[x][y+1].setStatus(status);
		}

		else if (dir.equalsIgnoreCase("v")) {
			this.grid[x-1][y].setStatus(status);
			this.grid[x+1][y].setStatus(status);
		}
	}

	public ArrayList<Square> listOfPossibilitiesFence(Player player) {
		ArrayList<Square> list = new ArrayList<Square>();

	//	for(int x=0; x < )
		return list;
	}

	/**
	 * Check the list of possibilities for the player
	 * @param player
	 */
	public ArrayList<Square> listOfPossibilitiesPawn(Player player) {
		ArrayList<Square> al = new ArrayList<Square>();

		int x = player.getCurrentSquare().getX();
		int y = player.getCurrentSquare().getY();

		int rep[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};

		// for (int i = 0; i < 4; i++) {
		// // 	try {
		// 		int a = rep[i][0];
		// 		int b = rep[i][1];
		// 		// System.out.println((x+a) + " " + (x+b));
		// 		if (this.grid[(x+a)][(y+b)].getStatus() != Status.FENCEPAWN1 && this.grid[(x+a)][(y+b)].getStatus() != Status.FENCEPAWN2) {
		// 			System.out.println("ok 1 " + a*2 + " " + b*2);
		// 			if (this.grid[(x+(a*2))][(y+(b*2))].getStatus() == Status.PAWN1 || this.grid[(x+(a*2))][(y+(b*2))].getStatus() == Status.PAWN2) {
		// 				System.out.println("ok 2");
		// 				if (this.grid[(x+(a*4))][(x+(b*4))].getStatus() == Status.PAWNPOSSIBLE && this.grid[(x+(a*3))][(x+(b*3))].getStatus() != Status.FENCEPAWN1 && this.grid[(x+(a*3))][(x+(b*3))].getStatus() != Status.FENCEPAWN2) {
		// 					System.out.println("ok 3");
		// 					al.add(this.grid[(x+(a*4))][(x+(b*4))]);
		// 				}
		// 			}

		// 		}
		// 	} catch (ArrayIndexOutOfBoundsException e) {}
		// }

		try {
			if (this.grid[x-1][y].getStatus() != Status.FENCEPAWN1 && this.grid[x-1][y].getStatus() != Status.FENCEPAWN2) {
				if (this.grid[x-2][y].getStatus() == Status.PAWN1 || this.grid[x-2][y].getStatus() == Status.PAWN2) {
					if (this.grid[x-4][y].getStatus() == Status.PAWNPOSSIBLE && this.grid[x-3][y].getStatus() != Status.FENCEPAWN1 && this.grid[x-3][y].getStatus() != Status.FENCEPAWN2) {
						al.add(this.grid[x-4][y]);
					}
				}
				else if (this.grid[x-2][y].getStatus() == Status.PAWNPOSSIBLE) al.add(this.grid[x-2][y]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (this.grid[x+1][y].getStatus() != Status.FENCEPAWN1 && this.grid[x+1][y].getStatus() != Status.FENCEPAWN2) {
				if (this.grid[x+2][y].getStatus() == Status.PAWN1 || this.grid[x+2][y].getStatus() == Status.PAWN2) {
					if (this.grid[x+4][y].getStatus() == Status.PAWNPOSSIBLE && this.grid[x+3][y].getStatus() != Status.FENCEPAWN1 && this.grid[x+3][y].getStatus() != Status.FENCEPAWN2) {
						al.add(this.grid[x+4][y]);
					}
				}
				else if (this.grid[x+2][y].getStatus() == Status.PAWNPOSSIBLE) al.add(this.grid[x+2][y]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (this.grid[x][y-1].getStatus() != Status.FENCEPAWN1 && this.grid[x][y-1].getStatus() != Status.FENCEPAWN2) {
				if (this.grid[x][y-2].getStatus() == Status.PAWN1 || this.grid[x][y-2].getStatus() == Status.PAWN2) {
					if (this.grid[x][y-4].getStatus() == Status.PAWNPOSSIBLE && this.grid[x][y-3].getStatus() != Status.FENCEPAWN1 && this.grid[x][y-3].getStatus() != Status.FENCEPAWN2) {
						al.add(this.grid[x][y-4]);
					}
				}
				else if (this.grid[x][y-2].getStatus() == Status.PAWNPOSSIBLE) al.add(this.grid[x][y-2]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (this.grid[x][y+1].getStatus() != Status.FENCEPAWN1 && this.grid[x][y+2].getStatus() != Status.FENCEPAWN2) {
				if (this.grid[x][y+2].getStatus() == Status.PAWN1 || this.grid[x][y+2].getStatus() == Status.PAWN2) {
					if (this.grid[x][y+4].getStatus() == Status.PAWNPOSSIBLE && this.grid[x][y+3].getStatus() != Status.FENCEPAWN1 && this.grid[x][y+3].getStatus() != Status.FENCEPAWN2) {
						al.add(this.grid[x][y+4]);
					}
				}
				else if (this.grid[x][y+2].getStatus() == Status.PAWNPOSSIBLE) al.add(this.grid[x][y+2]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		return al;
	}

	/**
	 * Print the list of possibilities
	 * @param player player
	 */
	public void printListOfPossibilitiesPawn(Player player) {
		// System.out.println(player.getCurrentSquare().getX()/2 + " " + player.getCurrentSquare().getY()/2);

		ArrayList<Square> al = listOfPossibilitiesPawn(player);

		// System.out.print("Coups possibles : ");
		for (Square s : al) {
			System.out.print(s.getX()/2 + ", " + s.getY()/2	 + " | ");
		}
		System.out.println();
	}


	/**
	 * Check if x and y are in the board
	 * @param  x horizontal
	 * @param  y vertical
	 * @return   true or false
	 */
	public boolean checkOutside(int x, int y) {
		return (x >= 0) && (x < this.getSIZE()) && (y >= 0) && (y < this.getSIZE());
	}

	public String toString() {
		String ret = "";

		Square temp = null;

		System.out.print("  ");
		for (int i = 0; i < this.getSIZE(); i++) System.out.print(ANSI_YELLOW + i + "   ");
		System.out.println("");

		for (int x = 0; x < this.getTotalSize(); x++) {
			if (this.isEvenNumber(x)) {
				ret += ANSI_YELLOW + x/2 + " ";
			} else ret += "  ";
			for (int y = 0; y < this.getTotalSize(); y++) {
				temp = this.grid[x][y];

				if (temp.isPawn()) {
					if (temp.isPawnPossible()) {
						ret += this.ANSI_WHITE;
					}

					else if (temp.isPawn1()) {
						ret += this.ANSI_RED;
					}

					else if (temp.isPawn2()) {
						ret += this.ANSI_GREEN;
					}

					ret += "X ";
				}


				else if (temp.isFence()) {
					if (temp.isFencePossible()) {
						ret += this.ANSI_GREY;
					}

					else if (temp.isFencePawn1()) {
						ret += this.ANSI_RED;
					}

					else if (temp.isFencePawn2()) {
						ret += this.ANSI_GREEN;
					}

					if (this.isEvenNumber(x)) {
						ret += "| ";
					}

					else if (this.isEvenNumber(y)) {
						ret += "─ ";
					}

					else {
						ret += "+ ";
					}
				}
			}

			ret += this.ANSI_RESET + "\n";
		}

		return ret;
	}

	public void displayForPawn() {
		Square temp = null;

		System.out.print("  ");
		for (int i = 0; i < this.getSIZE(); i++) System.out.print(ANSI_YELLOW + i + "   ");
		System.out.println("");

		for (int x = 0; x < this.getTotalSize(); x++) {
			if (this.isEvenNumber(x)) {
				System.out.print(ANSI_YELLOW + x/2 + " ");
			}
			else System.out.print("  ");

			for (int y = 0; y < this.getTotalSize(); y++) {
				temp = this.grid[x][y];

				if (temp.isPawn()) {
					if (temp.isPawnPossible()) {
						System.out.print(this.ANSI_WHITE);
					}

					else if (temp.isPawn1()) {
						System.out.print(this.ANSI_RED);
					}

					else if (temp.isPawn2()) {
						System.out.print(this.ANSI_GREEN);
					}

					System.out.print("X ");
				}


				else if (temp.isFence()) {
					if (temp.isFencePossible()) {
						System.out.print(this.ANSI_GREY);
					}

					else if (temp.isFencePawn1()) {
						System.out.print(this.ANSI_RED);
					}

					else if (temp.isFencePawn2()) {
						System.out.print(this.ANSI_GREEN);
					}

					if (this.isEvenNumber(x)) {
						System.out.print("| ");
					}

					else if (this.isEvenNumber(y)) {
						System.out.print("─ ");
					}

					else {
						System.out.print("+ ");
					}
				}
			}

			System.out.println(this.ANSI_RESET);
		}
	}

	public void displayForFence() {
		Square temp = null;

		System.out.print("    ");
		for (int i = 0; i < this.getSIZE()-1; i++) System.out.print(ANSI_YELLOW + i + "   ");
		System.out.println("");

		for (int x = 0; x < this.getTotalSize(); x++) {
			if (this.isOddNumber(x)) {
				System.out.print(ANSI_YELLOW + (int) (x/2) + " ");
			}
			else System.out.print("  ");

			for (int y = 0; y < this.getTotalSize(); y++) {
				temp = this.grid[x][y];

				if (temp.isPawn()) {
					if (temp.isPawnPossible()) {
						System.out.print(this.ANSI_WHITE);
					}

					else if (temp.isPawn1()) {
						System.out.print(this.ANSI_RED);
					}

					else if (temp.isPawn2()) {
						System.out.print(this.ANSI_GREEN);
					}

					System.out.print("X ");
				}


				else if (temp.isFence()) {
					if (temp.isFencePossible()) {
						System.out.print(this.ANSI_GREY);
					}

					else if (temp.isFencePawn1()) {
						System.out.print(this.ANSI_RED);
					}

					else if (temp.isFencePawn2()) {
						System.out.print(this.ANSI_GREEN);
					}

					if (this.isEvenNumber(x)) {
						System.out.print("| ");
					}

					else if (this.isEvenNumber(y)) {
						System.out.print("─ ");
					}

					else {
						System.out.print("+ ");
					}
				}
			}

			System.out.println(this.ANSI_RESET);
		}
	}


	public void stopColors() {
		this.ANSI_RESET = "";
		this.ANSI_GREY = "";
		this.ANSI_RED = "";
		this.ANSI_GREEN = "";
		this.ANSI_YELLOW = "";
		this.ANSI_BLUE = "";
		this.ANSI_PURPLE = "";
		this.ANSI_CYAN = "";
		this.ANSI_WHITE = "";
	}

}
