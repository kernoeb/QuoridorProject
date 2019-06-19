package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import controller.MouseButton;
import java.awt.event.*;


import quoridor.Board;
import quoridor.Square;

import java.util.ArrayList;
// import java.awt.Color;
// import java.awt.Graphics;
 
public class Damier extends JFrame {
    private Board board;

    private Container contents;
    private JButton[][] squares = new JButton[17][17];

    // COLORS
    private Color colorRed = new Color(186, 0, 32); 
    // private Color colorRed = new Color(192, 13, 30); 
    // private Color colorRed = new Color(180, 35, 25); 
    // private Color colorRed = new Color(211, 47, 47);
    // private Color colorRed = new Color(149, 26, 0);
    private Color colorWhite = Color.WHITE;
    private Color colorBlack = Color.BLACK;

    // background : #C3C394 (195, 195, 148)
    // orange (top pawn) : #C57600 (197, 118, 0)
    // blue (bottom pawn) : #1F6398 (31, 99, 152)


    private ImageIcon greenUser = new ImageIcon((new ImageIcon("../data/icons/pawn_green.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    private ImageIcon redUser = new ImageIcon((new ImageIcon("../data/icons/pawn_red.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    private ImageIcon pawn_possible = new ImageIcon((new ImageIcon("../data/icons/pawn_possible.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
    private ImageIcon fence_red = new ImageIcon((new ImageIcon("../data/icons/fence_red2.png")).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));
    private ImageIcon fence_green = new ImageIcon((new ImageIcon("../data/icons/fence_green2.png")).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Damier().setVisible(true);
            }
        });
    }

    public Damier() {
        super("Damier");

        this.board = board;

        contents = getContentPane();
        contents.setLayout(new FlowLayout());
        // contents.setBackground(Color.BLACK);
        // contents.setBackground(new Color(211, 47, 47));
        contents.setBackground(colorRed);

        ButtonHandler buttonHandler = new ButtonHandler(this);
        MouseButton mb = new MouseButton(this);

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                this.squares[i][j] = new JButton();
                if (i % 2 == 0 && j % 2 != 0) {
                    this.squares[i][j].setBackground(colorRed);
                    this.squares[i][j].setPreferredSize(new Dimension(7,30));
                }
                else if (i % 2 != 0) {
                    this.squares[i][j].setBackground(colorRed);
                    if (j % 2 == 0) squares[i][j].setPreferredSize(new Dimension(30,7));
                    else squares[i][j].setPreferredSize(new Dimension(7,7));
                }
                else {
                    // this.squares[i][j].setBackground(colorWhite);
                    this.squares[i][j].setBackground(colorBlack);
                    this.squares[i][j].setPreferredSize(new Dimension(30,30));
                }
                this.squares[i][j].setBorderPainted(false); 
                contents.add(squares[i][j]);
                this.squares[i][j].addActionListener(buttonHandler);
                this.squares[i][j].addMouseListener(mb);
            }
        }
        // squares[row][col].setIcon...
    
        setSize(420, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void addTmpPossibilities(ArrayList<Square> pos) {
        for (Square s : pos) {
            this.squares[s.getX()][s.getY()].setIcon(pawn_possible);
        }

    }

    public int getX(JButton jB) {
        for (int i = 0; i < this.squares.length; i++) {
           for (int j = 0; j < this.squares.length; j++) {
                if (this.squares[i][j] == jB) {
                    return i;
                }
            }
        }
        return -1;
    }


    public int getY(JButton jB) {
        for (int i = 0; i < this.squares.length; i++) {
           for (int j = 0; j < this.squares.length; j++) {
                if (this.squares[i][j] == jB) {
                    return j;
                }
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
                        this.squares[x][y].setIcon(redUser);
                    }

                    else if (temp.isPawn2()) {
                        this.squares[x][y].setIcon(greenUser);
                    }

                    else this.squares[x][y].setIcon(null);
                }
                else if (temp.isFence()) {
                    if (temp.isFencePossible()) {
                        this.squares[x][y].setIcon(null);
                    }

                    else if (temp.isFencePawn1()) {
                        this.squares[x][y].setIcon(fence_red);
                    }

                    else if (temp.isFencePawn2()) {
                        this.squares[x][y].setIcon(fence_green);
                    }
                }
            }  
        } 
    }  
}



class ButtonHandler implements ActionListener {
	Damier damier;

	public ButtonHandler(Damier damier) {
		this.damier = damier;
	}

    public void actionPerformed(ActionEvent e) {
    	JButton source = (JButton)e.getSource();
        System.out.println("Le joueur a cliqué en : " + this.damier.getX(source)/2 + ", " + this.damier.getY(source)/2);
    }
    // private boolean isValid
}