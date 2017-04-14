import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads in a file that is specified.
 *
 */
public class FileReader {
	
	/* Instance Variables */
	private String filename;
	private ArrayList<String> lines;
	
	
	/**
	 * The constructor
	 * @param file the file to read
	 */
	public FileReader(String file) {
		
		filename = file;
		lines = new ArrayList<String>();
		readFile();
		
		if (file == null) {
			throw new NullPointerException("File is null.");
		}
	}
	
	

	/**
	 * This will read in the entire file.
	 * It will store the contents in the lines ArrayList.
	 */
	private void readFile() {
		
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner(inputFile);
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
			}
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * The accessor method for lines
	 * @return the lines arraylist
	 */
	public ArrayList<String> getLines() {
		return lines;
	}
	
	
	
	/**
	 * Gets the file name
	 */
	public String getFileName() {
		if (filename == null) {
			System.out.println("The filename is null.");
			throw new NullPointerException();
		}
		return filename;
	}

}
