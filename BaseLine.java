import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;

public class BaseLine implements Prediction {

	Library library;
	private DecimalFormat df;
	double overallRatings;

	public BaseLine(Library library) {

		this.library = library;
		df = new DecimalFormat("##.###");
		df.setRoundingMode(RoundingMode.DOWN);
		overallRatings = calculateOverallRating();
	}

	@Override
	public double predictPreference(User user, Item item) {

		double preference = 0;
		double baselineUser = calculateUserBaseline(user);
		double baselineItem = calculateBaselineItem(user, item);
		preference = overallRatings + baselineUser + baselineItem;
		preference = Double.parseDouble(df.format(preference));
		return preference;
	}

	@Override
	public HashMap<Item, Double> produceRatings(User user, int threshold) {
		// TODO Auto-generated method stub
		return null;
	}

	public double calculateUserBaseline(User user) {
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

	public double calculateBaselineItem(User user, Item item) {
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

}
