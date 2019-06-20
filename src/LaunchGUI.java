import javax.swing.*;
import java.awt.*;

import view.*;

public class LaunchGUI {
  public static void main(String[] args) {
  	System.setProperty("awt.useSystemAAFontSettings","on"); 
	System.setProperty("swing.aatext", "true"); 
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new QuoridorGUI();
      }
    });
  }
}
