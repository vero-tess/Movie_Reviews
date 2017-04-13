import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FileParser2 {

	
	/* Instance variables */
	private ArrayList<String> lines;
	private ArrayList<User> users;
	private ArrayList<Item> movies;
	private Library library;
	private HashMap<Item, HashSet<User>> movieList;
	private HashSet<User> userList;

	
	/* Constructor */
	public FileParser2(ArrayList<String> lines) {
		users = new ArrayList<User>();
		movies = new ArrayList<Item>();
		// library = new Library(userList);
		movieList = new HashMap<Item, HashSet<User>>();
		userList = new HashSet<User>();
		this.lines = lines;
		if (lines == null) {
			throw new NullPointerException("No contents in the file.");
		}
	}

	
	/**
	* Creates a hashset of user objects
	*/
	public void createUserList() {

		String userIdString;
		String movieIdString;
		String userRatingString;
		int userID;
		int movieID;
		double userRating;
		User user;

	//	boolean found = false;
		for (String line : lines) {
			String delims = "::";
			String[] tokens = line.split(delims);

			userIdString = tokens[0];
			userID = Integer.parseInt(userIdString);
			// user = new User(userID);

			if (!userList.isEmpty()) {
				boolean found = false;
				for (User u : userList) {
					if (u.getUserID() == userID) {
					//	System.out.println(userID);
						found = true;
						break;
					}
				}
				if (found == false) {
				//	System.out.println("not found:" + userID);
					user = new User(userID);
					userList.add(user);
				}
			} else {
				user = new User(userID);
				userList.add(user);
			}
		}
	}

	
	/**
	 * Creates a hashmap of item (movie) objects and a hashset of all the users
	 * who have rated that movie (the user's rating for that movie) AND
	 * creates a hashmap for each user and populates it with movies that they have
	 * rated, including their rating
	 */
	public void createMoviesMap() {

		String userIdString;
		String movieIdString;
		String userRatingString;
		int userID;
		int movieID;
		double userRating;
		User user;
		Item movie; // key in HM

		for (String line : lines) {
			String delims = "::";
			String[] tokens = line.split(delims);

			userIdString = tokens[0];
			userID = Integer.parseInt(userIdString);
			// user = new User(userID);

			movieIdString = tokens[1];
			movieID = Integer.parseInt(movieIdString);

			userRatingString = tokens[2];
			userRating = Double.parseDouble(userRatingString);

			// movie = new Item(movieID);

			// the movie list is not empty
			boolean foundUser = false; 
			boolean foundMovie = false;
			if (!movieList.isEmpty()) {

				for (Item i : movieList.keySet()) {

					// the movie exists in the hashmap
					if (i.getMovieID() == movieID) {
						HashSet<User> tempUser = movieList.get(i);
						foundMovie = true; // found the movie!
						for (User u : userList) {

							// user exists in the userList!
							if (u.getUserID() == userID) {
								tempUser.add(u); // add the user for the movie
								movieList.put(i, tempUser); // re-put the
															// movie-userList
															// pair in HashMap

								u.addRating(i, userRating); // add the movie to
															// the user Ratings

								foundUser = true; // found the user!
								break;
							}
						}
						// if user is not found
						if (foundUser == false) {
							// create new user
							user = new User(userID);
							// add the movie for the user
							user.addRating(i, userRating);
							// add the user for the movie
							tempUser.add(user);
							// put the movie-userList pair on the hashmap
							movieList.put(i, tempUser);
							// put the user on the HashSet of users
							userList.add(user);
						}
						break;
					}
				}
				// if movie is not found
				if (foundMovie == false) {
					// create movie object
					movie = new Item(movieID);
					// create HashSet of users
					HashSet<User> users = new HashSet<User>();

					for (User u : userList) {
						// user exists!
						if (u.getUserID() == userID) {
							// add user to the HashSet for the movie
							users.add(u);
							// add the movie rating to the user
							u.addRating(movie, userRating);
							// put the movie-userList pair on the hashMap
							movieList.put(movie, users);

							foundUser = true; // user was found!
							break;
						}
					}
					// user was not found!
					if (foundUser == false) {
						// create new user
						user = new User(userID);
						// add the movie for the user
						user.addRating(movie, userRating);
						// add the user for the movie
						users.add(user);
						movieList.put(movie, users);
						userList.add(user);
					}
				}
			} else {
				// HashMap of movies was empty!
				// create new movie
				movie = new Item(movieID);
				HashSet<User> users = new HashSet<User>(); // create HashSet of
															// users

				for (User u : userList) {
					// found the user
					if (u.getUserID() == userID) {
						// add to HashSet for the movie
						users.add(u);
						// add the movie for the user
						u.addRating(movie, userRating);

						movieList.put(movie, users);
						foundUser = true;
						break;
					}
				}
				if (foundUser == false) {
					// user was not found, so create user object
					user = new User(userID);
					// add the movie for the user
					user.addRating(movie, userRating);
					// add the user for the movie
					users.add(user);
					movieList.put(movie, users);
					userList.add(user);
				}
			}
		}
	
//		for(Item i: movieList.keySet()) { 
//			System.out.println("movie: " + i.getMovieID()); 
//			System.out.print("Users: ");
//			for(User u: movieList.get(i)) { 
//				System.out.print(u.getUserID()+ " ");
//			}
//			System.out.println();
//		}
		
		
//		for(User u: userList) { 
//			System.out.println("User: "+u.getUserID());
//			for(Item i: u.getMovieRatings().keySet()) { 
//				System.out.print("Movie "+i.getMovieID()+ ", Rating: " + u.getMovieRatings().get(i) + " ");
//			}
//			System.out.println();
//		}
	}
	
	public HashMap<Item, HashSet<User>> getMoviesList() {
		return movieList;
	}
	
	
	public HashSet<User> getUsersList() {
		return userList;
	}

	
}