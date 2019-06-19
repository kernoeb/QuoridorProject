package textured;

import javax.swing.*;
import java.awt.*;

public class JTexturedButton extends JButton {
  private static final long serialVersionUID = 1L;

  public JTexturedButton(String text, String icon, String iconHover) {
    super(text);
    this.setForeground(Color.BLACK);
    this.setFont(new Font("Palatino Linotype", Font.BOLD, 35));

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
