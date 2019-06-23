package quoridor.view.textured;

/** This will allow us to use elements of the package swing.
*/
import javax.swing.*;

/** This will allow us to use elements of the package awt.
*/
import java.awt.*;

/** This will allow us to use elements of the class File.
*/
import java.io.File;

/**
 * Class representing a textured button.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class JTexturedButton extends JButton {

    /** This will allow us to create and use the object properly.
    */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of a textured button, defined by its text and its icons.
     * @param text      The text that will be put in the textured button.
     * @param icon      The icon of the textured button.
     * @param iconHover The icon of the textured button when the mouse will focus it.
     */
    public JTexturedButton(String text, String icon, String iconHover) {
        super(text);
        this.initializeComponents(icon, iconHover);
    }

    /**
     * Constructor of a textured button only defined by its icons.
     * @param icon      The icon of the textured button.
     * @param iconHover The icon of the textured button when the mouse will focus it.
     */
    public JTexturedButton(String icon, String iconHover) {
        this.initializeComponents(icon, iconHover);
    }

    /**
     * Initialize and create the textured button.
     * @param icon      The icon of the textured button.
     * @param iconHover The icon of the textured button when the mouse will focus it.
     */
    private void initializeComponents(String icon, String iconHover) {
        this.setForeground(Color.BLACK);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("../data/fonts/palab.ttf"));
            this.setFont(font.deriveFont(35F));
        } catch (Exception e) {
            System.err.println("JTexturedButton : " + e.getMessage());
        }

        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);

        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setHorizontalTextPosition(SwingConstants.CENTER);

        this.setIcon(new ImageIcon(icon));
        this.setRolloverIcon(new ImageIcon(iconHover));
    }
}
