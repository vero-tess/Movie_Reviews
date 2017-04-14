import java.util.HashMap;

/**
 * Class representing an item.
 *
 */
public class Item {


	/* Instance Variables */
	int userID;
	int movieID;

	
	/* Constructor */
	public Item(int movieID) {
		userID = 0;
		this.movieID = movieID;
	}


	/**
	 * Getter for userID
	 * @return userID
	 */
	public int getUserID() {
		return userID;
	}


	/**
	 * Setter for userID
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}


	/**
	 * Getter for movieID
	 * @return movieID
	 */
	public int getMovieID() {
		return movieID;
	}


	/**
	 * Setter for movieID
	 * @param movieID
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}	
	
	
}
