import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Neighborhoods implements Neighborhood {

	int n; 
	public Neighborhoods(int n) { 
		this.n = n;
		
	}
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
	
	public static Map sortByValue(Map unsortedMap) {
		Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}

}
