import javax.swing.*;
import java.awt.*;

public class BackgroundImage extends JComponent {
  Image image;

  public BackgroundImage(Image image) {
    this.image = image;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(this.image, 0, 0, null);
  }
}
