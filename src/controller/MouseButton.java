package controller;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.border.EmptyBorder;
import view.Damier;


public class MouseButton implements MouseListener {
    private Color colorRed = new Color(211, 47, 47);
    private Color colorWhite = Color.WHITE;
    private Damier damier;

    public MouseButton(Damier damier) {
        this.damier = damier;
    } 

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {
        JButton source = (JButton)e.getSource();
        source.setBackground(Color.RED);
    }

    public void mouseExited(MouseEvent e) {
        JButton source = (JButton)e.getSource();
        if (this.damier.getX(source) % 2 == 0 && this.damier.getY(source) % 2 != 0) {
            source.setBackground(colorRed);
        } else if (this.damier.getX(source) % 2 != 0) {
            source.setBackground(colorRed);
        } else {
            source.setBackground(colorWhite);
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