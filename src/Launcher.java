//No packages

// Import project
import quoridor.*;

// Import Java
// No import

/**
 * Game launcher of Quoridor
 * Contains the main method of the project
 * @author
 */

public class Launcher {

	/**
	 * Project's main method
	 * @param args
	 */
	public static void main(String[] args) {
		String loc = null;
		Mode mode = null;

		if (args.length != 0) {

			try {
				if (args[0].equals("HH")) {
					mode = Mode.HH;
					System.out.println("Chosen mode : HH");
				}

				else if (args[0].equals("HA")) {
					mode = Mode.HA;
					System.out.println("Chosen mode : HA");
				}

				else {
					loc = args[0];
					System.out.println("Chosen config : " + args[0]);
				}

			} catch (Exception e) {
				System.out.println("Mode not set");
				loc = "data/config.txt";
			}

			String p1 = "";
			String p2 = "";

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

			if((loc == null) && (mode != null)) {
				Quoridor quoridor = new Quoridor(mode, p1, p2);
			}
			else {
				System.out.println("Location : " + loc);
				Quoridor quoridor = new Quoridor(loc, p1, p2);
			}
		}

		else {
			System.out.println("Aucun argument, lancement du config.txt");
			Quoridor quoridor = new Quoridor("data/config.txt", "Red user", "Green user");
		}

	}
}
