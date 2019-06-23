package quoridor.model;

import quoridor.model.utilitary.Maze;
import quoridor.view.BoardGUI;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Player
 *
 * @author
 */
public abstract class Player implements Serializable {

    private static final long serialVersionUID = 52696565L;

    Board board;
    Square currentSquare;
    private String name;
    int nbFences;
    Game game;
    public ArrayList<Square> listOfOldPositions;
    boolean terminal;

    Player(Game game, String name, Board board, int initX, int initY, boolean terminal) {
        if ((game != null) && (name != null) && (board != null)) {
            this.name = name;
            this.board = board;
            this.nbFences = 10;
            this.game = game;
            this.listOfOldPositions = new ArrayList<Square>();
            this.terminal = terminal;

            if ((initX >= 0) && (initX < this.board.getSIZE()) && (initY >= 0) && (initY < this.board.getSIZE())) {
                // this.currentSquare = this.board.getGrid()[initX][initY];
                this.currentSquare = this.board.getGrid()[this.board.pawnCoord(initX)][this.board.pawnCoord(initY)];
            } else {
                System.out.println("Player : Paramètre(s) x et/ou y non valide(s). \n"
                        + "L'attribut currentSquare sera mis à null par défaut.");
                this.currentSquare = null;
            }
        } else {
            System.err.println("Player : Paramètre(s) non valide(s).");
        }
    }

    private boolean checkExistingPath(Player player, int x, int y, String dir) {
        Board tmp = this.board;
        tmp.setFence(x, y, dir, player);

        Maze maze;
        boolean ok;
        if (this.game.getPlayer1() == player) {
            maze = new Maze(tmp, 0);
            ok = maze.BFS(maze.convertToMaze(), player.getCurrentSquare().getX(),
                    player.getCurrentSquare().getY(), 0);
        } else {
            maze = new Maze(tmp, 1);
            ok = maze.BFS(maze.convertToMaze(), player.getCurrentSquare().getX(),
                    player.getCurrentSquare().getY(), this.board.getTotalSize() - 1);
        }
        if (!ok) {
            tmp.removeFence(x, y, dir);
            System.out.println("Aucun chemin possible !");
        }
        return ok;
    }

    public abstract Square randomSquare();

    public Square getCurrentSquare() {
        return this.currentSquare;
    }

    public String getName() {
        return this.name;
    }

    /**
     * @return the number of fences left playable by the player
     * @author
     */
    public int getNbRestingFences() {
        return this.nbFences;
    }

    public abstract void play();

    public abstract boolean play(Square square, BoardGUI boardGUI);

    public void movePawn(int x, int y) {
        if (this.currentSquare != null) {
            this.board.getGrid()[x][y].setStatus(this.currentSquare.getStatus());
            this.board.getGrid()[this.currentSquare.getX()][this.currentSquare.getY()].setStatus(Status.PAWNPOSSIBLE);
            this.currentSquare = this.board.getGrid()[x][y];
        } else {
            System.err.println("movePawn : L'attribut currentSquare de Player ne doit pas être null.");
        }
    }

    public void setNbFences(int nb) {
        this.nbFences = nb;
    }

    /**
     * @param square
     * @return
     */
    public boolean checkFencePossible(Square square, String dir) {
        boolean ret = false;

        if ((square != null) && (dir != null)) {
            int x = square.getX();
            int y = square.getY();

            if (dir.equalsIgnoreCase("h") || dir.equalsIgnoreCase("horizontal")) {
                ret = square.isFencePossible() && this.board.getGrid()[x][y - 1].isFencePossible()
                        && this.board.getGrid()[x][y + 1].isFencePossible();
            } else if (dir.equalsIgnoreCase("v") || dir.equalsIgnoreCase("vertical")) {
                ret = square.isFencePossible() && this.board.getGrid()[x - 1][y].isFencePossible()
                        && this.board.getGrid()[x + 1][y].isFencePossible();
            }

            ret = ret && this.checkExistingPath(this.game.getPlayer1(), x, y, dir) && this.checkExistingPath(this.game.getPlayer2(), x, y, dir);
        } else {
            System.err.println("checkFencePossible : Paramètre(s) non valide(s).");
        }

        return ret;
    }
}
