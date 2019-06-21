package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import controller.MouseButton;
import controller.SquareEcouteur;
import java.awt.event.*;

import quoridor.*;

import java.util.ArrayList;

public class BoardGUI extends JPanel {
    JButton[][] squares = new JButton[17][17];

    // COLORS
    Color colorRed = new Color(149, 26, 0);
    Color colorWhite = Color.WHITE;
    Color colorBlack = Color.BLACK;

    Game game;
    Board board;

    // background : #C3C394 (195, 195, 148)
    // orange (top pawn) : #C57600 (197, 118, 0)
    // blue (bottom pawn) : #1F6398 (31, 99, 152)


    ImageIcon player1 = new ImageIcon((new ImageIcon("../data/icons/orangePlayer.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon player2 = new ImageIcon((new ImageIcon("../data/icons/bluePlayer.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon pawn_possible = new ImageIcon((new ImageIcon("../data/icons/pawn_possible.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon fenceOrange = new ImageIcon((new ImageIcon("../data/icons/fenceOrange.png")).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon fenceBlue = new ImageIcon((new ImageIcon("../data/icons/fenceBlue.png")).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));
    // private ImageIcon player1 = new ImageIcon((new ImageIcon(getClass().getResource("/data/icons/pawn_green.png"))).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    // private ImageIcon player2 = new ImageIcon((new ImageIcon(getClass().getResource("/data/icons/pawn_red.png"))).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    // private ImageIcon pawn_possible = new ImageIcon((new ImageIcon(getClass().getResource("/data/icons/pawn_possible.png"))).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    // private ImageIcon fence_red = new ImageIcon((new ImageIcon(getClass().getResource("/data/icons/fence_red2.png"))).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));
    // private ImageIcon fence_green = new ImageIcon((new ImageIcon(getClass().getResource("/data/icons/fence_green2.png"))).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));

    public BoardGUI(Game game, Board board) {
        this.game = game;
        this.board = board;
        this.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 0));
        // super.setPreferredSize(new Dimension(360, 358));
        super.setPreferredSize(new Dimension(450, 441));
        // super.setMaximumSize(new Dimension(425, 441));
        this.setBackground(colorRed);

        SquareEcouteur sqEcouteur = new SquareEcouteur(this);
        MouseButton mb = new MouseButton(this);


        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                this.squares[i][j] = new JButton();
                if (i % 2 == 0 && j % 2 != 0) {
                    this.squares[i][j].setBackground(colorRed);
                    this.squares[i][j].setPreferredSize(new Dimension(12,35));
                }
                else if (i % 2 != 0) {
                    this.squares[i][j].setBackground(colorRed);
                    if (j % 2 == 0) squares[i][j].setPreferredSize(new Dimension(35,12));
                    else {
                      squares[i][j].setPreferredSize(new Dimension(12,12));
                      squares[i][j].setEnabled(false);
                    }
                }
                else {
                    this.squares[i][j].setBackground(colorBlack);
                    this.squares[i][j].setPreferredSize(new Dimension(35,35));
                }
                this.squares[i][j].setBorderPainted(false);
                this.add(squares[i][j]);
                this.squares[i][j].addActionListener(sqEcouteur);
                this.squares[i][j].addMouseListener(mb);
            }
        }
    }

    public Game getGame() {
      return this.game;
    }

    public Board getBoard() {
        return this.board;
    }

    public void addTmpPossibilities(ArrayList<Square> pos) {
        for (Square s : pos) {
            this.squares[s.getX()][s.getY()].setIcon(pawn_possible);
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
        for (int i = 0; i < this.squares.length; i++) {
           for (int j = 0; j < this.squares.length; j++) {
                if (this.squares[i][j] == jB) return j;
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

    public void addPawn(ImageIcon im, int i, int j) {
        this.squares[i][j].setIcon(im);
    }

    public JButton[][] getSquares() {
    	return this.squares;
    }

    public void setFencesEnabled(Square square) {
      int x = square.getX();
      int y = square.getY();

			// if (x != this.getSquares().length-1) {
			// 	this.getSquares()[x][y].setEnabled(false);
			// 	this.getSquares()[x+1][y].setEnabled(false);
			// 	this.getSquares()[x+2][y].setEnabled(false);
			// } else {
			// 		this.getSquares()[x][y].setEnabled(false);
			// 		this.getSquares()[x-1][y].setEnabled(false);
			// 		this.getSquares()[x-2][y].setEnabled(false);
			// }
      //
      // if (y != this.getSquares().length-1) {
      //   this.getSquares()[x][y].setEnabled(false);
      //   this.getSquares()[x][y+1].setEnabled(false);
      //   this.getSquares()[x][y+2].setEnabled(false);
      // } else {
      //   this.getSquares()[x][y].setEnabled(false);
      //   this.getSquares()[x][y-1].setEnabled(false);
      //   this.getSquares()[x][y-2].setEnabled(false);
      // }
    }

    public void displayBoardGUI() {
        Square temp = null;
        for (int x = 0; x < this.board.getTotalSize(); x++) {
            for (int y = 0; y < this.board.getTotalSize(); y++) {
                temp = this.board.getGrid()[x][y];

                if (temp.isPawn()) {
                    if (temp.isPawn1()) {
                        this.squares[x][y].setIcon(player2);
                    } else if (temp.isPawn2()) {
                        this.squares[x][y].setIcon(player1);
                    } else this.squares[x][y].setIcon(null);
                }

                else if (temp.isFence()) {
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
