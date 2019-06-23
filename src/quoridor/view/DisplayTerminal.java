package quoridor.view;

import quoridor.model.Board;
import quoridor.model.Game;
import quoridor.model.Player;
import quoridor.model.Square;

import java.util.ArrayList;

public class DisplayTerminal {

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_GREY = "\u001B[30m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_WHITE = "\u001B[37m";

    public DisplayTerminal() {
    }

    public void displayEndOfGame(Player winnerPlayer) {
        System.out.println("Félicitations ! Le joueur " + winnerPlayer.getName() + " a gagné la partie !");
    }

    public void displayGame(Game game) {
        System.out.println(game);
    }

    public void displayListOfPossibilitiesPawn(ArrayList<Square> listOfPossibilities) {
        System.out.print("Vous pouvez jouer un pion sur les cases : ");
        for (Square s : listOfPossibilities) {
            System.out.print(s.getX() / 2 + ", " + s.getY() / 2 + " | ");
        }
        System.out.println();
    }

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
