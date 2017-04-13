import java.util.ArrayList;
import java.util.HashMap;

public class FileParser {

	/* Instance variables */ 
	private ArrayList<String> lines;
	private ArrayList<User> users;

	
	/* Constructor */
	public FileParser(ArrayList<String> lines) {
		users = new ArrayList<User>();
		this.lines = lines;
		if (lines == null) {
			throw new NullPointerException("No contents in the file.");
		}
	}
	
	
	
	/**
	 * Creates a hashmap of item objects (movieID) and rating
	 * 1::122::5::838985046
	 */
	public void createUserHashMap() {
		
		String userIdString;
		String movieIdString;
		String userRatingString;
		int userID;
		int movieID;
		double userRating;
		User user;
		
		for (String line : lines) {
			String delims = "::";
			String[] tokens = line.split(delims);
			
			userIdString = tokens[0];
			userID = Integer.parseInt(userIdString);
			user = new User(userID);
			
		
			movieIdString = tokens[1];
			movieID = Integer.parseInt(movieIdString);
			
			
			userRatingString = tokens[2];
			userRating = Double.parseDouble(userRatingString);
			Item movie = new Item(movieID);
			
			
			// if users' ArrayList is empty, first user is added
			if (users.isEmpty()) {
				users.add(user);
				
				// populate movie HM of user with current movie
				user.addRating(movie, userRating);
			}
			// users' ArrayList is NOT empty
			else {
				
				// find user in users' ArrayList
				for (User tempUser : users) {
					// user has already been created
					if (user.getUserID() == tempUser.getUserID()) {
						// add movie to user's HM
						user.addRating(movie, userRating);
					}
					// user has NOT been created
					else {
						// create new user
						User newUser = new User(userID);
						
						// add movie to user's HM
						newUser.addRating(movie, userRating);
						
						// add user to users' ArrayList
						users.add(newUser);
						user = newUser;
						break;
					}
				}
			}
			
			// Prints out HM for each user
//			for (Item tempMovie: user.getMovieRatings().keySet()){
//	            int key = tempMovie.getMovieID();
//	            double value = user.getMovieRatings().get(tempMovie);
//	            System.out.println(key + " " + value);
//			}
		}
	}
	
	
	/**
	 * Creates a hashmap of item (movie) objects and a hashset of all the 
	 * users who have rated that movie (the user's rating for that movie)
	 * 1::122::5::838985046
	 */
//	public void createUserHashMap() {
//		
//		
//		
//		
//		
//	}
	
	
	
	
	
	
}