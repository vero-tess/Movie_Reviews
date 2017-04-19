import java.util.HashMap;

/**
 * Class representing an item.
 *
 */
public class Item {

	/* Instance Variables */
	String itemID;

	/* Constructor */
	public Item(String itemID) {
		// userID = 0;
		this.itemID = itemID;
	}

	/**
	 * Getter for itemID
	 * 
	 * @return itemID
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * Setter for itemID
	 * 
	 * @param itemID
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

}
