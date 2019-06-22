package quoridor;

// project import
import quoridor.Game;
import utilitary.RWFile;

import java.util.ArrayList;
import java.io.*;

// java import

/**
* Permits to launch a session from which can be launch and saved games
* @author
*/
public class Quoridor {

	private Game game;
	private String fileName;
	private boolean terminal;
	private boolean load;

    /**
     * Quoridor constructor
     * Create an object Quoridor
     * Serve as a platform to load and launch games
     * @param fileName file used
     * @param namePlayer1 player 1's name
     * @param namePlayer2 player 2's name
     * @param terminal teminal mode or not
     */
	public Quoridor(String fileName, String namePlayer1, String namePlayer2, boolean terminal) {
		try {
			ArrayList<String> list = RWFile.readFile(fileName);
			Mode mode = Mode.valueOf(list.get(0));
			this.terminal = terminal;
			this.load = false;
			this.game = new Game(mode, namePlayer1, namePlayer2, this.terminal);
			if (this.terminal) {
				this.game.start();
			}
		} catch (SaveGameException ex) {
			this.saveGame(this.game);
		} catch(Exception e) {
			System.err.println("Quoridor 1 : " + e.getMessage());
		}
	}

    /**
     * Quoridor constructor
     * Create an object Quoridor
     * Serve as a platform to load and launch games
     * @param mode game mode (HH or HA)
     * @param namePlayer1 player 1's username
     * @param namePlayer2 player 2's username
     * @param terminal terminal mode or not
     */
	public Quoridor(Mode mode, String namePlayer1, String namePlayer2, boolean terminal) {
		try {
			this.terminal = terminal;
			this.load = false;
			this.game = new Game(mode, namePlayer1, namePlayer2, this.terminal);
			if (this.terminal) {
				this.game.start();
			}
		} catch (SaveGameException ex) {
			this.saveGame(this.game);
		} catch (Exception e) {
			System.err.println("Quoridor 2 : " + e.getMessage());
		}
	}

	public Quoridor(String fileName, boolean terminal) {
		try {
			this.terminal = terminal;
			this.fileName = fileName;
			this.load = true;
			this.game = this.loadOldGame(this.fileName);
			// this.game.start();
			if (this.terminal) this.launchGame(this.game);
		} catch (SaveGameException ex) {
			this.saveGame(this.game);
	 	} catch (Exception e) {
			System.err.println("Quoridor 3 : " + e.getMessage());
		}
	}

	/**
	 * @return the Game of the current Quoridor.
	 * @author
	 */
	public Game getGame() {
		return this.game;
	}

	/**
	 * @return The previously saved game
	 * @author
	 */
	public Game loadOldGame(String fileName) {
		Game game = null;

		try {
			if (this.terminal) System.out.println("Reprise de la partie : " + fileName);
			game = RWFile.readObj(fileName);
		} catch (Exception e) {
			System.err.println("loadOldGame : " + e.getMessage());
		}

		return game;
	}

	/**
	 * Launch the chosen game
	 * @param game the desired game to launch
	 * @author
	 */
	public void launchGame(Game game) throws SaveGameException {
		if(game != null) {
			game.start();
		}

		else {
			System.err.println("launchGame : Paramètre non valide.");
		}
	}

	/**
	 * Save the desired game into the saving file of the current Quoridor object
	 * @param game The desired game to save
	 * @author
	 */
	public void saveGame(Game game) {
		if (game != null) {
			if(this.load) {
				RWFile.writeFile(game, this.fileName);
			}
			else {
				RWFile.writeFile(game, "");
			}
		}

		else {
			System.err.println("saveGame : Paramètre non valide.");
		}
	}
}
