package controller;
import java.awt.event.*;
import java.awt.*;

public class MouseEvent implements MouseListener {
    BackgroundFrameImage bgfi;

    public MouseEvent(BackgroundFrameImage bgfi) {
        this.bgfi = bgfi;
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println(x+", "+y);
    }
}