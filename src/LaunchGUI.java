import javax.swing.*;
import java.awt.*;

import view.*;

public class LaunchGUI {
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new QuoridorGUI();
      }
    });
  }
}