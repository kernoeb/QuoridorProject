package quoridor.controller;

import quoridor.model.Mode;
import quoridor.view.GameGUI;
import quoridor.view.QuoridorGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ActionEcouteur implements ActionListener {

    private final QuoridorGUI quoridorGUI;
    // boolean inGame;

    public ActionEcouteur(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        // this.inGame = false;
    }

    public void actionPerformed(ActionEvent e) {
        JComponent component = (JComponent) e.getSource();

        if (component == this.quoridorGUI.getButtonPlay()) {
            this.launchMenuMode(this.quoridorGUI.getMenuMain());
        } else if (component == this.quoridorGUI.getButtonLoad()) {
            this.quoridorGUI.getFileChooser().setSelectedFile(new File(""));
            this.launchMenuLoad();
        } else if (component == this.quoridorGUI.getButtonQuit()) {
            System.exit(0);
        } else if (component == this.quoridorGUI.getFileChooser()) {
            JFileChooser fileChooser = (JFileChooser) e.getSource();
            String command = e.getActionCommand();

            if (command.equals(JFileChooser.APPROVE_SELECTION)) {
                String fileChoosed = fileChooser.getSelectedFile().getAbsolutePath();
                this.launchOldGame(fileChoosed);
            } else if (command.equals(JFileChooser.CANCEL_OPTION)) {
            }
        } else if (component == this.quoridorGUI.getButtonModeHH()) {
            this.launchGameGUI(Mode.HH);
        } else if (component == this.quoridorGUI.getButtonModeHA()) {
            this.launchGameGUI(Mode.HA);
        } else if (component == this.quoridorGUI.getButtonLoadCross()) {
            this.launchMenuMain(this.quoridorGUI.getMenuLoad());
        } else if (component == this.quoridorGUI.getButtonModeCross()) {
            this.launchMenuMain(this.quoridorGUI.getMenuMode());
        } else if (component == this.quoridorGUI.getGameGUI().getButtonPause()) {
            this.launchMenuPause();
        } else if (component == this.quoridorGUI.getButtonResume()) {
            this.resumeGame();
        } else if (component == this.quoridorGUI.getButtonRestart()) {
            this.restartGame(this.quoridorGUI.getMenuPause());
        } else if (component == this.quoridorGUI.getButtonSaveAndQuit()) {
            this.saveAndQuitGame();
        } else if (component == this.quoridorGUI.getButtonRestartEnd()) {
            this.restartGame(this.quoridorGUI.getMenuEnd());
        } else if (component == this.quoridorGUI.getButtonMenuBack()) {
            this.menuBack();
        }
    }

    private void launchMenuLoad() {
        // Ne marche pas car cela enlève l'élément BackgroundImage dans le nouveau layout de la frame
        // En gros c'est comme si l'élément n'était plus accessible ...
        // Essayer d'ajouter tous les éléments dans le JPanel BackgroundImage
        //this.quoridorGUI.getContentPane().remove(this.quoridorGUI.getBackgroundImage());
        this.quoridorGUI.setBackgroundImage("../data/images/MenuBackground2.png");

        this.quoridorGUI.remove(this.quoridorGUI.getMenuMain());
        this.quoridorGUI.add(this.quoridorGUI.getMenuLoad());
        this.quoridorGUI.getMenuLoad().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    private void launchMenuMode(JPanel panel) {
        this.quoridorGUI.setBackgroundImage("../data/images/MenuBackground2.png");

        this.quoridorGUI.remove(panel);
        this.quoridorGUI.add(this.quoridorGUI.getMenuMode());

        this.quoridorGUI.getMenuMode().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    private void launchMenuMain(JPanel panel) {
        this.quoridorGUI.setBackgroundImage("../data/images/MenuBackground.png");

        this.quoridorGUI.remove(panel);
        this.quoridorGUI.add(this.quoridorGUI.getMenuMain());

        this.quoridorGUI.getMenuMain().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    private void launchGameGUI(Mode mode) {
        this.quoridorGUI.setBackgroundImage("../data/images/GameBoard.png");

        this.quoridorGUI.remove(this.quoridorGUI.getMenuMode());
        this.quoridorGUI.setQuoridor(new Quoridor(mode, "Red user", "Green user", false));
        this.quoridorGUI.setGameGUI(new GameGUI(this.quoridorGUI));
        this.quoridorGUI.getQuoridor().runAutoPlayer(this.quoridorGUI);
        this.quoridorGUI.add(this.quoridorGUI.getGameGUI());

        this.quoridorGUI.setFocusableWindowState(true);
        this.quoridorGUI.getGameGUI().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    private void launchMenuPause() {
        this.quoridorGUI.setBackgroundImage("../data/images/MenuBackground2.png");

        this.quoridorGUI.remove(this.quoridorGUI.getGameGUI());
        this.quoridorGUI.add(this.quoridorGUI.getMenuPause());

        // this.quoridorGUI.setFocusableWindowState(true);
        this.quoridorGUI.getMenuPause().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    private void launchOldGame(String fileChoosed) {
        this.quoridorGUI.setBackgroundImage("../data/images/GameBoard.png");

        this.quoridorGUI.remove(this.quoridorGUI.getMenuLoad());
        this.quoridorGUI.setQuoridor(new Quoridor(fileChoosed, false));
        this.quoridorGUI.setGameGUI(new GameGUI(this.quoridorGUI));
        this.quoridorGUI.getQuoridor().runAutoPlayer(this.quoridorGUI);
        this.quoridorGUI.getGameGUI().getBoardGUI().addAllListeners();
        this.quoridorGUI.add(this.quoridorGUI.getGameGUI());
        // this.inGame = true;

        this.quoridorGUI.setFocusableWindowState(true);
        this.quoridorGUI.getGameGUI().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    private void resumeGame() {
        this.quoridorGUI.setBackgroundImage("../data/images/GameBoard.png");

        this.quoridorGUI.remove(this.quoridorGUI.getMenuPause());
        this.quoridorGUI.add(this.quoridorGUI.getGameGUI());

        this.quoridorGUI.setFocusableWindowState(true);
        this.quoridorGUI.getGameGUI().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    private void restartGame(JPanel panel) {
        this.launchMenuMode(panel);
    }

    private void saveAndQuitGame() {
        this.quoridorGUI.getQuoridor().saveGame(this.quoridorGUI.getGameGUI().getGame());
        this.quoridorGUI.getFileChooser().rescanCurrentDirectory();
        // this.quoridorGUI.getFileChooser().setSelectedFile(new File(""));
        this.launchMenuMain(this.quoridorGUI.getMenuPause());
    }

    private void menuBack() {
        this.launchMenuMain(this.quoridorGUI.getMenuEnd());
    }

    // private void endOfGame() {
    //   this.quoridorGUI.setBackgroundImage("../data/images/GameBoard.png");
    //
    //   this.quoridorGUI.remove(this.quoridorGUI.getGameGUI());
    //   this.quoridorGUI.getEndText().setText("<html><head><style type='text/css'>body { font-family: Palatino Linotype; } </style></head><div align='center'>Félicitations ! Le joueur " + this.actualPlayer + " <br> a gagné la partie !</div></html>");
    //   this.quoridorGUI.add(this.quoridorGUI.getMenuEnd());
    //
    //   this.quoridorGUI.setFocusableWindowState(true);
    //   this.quoridorGUI.getGameGUI().setOpaque(false);
    //   this.quoridorGUI.revalidate();
    //   this.quoridorGUI.repaint();
    // }
}
