package view;

import javax.swing.*;
import java.awt.*;

import controller.ActionEcouteur;
import quoridor.Quoridor;

public class QuoridorGUI extends JFrame {
  JPanel loadMenu;
  JPanel mainMenu;
  JPanel modeMenu;

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

    this.quoridorText = new JLabel("Quoridor");
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

    this.mainMenu.add(this.loadButton);
    this.mainMenu.add(this.playButton);
    this.mainMenu.add(this.quitButton);

    this.modeMenu.add(this.modeHHButton);
    this.modeMenu.add(this.modeHAButton);

    //this.setContentPane(new BackgroundImage("../data/images/Menu.png"));
    this.mainMenu.setOpaque(false);
    this.add(this.mainMenu);

    this.mainMenu.setVisible(true);

    this.pack();
    this.setVisible(true);
  }

  // public void addGameGUI(GameGUI gameGUI) {
  //   this.gameGUI = gameGUI;
  //   this.add(this.gameGUI);
  // }
}
