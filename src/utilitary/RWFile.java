package utilitary;

import java.io.*;
import java.util.ArrayList;

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

	public Game readFile(String fileName) {
		Game game = null;

		if(fileName != null) {
			try {
				FileInputStream file = new FileInputStream(fileName);
				ObjectInputStream obj = new ObjectInputStream(file);

				game = obj.readObject();

				obj.close();

			} catch(FileNotFoundException e) {
				System.err.println("readFile : " + e.getMessage());
			} catch (IOException ex) {
				System.err.println("readFile : " + ex.getMessage());
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
	public void writeFile(String fileName, Game game) {
		if ((fileName != null) && (game != null)) {
			try {
				FileOutputStream file = new FileOutputStream(fileName);
				ObjectOutputStream obj = new ObjectOutputStream(file);

				obj.writeObject(game);

				obj.close();

			} catch (FileNotFoundException e) {
				System.err.println("writeFile : " + e.getMessage());
			} catch (IOException e) {
				System.err.println("writeFile : " + e.getMessage());
			}
		}

		else {
			System.err.println("writeFile : Paramètre(s) non valide(s).");
		}
	}

}
