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
		String loc = "";

		if (args.length != 0) {

			try {
				if (args[0].equals("HH")) {
					System.out.println("Chosen mode : HH");
					loc = "config/config.txt";
				} else if (args[0].equals("HA")) {
					loc = "config/configHA.txt";
					System.out.println("Chosen mode : HA");
				} else if (args[0].equals("AA")) {
					loc = "config/configAA.txt";
					System.out.println("Chosen mode : AA");
				} else {
					loc = args[0];
					System.out.println("Chosen config : " + args[0]);
				}

			} catch (Exception e) {
				System.out.println("Mode not set");
				loc = "config/config.txt";
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

			// int osUsed = 1;
			// try {
			// 	osUsed = Integer.parseInt(args[3]);
			// } catch (Exception e) {
			// 	osUsed = 1;
			// }

			System.out.println("Location : " + loc);
			// Quoridor quoridor = new Quoridor(loc, p1, p2, osUsed);
			Quoridor quoridor = new Quoridor(loc, p1, p2);

		} else {

			System.out.println("Aucun argument, lancement du config.txt");
			// Quoridor quoridor = new Quoridor("config/config.txt", "Red user", "Green user", 1);
			Quoridor quoridor = new Quoridor("config/config.txt", "Red user", "Green user");
		}

	}
}
