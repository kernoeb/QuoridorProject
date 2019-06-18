package view;

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.border.EmptyBorder;

import controller.*;


public class BackgroundFrameImage {

    public static void main(String[] args) {
        new BackgroundFrameImage();
    }

    public BackgroundFrameImage() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {}

                try {
                    JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("../data/images/Menu.png"))));

                    JFrame frame = new JFrame("Testing");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    frame.setContentPane(label);
                    // frame.setLayout(new BorderLayout());
                    frame.setLayout(new GridLayout(10,20));
                    JButton button = new JButton("Jouer");
                    button.setFont(new Font("Calibri", Font.PLAIN, 35));
                    button.setBackground(new Color(184, 184, 139));
                    button.setForeground(Color.white);
                    button.setUI(new StyledButtonUI());
                    // button.setMaximumSize(new Dimension(200, 50));
                    // text.setForeground(Color.WHITE);
                    // text.setHorizontalAlignment(JLabel.CENTER);

                    // frame.add(button);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);


                    frame.addMouseListener(new MouseEvent(this));


                } catch (IOException | HeadlessException exp) {
                    exp.printStackTrace();
                }
            }
        });
    }
}

class StyledButtonUI extends BasicButtonUI {

    @Override
    public void installUI (JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setBorder(new EmptyBorder(5, 15, 5, 15));
    }

    @Override
    public void paint (Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
        super.paint(g, c);
    }

    private void paintBackground (Graphics g, JComponent c, int yOffset) {
        Dimension size = c.getSize();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(c.getBackground().darker());
        g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
        g.setColor(c.getBackground());
        g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
    }
}

  // public static void main(String[] args) {
  //   javax.swing.SwingUtilities.invokeLater(new Runnable() {
  //     public void run() {
  //       new QuoridorGUI();
  //     }
  //   });
  // }

// public class BackgroundFrameImage extends JFrame {
//   JPanel loadMenu;
//   JPanel mainMenu;
//   JPanel modeMenu;

//   JLabel quoridorText;

//   JButton loadButton;
//   JButton playButton;
//   JButton quitButton;

//   JLabel modeText;

//   JButton modeHHButton;
//   JButton modeHAButton;

//   GameGUI gameGUI;

//   public BackgroundFrameImage() {
//     this.createAndShowGUI();
//   }

//   public JButton getButtonLoad() {
//     return this.loadButton;
//   }

//   public JButton getButtonPlay() {
//     return this.playButton;
//   }

//   public JButton getButtonQuit() {
//     return this.quitButton;
//   }

//   public JButton getButtonModeHH() {
//     return this.modeHHButton;
//   }

//   public JButton getButtonModeHA() {
//     return this.modeHAButton;
//   }

//   public JPanel getMenuLoad() {
//     return this.loadMenu;
//   }

//   public JPanel getMenuMain() {
//     return this.mainMenu;
//   }

//   public JPanel getMenuMode() {
//     return this.modeMenu;
//   }

//   private void createAndShowGUI() {
//     this.setTitle("Quoridor");
//     this.setPreferredSize(new Dimension(800, 800));
//     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     this.setResizable(false);

//     this.loadMenu = new JPanel();
//     this.mainMenu = new JPanel();
//     this.modeMenu = new JPanel();

//     this.quoridorText = new JLabel("Quoridor");
//     this.loadButton = new JButton("Charger une partie");
//     this.playButton = new JButton("Jouer");
//     this.quitButton = new JButton("Quitter");


//     this.modeText = new JLabel("Veuillez choisir un mode de jeu :");
//     this.modeHHButton = new JButton("Humain / Humain");
//     this.modeHAButton = new JButton("Humain / IA");

//     // this.loadButton.addActionListener(new ActionEcouteur(this));
//     // this.playButton.addActionListener(new ActionEcouteur(this));
//     // this.quitButton.addActionListener(new ActionEcouteur(this));

//     // this.modeHHButton.addActionListener(new ActionEcouteur(this));
//     // this.modeHAButton.addActionListener(new ActionEcouteur(this));


//     this.setContentPane(new BackgroundImage("../data/images/Menu.png"));
//     this.mainMenu.setOpaque(false);    
//     // this.mainMenu.setLocationRelativeTo(null);
//     this.add(this.mainMenu);

//     this.mainMenu.setVisible(true);

//     this.mainMenu.add(this.loadButton);
//     this.mainMenu.add(this.playButton);
//     this.mainMenu.add(this.quitButton);

//     this.modeMenu.add(this.modeHHButton);
//     this.modeMenu.add(this.modeHAButton);

//     this.pack();
//     this.setVisible(true);
//   }
