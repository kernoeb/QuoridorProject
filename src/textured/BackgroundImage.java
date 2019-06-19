package textured;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BackgroundImage extends JComponent {
  BufferedImage image;

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

  public Dimension getImageSize() {
    return new Dimension(this.image.getWidth(), this.image.getHeight());
  }
}
