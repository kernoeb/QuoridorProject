package controller;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.border.EmptyBorder;
import view.BoardGUI;


public class MouseButton implements MouseListener {
    // private Color colorRed = new Color(186, 0, 32); 
    // private Color colorRed = new Color(192, 13, 30); 
    // private Color colorRed = new Color(180, 35, 25); 
    private Color colorRed = new Color(149, 26, 0);
    // private Color colorRed = new Color(149, 26, 0);
    private Color colorWhite = Color.WHITE;
    private Color colorBlack = Color.BLACK;
    private Color colorTmp = new Color(167, 168, 170);
    private BoardGUI boardGUI;

    public MouseButton(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    } 

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {
        JButton source = (JButton)e.getSource();
        // if (this.boardGUI.getX(source) % 2 == 0 && this.boardGUI.getY(source) % 2 != 0) {
        //     try {
        //         this.boardGUI.getSquares()[]
        //     }
        // }
        // source.setBackground(Color.RED);
        source.setBackground(colorTmp);
    }

    public void mouseExited(MouseEvent e) {
        JButton source = (JButton)e.getSource();
        if (this.boardGUI.getX(source) % 2 == 0 && this.boardGUI.getY(source) % 2 != 0) {
            source.setBackground(colorRed);
        } else if (this.boardGUI.getX(source) % 2 != 0) {
            source.setBackground(colorRed);
        } else {
            // source.setBackground(colorWhite);
            source.setBackground(colorBlack);
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