package utilitary;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import quoridor.*;

public class RWFile {

	/**
	 *
	 * @param fileName
	 */
	public static ArrayList<String> readFile(String fileName) {
		// TODO - implement RWFile.readFile
		ArrayList<String> ret = new ArrayList<String>();

		if(fileName != null) {
			try {
				FileReader file = new FileReader(fileName);
				BufferedReader in = new BufferedReader(file);

				String s = in.readLine();

				while(s != null) {
					ret.add(s);
					s = in.readLine();
				}

				in.close();
			} catch(FileNotFoundException e) {
				System.err.println("readFile : " + e.getMessage());
			} catch (IOException ex) {
				System.err.println("readFile : " + ex.getMessage());
			}
		}
		else {
			System.err.println("readFile : Paramètre non valide.");
		}

		return ret;
	}

	public Game readObj(String fileName) {
		Game game = null;

		if(fileName != null) {
			try {
				FileInputStream file = new FileInputStream(fileName);
				ObjectInputStream obj = new ObjectInputStream(file);

				game = (Game) obj.readObject();

				obj.close();

			} catch (FileNotFoundException e) {
				System.err.println("readFile : " + e.getMessage());
			} catch (ClassNotFoundException ex) {
				System.err.println("readFile : " + ex.getMessage());
			} catch (IOException exc) {
				System.err.println("readFile : " + exc.getMessage());
			}
		}
		else {
			System.err.println("readFile : Paramètre non valide.");
		}

		return game;
	}

	/**
	 *
	 * @param fileName
	 */
	public void writeFile(Game game) {
		if (game != null) {
			try {
				String fileName = new SimpleDateFormat("'save_'yyyy-MM-dd'_'HH-mm-ss'.ser'").format(new Date());
				FileOutputStream file = new FileOutputStream(fileName);
				ObjectOutputStream obj = new ObjectOutputStream(file);

				obj.writeObject(game);

				obj.close();

			} catch (FileNotFoundException e) {
				System.err.println("writeFile : " + e.getMessage());
			} catch (ClassNotFoundException ex) {
				System.err.println("readFile : " + ex.getMessage());
			} catch (IOException e) {
				System.err.println("writeFile : " + e.getMessage());
			}
		}

		else {
			System.err.println("writeFile : Paramètre non valide.");
		}
	}

}
