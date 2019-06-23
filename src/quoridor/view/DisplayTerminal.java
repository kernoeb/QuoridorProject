package quoridor.view;

/** This will allow us to use elements of the class Board.
*/
import quoridor.model.Board;

/** This will allow us to use elements of the class Game.
*/
import quoridor.model.Game;

/** This will allow us to use elements of the class Player.
*/
import quoridor.model.Player;

/** This will allow us to use elements of the class Square.
*/
import quoridor.model.Square;

/** This will allow us to create an ArrayList.
*/
import java.util.ArrayList;

/**
 * Class representing a display unit for the terminal.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class DisplayTerminal {

    /** String used to reset the color of the terminal's text.
    */
    private final String ANSI_RESET = "\u001B[0m";

    /** String used to change the color of the terminal's text to grey.
    */
    private final String ANSI_GREY = "\u001B[30m";

    /** String used to change the color of the terminal's text to red.
    */
    private final String ANSI_RED = "\u001B[31m";

    /** String used to change the color of the terminal's text to green.
    */
    private final String ANSI_GREEN = "\u001B[32m";

    /** String used to change the color of the terminal's text to yellow.
    */
    private final String ANSI_YELLOW = "\u001B[33m";

    /** String used to change the color of the terminal's text to white.
    */
    private final String ANSI_WHITE = "\u001B[37m";

    /**
     * Constructor of a display unit, which effectuates nothing.
     */
    public DisplayTerminal() {
    }

    /**
     * Display the end of the game by writing the name of the winner.
     * @param winnerPlayer The player who won the game.
     */
    public void displayEndOfGame(Player winnerPlayer) {
        System.out.println("Félicitations ! Le joueur " + winnerPlayer.getName() + " a gagné la partie !");
    }

    /**
     * Display the game.
     * @param game The game we want to display.
     */
    public void displayGame(Game game) {
        System.out.println(game);
    }

    /**
     * Display the list of possibilities where the player can move his pawn.
     * @param listOfPossibilities The list of possibilities where the player can move his pawn.
     */
    public void displayListOfPossibilitiesPawn(ArrayList<Square> listOfPossibilities) {
        System.out.print("Vous pouvez jouer un pion sur les cases : ");
        for (Square s : listOfPossibilities) {
            System.out.print(s.getX() / 2 + ", " + s.getY() / 2 + " | ");
        }
        System.out.println();
    }

    /**
     * Display the board of the game when the player wants to move his pawn.
     * @param board The board we want to display.
     */
    public void displayForPawn(Board board) {
        Square temp;

        if (board.getColor()) {
            System.out.print("  ");
            for (int i = 0; i < board.getSIZE(); i++) System.out.print(ANSI_YELLOW + i + "   ");
            System.out.println();

            for (int x = 0; x < board.getTotalSize(); x++) {
                if (board.isEvenNumber(x)) {
                    System.out.print(ANSI_YELLOW + x / 2 + " ");
                } else System.out.print("  ");

                for (int y = 0; y < board.getTotalSize(); y++) {
                    temp = board.getGrid()[x][y];

                    if (temp.isPawn()) {
                        if (temp.isPawnPossible()) {
                            System.out.print(this.ANSI_WHITE);
                        } else if (temp.isPawn1()) {
                            System.out.print(this.ANSI_RED);
                        } else if (temp.isPawn2()) {
                            System.out.print(this.ANSI_GREEN);
                        }

                        System.out.print("X ");
                    } else if (temp.isFence()) {
                        if (temp.isFencePossible()) {
                            System.out.print(this.ANSI_GREY);
                        } else if (temp.isFencePawn1()) {
                            System.out.print(this.ANSI_RED);
                        } else if (temp.isFencePawn2()) {
                            System.out.print(this.ANSI_GREEN);
                        }

                        if (board.isEvenNumber(x)) {
                            System.out.print("| ");
                        } else if (board.isEvenNumber(y)) {
                            System.out.print("─ ");
                        } else {
                            System.out.print("+ ");
                        }
                    }
                }

                System.out.println(this.ANSI_RESET);
            }
        } else {
            System.out.print("  ");
            for (int i = 0; i < board.getSIZE(); i++) System.out.print(i + "   ");
            System.out.println();

            for (int x = 0; x < board.getTotalSize(); x++) {
                if (board.isEvenNumber(x)) {
                    System.out.print(x / 2 + " ");
                } else System.out.print("  ");

                for (int y = 0; y < board.getTotalSize(); y++) {
                    temp = board.getGrid()[x][y];

                    if (temp.isPawn()) {
                        if (temp.isPawnPossible()) {
                            System.out.print("  ");
                        } else if (temp.isPawn1()) {
                            System.out.print("X ");
                        } else if (temp.isPawn2()) {
                            System.out.print("O ");
                        }
                    } else if (temp.isFence()) {
                        if (temp.isFencePossible()) {
                            System.out.print("  ");
                        } else if (board.isEvenNumber(x)) {
                            System.out.print("| ");
                        } else if (board.isEvenNumber(y)) {
                            System.out.print("─ ");
                        } else {
                            System.out.print("+ ");
                        }
                    }
                }

                System.out.println();
            }
        }
    }

    /**
     * Display the board of the game when the player wants to place a fence.
     * @param board The board we want to display.
     */
    public void displayForFence(Board board) {
        Square temp;

        if (board.getColor()) {
            System.out.print("    ");
            for (int i = 0; i < board.getSIZE() - 1; i++) System.out.print(ANSI_YELLOW + i + "   ");
            System.out.println();

            for (int x = 0; x < board.getTotalSize(); x++) {
                if (board.isOddNumber(x)) {
                    System.out.print(ANSI_YELLOW + (x / 2) + " ");
                } else System.out.print("  ");

                for (int y = 0; y < board.getTotalSize(); y++) {
                    temp = board.getGrid()[x][y];

                    if (temp.isPawn()) {
                        if (temp.isPawnPossible()) {
                            System.out.print(this.ANSI_WHITE);
                        } else if (temp.isPawn1()) {
                            System.out.print(this.ANSI_RED);
                        } else if (temp.isPawn2()) {
                            System.out.print(this.ANSI_GREEN);
                        }

                        System.out.print("X ");
                    } else if (temp.isFence()) {
                        if (temp.isFencePossible()) {
                            System.out.print(this.ANSI_GREY);
                        } else if (temp.isFencePawn1()) {
                            System.out.print(this.ANSI_RED);
                        } else if (temp.isFencePawn2()) {
                            System.out.print(this.ANSI_GREEN);
                        }

                        if (board.isEvenNumber(x)) {
                            System.out.print("| ");
                        } else if (board.isEvenNumber(y)) {
                            System.out.print("─ ");
                        } else {
                            System.out.print("+ ");
                        }
                    }
                }

                System.out.println(this.ANSI_RESET);
            }
        } else {
            System.out.print("    ");
            for (int i = 0; i < board.getSIZE() - 1; i++) System.out.print(i + "   ");
            System.out.println();

            for (int x = 0; x < board.getTotalSize(); x++) {
                if (board.isOddNumber(x)) {
                    System.out.print((x / 2) + " ");
                } else System.out.print("  ");

                for (int y = 0; y < board.getTotalSize(); y++) {
                    temp = board.getGrid()[x][y];

                    if (temp.isPawn()) {
                        if (temp.isPawnPossible()) {
                            System.out.print("  ");
                        } else if (temp.isPawn1()) {
                            System.out.print("X ");
                        } else if (temp.isPawn2()) {
                            System.out.print("O ");
                        }
                    } else if (temp.isFence()) {
                        if (temp.isFencePossible()) {
                            System.out.print("  ");
                        } else if (board.isEvenNumber(x)) {
                            System.out.print("| ");
                        } else if (board.isEvenNumber(y)) {
                            System.out.print("─ ");
                        } else {
                            System.out.print("+ ");
                        }
                    }
                }

                System.out.println();
            }
        }
    }
}
