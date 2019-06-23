package quoridor.view.textured;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JTexturedButton extends JButton {
    private static final long serialVersionUID = 1L;

    public JTexturedButton(String text, String icon, String iconHover) {
        super(text);
        this.initializeComponents(icon, iconHover);
    }

    public JTexturedButton(String icon, String iconHover) {
        this.initializeComponents(icon, iconHover);
    }

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
