import javax.swing.*;
import java.awt.*;

public class LaunchGUI {
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new QuoridorGUI();
      }
    });
  }
}
