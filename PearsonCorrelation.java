import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;

public class PearsonCorrelation implements Similarity {

	DecimalFormat df;

	public PearsonCorrelation() {

		df = new DecimalFormat("##.###");
		df.setRoundingMode(RoundingMode.UP);
	}

	@Override
	public double returnSimilarity(User user1, User user2) {

		double numerator = numerator(user1, user2);
		double denominator = denominator(user1, user2);
		double similarity = numerator / denominator;
		similarity = Double.parseDouble(df.format(similarity));
		return similarity;
	}

	public double numerator(User user1, User user2) {

		double numerator = 0;
		HashMap<Item, Double> user1Ratings = user1.getMovieRatings();
		HashMap<Item, Double> user2Ratings = user2.getMovieRatings();

		for (Item i : user1Ratings.keySet()) {

			if (user2Ratings.containsKey(i)) {
				double value = (user1Ratings.get(i) - user1.getAverageRatings())
						* (user2Ratings.get(i) - user2.getAverageRatings());
				numerator = numerator + value;
			}
		}
		
		return numerator;
	}

	public double denominator(User user1, User user2) {
		double denominator = 0;
		HashMap<Item, Double> user1Ratings = user1.getMovieRatings();
		HashMap<Item, Double> user2Ratings = user2.getMovieRatings();
		double value1 = 0;
		double value2 = 0;

		for (Item i : user1Ratings.keySet()) {

			if (user2Ratings.containsKey(i)) {

				value1 = value1 + Math.pow(user1Ratings.get(i) - user1.getAverageRatings(), 2);
				value2 = value2 + Math.pow(user2Ratings.get(i) - user2.getAverageRatings(), 2);
			}
		}
		value1 = Math.sqrt(value1);
		value2 = Math.sqrt(value2);
		denominator = value1 * value2;
		
		return denominator;

	}

}
