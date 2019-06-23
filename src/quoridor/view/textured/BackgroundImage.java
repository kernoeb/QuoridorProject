package quoridor.view.textured;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundImage extends JComponent {
    private BufferedImage image;

    public BackgroundImage(String fileName) {
        try {
            this.image = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.err.println("BackgroundImage : " + e.getMessage());
        } catch (Exception ex) {
            System.err.println("BackgroundImage : " + ex.getMessage());
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }

}
