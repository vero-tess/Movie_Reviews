import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FileParser {

	/* Instance variables */
	private ArrayList<String> lines;
	private ArrayList<User> users;
	private ArrayList<Item> movies;
	private Library library;
	private HashMap<Item, HashSet<User>> movieList;
	private HashSet<User> userList;

	/* Constructor */
	public FileParser(ArrayList<String> lines) {
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
	 * Creates a hashmap of item objects (movieID) and rating
	 * 1::122::5::838985046
	 */
	public void createUserList() {

		String userIdString;
		String movieIdString;
		String userRatingString;
		int userID;
		int movieID;
		double userRating;
		User user;

		boolean found = false;
		for (String line : lines) {
			String delims = "::";
			String[] tokens = line.split(delims);

			userIdString = tokens[0];
			userID = Integer.parseInt(userIdString);
			// user = new User(userID);

			if (!userList.isEmpty()) {

				for (User u : userList) {
					if (u.getUserID() == userID) {
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
		//
		// movieIdString = tokens[1];
		// movieID = Integer.parseInt(movieIdString);
		//
		// userRatingString = tokens[2];
		// userRating = Double.parseDouble(userRatingString);
		// Item movie = new Item(movieID);

		// // if users' ArrayList is empty, first user is added
		// if (users.isEmpty()) {
		// users.add(user);
		//
		// // populate movie HM of user with current movie
		// user.addRating(movie, userRating);
		// }
		// // users' ArrayList is NOT empty
		// else {
		//
		// // find user in users' ArrayList
		// for (User tempUser : users) {
		// // user has already been created
		// if (user.getUserID() == tempUser.getUserID()) {
		// // add movie to user's HM
		// user.addRating(movie, userRating);
		// }
		// // user has NOT been created
		// else {
		// // create new user
		// User newUser = new User(userID);
		//
		// // add movie to user's HM
		// newUser.addRating(movie, userRating);
		//
		// // add user to users' ArrayList
		// users.add(newUser);
		// user = newUser;
		// break;
		// }
		// }
		// }
		//
		//
		// }
	}

	/**
	 * Creates a hashmap of item (movie) objects and a hashset of all the users
	 * who have rated that movie (the user's rating for that movie)
	 * 1::122::5::838985046
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

		boolean foundUser = false;
		boolean foundMovie = false;
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
	}
}

//
//
// if (userList)
// // if movies' ArrayList is empty, first movie is added
// if (movies.isEmpty()) {
// movies.add(movie);
//
// HashSet<User> tempUserSet = new HashSet<User>();
// tempUserSet.add(user);
//
// // populate movie HM of movie with current user
// userList.put(movie, tempUserSet);
// }
// // movies' ArrayList is NOT empty
// else {
//
// // find movie in movies' ArrayList
// for (Item tempMovie : movies) {
// // movie has already been created
// if (movie.getMovieID() == tempMovie.getMovieID()) {
// // add user to movie's Hashset
// HashSet<User> tempUserSet = userList.get(movie);
// tempUserSet.add(user);
//
// userList.put(tempMovie, tempUserSet);
// }
//
// // movie has NOT been created yet
// else {
// // create new movie
// Item newMovie = new Item(movieID);
//
// // create new HashSet for movie
// HashSet<User> newSet = new HashSet<User>();
//
// // add user to movies's HS
// newSet.add(user);
//
// // add HS to movie's value in HM
// userList.put(newMovie, newSet);
//
// // add user to users' ArrayList
// movies.add(newMovie);
// movie = newMovie;
// break;
// }
// }
// }
// }
// // Prints out HM for each movie
// for (Item tempMovie : userList.keySet()) {
// int key = tempMovie.getMovieID();
// HashSet<User> setOfUsers = userList.get(tempMovie);
// System.out.println(key + " " + setOfUsers);
// }
// }
