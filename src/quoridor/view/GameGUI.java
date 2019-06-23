package quoridor.view;

/** This will allow us to use elements of the class ActionEcouteur.
*/
import quoridor.controller.ActionEcouteur;

/** This will allow us to use elements of the class Game.
*/
import quoridor.model.Game;

/** This will allow us to use elements of the class JTexturedButton.
*/
import quoridor.view.textured.JTexturedButton;

/** This will allow us to use elements of the package swing.
*/
import javax.swing.*;

/** This will allow us to use elements of the class Border.
*/
import javax.swing.border.Border;

/** This will allow us to use elements of the class EmptyBorder.
*/
import javax.swing.border.EmptyBorder;

/** This will allow us to use elements of the package awt.
*/
import java.awt.*;

/**
 * Class representing a game of the graphical interface.
 * It is a JPanel containing all the elements of the game screen.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class GameGUI extends JPanel {

    /** The quoridor graphical interface it belongs to.
    */
    private final QuoridorGUI quoridorGUI;

    /** The game it corresponds to.
    */
    private final Game game;

    /** The board of the graphical interface.
    */
    private final BoardGUI boardGUI;

    /** The button used to make a pause of the game.
    */
    private JTexturedButton pauseButton;

    /** The label with the color of the actual player.
    */
    private JLabel currentPlayerColor;

    /** The JPanel containing the resting fences of the second player.
    */
    private JPanel nbBar1;

    /** The JPanel containing the resting fences of the first player.
    */
    private JPanel nbBar2;

    /** The JPanel containing the text and the color of the actual player.
    */
    private JPanel currentPlayer;

    /** The icon of the fence of the first player.
    */
    private ImageIcon fenceBlue;

    /** The icon of the pawn of the first player.
    */
    private ImageIcon pawnBlue;

    /** The icon of the fence of the second player.
    */
    private ImageIcon fenceOrange;

    /** The icon of the pawn of the second player.
    */
    private ImageIcon pawnOrange;

    /** The color red.
    */
    private final Color colorRed = new Color(149, 26, 0);

    /** The color beige used for background of the game screen.
    */
    private final Color colorBackground = new Color(195, 195, 148);

    /**
     * Constructor of the game of the graphical interface.
     * @param quoridorGUI The quoridor graphical interface it belongs to.
     */
    public GameGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        this.game = this.quoridorGUI.getQuoridor().getGame();
        this.boardGUI = new BoardGUI(this, this.game, this.game.getBoard());
        this.createAndShowGUI();
    }

    /**
     * Return the game it corresponds to.
     * @return The game it corresponds to.
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Return the quoridor graphical interface it belongs to.
     * @return The quoridor graphical interface it belongs to.
     */
    public QuoridorGUI getQuoridorGUI() {
        return this.quoridorGUI;
    }

    /**
     * Return the board of the graphical interface.
     * @return The board of the graphical interface.
     */
    public BoardGUI getBoardGUI() {
        return this.boardGUI;
    }

    /**
     * Return the button to set the game on pause.
     * @return The button to set the game on pause.
     */
    public JTexturedButton getButtonPause() {
        return this.pauseButton;
    }

    /**
     * Initialize and add all the components of the game screen to it.
     */
    private void createAndShowGUI() {
        Border redLine = BorderFactory.createLineBorder(this.colorRed, 15);
        this.boardGUI.setBorder(redLine);

        this.setLayout(new BorderLayout(0, 5));

        this.fenceBlue = new ImageIcon((new ImageIcon("../data/icons/blueFence.png")).getImage().getScaledInstance(8, 65, java.awt.Image.SCALE_SMOOTH));
        this.pawnBlue = new ImageIcon((new ImageIcon("../data/icons/bluePlayer.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        this.fenceOrange = new ImageIcon((new ImageIcon("../data/icons/orangeFence.png")).getImage().getScaledInstance(8, 65, java.awt.Image.SCALE_SMOOTH));
        this.pawnOrange = new ImageIcon((new ImageIcon("../data/icons/orangePlayer.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 3, -50, 0));

        this.pauseButton = new JTexturedButton("../data/images/ButtonPause.png", "../data/images/ButtonPause.png");
        top.add(this.pauseButton);
        this.pauseButton.addActionListener(new ActionEcouteur(this.quoridorGUI));

        JLabel time = new JLabel("Temps : 00:00");
        this.quoridorGUI.setFontPalatino(time, 25);
        top.add(time);

        this.currentPlayer = new JPanel();
        this.currentPlayer.setLayout(new GridLayout(1, 0, -150, 0));
        JLabel currentPlayerText = new JLabel("Joueur actuel :");
        this.quoridorGUI.setFontPalatino(currentPlayerText, 25);
        this.currentPlayer.add(currentPlayerText);
        this.currentPlayerColor = new JLabel();
        this.currentPlayer.setBackground(this.colorBackground);

        this.updateCurrentPlayer();

        top.add(this.currentPlayer);
        top.setBackground(this.colorBackground);

        this.add(top, BorderLayout.NORTH);

        JPanel board = new JPanel();
        board.setLayout(new BorderLayout(0, 10));
        this.add(board, BorderLayout.CENTER);
        board.setBackground(this.colorBackground);
        board.setBorder(new EmptyBorder(50, 200, 0, 200));

        this.nbBar1 = new JPanel();
        this.nbBar1.setLayout(new GridLayout(0, 10));
        this.nbBar1.setBackground(this.colorBackground);


        this.nbBar2 = new JPanel();
        this.nbBar2.setLayout(new GridLayout(0, 10));
        this.nbBar2.setBackground(this.colorBackground);

        this.updateFences();


        board.add(this.nbBar1, BorderLayout.NORTH);
        board.add(this.boardGUI, BorderLayout.CENTER);
        board.add(this.nbBar2, BorderLayout.SOUTH);

        this.boardGUI.displayBoardGUI();
        this.boardGUI.addTmpPossibilities(this.game.getBoard().listOfPossibilitiesPawn(this.game.getActualPlayer()), this.game.getActualPlayer());
    }

    /**
     * Update the color of the current player's label.
     */
    public void updateCurrentPlayer() {
        this.currentPlayer.remove(this.currentPlayerColor);
        if (this.game.getActualPlayer() == this.game.getPlayer1()) this.currentPlayerColor = new JLabel(this.pawnBlue);
        else this.currentPlayerColor = new JLabel(this.pawnOrange);

        this.currentPlayer.add(this.currentPlayerColor);
    }

    /**
     * Update the number of fences of the game screen.
     */
    public void updateFences() {
        this.nbBar1.removeAll();
        this.nbBar2.removeAll();

        for (int i = 0; i < this.game.getPlayer2().getNbRestingFences(); i++) {
            this.nbBar1.add(new JLabel(this.fenceOrange));
        }

        for (int i = 0; i < this.game.getPlayer1().getNbRestingFences(); i++) {
            this.nbBar2.add(new JLabel(this.fenceBlue));
        }
    }
}
