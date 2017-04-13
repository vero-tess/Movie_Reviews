import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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

		HashSet<User> userList = library.getMovieList().get(item);
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
	public HashMap<Item, Double> produceRatings(User user, int threshold) {
		HashMap<Item, HashSet<User>> movieList = library.getMovieList();
		HashMap<Item, Double> predictions = new HashMap();
		HashMap<Item, Double> nHighestPredictions = new HashMap();
		for (Item i : movieList.keySet()) {

			if (movieList.get(i).contains(user)) {
				// don't do anything
			} else {
				double prediction = predictPreference(user, i);
				predictions.put(i, prediction);
			}
		}

		Map<Item, Double> sortByValue = sortByValue(predictions);

		Iterator it = sortByValue.entrySet().iterator();
		int i = 0;
		while (i < n) {
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
