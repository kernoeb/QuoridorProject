import quoridor.view.QuoridorGUI;

class LaunchGUI {
    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        javax.swing.SwingUtilities.invokeLater(QuoridorGUI::new);
    }
}
