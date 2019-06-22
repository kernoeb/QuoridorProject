package controller;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.border.EmptyBorder;
import view.BoardGUI;


public class MouseEcouteur implements MouseListener {
    // private Color colorRed = new Color(186, 0, 32);
    // private Color colorRed = new Color(192, 13, 30);
    // private Color colorRed = new Color(180, 35, 25);
    private Color colorRed = new Color(149, 26, 0);
    // private Color colorRed = new Color(149, 26, 0);
    private Color colorWhite = Color.WHITE;
    private Color colorBlack = Color.BLACK;
    private Color colorTmp = new Color(167, 168, 170);
    private BoardGUI boardGUI;

    public MouseEcouteur(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {
        JButton source = (JButton)e.getSource();

        int x = this.boardGUI.getX(source);
        int y = this.boardGUI.getY(source);

        if (x % 2 != 0 && y % 2 == 0) {
            // if (this.boardGUI.getBoard().checkIfPossibilitiesFence(this.boardGUI.getBoard().getGrid()[x][y+1])) {
            if (this.boardGUI.getBoard().possibleFence(this.boardGUI.getBoard().getGrid()[x][y], this.boardGUI.getGame().getActualPlayer())) {
                try {
                    if (y != this.boardGUI.getSquares().length-1) {
                        this.boardGUI.getSquares()[x][y].setBackground(colorTmp);
                        this.boardGUI.getSquares()[x][y+1].setBackground(colorTmp);
                        this.boardGUI.getSquares()[x][y+2].setBackground(colorTmp);
                    } else {
                        this.boardGUI.getSquares()[x][y].setBackground(colorTmp);
                        this.boardGUI.getSquares()[x][y-1].setBackground(colorTmp);
                        this.boardGUI.getSquares()[x][y-2].setBackground(colorTmp);
                    }
                } catch (Exception ex) {
                    System.out.println("");
                }
            }
        } else if (x % 2 == 0 && y % 2 != 0) {
            // if (this.boardGUI.getBoard().checkIfPossibilitiesFence(this.boardGUI.getBoard().getGrid()[x+1][y])) {
            if (this.boardGUI.getBoard().possibleFence(this.boardGUI.getBoard().getGrid()[x][y], this.boardGUI.getGame().getActualPlayer())) {
                try {
                    if (x != this.boardGUI.getSquares().length-1) {
                        this.boardGUI.getSquares()[x][y].setBackground(colorTmp);
                        this.boardGUI.getSquares()[x+1][y].setBackground(colorTmp);
                        this.boardGUI.getSquares()[x+2][y].setBackground(colorTmp);
                    } else {
                        this.boardGUI.getSquares()[x][y].setBackground(colorTmp);
                        this.boardGUI.getSquares()[x-1][y].setBackground(colorTmp);
                        this.boardGUI.getSquares()[x-2][y].setBackground(colorTmp);
                    }
                } catch (Exception ex) {
                    System.out.println("");
                }
            }

        }
        // source.setBackground(Color.RED);
        if (!(x % 2 != 0 && y % 2 != 0) && this.boardGUI.getBoard().getGrid()[x][y].isPawn()) source.setBackground(colorTmp);
    }

    public void mouseExited(MouseEvent e) {
        JButton source = (JButton)e.getSource();

        int x = this.boardGUI.getX(source);
        int y = this.boardGUI.getY(source);

        if (x % 2 == 0 && y % 2 != 0) {
            source.setBackground(colorRed);
        } else if (x % 2 != 0) {
            source.setBackground(colorRed);
        } else {
            // source.setBackground(colorWhite);
            source.setBackground(colorBlack);
        }

        if (x % 2 != 0 && y % 2 == 0) {
            try {
                if (y != this.boardGUI.getSquares().length-1) {
                  this.boardGUI.getSquares()[x][y].setBackground(colorRed);
                  this.boardGUI.getSquares()[x][y+1].setBackground(colorRed);
                  this.boardGUI.getSquares()[x][y+2].setBackground(colorRed);
                } else {
                  this.boardGUI.getSquares()[x][y].setBackground(colorRed);
                  this.boardGUI.getSquares()[x][y-1].setBackground(colorRed);
                  this.boardGUI.getSquares()[x][y-2].setBackground(colorRed);
                }
            } catch (Exception ex) {}
        } else if (x % 2 == 0 && y % 2 != 0) {
            try {
                if (x != this.boardGUI.getSquares().length-1) {
                  this.boardGUI.getSquares()[x][y].setBackground(colorRed);
                  this.boardGUI.getSquares()[x+1][y].setBackground(colorRed);
                  this.boardGUI.getSquares()[x+2][y].setBackground(colorRed);
                } else {
                    this.boardGUI.getSquares()[x][y].setBackground(colorRed);
                    this.boardGUI.getSquares()[x-1][y].setBackground(colorRed);
                    this.boardGUI.getSquares()[x-2][y].setBackground(colorRed);
                }
            } catch (Exception ex) {}
        }
    }

    public void mouseClicked(MouseEvent e) {
        // int x = e.getX();
        // int y = e.getY();
        // if (x > 240 && x < 560 && y > 320 && y < 400) {
        //     System.out.println("Jouer !");
        // }
        // System.out.println(x+", "+y);
    }
}
