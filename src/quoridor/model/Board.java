package quoridor.model;

/** This will allow us to use elements of the class Serializable.
*/
import java.io.Serializable;

/** This will allow us to create an ArrayList.
*/
import java.util.ArrayList;

/**
 * Class representing the board of the game.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class Board implements Serializable {

    /** This will allow us to serialize the object.
    */
    private static final long serialVersionUID = 54254574554L;

    /** Boolean used to know if the user wants to have colors in his terminal.
    */
    private boolean color;

    /** The fixed size of the board which is 9.
    */
    private final int SIZE = 9;

    /** The grid of the board which will contained Square elements.
    * The grid will have a size of 17 per 17 because it will contain pawns and fences.
    */
    private final Square[][] grid;

    /**
     * Constructor of the board.
     * Initialize the grid by setting its size according to the final int SIZE attribute.
     * Its size is not only 9 per 9 because it will also contain the fences of the game.
     * Initialize then all the squares with their corresponding status.
     */
    public Board() {
        this.grid = new Square[this.getTotalSize()][this.getTotalSize()];
        this.initializeBoard();
    }

    /**
     * Return the boolean color, corresponding if the user wants colors in his terminal or not.
     * @return The boolean color.
     */
    public boolean getColor() {
        return this.color;
    }

    /**
     * Set the value of the boolean color.
     * @param color The boolean used to know if the user wants colors in his terminal.
     */
    public void setColor(boolean color) {
        this.color = color;
    }

    /**
     * Initialize the board by setting a status to each square of the grid, according to their position
     * in it.
     */
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
    }

    /**
     * Return the size of the board (9).
     * @return The size of the board (9).
     */
    public int getSIZE() {
        return this.SIZE;
    }

    /**
     * Return the grid of the board.
     * @return The grid of the board.
     */
    public Square[][] getGrid() {
        return this.grid;
    }

    /**
     * Return the total size of the board (with fences and pawns) (17)
     * @return Return the total size of the board (17).
     */
    public int getTotalSize() {
        return this.SIZE * 2 - 1;
    }

    /**
     * Return true if the number given in parameters is an even number, false otherwise.
     * @param  x The number we want to know if it is an even number.
     * @return   True if it is an even number.
     */
    public boolean isEvenNumber(int x) {
        return (x % 2) == 0;
    }

    /**
     * Return true if the number given in parameters is an odd number, false otherwise.
     * @param  x The number we want to know if it is an odd number.
     * @return   True if it is an odd number.
     */
    public boolean isOddNumber(int x) {
        return (x % 2) != 0;
    }

    /**
     * Transform the coordinate of a pawn from the normal size (9) to the total size (17).
     * @param  x The number we want to transform.
     * @return   Return the number in the total size.
     */
    public int pawnCoord(int x) {
        return x * 2;
    }

    /**
     * Transform the coordinate of a fence from the normal size (9) to the total size (17).
     * @param  x The number we want to transform.
     * @return   Return the number in the total size.
     */
    public int fenceCoord(int x) {
        return (x * 2) + 1;
    }

    /**
     * Set a fence with the specified caracteristics given in parameters.
     * @param x      The coordinate x of the fence we want to set.
     * @param y      The coordinate y of the fence we want to set.
     * @param dir    The direction of the fence we want to set.
     * @param player The player of the fence we want to set.
     */
    public void setFence(int x, int y, String dir, Player player) {
        Status status = null;

        if (player.getCurrentSquare().isPawn1()) {
            status = Status.FENCEPAWN1;
        } else if (player.getCurrentSquare().isPawn2()) {
            status = Status.FENCEPAWN2;
        }

        this.grid[x][y].setStatus(status);

        if (dir.equalsIgnoreCase("h")) {
            this.grid[x][y - 1].setStatus(status);
            this.grid[x][y + 1].setStatus(status);
        } else if (dir.equalsIgnoreCase("v")) {
            this.grid[x - 1][y].setStatus(status);
            this.grid[x + 1][y].setStatus(status);
        }
    }

    /**
     * Remove the fence with the specified caracteristics given in parameters.
     * @param x   The coordinate x of the fence we want to remove.
     * @param y   The coordinate y of the fence we want to remove.
     * @param dir The direction of the fence we want to remove.
     */
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

    /**
     * Return true if the square given in parameter can be set as a fence for the
     * player given in parameter.
     * @param  square The square for which we want to check.
     * @param  player The player for which we want to check.
     * @return        Return true if the square is possible.
     */
    public boolean possibleFence(Square square, Player player) {
        boolean ret = false;
        int x = square.getX();
        int y = square.getY();
        Square squareFence;

        // Horizontal fence
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

        // Vertical fence
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

    /**
     * Return an array of objects which correspond to the different possibilities
     * where the player given in parameters can place a fence.
     * @param  player The player for who we want to know the possibilities.
     * @return        An array of objects which are the possibilities of fence.
     */
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

    /**
     * Return an ArrayList of squares on the possibilities where the player given in parameters
     * can move his pawn.
     * @param  player The player for who we want to know the possibilities.
     * @return        An ArrayList of squares which are the possibilities of pawn.
     */
    public ArrayList<Square> listOfPossibilitiesPawn(Player player) {
        ArrayList<Square> listOfPossibilities = new ArrayList<Square>();

        int x = player.getCurrentSquare().getX();
        int y = player.getCurrentSquare().getY();

        // Verify the positions at the top.
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

        // Verify the positions at the bottom.
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

        // Verify the positions at the left.
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

        // Verify the positions at the right.
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

    /**
     * Return a string which contain the board.
     * The display is different according if the user wants colors in his terminal or not.
     * @return A string which allow to display the board.
     */
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
}
