import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents the baseline model for predicting preferences
 */
public class BaseLine implements Prediction {

	Library library;
	private DecimalFormat df;
	double overallRatings;

	/**
	 * This is the constructor for the class
	 * 
	 * @param library
	 */
	public BaseLine(Library library) {

		this.library = library;
		df = new DecimalFormat("##.###");
		df.setRoundingMode(RoundingMode.DOWN);
		overallRatings = calculateOverallRating();
	}

	/**
	 * Predicts the preference of an item for a user
	 * 
	 * @param user
	 *            the user for which the prediction is being made
	 * @param item
	 *            the item of which to predict the preference
	 */
	@Override
	public double predictPreference(User user, Item item) {

		double preference = 0;
		double baselineUser = calculateUserBaseline(user);
		double baselineItem = calculateBaselineItem(user, item);
		preference = overallRatings + baselineUser + baselineItem;
		preference = Double.parseDouble(df.format(preference));
		return preference;
	}

	/**
	 * Computes the predicted movies for a user given a threshold
	 * 
	 * @param user
	 *            the user for which to predict the movies
	 * @param threshold
	 *            the ceiling for the number of movies to predict
	 */
	@Override
	public HashMap<Item, Double> produceRatings(User user, int threshold) {
		HashMap<Item, HashSet<User>> itemList = library.getItemList();
		HashMap<Item, Double> predictions = new HashMap();

		HashMap<Item, Double> nHighestPredictions = new HashMap();
		for (Item i : itemList.keySet()) {

			if (itemList.get(i).contains(user)) {
				// if user's already rated movie, don't do anything
			} else {
				// get a prediction for that user for the movie
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

	/**
	 * Calculate the baseline for the user
	 * 
	 * @param user
	 * @return the baseline prediction for the user
	 */
	private double calculateUserBaseline(User user) {
		double baselineUser = 0;

		HashSet<User> userList = library.getUserList();
		HashMap<Item, HashSet<User>> itemList = library.getItemList();
		HashMap<Item, Double> userRatings = user.getRatings();

		double itemCardinality;
		if (userRatings.size() == 0) {
			itemCardinality = 0;
		} else {

			itemCardinality = (double) 1 / (userRatings.size());
		}

		for (Item i : userRatings.keySet()) {

			baselineUser = baselineUser + (userRatings.get(i) - overallRatings);

		}

		baselineUser = baselineUser * itemCardinality;

		return baselineUser;
	}

	/**
	 * Calculate the baseline prediction for the item
	 * 
	 * @param user
	 * @param item
	 * @return the baseline prediction for the user
	 */
	private double calculateBaselineItem(User user, Item item) {
		double baselineItem = 0;
		HashSet<User> userList = library.getUserList();
		HashMap<Item, HashSet<User>> itemList = library.getItemList();
		HashSet<User> itemUsers = itemList.get(item);
		double userCardinality;
		if (itemUsers.size() == 0) {
			userCardinality = 0;
		} else {
			userCardinality = (double) 1 / (itemUsers.size());
		}

		for (User u : itemUsers) {
			double baselineUser = calculateUserBaseline(u);

			double rating = u.getRatings().get(item);

			baselineItem = baselineItem + (rating - baselineUser - overallRatings);

		}
		baselineItem = baselineItem * userCardinality;
		return baselineItem;
	}

	public double calculateOverallRating() {
		double totalRating = 0;
		double total = 0;
		HashSet<User> userList = library.getUserList();
		HashMap<Item, HashSet<User>> itemList = library.getItemList();

		for (User u : userList) {

			HashMap<Item, Double> items = u.getRatings();
			for (Item i : items.keySet()) {
				total++;
				totalRating = totalRating + items.get(i);
			}
		}

		overallRatings = totalRating / total;

		return overallRatings;
	}

	/**
	 * Sort an unsorted map
	 * 
	 * @param unsortedMap
	 *            the unsorted map
	 * @return the sorted map
	 */
	public static Map sortByValue(Map unsortedMap) {
		Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}

}
