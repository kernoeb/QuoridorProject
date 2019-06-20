package view;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import controller.ActionEcouteur;
import quoridor.Quoridor;
import textured.BackgroundImage;
import textured.JTexturedButton;

public class QuoridorGUI extends JFrame {
  JPanel loadMenu;
  JPanel mainMenu;
  JPanel modeMenu;
  GameGUI gameGUI;

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
    // System.out.println(this.back.getImageSize());
    // this.setPreferredSize(this.back.getImageSize());
    this.setLayout(new FlowLayout());
  }

  public void setFontPalatino(JComponent component, int size) {    
    try {
      Font font = Font.createFont(Font.TRUETYPE_FONT, new File("../data/fonts/palab.ttf"));
      component.setFont(font.deriveFont(size * 1.0f));
    } catch (Exception e) {
      System.err.println("JTexturedButton : " + e.getMessage());
    }
  }

  public Font getFontPalatino() throws FontFormatException, IOException {
    return Font.createFont(Font.TRUETYPE_FONT, new File("../data/fonts/palab.ttf"));
  }

  private void createAndShowGUI() {
    this.setTitle("Quoridor");
    this.setMinimumSize(new Dimension(820, 850));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // this.setResizable(false);

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
    this.setFontPalatino(this.quoridorText, 70);
    this.quoridorText.setPreferredSize(new Dimension(450, 250));
    this.quoridorText.setHorizontalAlignment(JLabel.CENTER);
    this.playButton = new JTexturedButton("Jouer", "../data/images/Button.png", "../data/images/ButtonHover.png");
    this.loadButton = new JTexturedButton("Charger", "../data/images/Button.png", "../data/images/ButtonHover.png");
    this.quitButton = new JTexturedButton("Quitter", "../data/images/Button.png", "../data/images/ButtonHover.png");

    this.buttonMainMenu.setLayout(new GridLayout(3, 0, 30, 30));
    this.buttonMainMenu.add(this.playButton);
    this.buttonMainMenu.add(this.loadButton);
    this.buttonMainMenu.add(this.quitButton);
    this.buttonMainMenu.setOpaque(false);

    this.playButton.addActionListener(new ActionEcouteur(this));
    this.loadButton.addActionListener(new ActionEcouteur(this));
    this.quitButton.addActionListener(new ActionEcouteur(this));

    this.mainMenu.setLayout(new BorderLayout(30, 30));
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

    GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
    try {
      genv.registerFont(getFontPalatino());
    } catch (FontFormatException ffe) {
    } catch (IOException io) {}

    this.modeText = new JLabel("<html><head><style type='text/css'>body { font-family: Palatino Linotype; } </style></head><div align='center'>Veuillez choisir un <br> mode de jeu :</div></html>");
    this.setFontPalatino(this.modeText, 40);
    this.modeText.setPreferredSize(new Dimension(400, 300));
    this.modeText.setHorizontalAlignment(JLabel.CENTER);
    this.modeText.setForeground(Color.WHITE);

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
