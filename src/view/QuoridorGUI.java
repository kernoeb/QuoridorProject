package view;

import javax.swing.*;
import java.awt.*;

import controller.ActionEcouteur;
import quoridor.Quoridor;
import textured.BackgroundImage;
import textured.JTexturedButton;

public class QuoridorGUI extends JFrame {
  JPanel loadMenu;
  JPanel mainMenu;
  JPanel modeMenu;

  JPanel buttonMainMenu;
  JPanel buttonModeMenu;

  JLabel quoridorText;
  BackgroundImage back;

  JTexturedButton playButton;
  JTexturedButton loadButton;
  JTexturedButton quitButton;

  JLabel modeText;

  JTexturedButton modeHHButton;
  JTexturedButton modeHAButton;

  GameGUI gameGUI;

  Quoridor quoridor;

  public QuoridorGUI() {
    this.createAndShowGUI();
  }

  public JTexturedButton getButtonPlay() {
    return this.playButton;
  }

  public JTexturedButton getButtonLoad() {
    return this.loadButton;
  }

  public JTexturedButton getButtonQuit() {
    return this.quitButton;
  }

  public JTexturedButton getButtonModeHH() {
    return this.modeHHButton;
  }

  public JTexturedButton getButtonModeHA() {
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

  public BackgroundImage getBackgroundImage() {
    return this.back;
  }

  public void setBackgroundImage(String fileName) {
    this.back = new BackgroundImage(fileName);
    this.setContentPane(this.back);
    this.setLayout(new FlowLayout());
  }

  private void createAndShowGUI() {
    this.setTitle("Quoridor");
    this.setPreferredSize(new Dimension(800, 800));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);

    // quoridor = new Quoridor()

    this.initializeLoadMenu();
    this.initializeMainMenu();
    this.initializeModeMenu();


    this.add(this.mainMenu);

    // this.mainMenu.setVisible(true);

    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  private void initializeLoadMenu() {
    this.loadMenu = new JPanel();


  }

  private void initializeMainMenu() {
    this.mainMenu = new JPanel();
    this.buttonMainMenu = new JPanel();

    this.quoridorText = new JLabel("QUORIDOR");
    this.quoridorText.setForeground(Color.WHITE);
    this.quoridorText.setFont(new Font("Palatino Linotype", Font.BOLD, 55));
    this.quoridorText.setPreferredSize(new Dimension(300, 250));
    this.quoridorText.setHorizontalAlignment(JLabel.CENTER);
    this.playButton = new JTexturedButton("Jouer", "../data/images/Button.png", "../data/images/ButtonHover.png");
    this.loadButton = new JTexturedButton("Charger", "../data/images/Button.png", "../data/images/ButtonHover.png");
    this.quitButton = new JTexturedButton("Quitter", "../data/images/Button.png", "../data/images/ButtonHover.png");

    this.buttonMainMenu.setLayout(new GridLayout(3, 0, 50, 50));
    this.buttonMainMenu.add(this.playButton);
    this.buttonMainMenu.add(this.loadButton);
    this.buttonMainMenu.add(this.quitButton);
    this.buttonMainMenu.setOpaque(false);

    this.playButton.addActionListener(new ActionEcouteur(this));
    this.loadButton.addActionListener(new ActionEcouteur(this));
    this.quitButton.addActionListener(new ActionEcouteur(this));

    this.mainMenu.setLayout(new BorderLayout(80, 80));
    //this.mainMenu.add(Box.createRigidArea(new Dimension(50,50)), BorderLayout.PAGE_START);
    this.mainMenu.add(this.quoridorText, BorderLayout.NORTH);
    this.mainMenu.add(this.buttonMainMenu, BorderLayout.CENTER);

    this.setBackgroundImage("../data/images/MenuBackground.png");
    // this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    // this.setLayout(new FlowLayout());
    this.mainMenu.setOpaque(false);
  }

  private void initializeModeMenu() {
    this.modeMenu = new JPanel();
    this.buttonModeMenu = new JPanel();

    this.modeText = new JLabel("<html><div align='center'>Veuillez choisir un <br> mode de jeu :</div></html>");
    this.modeText.setPreferredSize(new Dimension(400, 300));
    this.modeText.setHorizontalAlignment(JLabel.CENTER);
    this.modeText.setForeground(Color.WHITE);
    this.modeText.setFont(new Font("Palatino Linotype", Font.BOLD, 40));

    this.modeHHButton = new JTexturedButton("Humain / Humain", "../data/images/Button.png", "../data/images/ButtonHover.png");
    this.modeHAButton = new JTexturedButton("Humain / IA", "../data/images/Button.png", "../data/images/ButtonHover.png");

    this.buttonModeMenu.setLayout(new GridLayout(2, 0, 50, 50));
    this.buttonModeMenu.add(this.modeHHButton);
    this.buttonModeMenu.add(this.modeHAButton);
    this.buttonModeMenu.setOpaque(false);

    this.modeMenu.setLayout(new BorderLayout(50, 50));
    this.modeMenu.add(this.modeText, BorderLayout.NORTH);
    this.modeMenu.add(this.buttonModeMenu, BorderLayout.CENTER);

    this.modeHHButton.addActionListener(new ActionEcouteur(this));
    this.modeHAButton.addActionListener(new ActionEcouteur(this));
  }
}
