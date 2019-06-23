package quoridor.view;

/** This will allow us to use elements of the class MouseEcouteur.
*/
import quoridor.controller.MouseEcouteur;

/** This will allow us to use elements of the class SquareEcouteur.
*/
import quoridor.controller.SquareEcouteur;

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

/** This will allow us to use elements of the package swing.
*/
import javax.swing.*;

/** This will allow us to use elements of the package awt.
*/
import java.awt.*;

/** This will allow us to create an ArrayList.
*/
import java.util.ArrayList;

/**
 * Class representing a board of the graphical interface.
 * It is a JPanel which contains all the elements of a board.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class BoardGUI extends JPanel {

    /** The grid of the board, which is an array of an array of JButton.
    */
    private final JButton[][] squares;

    /** The game it belongs to.
    */
    private final Game game;

    /** The board it corresponds to.
    */
    private final Board board;

    /** The gameGUI it belongs to.
    */
    private final GameGUI gameGUI;

    /** The icon of the first player.
    */
    private final ImageIcon player1 = new ImageIcon((new ImageIcon("../data/icons/orangePlayer.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

    /** The icon of the second player.
    */
    private final ImageIcon player2 = new ImageIcon((new ImageIcon("../data/icons/bluePlayer.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

    /** The icon of the square where the second player can move his pawn.
    */
    private final ImageIcon orangePlayerPossible = new ImageIcon((new ImageIcon("../data/icons/orangePlayerPossible.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

    /** The icon of the square where the first player can move his pawn.
    */
    private final ImageIcon bluePlayerPossible = new ImageIcon((new ImageIcon("../data/icons/bluePlayerPossible.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

    /** The icon of the fence of the second player.
    */
    private final ImageIcon fenceOrange = new ImageIcon((new ImageIcon("../data/icons/fenceOrange.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

    /** The icon of the fence of the first player.
    */
    private final ImageIcon fenceBlue = new ImageIcon((new ImageIcon("../data/icons/fenceBlue.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

    /**
     * Constructor of a board of the graphical interface.
     * @param gameGUI The game of the graphical interface it belongs to.
     * @param game    The game it belongs to.
     * @param board   The board it corresponds to.
     */
    public BoardGUI(GameGUI gameGUI, Game game, Board board) {
        this.game = game;
        this.board = board;
        this.gameGUI = gameGUI;

        this.squares = new JButton[this.board.getTotalSize()][this.board.getTotalSize()];

        this.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 0));
        super.setPreferredSize(new Dimension(450, 441));

        // COLORS
        Color colorRed = new Color(149, 26, 0);
        this.setBackground(colorRed);

        SquareEcouteur sqEcouteur = new SquareEcouteur(this);
        MouseEcouteur moEcouteur = new MouseEcouteur(this);


        for (int i = 0; i < this.board.getTotalSize(); i++) {
            for (int j = 0; j < this.board.getTotalSize(); j++) {
                this.squares[i][j] = new JButton();
                if (i % 2 == 0 && j % 2 != 0) {
                    this.squares[i][j].setBackground(colorRed);
                    this.squares[i][j].setPreferredSize(new Dimension(12, 35));
                } else if (i % 2 != 0) {
                    this.squares[i][j].setBackground(colorRed);
                    if (j % 2 == 0) squares[i][j].setPreferredSize(new Dimension(35, 12));
                    else {
                        squares[i][j].setPreferredSize(new Dimension(12, 12));
                        squares[i][j].setBorderPainted(false);
                        squares[i][j].setFocusPainted(false);
                    }
                } else {
                    Color colorBlack = Color.BLACK;
                    this.squares[i][j].setBackground(colorBlack);
                    this.squares[i][j].setPreferredSize(new Dimension(35, 35));
                }
                this.squares[i][j].setBorderPainted(false);
                this.add(squares[i][j]);
                this.squares[i][j].addActionListener(sqEcouteur);
                this.squares[i][j].addMouseListener(moEcouteur);
            }
        }
    }

    /**
     * Return the game of the graphical interface it belongs to.
     * @return The game of the graphical interface it belongs to.
     */
    public GameGUI getGameGUI() {
        return this.gameGUI;
    }

    /**
     * Add for each square two listeners : MouseEcouteur which is used to update the board
     * according to the focus of the mouse and SquareEcouteur which is used to update the
     * board according to where the player decides to play.
     */
    public void addAllListeners() {
        SquareEcouteur sqEcouteur = new SquareEcouteur(this);
        MouseEcouteur moEcouteur = new MouseEcouteur(this);

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                this.squares[i][j].addActionListener(sqEcouteur);
                this.squares[i][j].addMouseListener(moEcouteur);
            }
        }
    }

    /**
     * Return the game it belongs to.
     * @return The game it belongs to.
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Return the board it corresponds to.
     * @return The board it corresponds to.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Modify the icons of the squares where the player given in parameter can play so
     * that it is very easy to see the possibilities.
     * @param pos    The list of possibilities where the player can move his pawn.
     * @param player The player which will have its possibilities displayed.
     */
    public void addTmpPossibilities(ArrayList<Square> pos, Player player) {
        for (Square s : pos) {
            if (player == this.game.getPlayer1()) this.squares[s.getX()][s.getY()].setIcon(bluePlayerPossible);
            else this.squares[s.getX()][s.getY()].setIcon(orangePlayerPossible);
        }
    }

    /**
     * Return the coordinate x of the specified button.
     * @param  jB The button for which we want to have the coordinate x.
     * @return    The coordinate x of the specified button.
     */
    public int getX(JButton jB) {
        for (int i = 0; i < this.squares.length; i++) {
            for (int j = 0; j < this.squares.length; j++) {
                if (this.squares[i][j] == jB) return i;
            }
        }
        return -1;
    }

    /**
     * Return the coordinate y of the specified button.
     * @param  jB The button for which we want to have the coordinate y.
     * @return    The coordinate y of the specified button.
     */
    public int getY(JButton jB) {
        for (JButton[] square : this.squares) {
            for (int j = 0; j < this.squares.length; j++) {
                if (square[j] == jB) return j;
            }
        }
        return -1;
    }

    /**
     * Return the square of the board corresponding to the button of the board
     * of the graphical interface.
     * @param  button The button for which we want to have the square.
     * @return        The square of the board of the specified button.
     */
    public Square getSquare(JButton button) {
        for (int x = 0; x < this.board.getTotalSize(); x++) {
            for (int y = 0; y < this.board.getTotalSize(); y++) {
                if (this.squares[x][y] == button) return this.board.getGrid()[x][y];
            }
        }
        return null;
    }

    /**
     * Return the grid of buttons of the graphical interface's board.
     * @return The grid of buttons of the graphical interface's board.
     */
    public JButton[][] getSquares() {
        return this.squares;
    }

    /**
     * Display the board to the graphical interface.
     */
    public void displayBoardGUI() {
        Square temp;
        for (int x = 0; x < this.board.getTotalSize(); x++) {
            for (int y = 0; y < this.board.getTotalSize(); y++) {
                temp = this.board.getGrid()[x][y];

                if (temp.isPawn()) {
                    if (temp.isPawn1()) {
                        this.squares[x][y].setIcon(player2);
                    } else if (temp.isPawn2()) {
                        this.squares[x][y].setIcon(player1);
                    } else this.squares[x][y].setIcon(null);
                } else if (temp.isFence()) {
                    if (temp.isFencePossible()) {
                        this.squares[x][y].setIcon(null);
                    } else if (temp.isFencePawn1()) {
                        this.squares[x][y].setIcon(fenceBlue);
                    } else if (temp.isFencePawn2()) {
                        this.squares[x][y].setIcon(fenceOrange);
                    }
                }
            }
        }
    }
}
