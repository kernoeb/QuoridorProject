import quoridor.controller.Quoridor;
import quoridor.model.Mode;
import quoridor.view.QuoridorGUI;

/**
 * Game launcher of Quoridor
 * Contains the main method of the project
 *
 * @author
 */

class Launcher {

    /**
     * Project's main method
     *
     * @param args
     */
    public static void main(String[] args) {
        String loc = null;
        Mode chosenMode = null;
        boolean mode = false;
        boolean load = false;

        if (args.length != 0) {

            try {
                if (args[0].equals("HH")) {
                    chosenMode = Mode.HH;
                    mode = true;
                    System.out.println("Chosen mode : HH");
                } else if (args[0].equals("HA")) {
                    chosenMode = Mode.HA;
                    mode = true;
                    System.out.println("Chosen mode : HA");
                } else if (args[0].equalsIgnoreCase("load")) {
                    load = true;

                    if (args[1] != null) {
                        loc = args[1];
                    } else {
                        System.out.println("Aucun fichier n'a été rentré. \n"
                                + "Une nouvelle partie va être lancée.");

                        loc = "data/config.txt";
                    }
                } else {
                    loc = args[0];
                    System.out.println("Chosen config : " + args[0]);
                }

            } catch (Exception e) {
                System.out.println("Mode not set");
                loc = "data/config.txt";
            }

            String p1 = "";
            String p2 = "";

            if (mode) {
                try {
                    p1 = args[1];
                } catch (Exception e) {
                    p1 = "Red user";
                }
                try {
                    p2 = args[2];
                } catch (Exception e) {
                    p2 = "Green user";
                }
            }

            if ((loc == null) && (chosenMode != null)) {
                Quoridor quoridor = new Quoridor(chosenMode, p1, p2, true);
            } else if (load && (!loc.equalsIgnoreCase("data/config.txt"))) {
                Quoridor quoridor = new Quoridor(loc, true);
            } else {
                System.out.println("Location : " + loc);
                Quoridor quoridor = new Quoridor(loc, p1, p2, true);
            }
        } else {
            System.setProperty("awt.useSystemAAFontSettings", "on");
            System.setProperty("swing.aatext", "true");
            javax.swing.SwingUtilities.invokeLater(QuoridorGUI::new);
        }

    }
}
