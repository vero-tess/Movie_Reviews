import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Parses the lines of a file and populates all of the data structures used.
 *
 */
public class FileParser {

	/* Instance variables */
	private ArrayList<String> lines;
	private HashMap<Item, HashSet<User>> itemList;
	private HashSet<User> userList;

	/* Constructor */
	public FileParser(ArrayList<String> lines) {
		itemList = new HashMap<Item, HashSet<User>>();
		userList = new HashSet<User>();
		this.lines = lines;
		if (lines == null) {
			throw new NullPointerException("No contents in the file.");
		}
	}

	/**
	 * Creates a hashset of user objects.
	 */
	public void createUserList() {

		String userID;
		String movieID;
		String userRatingString;

		double userRating;
		User user;

		for (String line : lines) {
			String delims = "::";
			String[] tokens = line.split(delims);

			userID = tokens[0];
			// userID = Integer.parseInt(userIdString);

			if (!userList.isEmpty()) {
				boolean found = false;
				for (User u : userList) {
					if (u.getUserID().equalsIgnoreCase(userID)) {
						found = true;
						break;
					}
				}
				if (found == false) {
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
	 * who have rated that movie (the user's rating for that movie) AND creates
	 * a hashmap for each user and populates it with movies that they have
	 * rated, including their rating
	 */
	public void createMoviesMap() {

		String userID;
		String itemID;
		String userRatingString;

		double userRating;
		User user;
		Item item; // key in HM

		for (String line : lines) {
			String delims = "::";
			String[] tokens = line.split(delims);

			userID = tokens[0];
			// userID = Integer.parseInt(userIdString);
			// user = new User(userID);

			itemID = tokens[1];
			// movieID = Integer.parseInt(movieIdString);

			userRatingString = tokens[2];
			userRating = Double.parseDouble(userRatingString);

			// the movie list is not empty
			boolean foundUser = false;
			boolean foundItem = false;
			if (!itemList.isEmpty()) {

				for (Item i : itemList.keySet()) {

					// the movie exists in the hashmap
					if (i.getItemID().equalsIgnoreCase(itemID)) {
						HashSet<User> tempUser = itemList.get(i);
						foundItem = true; // found the movie!
						for (User u : userList) {

							// user exists in the userList!
							if (u.getUserID().equalsIgnoreCase(userID)) {
								tempUser.add(u); // add the user for the movie
								itemList.put(i, tempUser); // re-put the
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
							itemList.put(i, tempUser);
							// put the user on the HashSet of users
							userList.add(user);
						}
						break;
					}
				}
				// if movie is not found
				if (foundItem == false) {
					// create movie object
					item = new Item(itemID);
					// create HashSet of users
					HashSet<User> users = new HashSet<User>();

					for (User u : userList) {
						// user exists!
						if (u.getUserID().equalsIgnoreCase(userID)) {
							// add user to the HashSet for the movie
							users.add(u);
							// add the movie rating to the user
							u.addRating(item, userRating);
							// put the movie-userList pair on the hashMap
							itemList.put(item, users);

							foundUser = true; // user was found!
							break;
						}
					}
					// user was not found!
					if (foundUser == false) {
						// create new user
						user = new User(userID);
						// add the movie for the user
						user.addRating(item, userRating);
						// add the user for the movie
						users.add(user);
						itemList.put(item, users);
						userList.add(user);
					}
				}
			} else {
				// HashMap of movies was empty!
				// create new movie
				item = new Item(itemID);
				HashSet<User> users = new HashSet<User>(); // create HashSet of
															// users

				for (User u : userList) {
					// found the user
					if (u.getUserID().equalsIgnoreCase(userID)) {
						// add to HashSet for the movie
						users.add(u);
						// add the movie for the user
						u.addRating(item, userRating);

						itemList.put(item, users);
						foundUser = true;
						break;
					}
				}
				if (foundUser == false) {
					// user was not found, so create user object
					user = new User(userID);
					// add the movie for the user
					user.addRating(item, userRating);
					// add the user for the movie
					users.add(user);
					itemList.put(item, users);
					userList.add(user);
				}
			}
		}

		// Print movieList
		// for(Item i: movieList.keySet()) {
		// System.out.println("movie: " + i.getMovieID());
		// System.out.print("Users: ");
		// for(User u: movieList.get(i)) {
		// System.out.print(u.getUserID()+ " ");
		// }
		// System.out.println();
		// }

		// Print userList
		// for(User u: userList) {
		// System.out.println("User: "+u.getUserID());
		// for(Item i: u.getMovieRatings().keySet()) {
		// System.out.print("Movie "+i.getMovieID()+ ", Rating: " +
		// u.getMovieRatings().get(i) + " ");
		// }
		// System.out.println();
		// }
	}

	/**
	 * Getter for movieList
	 * 
	 * @return movieList
	 */
	public HashMap<Item, HashSet<User>> getItemsList() {
		return itemList;
	}

	/**
	 * Getter for userList
	 * 
	 * @return userList
	 */
	public HashSet<User> getUsersList() {
		return userList;
	}

}