import java.util.HashMap;

/**
 * Class representing an item.
 *
 */
public class Item {

	/* Instance Variables */
	// String userID;
	String itemID;

	/* Constructor */
	public Item(String itemID) {
		// userID = 0;
		this.itemID = itemID;
	}

	/**
	 * Getter for movieID
	 * 
	 * @return movieID
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * Setter for movieID
	 * 
	 * @param movieID
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

}
