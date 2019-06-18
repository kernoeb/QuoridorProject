package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
// import java.awt.Color;
// import java.awt.Graphics;
 
public class Damier extends JFrame {
    private Container contents;

    private JButton[][] squares = new JButton[17][17];

    private Color colorBlack = Color.BLACK;
    private Color colorWhite = Color.WHITE;

    public static void main(String[] args) {
        Damier gui = new Damier();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Damier() {
        super("Damier");
        contents = getContentPane();
        contents.setLayout(new GridLayout(17,17));

        ButtonHandler buttonHandler = new ButtonHandler();

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                squares[i][j] = new JButton();
                if (i % 2 == 0 && j % 2 != 0) {
                    squares[i][j].setBackground(colorBlack);
                    squares[i][j].setBounds(5,5,5,5);
                    // squares[i][j].setPreferredSize(new Dimension(50,50));
                }
                else if (i % 2 != 0) {
                    squares[i][j].setBackground(colorBlack);
                    // squares[i][j].setPreferredSize(new Dimension(50,50));
                    squares[i][j].setBounds(5,5,5,5);
                }
                else squares[i][j].setBackground(colorWhite);
                contents.add(squares[i][j]);
                squares[i][j].addActionListener(buttonHandler);
            }
        }
        // squares[row][col].setIcon...
    

        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


class ButtonHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // if (e.getSource() == squares[i][j])
            // System.out.println("hey");
    }
    // private boolean isValid
}




// public class Damier extends JFrame {
//     public Damier() {
//     	JFrame f = new JFrame("Jeux de Dame en Java");
//         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         f.setSize(500, 530);
//         JPanel damier = new Checkerboard();
//         f.add(damier);
//         f.setVisible(true);
//     }

// 	public static void main(String argv[]) {
//         Runnable showMyGUI = new Runnable() {
//             public void run() {
//                 new Damier();
//             }
//         };
//         SwingUtilities.invokeLater(showMyGUI);
//     }
// }

// class Checkerboard extends JPanel {
//     public void paintComponent(Graphics g) {
//         int row;
//         int col;
//         int x,y;

//         for (row = 0;  row <= 18;  row++ ) {
//            for ( col = 0;  col <= 18;  col++ ) {
              
//                 if (row % 2 != 0 && col % 2 != 0) {
//                     x = 10*col;
//                     y = 20*row;
//                     g.setColor(Color.white);
//                     g.fillRect(x,y,10,20);
//                 } else {
//                     x = 20*col;
//                     y = 10*row;                    
//                     g.setColor(Color.black);
//                     g.fillRect(x,y,20,10);
//                 }
//            }
//         }
//     }

