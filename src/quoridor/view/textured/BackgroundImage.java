package quoridor.view.textured;

/** This will allow us to use elements of the package swing.
*/
import javax.swing.*;

/** This will allow us to use elements of the package awt.
*/
import java.awt.*;

/** This will allow us to use elements of the class BufferedImage.
*/
import java.awt.image.BufferedImage;

/** This will allow us to use elements of the class ImageIO.
*/
import javax.imageio.ImageIO;

/** This will allow us to use elements of the class File.
*/
import java.io.File;

/** This will allow us to use elements of the class IOException.
*/
import java.io.IOException;

/**
 * Class representing a background image.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public class BackgroundImage extends JComponent {

    /** Image used to create the background image.
    */
    private BufferedImage image;

    /**
     * Constructor of a background image.
     * @param fileName The filename of the image which will be used for the background image.
     */
    public BackgroundImage(String fileName) {
        try {
            //this.image = ImageIO.read(new File(fileName);
            this.image = ImageIO.read(getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            System.err.println("BackgroundImage : " + e.getMessage());
        } catch (Exception ex) {
            System.err.println("BackgroundImage : " + ex.getMessage());
        }
    }

    /**
     * Create the background image by "painting" it.
     * @param g Graphic element used to paint.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }

}
