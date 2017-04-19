import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Creates the similarity, or the correlation coefficient between two users.
 *
 */
public class PearsonCorrelation implements Similarity {

	/* Instance Variables */
	DecimalFormat df;

	/* Constructor */
	public PearsonCorrelation() {
		df = new DecimalFormat("##.###");
		df.setRoundingMode(RoundingMode.UP);
	}

	/**
	 * Computes the similarity between two users.
	 * 
	 * @param user1
	 *            the first user
	 * @param user2
	 *            the second user
	 */
	@Override
	public double returnSimilarity(User user1, User user2) {
		double similarity;
		double numerator = numerator(user1, user2);
		double denominator = denominator(user1, user2);
		if (denominator == 0) {
			similarity = 0;
		} else {
			similarity = numerator / denominator;
		}

		similarity = Double.parseDouble(df.format(similarity));
		return similarity;
	}

	/**
	 * Creates the numerator of the similarity equation.
	 * 
	 * @param user1
	 * @param user2
	 * @return value of numerator
	 */
	private double numerator(User user1, User user2) {

		double numerator = 0;
		HashMap<Item, Double> user1Ratings = user1.getRatings();
		HashMap<Item, Double> user2Ratings = user2.getRatings();

		for (Item i : user1Ratings.keySet()) {

			if (user2Ratings.containsKey(i)) {
				double value = (user1Ratings.get(i) - user1.getAverageRatings())
						* (user2Ratings.get(i) - user2.getAverageRatings());
				numerator = numerator + value;
			}
		}

		return numerator;
	}

	/**
	 * Creates the denominator of the similarity equation.
	 * 
	 * @param user1
	 * @param user2
	 * @return the value of the denominator
	 */
	private double denominator(User user1, User user2) {
		double denominator = 0;
		HashMap<Item, Double> user1Ratings = user1.getRatings();
		HashMap<Item, Double> user2Ratings = user2.getRatings();
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
