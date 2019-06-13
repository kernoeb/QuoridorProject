package utilitary;

import java.io.*;

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
				System.err.println("readFile : "+e.getMessage());
			}
		}
		else {
			System.err.println("readFile : fileName n'est pas valide.");
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
