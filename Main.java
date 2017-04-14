import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the Movie Recommendation Tool.");
		System.out.println("Enter the file which you wish to use and press ENTER: ");
		String inputFile = in.nextLine();
		
		
		// check for validity of inputFile
		File f = new File(inputFile);
		if(f.exists() && !f.isDirectory()) {
			
			
			// read in file
			FileReader fr = new FileReader(inputFile);
			ArrayList<String> lines = fr.getLines();
			
			
			// parse file contents and create data structures
			FileParser fp = new FileParser(lines);
			fp.createUserList();
			fp.createMoviesMap();
			
			HashMap<Item, HashSet<User>> moviesMap = fp.getMoviesList();
			HashSet<User> usersList = fp.getUsersList();
			
			Library library = new Library();
			library.setMovieList(moviesMap);
			library.setUserList(usersList);
			
			Predictions prediction = new Predictions(library);
			
			// Print out userList
//			System.out.println("Users List: ");
//			for (User user : library.getUserList()) {
//				System.out.println("User ID: " + user.getUserID());
//			}
			
			// Print out Movie HashMap
//			System.out.println("Movies List: ");
//			
//			for(Item i: library.getMovieList().keySet()) { 
//				System.out.println(i.getMovieID());
//			}
		
			boolean flag = false;
			while (!flag) {
				
				System.out.println("If you would like to predict the user's likely preference for a movie type 1,"
						+ " if you would like to display the n-highest predicted preferences for a user type 2.");
				int userChoice = in.nextInt();
				
				// part A
				if (userChoice == 1) {
					
					flag = true;
					
					System.out.println("Type the ID of the USER and press ENTER: "); // 2 for testing
					int userID = in.nextInt();
					
					System.out.println("Type the ID of the MOVIE and press ENTER: "); // 4 for testing
					int movieID = in.nextInt();
					
					User user = null; 
					for(User u: library.getUserList()) { 
						if (u.getUserID() == userID) { 
							user = u;
							break;
						}
					}

					Item movie = null;
					for(Item i: library.getMovieList().keySet()) { 
						if (i.getMovieID() == movieID) { 
							movie = i;
							break;
						}
					}
					
					double predictionReturn = prediction.predictPreference(user, movie);
					System.out.println("Prediction: " + predictionReturn);
					System.out.println(" ");
				}
				// part B
				else if (userChoice == 2) {
					
					flag = true;
					
					System.out.println("Type the ID of the USER and press ENTER: "); // 2 for testing
					int userID = in.nextInt();
					
					System.out.println("Type the THRESHOLD and press ENTER: "); // 2 for testing
					int threshold = in.nextInt();
					
					User user = null; 
					for(User u: library.getUserList()) { 
						if (u.getUserID() == userID) {
							user = u;
							break;
						}
					}
					
					HashMap<Item, Double> ratings = prediction.produceRatings(user, threshold); // 2 for testing
					for(Item i: ratings.keySet()) { 
						System.out.println("Movie: " + i.getMovieID() + " | " + "Rating: " + ratings.get(i));
					}
				}
				// error
				else {
					System.out.println("You have not slected a valid value. Please try again.");
				}
			}
		}
		else {
			System.out.println("Something was wrong with your input file.");
			System.out.println("Make sure your file is a valid file or file path and try again.");
		}
		in.close();
	}
}
