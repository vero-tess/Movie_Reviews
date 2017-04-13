import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Predictions implements Prediction {

	private Library library;
	private PearsonCorrelation correlation;
	private Neighborhoods neighborhood;
	private final int n = 2;

	private DecimalFormat df;

	public Predictions(Library library) {

		this.library = library;
		correlation = new PearsonCorrelation();
		neighborhood = new Neighborhoods(n);

		df = new DecimalFormat("##.###");
		df.setRoundingMode(RoundingMode.DOWN);
	}

	@Override
	public double predictPreference(User user, Item item) {
		double prediction = 0;
		HashSet<User> userList = library.getMovieList().get(item);
		HashMap<User, Double> similarities = new HashMap();
		for (User u : userList) {
			similarities.put(u, correlation.returnSimilarity(user, u));
		}
		neighborhood.generateNeighborhood(similarities);

		double numerator = numerator(user, item, similarities);
		double denominator = denominator(similarities);

		if (denominator == 0) {
			prediction = 0;
		} else {
			prediction = user.getAverageRatings() + (numerator / denominator);
		}
		prediction = Double.parseDouble(df.format(prediction));
		return prediction;

	}

	private double numerator(User user, Item item, HashMap<User, Double> similarities) {
		double numerator = 0;

		for (User u : similarities.keySet()) {
			double rating = u.getMovieRatings().get(item);
			double value = similarities.get(u) * (rating - u.getAverageRatings());
			numerator = numerator + value;
		}

		return numerator;

	}

	private double denominator(HashMap<User, Double> similarities) {

		double denominator = 0;

		for (User u : similarities.keySet()) {
			denominator = denominator + Math.abs(similarities.get(u));
		}

		return denominator;

	}

	@Override
	public HashMap<Item, Double> produceRatings(User user, int threshold) {
	
		HashMap<Item, HashSet<User>> movieList = library.getMovieList();
		HashMap<Item, Double> predictions = new HashMap();

		HashMap<Item, Double> nHighestPredictions = new HashMap();
		for (Item i : movieList.keySet()) {

			if (movieList.get(i).contains(user)) {
				//if user's already rated movie, don't do anything 
			} else {
				//get a prediction for that user for the movie 
				double prediction = predictPreference(user, i);
				predictions.put(i, prediction);
			}
		}

		Map<Item, Double> sortByValue = sortByValue(predictions);
		
		Iterator it = sortByValue.entrySet().iterator();
		int i = 0;
		// if movie predictions is less than threshold n, return all predictions
		if (sortByValue.size() < threshold) {
			nHighestPredictions.putAll(sortByValue);
			return nHighestPredictions;
		}
		// else get top n
		while (i < threshold) {
			Map.Entry pair = (Map.Entry) it.next();
			Item item = (Item) pair.getKey();
			Double value = (Double) pair.getValue();
			nHighestPredictions.put(item, value);
			i++;
		}
		return nHighestPredictions;
	}

	public static Map sortByValue(Map unsortedMap) {
		Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}

}
