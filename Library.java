import java.util.HashMap;
import java.util.HashSet;

/**
 * Represents a library.
 * Similar to a Netflix library.
 * It contains a HashSet of all users
 * and a HashMap of all movies and the users that rated that specific movie.
 *
 */
public class Library {

	/* Instance Variables*/
	private HashMap<Item, HashSet<User>> movieList;
	private HashSet<User> userList;

	/* Constructor*/
	public Library() {
		movieList = new HashMap<Item, HashSet<User>>();
		userList = new HashSet<User>();
	}

	
	/**
	 * Getter for movieList
	 * @return movieList
	 */
	public HashMap<Item, HashSet<User>> getMovieList() {
		return movieList;
	}


	/**
	 * Setter for movieList
	 * @param movieList
	 */
	public void setMovieList(HashMap<Item, HashSet<User>> movieList) {
		this.movieList = movieList;
	}


	/**
	 * Getter for userList
	 * @return userList
	 */
	public HashSet<User> getUserList() {
		return userList;
	}


	/**
	 * Setter for userList
	 * @param userList
	 */
	public void setUserList(HashSet<User> userList) {
		this.userList = userList;
	}
	
	

}
