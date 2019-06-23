package quoridor.model;

//import GUI.MainGUI;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for the initializing of the board.
 * Contains the grid and calls the GUI package to show the game
 *
 */
public class Board implements Serializable {

    private static final long serialVersionUID = 54254574554L;

    private boolean color;
    private final int SIZE = 9;
    private final Square[][] grid;

    // private Object[][] listOfPossibilitiesFence;

    /**
     * Board constructor
     * intiliaze a 81 squares board (9*9)
     * Initialize walls and positionates players on their starting positions
     */
    public Board() {
        this.grid = new Square[this.getTotalSize()][this.getTotalSize()];
        // this.listOfPossibilitiesFence = new Object[128][2];
        this.initializeBoard();
        // stopColors();
    }

    public boolean getColor() {
        return this.color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    private void initializeBoard() {
        for (int x = 0; x < this.getTotalSize(); x++) {
            for (int y = 0; y < this.getTotalSize(); y++) {

                if (this.isEvenNumber(x) && this.isOddNumber(y)) {
                    this.grid[x][y] = new Square(x, y, Status.FENCEPOSSIBLE);
                } else if (this.isOddNumber(x)) {
                    this.grid[x][y] = new Square(x, y, Status.FENCEPOSSIBLE);
                } else {
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
        return x * 2;
    }

    public int fenceCoord(int x) {
        return (x * 2) + 1;
    }

    public void setFence(int x, int y, String dir, Player player) {
        Status status = null;

        if (player.getCurrentSquare().isPawn1()) {
            status = Status.FENCEPAWN1;
        } else if (player.getCurrentSquare().isPawn2()) {
            status = Status.FENCEPAWN2;
        }

        // System.out.println("x : " + x + "   " + "y : " + y);

        this.grid[x][y].setStatus(status);

        if (dir.equalsIgnoreCase("h")) {
            this.grid[x][y - 1].setStatus(status);
            this.grid[x][y + 1].setStatus(status);
        } else if (dir.equalsIgnoreCase("v")) {
            this.grid[x - 1][y].setStatus(status);
            this.grid[x + 1][y].setStatus(status);
        }
    }

    public void removeFence(int x, int y, String dir) {

        this.grid[x][y].setStatus(Status.FENCEPOSSIBLE);

        if (dir.equalsIgnoreCase("h")) {
            this.grid[x][y - 1].setStatus(Status.FENCEPOSSIBLE);
            this.grid[x][y + 1].setStatus(Status.FENCEPOSSIBLE);
        } else if (dir.equalsIgnoreCase("v")) {
            this.grid[x - 1][y].setStatus(Status.FENCEPOSSIBLE);
            this.grid[x + 1][y].setStatus(Status.FENCEPOSSIBLE);
        }

    }

    public boolean possibleFence(Square square, Player player) {
        boolean ret = false;
        int x = square.getX();
        int y = square.getY();
        Square squareFence;
        // Barrière horizontale
        if (x % 2 != 0 && y % 2 == 0) {
            try {
                if (y != this.getTotalSize() - 1) squareFence = this.grid[x][y + 1];
                else squareFence = this.grid[x][y - 1];

                if (player.checkFencePossible(squareFence, "h")) {
                    this.removeFence(squareFence.getX(), squareFence.getY(), "h");
                    ret = true;
                }

            } catch (Exception ignored) {
            }
        }
        // Barrière verticale
        else if (x % 2 == 0 && y % 2 != 0) {
            try {
                if (y != this.getTotalSize() - 1) squareFence = this.grid[x + 1][y];
                else squareFence = this.grid[x - 1][y];

                if (player.checkFencePossible(squareFence, "v")) {
                    this.removeFence(squareFence.getX(), squareFence.getY(), "v");
                    ret = true;
                }
            } catch (Exception ignored) {
            }
        }
        return ret;
    }

    // public void listOfPossibilitiesFence(Player player) {
    public Object[][] listOfPossibilitiesFence(Player player) {
        Object[][] listOfPossibilitiesFence = new Object[128][2];
        int val = 0;
        for (int i = 0; i < getTotalSize(); i++) {
            for (int j = 0; j < getTotalSize(); j++) {
                if (((i % 2 != 0) && (j % 2 != 0))) {
                    if (player.checkFencePossible(this.grid[i][j], "h")) {
                        listOfPossibilitiesFence[val][0] = this.grid[i][j];
                        listOfPossibilitiesFence[val][1] = "h";
                        val++;
                        this.removeFence(i, j, "h");
                    } else {
                        listOfPossibilitiesFence[val][0] = null;
                        listOfPossibilitiesFence[val][1] = "h";
                        val++;
                    }

                    if (player.checkFencePossible(this.grid[i][j], "v")) {
                        listOfPossibilitiesFence[val][0] = this.grid[i][j];
                        listOfPossibilitiesFence[val][1] = "v";
                        val++;
                        this.removeFence(i, j, "v");
                    } else {
                        listOfPossibilitiesFence[val][0] = null;
                        listOfPossibilitiesFence[val][1] = "h";
                        val++;
                    }
                }
            }
        }
        return listOfPossibilitiesFence;
    }

    // public void removeFence(Square s) {
    // 	for (int i = 0; i < this.listOfPossibilitiesFence.length; i++) {
    // 		if (this.listOfPossibilitiesFence[i][0] == s) {
    // 			this.listOfPossibilitiesFence[i][0] = null;
    // 			System.out.println("Trouvé !");
    // 			break;
    // 		}
    // 	}
    // }

    // public Object[][] getListOfPossibilitiesFence() {
    // 	return this.listOfPossibilitiesFence;
    // }

    // public boolean checkIfPossibilitiesFence(Square s) {
    // 	for (int i = 0; i < this.listOfPossibilitiesFence.length; i++) {
    // 		if (this.listOfPossibilitiesFence[i][0] == s) return true;
    // 	}
    // 	return false;
    // }


    /**
     * Check the list of possibilities for the player
     *
     * @param player list of possibilities for one player
     */
    public ArrayList<Square> listOfPossibilitiesPawn(Player player) {
        ArrayList<Square> listOfPossibilities = new ArrayList<Square>();

        int x = player.getCurrentSquare().getX();
        int y = player.getCurrentSquare().getY();

        // Vérification du haut
        try {
            if (this.grid[x - 1][y].isFencePossible()) {
                if (!this.grid[x - 2][y].isPawnPossible()) {
                    if (this.grid[x - 4][y].isPawnPossible() && this.grid[x - 3][y].isFencePossible()) {
                        listOfPossibilities.add(this.grid[x - 4][y]);
                    } else {
                        if (this.grid[x - 2][y - 1].isFencePossible()) {
                            listOfPossibilities.add(this.grid[x - 2][y - 2]);
                        }
                        if (this.grid[x - 2][y + 1].isFencePossible()) {
                            listOfPossibilities.add(this.grid[x - 2][y + 2]);
                        }
                    }
                } else if (this.grid[x - 2][y].isPawnPossible()) {
                    listOfPossibilities.add(this.grid[x - 2][y]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        // Verification du bas
        try {
            if (this.grid[x + 1][y].isFencePossible()) {
                if (!this.grid[x + 2][y].isPawnPossible()) {
                    if (this.grid[x + 4][y].isPawnPossible() && this.grid[x + 3][y].isFencePossible()) {
                        listOfPossibilities.add(this.grid[x + 4][y]);
                    } else {
                        if (this.grid[x + 2][y - 1].isFencePossible()) {
                            listOfPossibilities.add(this.grid[x + 2][y - 2]);
                        }
                        if (this.grid[x + 2][y + 1].isFencePossible()) {
                            listOfPossibilities.add(this.grid[x + 2][y + 2]);
                        }
                    }
                } else if (this.grid[x + 2][y].isPawnPossible()) {
                    listOfPossibilities.add(this.grid[x + 2][y]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        // Verification de la gauche
        try {
            if (this.grid[x][y - 1].isFencePossible()) {
                if (!this.grid[x][y - 2].isPawnPossible()) {
                    if (this.grid[x][y - 4].isPawnPossible() && this.grid[x][y - 3].isFencePossible()) {
                        listOfPossibilities.add(this.grid[x][y - 4]);
                    } else {
                        if (this.grid[x - 1][y - 2].isFencePossible()) {
                            listOfPossibilities.add(this.grid[x - 2][y - 2]);
                        }
                        if (this.grid[x + 1][y - 2].isFencePossible()) {
                            listOfPossibilities.add(this.grid[x + 2][y - 2]);
                        }
                    }
                } else if (this.grid[x][y - 2].isPawnPossible()) {
                    listOfPossibilities.add(this.grid[x][y - 2]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        // Verification de la droite
        try {
            if (this.grid[x][y + 1].isFencePossible()) {
                if (!this.grid[x][y + 2].isPawnPossible()) {
                    if (this.grid[x][y + 4].isPawnPossible() && this.grid[x][y + 3].isFencePossible()) {
                        listOfPossibilities.add(this.grid[x][y + 4]);
                    } else {
                        if (this.grid[x - 1][y + 2].isFencePossible()) {
                            listOfPossibilities.add(this.grid[x - 2][y + 2]);
                        }
                        if (this.grid[x + 1][y + 2].isFencePossible()) {
                            listOfPossibilities.add(this.grid[x + 2][y + 2]);
                        }
                    }
                } else if (this.grid[x][y + 2].isPawnPossible()) {
                    listOfPossibilities.add(this.grid[x][y + 2]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        return listOfPossibilities;
    }

    // /**
    //  * Print the list of possibilities
    //  * @param player player
    //  */
    // public void printListOfPossibilitiesPawn(Player player) {
    // 	// System.out.println(player.getCurrentSquare().getX()/2 + " " + player.getCurrentSquare().getY()/2);
    //
    // 	ArrayList<Square> listOfPossibilities = listOfPossibilitiesPawn(player);
    //
    // 	// System.out.print("Coups possibles : ");
    // 	for (Square s : listOfPossibilities) {
    // 		System.out.print(s.getX()/2 + ", " + s.getY()/2	 + " | ");
    // 	}
    // 	System.out.println();
    // }


    public String toString() {
        StringBuilder ret = new StringBuilder();

        Square temp;


        if (this.color) {
            ret.append("  ");
            String ANSI_YELLOW = "\u001B[33m";
            for (int i = 0; i < this.getSIZE(); i++) ret.append(ANSI_YELLOW).append(i).append("   ");
            ret.append("\n");
            for (int x = 0; x < this.getTotalSize(); x++) {
                if (this.isEvenNumber(x)) {
                    ret.append(ANSI_YELLOW).append(x / 2).append(" ");
                } else ret.append("  ");
                for (int y = 0; y < this.getTotalSize(); y++) {
                    temp = this.grid[x][y];

                    String ANSI_GREEN = "\u001B[32m";
                    String ANSI_RED = "\u001B[31m";
                    if (temp.isPawn()) {
                        if (temp.isPawnPossible()) {
                            String ANSI_WHITE = "\u001B[37m";
                            ret.append(ANSI_WHITE);
                        } else if (temp.isPawn1()) {
                            ret.append(ANSI_RED);
                        } else if (temp.isPawn2()) {
                            ret.append(ANSI_GREEN);
                        }

                        ret.append("X ");
                    } else if (temp.isFence()) {
                        if (temp.isFencePossible()) {
                            String ANSI_GREY = "\u001B[30m";
                            ret.append(ANSI_GREY);
                        } else if (temp.isFencePawn1()) {
                            ret.append(ANSI_RED);
                        } else if (temp.isFencePawn2()) {
                            ret.append(ANSI_GREEN);
                        }

                        if (this.isEvenNumber(x)) {
                            ret.append("| ");
                        } else if (this.isEvenNumber(y)) {
                            ret.append("─ ");
                        } else {
                            ret.append("+ ");
                        }
                    }
                }

                String ANSI_RESET = "\u001B[0m";
                ret.append(ANSI_RESET).append("\n");
            }
        } else {
            ret.append("  ");
            for (int i = 0; i < this.getSIZE(); i++) ret.append(i).append("   ");
            ret.append("\n");
            for (int x = 0; x < this.getTotalSize(); x++) {
                if (this.isEvenNumber(x)) {
                    ret.append(x / 2).append(" ");
                } else ret.append("  ");
                for (int y = 0; y < this.getTotalSize(); y++) {
                    temp = this.grid[x][y];

                    if (temp.isPawn()) {
                        if (temp.isPawnPossible()) {
                            ret.append("  ");
                        } else if (temp.isPawn1()) {
                            ret.append("X ");
                        } else if (temp.isPawn2()) {
                            ret.append("O ");
                        }
                    } else if (temp.isFence()) {
                        if (temp.isFencePossible()) {
                            ret.append("  ");
                        } else if (this.isEvenNumber(x)) {
                            ret.append("| ");
                        } else if (this.isEvenNumber(y)) {
                            ret.append("─ ");
                        } else {
                            ret.append("+ ");
                        }
                    }
                }

                ret.append("\n");
            }
        }
        return ret.toString();
    }


    // private void stopColors() {
    // 	this.ANSI_RESET = "";
    // 	this.ANSI_GREY = "";
    // 	this.ANSI_RED = "";
    // 	this.ANSI_GREEN = "";
    // 	this.ANSI_YELLOW = "";
    // 	this.ANSI_BLUE = "";
    // 	this.ANSI_PURPLE = "";
    // 	this.ANSI_CYAN = "";
    // 	this.ANSI_WHITE = "";
    // }

    // public void displayForPawn() {
    // 	Square temp = null;
    //
    // 	System.out.print("  ");
    // 	for (int i = 0; i < this.getSIZE(); i++) System.out.print(ANSI_YELLOW + i + "   ");
    // 	System.out.println("");
    //
    // 	for (int x = 0; x < this.getTotalSize(); x++) {
    // 		if (this.isEvenNumber(x)) {
    // 			System.out.print(ANSI_YELLOW + x/2 + " ");
    // 		}
    // 		else System.out.print("  ");
    //
    // 		for (int y = 0; y < this.getTotalSize(); y++) {
    // 			temp = this.grid[x][y];
    //
    // 			if (temp.isPawn()) {
    // 				if (temp.isPawnPossible()) {
    // 					System.out.print(this.ANSI_WHITE);
    // 				}
    //
    // 				else if (temp.isPawn1()) {
    // 					System.out.print(this.ANSI_RED);
    // 				}
    //
    // 				else if (temp.isPawn2()) {
    // 					System.out.print(this.ANSI_GREEN);
    // 				}
    //
    // 				System.out.print("X ");
    // 			}
    //
    //
    // 			else if (temp.isFence()) {
    // 				if (temp.isFencePossible()) {
    // 					System.out.print(this.ANSI_GREY);
    // 				}
    //
    // 				else if (temp.isFencePawn1()) {
    // 					System.out.print(this.ANSI_RED);
    // 				}
    //
    // 				else if (temp.isFencePawn2()) {
    // 					System.out.print(this.ANSI_GREEN);
    // 				}
    //
    // 				if (this.isEvenNumber(x)) {
    // 					System.out.print("| ");
    // 				}
    //
    // 				else if (this.isEvenNumber(y)) {
    // 					System.out.print("─ ");
    // 				}
    //
    // 				else {
    // 					System.out.print("+ ");
    // 				}
    // 			}
    // 		}
    //
    // 		System.out.println(this.ANSI_RESET);
    // 	}
    // }

    // public void displayForFence() {
    // 	Square temp = null;
    //
    // 	System.out.print("    ");
    // 	for (int i = 0; i < this.getSIZE()-1; i++) System.out.print(ANSI_YELLOW + i + "   ");
    // 	System.out.println("");
    //
    // 	for (int x = 0; x < this.getTotalSize(); x++) {
    // 		if (this.isOddNumber(x)) {
    // 			System.out.print(ANSI_YELLOW + (int) (x/2) + " ");
    // 		}
    // 		else System.out.print("  ");
    //
    // 		for (int y = 0; y < this.getTotalSize(); y++) {
    // 			temp = this.grid[x][y];
    //
    // 			if (temp.isPawn()) {
    // 				if (temp.isPawnPossible()) {
    // 					System.out.print(this.ANSI_WHITE);
    // 				}
    //
    // 				else if (temp.isPawn1()) {
    // 					System.out.print(this.ANSI_RED);
    // 				}
    //
    // 				else if (temp.isPawn2()) {
    // 					System.out.print(this.ANSI_GREEN);
    // 				}
    //
    // 				System.out.print("X ");
    // 			}
    //
    //
    // 			else if (temp.isFence()) {
    // 				if (temp.isFencePossible()) {
    // 					System.out.print(this.ANSI_GREY);
    // 				}
    //
    // 				else if (temp.isFencePawn1()) {
    // 					System.out.print(this.ANSI_RED);
    // 				}
    //
    // 				else if (temp.isFencePawn2()) {
    // 					System.out.print(this.ANSI_GREEN);
    // 				}
    //
    // 				if (this.isEvenNumber(x)) {
    // 					System.out.print("| ");
    // 				}
    //
    // 				else if (this.isEvenNumber(y)) {
    // 					System.out.print("─ ");
    // 				}
    //
    // 				else {
    // 					System.out.print("+ ");
    // 				}
    // 			}
    // 		}
    //
    // 		System.out.println(this.ANSI_RESET);
    // 	}
    // }


}
