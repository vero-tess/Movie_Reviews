import java.util.Comparator;
import java.util.Map;

/**
 * Overriding the compare method by
 * implementing Comparator
 */
public class ValueComparator implements Comparator {

	/* Instance Variables */
	Map map;

	
	/* Constructor */
	public ValueComparator(Map map) {
		this.map = map;
	}

	
	/**
	 * Overriding the compare mathod
	 */
	@Override
	public int compare(Object o1, Object o2) {
		Comparable valueA = (Comparable) map.get(o1);
		Comparable valueB = (Comparable) map.get(o2);
		return valueB.compareTo(valueA);
	}

}
