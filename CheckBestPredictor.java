import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * checks which model is most efficient
 *
 */
public class CheckBestPredictor {

	Library library;
	Random rand;

	public CheckBestPredictor(Library library) {
		this.library = library;
	}

	/**
	 * returns the most efficient model. Uses regression model to calculate most
	 * efficient model
	 * 
	 * @return - object of model efficient model
	 */
	public Prediction returnBestPredictor() {
		HashSet<User> users = library.getUserList();
		double pearsonCorrelationRegression = 0;
		double cosineSimilarityRegression = 0;
		double baselineRegression = 0;
		double sumOfResidualBase = 0;
		double sumOfResidualPearson = 0;
		double sumOfResidualCosine = 0;

		Similarity pearson = new PearsonCorrelation();
		Similarity cosine = new CosineSimilarity();
		Prediction pearsonPrediction = new Predictions(library, pearson);
		Prediction cosinePrediction = new Predictions(library, cosine);
		Prediction baselinePrediction = new BaseLine(library);

		// int index = rand.nextInt(users.size());
		User testUser = null;
		for (User u : users) {
			testUser = u;
			break;
		}

		HashMap<Item, Double> realRatings = testUser.getRatings();
		int itemCount = 0;
		double meanObserved = 0;
		double totalSumSquares = 0;
		for (Item i : realRatings.keySet()) {

			meanObserved = meanObserved + realRatings.get(i);
		}

		meanObserved = meanObserved / realRatings.size();

		for (Item i : realRatings.keySet()) {
			totalSumSquares = totalSumSquares + Math.pow((realRatings.get(i) - meanObserved), 2);

		}

		for (Item i : realRatings.keySet()) {

			double baseValue = baselinePrediction.predictPreference(testUser, i);
			double pearsonValue = pearsonPrediction.predictPreference(testUser, i);
			double cosineValue = cosinePrediction.predictPreference(testUser, i);

			sumOfResidualBase = sumOfResidualBase + Math.pow((realRatings.get(i) - baseValue), 2);
			sumOfResidualPearson = sumOfResidualPearson + Math.pow((realRatings.get(i) - pearsonValue), 2);
			sumOfResidualCosine = sumOfResidualCosine + Math.pow((realRatings.get(i) - cosineValue), 2);
		}

		baselineRegression = (double) 1 - (sumOfResidualBase / totalSumSquares);
		pearsonCorrelationRegression = (double) 1 - (sumOfResidualPearson / totalSumSquares);
		cosineSimilarityRegression = (double) 1 - (sumOfResidualCosine / totalSumSquares);

		if (Math.max(baselineRegression,
				Math.max(pearsonCorrelationRegression, cosineSimilarityRegression)) == baselineRegression) {
			System.out.println("Based on your data, the best model is Baseline Prediction.");
			return baselinePrediction;
		} else if (Math.max(baselineRegression,
				Math.max(pearsonCorrelationRegression, cosineSimilarityRegression)) == pearsonCorrelationRegression) {
			System.out.println("Based on your data, the best model is Pearson Correlation Prediction Model.");
			return pearsonPrediction;
		} else if (Math.max(baselineRegression,
				Math.max(pearsonCorrelationRegression, cosineSimilarityRegression)) == cosineSimilarityRegression) {
			System.out.println("Based on your data, the best model is Cosine Similarity Prediction Model.");
			return cosinePrediction;
		} else {
			return null;
		}

	}

}
