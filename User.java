import java.util.HashMap;

public class User {

	HashMap<Item, Double> movieRatings;
	double averageRatings;
	double totalRatings;

	public User() {
		movieRatings = new HashMap<Item, Double>();
		averageRatings = 0;
		totalRatings = 0;
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
}
