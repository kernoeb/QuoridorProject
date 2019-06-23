package quoridor.controller;

import quoridor.view.BoardGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseEcouteur implements MouseListener {
    private final Color colorRed = new Color(149, 26, 0);
    private final Color colorBlack = Color.BLACK;
    private final Color colorTmp = new Color(167, 168, 170);
    private final BoardGUI boardGUI;

    public MouseEcouteur(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        JButton source = (JButton) e.getSource();

        int x = this.boardGUI.getX(source);
        int y = this.boardGUI.getY(source);

        if (this.boardGUI.getGame().getActualPlayer().getNbRestingFences() > 0) {
            if (x % 2 != 0 && y % 2 == 0) {
                if (this.boardGUI.getBoard().possibleFence(this.boardGUI.getBoard().getGrid()[x][y], this.boardGUI.getGame().getActualPlayer())) {
                    try {
                        if (y != this.boardGUI.getSquares().length - 1) {
                            this.boardGUI.getSquares()[x][y].setBackground(colorTmp);
                            this.boardGUI.getSquares()[x][y + 1].setBackground(colorTmp);
                            this.boardGUI.getSquares()[x][y + 2].setBackground(colorTmp);
                        } else {
                            this.boardGUI.getSquares()[x][y].setBackground(colorTmp);
                            this.boardGUI.getSquares()[x][y - 1].setBackground(colorTmp);
                            this.boardGUI.getSquares()[x][y - 2].setBackground(colorTmp);
                        }
                    } catch (Exception ex) {
                        System.out.println();
                    }
                }
            } else if (x % 2 == 0 && y % 2 != 0) {
                if (this.boardGUI.getBoard().possibleFence(this.boardGUI.getBoard().getGrid()[x][y], this.boardGUI.getGame().getActualPlayer())) {
                    try {
                        if (x != this.boardGUI.getSquares().length - 1) {
                            this.boardGUI.getSquares()[x][y].setBackground(colorTmp);
                            this.boardGUI.getSquares()[x + 1][y].setBackground(colorTmp);
                            this.boardGUI.getSquares()[x + 2][y].setBackground(colorTmp);
                        } else {
                            this.boardGUI.getSquares()[x][y].setBackground(colorTmp);
                            this.boardGUI.getSquares()[x - 1][y].setBackground(colorTmp);
                            this.boardGUI.getSquares()[x - 2][y].setBackground(colorTmp);
                        }
                    } catch (Exception ex) {
                        System.out.println();
                    }
                }

            }
        }
        if (!(x % 2 != 0 && y % 2 != 0) && this.boardGUI.getBoard().getGrid()[x][y].isPawn())
            source.setBackground(colorTmp);
    }

    public void mouseExited(MouseEvent e) {
        JButton source = (JButton) e.getSource();

        int x = this.boardGUI.getX(source);
        int y = this.boardGUI.getY(source);

        if (x % 2 == 0 && y % 2 != 0) {
            source.setBackground(colorRed);
        } else if (x % 2 != 0) {
            source.setBackground(colorRed);
        } else {
            source.setBackground(colorBlack);
        }

        if (x % 2 != 0 && y % 2 == 0) {
            try {
                if (y != this.boardGUI.getSquares().length - 1) {
                    this.boardGUI.getSquares()[x][y].setBackground(colorRed);
                    this.boardGUI.getSquares()[x][y + 1].setBackground(colorRed);
                    this.boardGUI.getSquares()[x][y + 2].setBackground(colorRed);
                } else {
                    this.boardGUI.getSquares()[x][y].setBackground(colorRed);
                    this.boardGUI.getSquares()[x][y - 1].setBackground(colorRed);
                    this.boardGUI.getSquares()[x][y - 2].setBackground(colorRed);
                }
            } catch (Exception ignored) {
            }
        } else if (x % 2 == 0 && y % 2 != 0) {
            try {
                if (x != this.boardGUI.getSquares().length - 1) {
                    this.boardGUI.getSquares()[x][y].setBackground(colorRed);
                    this.boardGUI.getSquares()[x + 1][y].setBackground(colorRed);
                    this.boardGUI.getSquares()[x + 2][y].setBackground(colorRed);
                } else {
                    this.boardGUI.getSquares()[x][y].setBackground(colorRed);
                    this.boardGUI.getSquares()[x - 1][y].setBackground(colorRed);
                    this.boardGUI.getSquares()[x - 2][y].setBackground(colorRed);
                }
            } catch (Exception ignored) {
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }
}
