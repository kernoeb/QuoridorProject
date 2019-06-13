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

	/**
	 * Quoridor constructor
	 * Create an object Quoridor
	 * Serve as a platform to load and launch games
	 * @param fileName path to the file where the current game will be saved in
	 * @author
	 */
	public Quoridor(String fileName, String namePlayer1, String namePlayer2) {
		try {
			ArrayList<String> list = RWFile.readFile(fileName);
			Mode mode = Mode.valueOf(list.get(0));
			this.game = new Game(mode, namePlayer1, namePlayer2);
		} catch(Exception e) {
			System.err.println("Quoridor : " + e.getMessage());
		}
	}

	/**
	 * Quoridor constructor
	 * Create an object Quoridor
	 * Serve as a platform to load and launch games
	 * @param fileName path to the file where the current game will be saved in
	 * @author
	*/
	public Quoridor(Mode mode, String namePlayer1, String namePlayer2) {
		// try {
			this.game = new Game(mode, namePlayer1, namePlayer2);
		// } catch (Exception e) {
			// System.err.println("Quoridor : " + e.getMessage());
		// }
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
	public Game loadOldGame() {
		// TODO - implement Quoridor.loadOldGame
		return this.game;
	}

	/**
	 * Launch the choosen game
	 * @param game the desired game to launch
	 * @author
	 */
	public void launchGame(Game game) {
		// TODO - implement Quoridor.launchGame
	}

	/**
	 * Save the desired game into the saving file of the current Quoridor object
	 * @param game The desired game to save
	 * @author
	 */
	public void saveGame(Game game) {
		// TODO - implement Quoridor.saveGame
	}

}
