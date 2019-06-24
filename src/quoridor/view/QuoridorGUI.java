package quoridor.view;

/** This will allow us to use elements of the class ActionEcouteur.
*/
import quoridor.controller.ActionEcouteur;

/** This will allow us to use elements of the class Quoridor.
*/
import quoridor.controller.Quoridor;

/** This will allow us to use elements of the class BackgroundImage.
*/
import quoridor.view.textured.BackgroundImage;

/** This will allow us to use elements of the class JTexturedButton.
*/
import quoridor.view.textured.JTexturedButton;

/** This will allow us to use elements of the package swing.
*/
import javax.swing.*;

/** This will allow us to use elements of the class FileNameExtensionFilter.
*/
import javax.swing.filechooser.FileNameExtensionFilter;

/** This will allow us to use elements of the package awt.
*/
import java.awt.*;

/** This will allow us to use elements of the class File.
*/
import java.io.File;

/** This will allow us to use elements of the class IOException.
*/
import java.io.IOException;

/**
 * Class representing a quoridor graphical interface.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class QuoridorGUI extends JFrame {

    /** The menu where it is possible to load games.
    */
    private JPanel loadMenu;

    /** The principal menu.
    */
    private JPanel mainMenu;

    /** The menu where it is possible to choose the mode of the future game.
    */
    private JPanel modeMenu;

    /** The menu when the game is on pause.
    */
    private JPanel pauseMenu;

    /** The menu when it is the end of the game.
    */
    private JPanel endMenu;

    /** The game of the graphical interface.
    */
    private GameGUI gameGUI;

    /** The mainMenu's button to go to the modeMenu.
    */
    private JTexturedButton playButton;

    /** The mainMenu's button to go to the loadMenu.
    */
    private JTexturedButton loadButton;

    /** The mainMenu's button to quit the game.
    */
    private JTexturedButton quitButton;

    /** The modeMenu's button to choose to start a HH (Human vs Human) game.
    */
    private JTexturedButton modeHHButton;

    /** The modeMenu's button to choose to start a HA (Human vs Auto) game.
    */
    private JTexturedButton modeHAButton;

    /** The modeMenu's cross button to return to the mainMenu.
    */
    private JTexturedButton modeCrossButton;

    /** The loadMenu's cross button to return to the mainMenu.
    */
    private JTexturedButton loadCrossButton;

    /** The FileChooser used to load games.
    */
    private JFileChooser fileChooser;

    /** The pauseMenu's button to resume the game.
    */
    private JTexturedButton resumeButton;

    /** The pauseMenu's button to restart a game.
    */
    private JTexturedButton restartButton;

    /** The pauseMenu's button to save the game and return to the mainMenu.
    */
    private JTexturedButton saveAndQuitButton;

    /** The endMenu's text to print the winner of the game.
    */
    private JLabel endText;

    /** The endMenu's button to restart a game.
    */
    private JTexturedButton restartEndButton;

    /** The endMenu's button to return to the mainMenu.
    */
    private JTexturedButton menuBackButton;

    /** The quoridor it corresponds to.
    */
    private Quoridor quoridor;

    /**
     * Constructor of a quoridor graphical interface.
     */
    public QuoridorGUI() {
        this.createAndShowGUI();
    }

    /**
     * Return the mainMenu's button to go to the modeMenu.
     * @return The mainMenu's button to go to the modeMenu.
     */
    public JTexturedButton getButtonPlay() {
        return this.playButton;
    }

    /**
     * Return the mainMenu's button to go to the loadMenu.
     * @return The mainMenu's button to go to the loadMenu.
     */
    public JTexturedButton getButtonLoad() {
        return this.loadButton;
    }

    /**
     * Return the mainMenu's button to quit the game.
     * @return The mainMenu's button to quit the game.
     */
    public JTexturedButton getButtonQuit() {
        return this.quitButton;
    }

    /**
     * Return the modeMenu's button to choose to start a HH (Human vs Human) game.
     * @return The modeMenu's button to choose to start a HH (Human vs Human) game.
     */
    public JTexturedButton getButtonModeHH() {
        return this.modeHHButton;
    }

    /**
     * Return the modeMenu's button to choose to start a HA (Human vs Auto) game.
     * @return The modeMenu's button to choose to start a HA (Human vs Auto) game.
     */
    public JTexturedButton getButtonModeHA() {
        return this.modeHAButton;
    }

    /**
     * Return the modeMenu's cross button to return to the mainMenu.
     * @return The modeMenu's cross button to return to the mainMenu.
     */
    public JTexturedButton getButtonModeCross() {
        return this.modeCrossButton;
    }

    /**
     * Return the loadMenu's cross button to return to the mainMenu.
     * @return The loadMenu's cross button to return to the mainMenu.
     */
    public JTexturedButton getButtonLoadCross() {
        return this.loadCrossButton;
    }

    /**
     * Return the pauseMenu's button to resume the game.
     * @return The pauseMenu's button to resume the game.
     */
    public JTexturedButton getButtonResume() {
        return this.resumeButton;
    }

    /**
     * Return the pauseMenu's button to restart a game.
     * @return The pauseMenu's button to restart a game.
     */
    public JTexturedButton getButtonRestart() {
        return this.restartButton;
    }

    /**
     * Return the pauseMenu's button to save the game and return to the mainMenu.
     * @return The pauseMenu's button to save the game and return to the mainMenu.
     */
    public JTexturedButton getButtonSaveAndQuit() {
        return this.saveAndQuitButton;
    }

    /**
     * Return the endMenu's button to restart a game.
     * @return The endMenu's button to restart a game.
     */
    public JTexturedButton getButtonRestartEnd() {
        return this.restartEndButton;
    }

    /**
     * Return the endMenu's button to return to the mainMenu.
     * @return The endMenu's button to return to the mainMenu.
     */
    public JTexturedButton getButtonMenuBack() {
        return this.menuBackButton;
    }

    /**
     * Return the menu where it is possible to load games.
     * @return The menu where it is possible to load games.
     */
    public JPanel getMenuLoad() {
        return this.loadMenu;
    }

    /**
     * Return the principal menu.
     * @return The principal menu.
     */
    public JPanel getMenuMain() {
        return this.mainMenu;
    }

    /**
     * Return the menu where it is possible to choose the mode of the future game.
     * @return The menu where it is possible to choose the mode of the future game.
     */
    public JPanel getMenuMode() {
        return this.modeMenu;
    }

    /**
     * Return the menu when the game is on pause.
     * @return The menu when the game is on pause.
     */
    public JPanel getMenuPause() {
        return this.pauseMenu;
    }

    /**
     * Return the menu when it is the end of the game.
     * @return The menu when it is the end of the game.
     */
    public JPanel getMenuEnd() {
        return this.endMenu;
    }

    /**
     * Return the endMenu's text to print the winner of the game.
     * @return The endMenu's text to print the winner of the game.
     */
    public JLabel getEndText() {
        return this.endText;
    }

    /**
     * Return the FileChooser used to load games.
     * @return The FileChooser used to load games.
     */
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }

    /**
     * Return the game of the graphical interface.
     * @return The game of the graphical interface.
     */
    public GameGUI getGameGUI() {
        return this.gameGUI;
    }

    /**
     * Modify the game of the graphical interface.
     * @param gameGUI The game of the graphical interface.
     */
    public void setGameGUI(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
    }

    /**
     * Return the quoridor it corresponds to.
     * @return The quoridor it corresponds to.
     */
    public Quoridor getQuoridor() {
        return this.quoridor;
    }

    /**
     * Modify the quoridor it corresponds to.
     * @param quoridor The quoridor it corresponds to.
     */
    public void setQuoridor(Quoridor quoridor) {
        this.quoridor = quoridor;
    }

    /**
     * Modify the background image.
     * @param fileName The filename of the image.
     */
    public void setBackgroundImage(String fileName) {
        BackgroundImage back = new BackgroundImage(fileName);
        this.setContentPane(back);
        this.setLayout(new FlowLayout());
    }

    /**
     * Set the font of the component given in parameter to Palatino.
     * @param component The component for which we want to change the font.
     * @param size      The size of the font.
     */
    public void setFontPalatino(JComponent component, int size) {
        try {
            //Font font = Font.createFont(Font.TRUETYPE_FONT, new File("../data/fonts/palab.ttf"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/data/fonts/palab.ttf"));
            component.setFont(font.deriveFont(size * 1.0f));
        } catch (Exception e) {
            System.err.println("JTexturedButton : " + e.getMessage());
        }
    }

    /**
     * Return the font Palatino created.
     * @return The font Palatino created.
     * @throws FontFormatException
     * @throws IOException
     */
    private Font getFontPalatino() throws FontFormatException, IOException {
        //return Font.createFont(Font.TRUETYPE_FONT, new File("../data/fonts/palab.ttf"));
        return Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/data/fonts/palab.ttf"));
    }

    /**
     * Initialize and add all the components of the quoridor graphical interface to it by
     * calling each initialize method.
     */
    private void createAndShowGUI() {
        this.setTitle("Quoridor");
        this.setMinimumSize(new Dimension(800, 828));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.initializeLoadMenu();
        this.initializeMainMenu();
        this.initializeModeMenu();
        this.initializePauseMenu();
        this.initializeEndMenu();

        this.add(this.mainMenu);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Initialize the components of the loadMenu.
     */
    private void initializeLoadMenu() {
        this.loadMenu = new JPanel();
        this.loadMenu.setLayout(new BorderLayout());

        this.loadCrossButton = new JTexturedButton("/data/images/ArrowBack.png", "/data/images/ArrowBackHover.png");
        this.loadCrossButton.setHorizontalAlignment(SwingConstants.LEFT);
        this.loadCrossButton.addActionListener(new ActionEcouteur(this));

        //this.fileChooser = new JFileChooser(new File("../data/save/"));
        this.fileChooser = new JFileChooser(new File("save/"));
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Save files (.ser)", "ser");
        this.fileChooser.setFileFilter(fileFilter);
        this.fileChooser.addActionListener(new ActionEcouteur(this));

        this.loadMenu.add(this.loadCrossButton, BorderLayout.NORTH);
        this.loadMenu.add(this.fileChooser, BorderLayout.CENTER);
    }

    /**
     * Initialize the components of the mainMenu.
     */
    private void initializeMainMenu() {
        this.mainMenu = new JPanel();
        JPanel buttonMainMenu = new JPanel();

        JLabel quoridorText = new JLabel("QUORIDOR");
        quoridorText.setForeground(Color.WHITE);
        this.setFontPalatino(quoridorText, 70);
        quoridorText.setPreferredSize(new Dimension(450, 250));
        quoridorText.setHorizontalAlignment(JLabel.CENTER);
        this.playButton = new JTexturedButton("Jouer", "/data/images/Button.png", "/data/images/ButtonHover.png");
        this.loadButton = new JTexturedButton("Charger", "/data/images/Button.png", "/data/images/ButtonHover.png");
        this.quitButton = new JTexturedButton("Quitter", "/data/images/Button.png", "/data/images/ButtonHover.png");

        buttonMainMenu.setLayout(new GridLayout(3, 0, 30, 30));
        buttonMainMenu.add(this.playButton);
        buttonMainMenu.add(this.loadButton);
        buttonMainMenu.add(this.quitButton);
        buttonMainMenu.setOpaque(false);

        this.playButton.addActionListener(new ActionEcouteur(this));
        this.loadButton.addActionListener(new ActionEcouteur(this));
        this.quitButton.addActionListener(new ActionEcouteur(this));

        this.mainMenu.setLayout(new BorderLayout(30, 30));
        this.mainMenu.add(quoridorText, BorderLayout.NORTH);
        this.mainMenu.add(buttonMainMenu, BorderLayout.CENTER);

        this.setBackgroundImage("/data/images/MenuBackground.png");
        this.mainMenu.setOpaque(false);
    }

    /**
     * Initialize the components of the modeMenu.
     */
    private void initializeModeMenu() {
        this.modeMenu = new JPanel();
        JPanel buttonModeMenu = new JPanel();

        this.modeCrossButton = new JTexturedButton("/data/images/ArrowBack.png", "/data/images/ArrowBackHover.png");

        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            genv.registerFont(getFontPalatino());
        } catch (FontFormatException ignored) {
        } catch (IOException ignored) {
        }


        JLabel modeText = new JLabel("<html><head><style type='text/css'>body { font-family: Palatino Linotype; } </style></head><div align='center'>Veuillez choisir un <br> mode de jeu :</div></html>");
        this.setFontPalatino(modeText, 40);
        modeText.setPreferredSize(new Dimension(400, 300));
        modeText.setHorizontalAlignment(JLabel.CENTER);
        modeText.setForeground(Color.WHITE);

        this.modeHHButton = new JTexturedButton("Humain / Humain", "/data/images/Button.png", "/data/images/ButtonHover.png");
        this.modeHAButton = new JTexturedButton("Humain / IA", "/data/images/Button.png", "/data/images/ButtonHover.png");

        buttonModeMenu.setLayout(new GridLayout(2, 0, 50, 50));
        buttonModeMenu.add(this.modeHHButton);
        buttonModeMenu.add(this.modeHAButton);
        buttonModeMenu.setOpaque(false);

        this.modeMenu.setLayout(new BorderLayout(0, -30));

        JPanel crossPanel = new JPanel();
        crossPanel.setLayout(new GridLayout(0, 3, 300, 0));
        crossPanel.add(this.modeCrossButton);
        crossPanel.setOpaque(false);

        this.modeMenu.add(crossPanel, BorderLayout.NORTH);
        JPanel jP = new JPanel();
        jP.setLayout(new BorderLayout(0, 50));
        jP.setOpaque(false);
        jP.add(modeText, BorderLayout.NORTH);
        jP.add(buttonModeMenu, BorderLayout.CENTER);
        this.modeMenu.add(jP);

        this.modeHHButton.addActionListener(new ActionEcouteur(this));
        this.modeHAButton.addActionListener(new ActionEcouteur(this));
        this.modeCrossButton.addActionListener(new ActionEcouteur(this));
    }

    /**
     * Initialize the components of the pauseMenu.
     */
    private void initializePauseMenu() {
        this.pauseMenu = new JPanel();
        JPanel buttonPauseMenu = new JPanel();

        JLabel pauseText = new JLabel("Pause");
        pauseText.setForeground(Color.WHITE);
        pauseText.setPreferredSize(new Dimension(400, 235));
        this.setFontPalatino(pauseText, 40);
        pauseText.setHorizontalAlignment(JLabel.CENTER);
        this.resumeButton = new JTexturedButton("Reprendre", "/data/images/Button.png", "/data/images/ButtonHover.png");
        this.restartButton = new JTexturedButton("Recommencer", "/data/images/Button.png", "/data/images/ButtonHover.png");
        this.saveAndQuitButton = new JTexturedButton("Sauver et quitter", "/data/images/Button.png", "/data/images/ButtonHover.png");

        buttonPauseMenu.setLayout(new GridLayout(3, 0, 30, 30));
        buttonPauseMenu.add(this.resumeButton);
        buttonPauseMenu.add(this.restartButton);
        buttonPauseMenu.add(this.saveAndQuitButton);
        buttonPauseMenu.setOpaque(false);

        this.resumeButton.addActionListener(new ActionEcouteur(this));
        this.restartButton.addActionListener(new ActionEcouteur(this));
        this.saveAndQuitButton.addActionListener(new ActionEcouteur(this));

        this.pauseMenu.setLayout(new BorderLayout(30, 30));
        this.pauseMenu.add(pauseText, BorderLayout.NORTH);
        this.pauseMenu.add(buttonPauseMenu, BorderLayout.CENTER);
    }

    /**
     * Initialize the components of the endMenu.
     */
    private void initializeEndMenu() {
        this.endMenu = new JPanel();
        JPanel buttonEndMenu = new JPanel();

        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            genv.registerFont(getFontPalatino());
        } catch (FontFormatException ignored) {
        } catch (IOException ignored) {
        }


        this.endText = new JLabel();
        this.endText.setForeground(Color.WHITE);
        this.endText.setPreferredSize(new Dimension(400, 300));
        this.setFontPalatino(this.endText, 40);
        this.endText.setHorizontalAlignment(JLabel.CENTER);
        this.restartEndButton = new JTexturedButton("Recommencer", "/data/images/Button.png", "/data/images/ButtonHover.png");
        this.menuBackButton = new JTexturedButton("Revenir au menu", "/data/images/Button.png", "/data/images/ButtonHover.png");

        buttonEndMenu.setLayout(new GridLayout(2, 0, 30, 30));
        buttonEndMenu.add(this.restartEndButton);
        buttonEndMenu.add(this.menuBackButton);
        buttonEndMenu.setOpaque(false);

        this.restartEndButton.addActionListener(new ActionEcouteur(this));
        this.menuBackButton.addActionListener(new ActionEcouteur(this));

        this.endMenu.setLayout(new BorderLayout(30, 30));
        this.endMenu.add(this.endText, BorderLayout.NORTH);
        this.endMenu.add(buttonEndMenu, BorderLayout.CENTER);
    }
}
