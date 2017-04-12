
public interface Prediction {

	public double predictPreference(User user, Item item);

	public double produceRatings(User user, int threshold);

}
