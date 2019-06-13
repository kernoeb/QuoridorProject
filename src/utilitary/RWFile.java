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

	/**
	 *
	 * @param fileName
	 */
	public void writeFile(String fileName) {
		// TODO - implement RWFile.writeFile
		throw new UnsupportedOperationException();
	}

}
