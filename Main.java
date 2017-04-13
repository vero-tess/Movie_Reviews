import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/////////// File Reading/Parsing Testing ///////////////
		
		FileReader fr = new FileReader("ratings_test.txt");
		ArrayList<String> lines = fr.getLines();
		
//		for (int i = 0; i < lines.size(); i++) {
//			System.out.println(lines.get(i));
//		}
		
		FileParser fp = new FileParser(lines);
//		fp.createUserHashMap();
		fp.createMoviesMap();
		
	
	}

}
