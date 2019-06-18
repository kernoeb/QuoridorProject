package controller;
import java.awt.event.*;
import java.awt.*;
import view.BackgroundFrameImage;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.border.EmptyBorder;


public class MouseBGFI implements MouseListener {
    JFrame bgfi;

    public MouseBGFI(JFrame bgfi) {
        this.bgfi = bgfi;
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x > 240 && x < 560 && y > 320 && y < 400) {
            System.out.println("Jouer !");
        }
        // System.out.println(x+", "+y);
    }
}