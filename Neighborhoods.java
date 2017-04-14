import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* Creates the neighborhood of size n of users for a given user. */
public class Neighborhoods implements Neighborhood {

	
	/* Instance Variables */
	int n; 
	
	
	/* Constructor*/
	public Neighborhoods(int n) { 
		this.n = n;
	}
	
	
	/**
	 * Generates a neighborhood of n users.
	 * @param hashmap of similarities
	 * @return a map of all neighbors and their similarity to a given user
	 */
	@Override
	public Map<User, Double> generateNeighborhood(HashMap<User, Double> similarities) {
		HashMap<User, Double> neighborhood = new HashMap(); 
		
		Map<User, Double> sortByValue = sortByValue(similarities);
		
		Iterator it = sortByValue.entrySet().iterator();
		int i = 0; 
		//if not enough similar users for neighborhood
		if (sortByValue.size() < n) { 
			neighborhood.putAll(sortByValue);
			return neighborhood;
		}
	
		while (i < n) { 
			Map.Entry pair = (Map.Entry)it.next();
			User user = (User) pair.getKey();
			Double value = (Double) pair.getValue();
			neighborhood.put(user, value);
			i++;
		}
		
		return neighborhood;
	}
	
	
	/**
	 * @param unsortedMap the unsorted map
	 * @return the sorted map
	 */
	public static Map sortByValue(Map unsortedMap) {
		Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}

}
