package quoridor.model;

// import project
//import quoridor.model.Pawn;

// import java

import quoridor.view.BoardGUI;

import java.io.Serializable;

public class HumanPlayer extends Player implements Serializable {

    private static final long serialVersionUID = 52698554L;

    /**
     * HumanPlayer constructor
     *
     * @param name
     * @author
     */
    public HumanPlayer(Game game, String name, Board board, int initX, int initY, boolean terminal) {
        super(game, name, board, initX, initY, terminal);
    }

    /**
     * Let the player choose between if he wants to play a fence or moves its pawn
     *
     * @author
     */
    public void play() {
        // if(this.terminal) {
        //     if(this.nbFences > 0) {
        //         String mode = this.askMode();
        //
        //         if (mode.equalsIgnoreCase("pawn")) {
        //             this.board.displayForPawn();
        //             this.playPawn();
        //         }
        //
        //         else if (mode.equalsIgnoreCase("fence")) {
        //             this.board.displayForFence();
        //             this.playFence();
        //             System.out.println("Il vous reste " + super.nbFences + " barrières !");
        //         }
        //         else if (mode.equalsIgnoreCase("save")) {
        //         	throw new SaveGameException("");
        //         }
        //     }
        //     else {
        //         System.out.println("Vous n'avez plus de murs disponibles !");
        //
        //         this.board.displayForPawn();
        //         this.playPawn();
        //     }
        // }
    }

    public boolean play(Square square, BoardGUI boardGUI) {
        boolean ret = false;

        if (!this.terminal) {
            if (square.isPawn()) {
                ret = this.playPawn(square);
                // System.out.println("playPawn : " + ret);
                if (ret) {
                    // System.out.println("HumanPlayer - play() isPawn -> nextPlayerGUI");
                    this.game.setActualPlayer();
                }
            } else if (square.isFence()) {
                ret = this.playFence(square, boardGUI);
                // System.out.println("playFence : " + ret);
                if (ret) {
                    // System.out.println("HumanPlayer - play() isFence -> nextPlayerGUI");
                    this.game.setActualPlayer();
                }
            }
        }
        return ret;
    }


    private boolean playPawn(Square square) {
        boolean ret = false;

        if (this.board.listOfPossibilitiesPawn(this).contains(square)) {
            super.movePawn(square.getX(), square.getY());
            // this.game.getActualPlayer().playPawn(square);
            ret = true;
        }

        return ret;
    }

    private boolean playFence(Square square, BoardGUI boardGUI) {
        boolean ret = false;

        if (this.getNbRestingFences() > 0) {
            int x = square.getX();
            int y = square.getY();
            Square squareFence;
            // Barrière horizontale
            if (x % 2 != 0 && y % 2 == 0) {
                try {
                    if (y != boardGUI.getSquares().length - 1) squareFence = this.board.getGrid()[x][y + 1];
                    else squareFence = this.board.getGrid()[x][y - 1];

                    if (this.checkFencePossible(squareFence, "h")) {
                        this.board.setFence(squareFence.getX(), squareFence.getY(), "h", this);
                        this.setNbFences(this.nbFences - 1);
                        ret = true;
                    }

                } catch (Exception ignored) {
                }
            }
            // Barrière verticale
            else if (x % 2 == 0 && y % 2 != 0) {
                try {
                    if (y != boardGUI.getSquares().length - 1) squareFence = this.board.getGrid()[x + 1][y];
                    else squareFence = this.board.getGrid()[x - 1][y];

                    if (this.checkFencePossible(squareFence, "v")) {
                        this.board.setFence(squareFence.getX(), squareFence.getY(), "v", this);
                        this.setNbFences(this.nbFences - 1);
                        ret = true;
                    }
                } catch (Exception ignored) {
                }
            }
            // if (ret) this.board.removeFence(squareFence);
        }
        return ret;

        // TODO - Réactualiser le nombre de barrières en ayant accès à GameGUI

        // // TODO - implement HumanPlayer.playFence
        // this.listOfOldPositions.clear();
        //
        // Square currentSquare = this.getCurrentSquare();
        //
        // while (!this.checkFencePossible(this.board.getGrid()[this.board.fenceCoord(x)][this.board.fenceCoord(y)], dir)
        //     || (!this.checkExistingPath(this.game.getPlayer1(), x, y, dir)) || (!this.checkExistingPath(this.game.getPlayer2(), x, y, dir))) {
        //     System.out.println("Vous ne pouvez pas jouer sur cette case. \n"
        //                         + "Veuillez en choisir une autre !");
        //
        //     x = this.askX(this.board.getSIZE() - 1);
        //     y = this.askY(this.board.getSIZE() - 1);
        //     dir = this.askDir();
        //
        //     this.setCurrentSquare(currentSquare);
        // }
        //
        // this.setCurrentSquare(currentSquare);
        //
        // this.board.setFence(this.board.fenceCoord(x), this.board.fenceCoord(y), dir, this);
        // this.setNbFences(this.nbFences - 1);

    }

    public Square randomSquare() {
        return null;
    }

