package quoridor.model.utilitary;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import quoridor.model.*;

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

	public static Game readObj(String fileName) {
		Game game = null;

		if (fileName != null) {
			try {
				FileInputStream file = new FileInputStream(fileName);
				ObjectInputStream obj = new ObjectInputStream(file);

				game = (Game) obj.readObject();

				obj.close();

			} catch (FileNotFoundException e) {
				System.err.println("readFile FFE : " + e.getMessage());
				e.printStackTrace();
			} catch (IOException ex) {
				System.err.println("readFile IO : " + ex.getMessage());
				ex.printStackTrace();
			} catch (Exception exc) {
				System.err.println("readFile : " + exc.getMessage());
				exc.printStackTrace();
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
	public static void writeFile(Game game, String oldFileName) {
		if (game != null) {
			try {
				if(oldFileName != null) {
					File oldFile = new File(oldFileName);
					if(oldFile.delete()) {
						System.out.println("Fichier supprimé : " + oldFileName);
					}
				}

				String fileName = new SimpleDateFormat("'../data/save/save_'yyyy-MM-dd'_'HH-mm-ss'.ser'").format(new Date());
				FileOutputStream file = new FileOutputStream(fileName);
				ObjectOutputStream obj = new ObjectOutputStream(file);

				obj.writeObject(game);

				obj.close();

			} catch (FileNotFoundException e) {
				System.err.println("writeFile FileNotFoundException : " + e.getMessage());
			} catch (IOException ex) {
				System.out.print("writeFile IO : ");
				ex.printStackTrace();
				// System.err.println("writeFile IO : " + ex.getMessage());
			} catch (Exception exc) {
				System.err.println("writeFile Other : " + exc.getMessage());
			}
		}

		else {
			System.err.println("writeFile : Paramètre non valide.");
		}
	}

}
