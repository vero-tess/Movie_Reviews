import java.util.HashMap;

public interface Prediction {

	public double predictPreference(User user, Item item);

	public HashMap<Item, Double> produceRatings(User user, int threshold);

}
