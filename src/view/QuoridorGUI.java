package view;

import javax.swing.*;
import java.awt.*;

import controller.ActionEcouteur;
import quoridor.Quoridor;

public class QuoridorGUI extends JFrame {
  JPanel loadMenu;
  JPanel mainMenu;
  JPanel modeMenu;

  JPanel buttonMainMenu;

  JLabel quoridorText;

  JButton loadButton;
  JButton playButton;
  JButton quitButton;

  JLabel modeText;

  JButton modeHHButton;
  JButton modeHAButton;

  GameGUI gameGUI;

  Quoridor quoridor;

  public QuoridorGUI() {
    this.createAndShowGUI();
  }

  public JButton getButtonLoad() {
    return this.loadButton;
  }

  public JButton getButtonPlay() {
    return this.playButton;
  }

  public JButton getButtonQuit() {
    return this.quitButton;
  }

  public JButton getButtonModeHH() {
    return this.modeHHButton;
  }

  public JButton getButtonModeHA() {
    return this.modeHAButton;
  }

  public JPanel getMenuLoad() {
    return this.loadMenu;
  }

  public JPanel getMenuMain() {
    return this.mainMenu;
  }

  public JPanel getMenuMode() {
    return this.modeMenu;
  }

  public GameGUI getGameGUI() {
    return this.gameGUI;
  }

  public void setGameGUI(GameGUI gameGUI) {
    this.gameGUI = gameGUI;
  }

  private void createAndShowGUI() {
    this.setTitle("Quoridor");
    this.setPreferredSize(new Dimension(800, 800));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);

    // quoridor = new Quoridor()

    this.loadMenu = new JPanel();
    this.mainMenu = new JPanel();
    this.modeMenu = new JPanel();

    this.buttonMainMenu = new JPanel();

    this.quoridorText = new JLabel("QUORIDOR");
    this.quoridorText.setForeground(Color.WHITE);
    this.quoridorText.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
    this.loadButton = new JButton("Charger une partie");
    this.playButton = new JButton("Jouer");
    this.quitButton = new JButton("Quitter");


    this.modeText = new JLabel("Veuillez choisir un mode de jeu :");
    this.modeHHButton = new JButton("Humain / Humain");
    this.modeHAButton = new JButton("Humain / IA");

    this.loadButton.addActionListener(new ActionEcouteur(this));
    this.playButton.addActionListener(new ActionEcouteur(this));
    this.quitButton.addActionListener(new ActionEcouteur(this));

    this.modeHHButton.addActionListener(new ActionEcouteur(this));
    this.modeHAButton.addActionListener(new ActionEcouteur(this));

    this.buttonMainMenu.setLayout(new GridLayout(3, 0, 50, 50));
    this.buttonMainMenu.add(this.loadButton);
    this.buttonMainMenu.add(this.playButton);
    this.buttonMainMenu.add(this.quitButton);
    this.buttonMainMenu.setOpaque(false);

    this.mainMenu.setLayout(new BorderLayout(200, 200));
    //this.mainMenu.add(Box.createRigidArea(new Dimension(50,50)), BorderLayout.PAGE_START);
    this.mainMenu.add(this.quoridorText, BorderLayout.NORTH);
    this.mainMenu.add(this.buttonMainMenu, BorderLayout.CENTER);

    this.modeMenu.add(this.modeHHButton);
    this.modeMenu.add(this.modeHAButton);

    this.setBackgroundImage("../data/images/MenuBackground.png");
    // this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.setLayout(new FlowLayout());
    this.mainMenu.setOpaque(false);
    this.add(this.mainMenu);

    this.mainMenu.setVisible(true);

    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  public void setBackgroundImage(String fileName) {
    this.setContentPane(new BackgroundImage(fileName));
  }
}
