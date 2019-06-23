package quoridor.view;

import quoridor.controller.ActionEcouteur;
import quoridor.controller.Quoridor;
import quoridor.view.textured.BackgroundImage;
import quoridor.view.textured.JTexturedButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class QuoridorGUI extends JFrame {
    private JPanel loadMenu;
    private JPanel mainMenu;
    private JPanel modeMenu;
    private JPanel pauseMenu;
    private JPanel endMenu;
    private GameGUI gameGUI;

    private JTexturedButton playButton;
    private JTexturedButton loadButton;
    private JTexturedButton quitButton;

    private JTexturedButton modeHHButton;
    private JTexturedButton modeHAButton;
    private JTexturedButton modeCrossButton;

    private JTexturedButton loadCrossButton;
    private JFileChooser fileChooser;

    private JTexturedButton resumeButton;
    private JTexturedButton restartButton;
    private JTexturedButton saveAndQuitButton;

    private JLabel endText;
    private JTexturedButton restartEndButton;
    private JTexturedButton menuBackButton;

    private Quoridor quoridor;

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

    public JTexturedButton getButtonModeCross() {
        return this.modeCrossButton;
    }

    public JTexturedButton getButtonLoadCross() {
        return this.loadCrossButton;
    }

    public JTexturedButton getButtonResume() {
        return this.resumeButton;
    }

    public JTexturedButton getButtonRestart() {
        return this.restartButton;
    }

    public JTexturedButton getButtonSaveAndQuit() {
        return this.saveAndQuitButton;
    }

    public JTexturedButton getButtonRestartEnd() {
        return this.restartEndButton;
    }

    public JTexturedButton getButtonMenuBack() {
        return this.menuBackButton;
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

    public JPanel getMenuPause() {
        return this.pauseMenu;
    }

    public JPanel getMenuEnd() {
        return this.endMenu;
    }

    public JLabel getEndText() {
        return this.endText;
    }

    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }

    public GameGUI getGameGUI() {
        return this.gameGUI;
    }

    public void setGameGUI(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
    }

    public Quoridor getQuoridor() {
        return this.quoridor;
    }

    public void setQuoridor(Quoridor quoridor) {
        this.quoridor = quoridor;
    }

    public void setBackgroundImage(String fileName) {
        BackgroundImage back = new BackgroundImage(fileName);
        this.setContentPane(back);
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

    private Font getFontPalatino() throws FontFormatException, IOException {
        return Font.createFont(Font.TRUETYPE_FONT, new File("../data/fonts/palab.ttf"));
    }

    private void createAndShowGUI() {
        this.setTitle("Quoridor");
        this.setMinimumSize(new Dimension(800, 828));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // quoridor.model = new Quoridor()

        this.initializeLoadMenu();
        this.initializeMainMenu();
        this.initializeModeMenu();
        this.initializePauseMenu();
        this.initializeEndMenu();

        this.add(this.mainMenu);
        // this.mainMenu.setVisible(true);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initializeLoadMenu() {
        this.loadMenu = new JPanel();
        this.loadMenu.setLayout(new BorderLayout());

        this.loadCrossButton = new JTexturedButton("../data/images/ArrowBack.png", "../data/images/ArrowBackHover.png");
        this.loadCrossButton.setHorizontalAlignment(SwingConstants.LEFT);
        this.loadCrossButton.addActionListener(new ActionEcouteur(this));

        this.fileChooser = new JFileChooser(new File("../data/save/"));
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Save files (.ser)", "ser");
        this.fileChooser.setFileFilter(fileFilter);
        this.fileChooser.addActionListener(new ActionEcouteur(this));

        this.loadMenu.add(this.loadCrossButton, BorderLayout.NORTH);
        this.loadMenu.add(this.fileChooser, BorderLayout.CENTER);
    }

    private void initializeMainMenu() {
        this.mainMenu = new JPanel();
        JPanel buttonMainMenu = new JPanel();

        JLabel quoridorText = new JLabel("QUORIDOR");
        quoridorText.setForeground(Color.WHITE);
        this.setFontPalatino(quoridorText, 70);
        quoridorText.setPreferredSize(new Dimension(450, 250));
        quoridorText.setHorizontalAlignment(JLabel.CENTER);
        this.playButton = new JTexturedButton("Jouer", "../data/images/Button.png", "../data/images/ButtonHover.png");
        this.loadButton = new JTexturedButton("Charger", "../data/images/Button.png", "../data/images/ButtonHover.png");
        this.quitButton = new JTexturedButton("Quitter", "../data/images/Button.png", "../data/images/ButtonHover.png");

        buttonMainMenu.setLayout(new GridLayout(3, 0, 30, 30));
        buttonMainMenu.add(this.playButton);
        buttonMainMenu.add(this.loadButton);
        buttonMainMenu.add(this.quitButton);
        buttonMainMenu.setOpaque(false);

        this.playButton.addActionListener(new ActionEcouteur(this));
        this.loadButton.addActionListener(new ActionEcouteur(this));
        this.quitButton.addActionListener(new ActionEcouteur(this));

        this.mainMenu.setLayout(new BorderLayout(30, 30));
        //this.mainMenu.add(Box.createRigidArea(new Dimension(50,50)), BorderLayout.PAGE_START);
        this.mainMenu.add(quoridorText, BorderLayout.NORTH);
        this.mainMenu.add(buttonMainMenu, BorderLayout.CENTER);

        this.setBackgroundImage("../data/images/MenuBackground.png");
        // this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // this.setLayout(new FlowLayout());
        this.mainMenu.setOpaque(false);
    }

    private void initializeModeMenu() {
        this.modeMenu = new JPanel();
        JPanel buttonModeMenu = new JPanel();

        this.modeCrossButton = new JTexturedButton("../data/images/ArrowBack.png", "../data/images/ArrowBackHover.png");

        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            genv.registerFont(getFontPalatino());
        } catch (FontFormatException ffe) {
        } catch (IOException io) {
        }


        JLabel modeText = new JLabel("<html><head><style type='text/css'>body { font-family: Palatino Linotype; } </style></head><div align='center'>Veuillez choisir un <br> mode de jeu :</div></html>");
        this.setFontPalatino(modeText, 40);
        modeText.setPreferredSize(new Dimension(400, 300));
        modeText.setHorizontalAlignment(JLabel.CENTER);
        modeText.setForeground(Color.WHITE);

        this.modeHHButton = new JTexturedButton("Humain / Humain", "../data/images/Button.png", "../data/images/ButtonHover.png");
        this.modeHAButton = new JTexturedButton("Humain / IA", "../data/images/Button.png", "../data/images/ButtonHover.png");

        buttonModeMenu.setLayout(new GridLayout(2, 0, 50, 50));
        buttonModeMenu.add(this.modeHHButton);
        buttonModeMenu.add(this.modeHAButton);
        buttonModeMenu.setOpaque(false);

        // this.modeMenu.setLayout(new BorderLayout(50, 50));
        // this.modeMenu.add(this.modeText, BorderLayout.NORTH);
        // this.modeMenu.add(this.buttonModeMenu, BorderLayout.CENTER);


        this.modeMenu.setLayout(new BorderLayout(0, -30));

        JPanel crossPanel = new JPanel();
        // crossPanel.setLayout(new GridLayout(0, 3, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 0));
        crossPanel.setLayout(new GridLayout(0, 3, 300, 0));
        crossPanel.add(this.modeCrossButton);
        crossPanel.setOpaque(false);
        // crossPanel.add(new JPanel());

        // cons.anchor = GridBagConstraints.WEST;

        this.modeMenu.add(crossPanel, BorderLayout.NORTH);
        JPanel jP = new JPanel();
        // jP.setLayout(new GridLayout(0,1));
        jP.setLayout(new BorderLayout(0, 50));
        jP.setOpaque(false);
        jP.add(modeText, BorderLayout.NORTH);
        jP.add(buttonModeMenu, BorderLayout.CENTER);
        this.modeMenu.add(jP);


        // this.modeMenu.setLayout(new GridBagLayout());
        // GridBagConstraints cons = new GridBagConstraints();
        // // cons.anchor = GridBagConstraints.NORTHWEST;
        // cons.gridx = 0;
        // cons.gridy = 0;
        // cons.weightx = GridBagConstraints.LINE_START;
        // // cons.fill = GridBagConstraints.NORTHEAST;
        // this.modeMenu.add(this.modeCrossButton, cons);
        // // this.modeCrossButton.setBorder(new EmptyBorder(0, -20, 0, 0));

        // // cons.insets = new Insets(-25, -50, 0, 0);

        // cons.gridx = 1;
        // cons.gridy = 1;
        // // cons.anchor = GridBagConstraints.NORTH;
        // cons.anchor = GridBagConstraints.NORTHWEST;
        // this.modeMenu.add(this.modeText, cons);

        // cons.gridy = 2;
        // // cons.anchor = GridBagConstraints.CENTER;
        // this.modeMenu.add(this.buttonModeMenu, cons);

        this.modeHHButton.addActionListener(new ActionEcouteur(this));
        this.modeHAButton.addActionListener(new ActionEcouteur(this));
        this.modeCrossButton.addActionListener(new ActionEcouteur(this));
    }

    private void initializePauseMenu() {
        this.pauseMenu = new JPanel();
        JPanel buttonPauseMenu = new JPanel();

        JLabel pauseText = new JLabel("Pause");
        pauseText.setForeground(Color.WHITE);
        pauseText.setPreferredSize(new Dimension(400, 235));
        this.setFontPalatino(pauseText, 40);
        pauseText.setHorizontalAlignment(JLabel.CENTER);
        this.resumeButton = new JTexturedButton("Reprendre", "../data/images/Button.png", "../data/images/ButtonHover.png");
        this.restartButton = new JTexturedButton("Recommencer", "../data/images/Button.png", "../data/images/ButtonHover.png");
        this.saveAndQuitButton = new JTexturedButton("Sauver et quitter", "../data/images/Button.png", "../data/images/ButtonHover.png");

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

    private void initializeEndMenu() {
        this.endMenu = new JPanel();
        JPanel buttonEndMenu = new JPanel();

        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            genv.registerFont(getFontPalatino());
        } catch (FontFormatException ffe) {
        } catch (IOException io) {
        }


        this.endText = new JLabel();
        this.endText.setForeground(Color.WHITE);
        this.endText.setPreferredSize(new Dimension(400, 300));
        this.setFontPalatino(this.endText, 40);
        this.endText.setHorizontalAlignment(JLabel.CENTER);
        this.restartEndButton = new JTexturedButton("Recommencer", "../data/images/Button.png", "../data/images/ButtonHover.png");
        this.menuBackButton = new JTexturedButton("Revenir au menu", "../data/images/Button.png", "../data/images/ButtonHover.png");

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
