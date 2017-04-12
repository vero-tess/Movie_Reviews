import java.util.HashMap;
import java.util.Map;

public interface Neighborhood {

	
	public Map<User, Double> generateNeighborhood(HashMap<User, Double> similarities);
}
