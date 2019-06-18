import javax.swing.*;
import java.awt.event.*;

public class ActionEcouteur implements ActionListener {

  QuoridorGUI quoridor;

  public ActionEcouteur(QuoridorGUI quoridor) {
    this.quoridor = quoridor;
  }

  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton) e.getSource();

    if (button == this.quoridor.getButtonPlay()) {
      this.quoridor.getMenuMain().setVisible(false);
      this.quoridor.add(this.quoridor.getMenuMode());
      this.quoridor.getMenuMode().setVisible(true);
    }

    else if (button == this.quoridor.getButtonLoad()) {

    }

    else if (button == this.quoridor.getButtonQuit()) {
      System.exit(0);
    }
  }

}
