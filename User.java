import java.util.HashMap;

public class User {

	HashMap<Item, Double> movieRatings;
	double averageRatings;
	double totalRatings;
	int userID;
	double movieRating;

	/* Constructor */
	public User(int userID) {
		movieRatings = new HashMap<Item, Double>();
		averageRatings = 0;
		totalRatings = 0;
		this.userID = userID;
	}
	
	

	public HashMap<Item, Double> getMovieRatings() {

		return movieRatings;
	}

	
	
	public double getAverageRatings() {

		return averageRatings;
	}

	
	
	public void addRating(Item item, double rating) {

		movieRatings.put(item, rating);
		totalRatings = totalRatings + rating;
		averageRatings = totalRatings / movieRatings.size();
	}



	public int getUserID() {
		return userID;
	}



	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
}
