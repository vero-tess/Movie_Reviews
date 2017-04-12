
public interface Prediction {

	public void predictPreference(User user, Item item); 
	
	public void produceRatings(User user, int threshold);
	
	
}
