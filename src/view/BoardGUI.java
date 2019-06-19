package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import controller.MouseButton;
import java.awt.event.*;

import quoridor.Board;
import quoridor.Square;

import java.util.ArrayList;

public class BoardGUI extends JPanel {
    JButton[][] squares = new JButton[17][17];

    // COLORS
    Color colorRed = new Color(149, 26, 0);
    Color colorWhite = Color.WHITE;
    Color colorBlack = Color.BLACK;

    // background : #C3C394 (195, 195, 148)
    // orange (top pawn) : #C57600 (197, 118, 0)
    // blue (bottom pawn) : #1F6398 (31, 99, 152)


    ImageIcon player1 = new ImageIcon((new ImageIcon("../data/icons/orangePlayer.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon player2 = new ImageIcon((new ImageIcon("../data/icons/bluePlayer.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon pawn_possible = new ImageIcon((new ImageIcon("../data/icons/pawn_possible.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon fence_red = new ImageIcon((new ImageIcon("../data/icons/fence_red2.png")).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));
    ImageIcon fence_green = new ImageIcon((new ImageIcon("../data/icons/fence_green2.png")).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));
    // private ImageIcon player1 = new ImageIcon((new ImageIcon(getClass().getResource("/data/icons/pawn_green.png"))).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    // private ImageIcon player2 = new ImageIcon((new ImageIcon(getClass().getResource("/data/icons/pawn_red.png"))).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    // private ImageIcon pawn_possible = new ImageIcon((new ImageIcon(getClass().getResource("/data/icons/pawn_possible.png"))).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    // private ImageIcon fence_red = new ImageIcon((new ImageIcon(getClass().getResource("/data/icons/fence_red2.png"))).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));
    // private ImageIcon fence_green = new ImageIcon((new ImageIcon(getClass().getResource("/data/icons/fence_green2.png"))).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));

    public BoardGUI() {
        super();
        this.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 0));
        // super.setPreferredSize(new Dimension(360, 358));
        super.setPreferredSize(new Dimension(425, 441));
        this.setBackground(colorRed);

        ButtonHandler buttonHandler = new ButtonHandler(this);
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
                this.squares[i][j].addActionListener(buttonHandler);
                this.squares[i][j].addMouseListener(mb);
            }
        }
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

    public JButton[][] getSquares() {
    	return this.squares;
    }

    public void displayBoardGUI(Board board) {
        Square temp = null;
        for (int x = 0; x < board.getTotalSize(); x++) {
            for (int y = 0; y < board.getTotalSize(); y++) {
                temp = board.getGrid()[x][y];

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
                        this.squares[x][y].setIcon(fence_red);
                    } else if (temp.isFencePawn2()) {
                        this.squares[x][y].setIcon(fence_green);
                    }
                }
            }
        }
    }
}



class ButtonHandler implements ActionListener {
	BoardGUI boardGUI;

	public ButtonHandler(BoardGUI boardGUI) {
		this.boardGUI = boardGUI;
	}

    public void actionPerformed(ActionEvent e) {
    	JButton source = (JButton)e.getSource();
        System.out.println("Le joueur a cliqué en : " + this.boardGUI.getX(source)/2 + ", " + this.boardGUI.getY(source)/2);
    }
    // private boolean isValid
}