    // private String askMode() {
    // 	System.out.println("Quelle pièce voulez-vous jouer ? \n"
    // 		+ "Pion (p) ou Mur (m) | Arrêter et sauvegarder la partie (s)");
    //
    // 	String ret = null;
    // 	this.scan = new Scanner(System.in);
    // 	String s = this.scan.nextLine();
    //
    //
    // 	while ((!s.equalsIgnoreCase("p")) && (!s.equalsIgnoreCase("pion")) &&
    // 		(!s.equalsIgnoreCase("mur")) && (!s.equalsIgnoreCase("m")) &&
    // 		((!s.equalsIgnoreCase("s")) && (!s.equalsIgnoreCase("save")))) {
    // 			System.out.println("La chaîne de caractères est incorrecte !\n"
    // 				+ "Veuillez écrire la pièce que vous voulez jouer : Pion (p) ou Mur (m) | Arrêter et sauvegarder la partie (s)");
    // 			s = this.scan.nextLine();
    // 	}
    //
    // 	if ((s.equalsIgnoreCase("p")) || (s.equalsIgnoreCase("pion"))) {
    // 		ret = "pawn";
    // 	}
    // 	else if ((s.equalsIgnoreCase("m")) || (s.equalsIgnoreCase("mur"))) {
    // 		ret = "fence";
    // 	}
    // 	else if ((s.equalsIgnoreCase("s")) || (s.equalsIgnoreCase("save"))) {
    // 		ret = "save";
    // 	}
    //
    // 	return ret;
    // }

    // Méthode pour Pawn pour l'instant ...
    // private int askX(int maxSize) {
    // 	int x = 0;
    //
    // 	System.out.print("\nCoordonnée x : ");
    //
    // 	boolean isNumeric = false;
    // 	while (!isNumeric) {
    // 		try {
    // 			x = this.scan.nextInt();
    // 			isNumeric = true;
    // 		} catch (java.util.InputMismatchException e) {
    // 			System.out.println("La chaîne de caractère est incorrecte !");
    // 			this.scan.nextLine();
    // 			System.out.print("Coordonnée x : ");
    // 		}
    // 	}
    //
    // 	while ((x < 0) || (x >= maxSize)) {
    // 		System.out.println("La coordonnée n'est pas valide. Veuillez en saisir une nouvelle !");
    // 		System.out.print("\nCoordonnée x : ");
    // 		isNumeric = false;
    // 		while (!isNumeric) {
    // 			try {
    // 				x = this.scan.nextInt();
    // 				isNumeric = true;
    // 			} catch (java.util.InputMismatchException e) {
    // 				System.out.println("La chaîne de caractère est incorrecte !");
    // 				this.scan.nextLine();
    // 				System.out.print("Coordonnée x : ");
    // 			}
    // 		}
    // 	}
    //
    // 	return x;
    // }
    //
    // // Méthode pour Pawn pour l'instant ...
    // private int askY(int maxSize) {
    // 	int y = 0;
    //
    // 	System.out.print("Coordonnée y : ");
    // 	boolean isNumeric = false;
    // 	while (!isNumeric) {
    // 		try {
    // 			y = this.scan.nextInt();
    // 			isNumeric = true;
    // 		} catch (java.util.InputMismatchException e) {
    // 			System.out.println("La chaîne de caractère est incorrecte !");
    // 			this.scan.nextLine();
    // 			System.out.print("Coordonnée y : ");
    // 		}
    // 	}
    //
    // 	while((y < 0) || (y >= maxSize)) {
    // 		System.out.println("La coordonnée n'est pas valide. Veuillez en saisir une nouvelle !");
    // 		System.out.print("\nCoordonnée y : ");
    // 		isNumeric = false;
    // 		while (!isNumeric) {
    // 			try {
    // 				y = this.scan.nextInt();
    // 				isNumeric = true;
    // 			} catch (java.util.InputMismatchException e) {
    // 				System.out.println("La chaîne de caractère est incorrecte !");
    // 				this.scan.nextLine();
    // 				System.out.print("Coordonnée y : ");
    // 			}
    // 		}
    // 	}
    //
    // 	return y;
    // }
    //
    // private String askDir() {
    // 	String ret = null;
    //
    // 	System.out.println("Dans quelle direction voulez-vous mettre le mur ? \n"
    // 		+"'H' (Horizontal) ou 'V' (Vertical)");
    //
    // 	this.scan = new Scanner(System.in);
    // 	ret = this.scan.nextLine();
    //
    // 	while ((!ret.equalsIgnoreCase("h")) && (!ret.equalsIgnoreCase("horizontal")) &&
    // 		(!ret.equalsIgnoreCase("v")) && (!ret.equalsIgnoreCase("vertical"))) {
    // 			System.out.println("La chaîne de caractères est incorrecte !\n"
    // 				+ "Veuillez écrire la direction du mur que vous voulez jouer : \n"
    // 				+ "'H' (Horizontal) ou 'V' (Vertical)");
    // 			ret = this.scan.nextLine();
    // 	}
    //
    // 	if ((ret.equalsIgnoreCase("h")) || (ret.equalsIgnoreCase("horizontal"))) {
    // 		ret = "h";
    // 	}
    // 	else {
    // 		ret = "v";
    // 	}
    //
    // 	return ret;
    // }

}
