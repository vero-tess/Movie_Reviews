import java.util.HashMap;

/**
 * Class representing a user object.
 */
public class User {

	/* Instance Variables */
	HashMap<Item, Double> ratings;
	double averageRatings;
	double totalRatings;
	int userID;
	double movieRating;

	
	/* Constructor */
	public User(int userID) {
		ratings = new HashMap<Item, Double>();
		averageRatings = 0;
		totalRatings = 0;
		this.userID = userID;
	}
	
	
	/**
	 * Getter for movie ratings
	 * @return hashmap of movie ratings
	 */
	public HashMap<Item, Double> getRatings() {
		return ratings;
	}

	
	/**
	 * Getter for average ratings
	 * @return averageRatings
	 */
	public double getAverageRatings() {
		return averageRatings;
	}

	
	/**
	 * Adds a rating to the movieRatings map
	 * @param item item to be added
	 * @param rating of item
	 */
	public void addRating(Item item, double rating) {
		ratings.put(item, rating);
		totalRatings = totalRatings + rating;
		averageRatings = totalRatings / ratings.size();
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
	
	
}
