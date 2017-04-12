import java.util.HashMap;
import java.util.HashSet;

public class Predictions implements Prediction {

	private Library library;
	private PearsonCorrelation correlation;
	private Neighborhoods neighborhood;
	private final int n = 20;

	public Predictions(Library library) {

		this.library = library;
		correlation = new PearsonCorrelation();
		neighborhood = new Neighborhoods(20);

	}

	@Override
	public double predictPreference(User user, Item item) {

		HashSet<User> userList = library.getUserList().get(item);
		HashMap<User, Double> similarities = new HashMap();
		for (User u : userList) {
			similarities.put(u, correlation.returnSimilarity(user, u));
		}
		neighborhood.generateNeighborhood(similarities);

		double numerator = numerator(user, item, similarities);
		double denominator = denominator(similarities);

		double prediction = user.getAverageRatings() + (numerator / denominator);

		return prediction;

	}

	public double numerator(User user, Item item, HashMap<User, Double> similarities) {
		double numerator = 0;

		for (User u : similarities.keySet()) {
			double rating = u.getMovieRatings().get(item);
			double value = similarities.get(u) * (rating - u.getAverageRatings());
			numerator = numerator + value;
		}

		return numerator;

	}

	public double denominator(HashMap<User, Double> similarities) {

		double denominator = 0;

		for (User u : similarities.keySet()) {
			denominator = denominator + Math.abs(similarities.get(u));
		}

		return denominator;

	}

	@Override
	public double produceRatings(User user, int threshold) {
		// TODO Auto-generated method stub
		return 0;
	}

}
