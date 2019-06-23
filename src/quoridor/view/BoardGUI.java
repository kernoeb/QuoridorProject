package quoridor.view;

import quoridor.controller.MouseEcouteur;
import quoridor.controller.SquareEcouteur;
import quoridor.model.Board;
import quoridor.model.Game;
import quoridor.model.Player;
import quoridor.model.Square;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class BoardGUI extends JPanel implements Serializable {

    private static final long serialVersionUID = -350671869168492924L;

    private final JButton[][] squares;

    private final Game game;
    private final Board board;
    private final GameGUI gameGUI;

    private final ImageIcon player1 = new ImageIcon((new ImageIcon("../data/icons/orangePlayer.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    private final ImageIcon player2 = new ImageIcon((new ImageIcon("../data/icons/bluePlayer.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    private final ImageIcon orangePlayerPossible = new ImageIcon((new ImageIcon("../data/icons/orangePlayerPossible.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    private final ImageIcon bluePlayerPossible = new ImageIcon((new ImageIcon("../data/icons/bluePlayerPossible.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    private final ImageIcon fenceOrange = new ImageIcon((new ImageIcon("../data/icons/fenceOrange.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
    private final ImageIcon fenceBlue = new ImageIcon((new ImageIcon("../data/icons/fenceBlue.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

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

    public GameGUI getGameGUI() {
        return this.gameGUI;
    }

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

    public Game getGame() {
        return this.game;
    }

    public Board getBoard() {
        return this.board;
    }

    public void addTmpPossibilities(ArrayList<Square> pos, Player player) {
        for (Square s : pos) {
            if (player == this.game.getPlayer1()) this.squares[s.getX()][s.getY()].setIcon(bluePlayerPossible);
            else this.squares[s.getX()][s.getY()].setIcon(orangePlayerPossible);
        }

    }

    public int getX(JButton jB) {
        for (int i = 0; i < this.squares.length; i++) {
            for (int j = 0; j < this.squares.length; j++) {
                if (this.squares[i][j] == jB) return i;
            }
        }
        return -1;
    }


    public int getY(JButton jB) {
        for (JButton[] square : this.squares) {
            for (int j = 0; j < this.squares.length; j++) {
                if (square[j] == jB) return j;
            }
        }
        return -1;
    }

    public Square getSquare(JButton button) {
        for (int x = 0; x < this.board.getTotalSize(); x++) {
            for (int y = 0; y < this.board.getTotalSize(); y++) {
                if (this.squares[x][y] == button) return this.board.getGrid()[x][y];
            }
        }
        return null;
    }

    public JButton[][] getSquares() {
        return this.squares;
    }

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
