package quoridor.controller;

import quoridor.model.Mode;
import quoridor.view.GameGUI;
import quoridor.view.QuoridorGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * ActionEcouteur
 * Listen for button click on GUI menus
 * @author BOISNARD Noéwen
 * @author GAVIGNET Sébastien
 */
public class ActionEcouteur implements ActionListener {

    private final QuoridorGUI quoridorGUI;

    /**
     * Constructor
     * @param quoridorGUI menu GUI
     */
    public ActionEcouteur(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
    }

    /**
     * If any action performed
     * @param e ActionEvent
     */
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

    /**
     * Launch the load menu
     */
    private void launchMenuLoad() {
        this.quoridorGUI.setBackgroundImage("../data/images/MenuBackground2.png");

        this.quoridorGUI.remove(this.quoridorGUI.getMenuMain());
        this.quoridorGUI.add(this.quoridorGUI.getMenuLoad());
        this.quoridorGUI.getMenuLoad().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    /**
     * Load the mode menu
     * @param panel used JPanel and modify it
     */
    private void launchMenuMode(JPanel panel) {
        this.quoridorGUI.setBackgroundImage("../data/images/MenuBackground2.png");

        this.quoridorGUI.remove(panel);
        this.quoridorGUI.add(this.quoridorGUI.getMenuMode());

        this.quoridorGUI.getMenuMode().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    /**
     * Load the main menu
     * @param panel used JPanel and modify it
     */
    private void launchMenuMain(JPanel panel) {
        this.quoridorGUI.setBackgroundImage("../data/images/MenuBackground.png");

        this.quoridorGUI.remove(panel);
        this.quoridorGUI.add(this.quoridorGUI.getMenuMain());

        this.quoridorGUI.getMenuMain().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    /**
     * Launch the game GUI
     * @param mode Run the game with chosed mode
     */
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

    /**
     * Launch the pause menu
     * Allow user to pause the game, resume or save the game
     */
    private void launchMenuPause() {
        this.quoridorGUI.setBackgroundImage("../data/images/MenuBackground2.png");

        this.quoridorGUI.remove(this.quoridorGUI.getGameGUI());
        this.quoridorGUI.add(this.quoridorGUI.getMenuPause());

        this.quoridorGUI.getMenuPause().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    /**
     * Launch an old game from a file
     * @param fileChoosed file with save informations
     */
    private void launchOldGame(String fileChoosed) {
        this.quoridorGUI.setBackgroundImage("../data/images/GameBoard.png");

        this.quoridorGUI.remove(this.quoridorGUI.getMenuLoad());
        this.quoridorGUI.setQuoridor(new Quoridor(fileChoosed, false));
        this.quoridorGUI.setGameGUI(new GameGUI(this.quoridorGUI));
        this.quoridorGUI.getQuoridor().runAutoPlayer(this.quoridorGUI);
        this.quoridorGUI.getGameGUI().getBoardGUI().addAllListeners();
        this.quoridorGUI.add(this.quoridorGUI.getGameGUI());

        this.quoridorGUI.setFocusableWindowState(true);
        this.quoridorGUI.getGameGUI().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    /**
     * Resume the game
     */
    private void resumeGame() {
        this.quoridorGUI.setBackgroundImage("../data/images/GameBoard.png");

        this.quoridorGUI.remove(this.quoridorGUI.getMenuPause());
        this.quoridorGUI.add(this.quoridorGUI.getGameGUI());

        this.quoridorGUI.setFocusableWindowState(true);
        this.quoridorGUI.getGameGUI().setOpaque(false);
        this.quoridorGUI.revalidate();
        this.quoridorGUI.repaint();
    }

    /**
     * Restart the game
     * @param panel used JPanel to launch again the mode menu
     */
    private void restartGame(JPanel panel) {
        this.launchMenuMode(panel);
    }

    /**
     * Save the game (saveGame)
     * and return to main menu
     */
    private void saveAndQuitGame() {
        this.quoridorGUI.getQuoridor().saveGame(this.quoridorGUI.getGameGUI().getGame());
        this.quoridorGUI.getFileChooser().rescanCurrentDirectory();
        this.launchMenuMain(this.quoridorGUI.getMenuPause());
    }

    /**
     * Back to menu
     */
    private void menuBack() {
        this.launchMenuMain(this.quoridorGUI.getMenuEnd());
    }
}
