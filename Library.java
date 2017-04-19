import java.util.HashMap;
import java.util.HashSet;

/**
 * Represents a library. Similar to a Netflix library. It contains a HashSet of
 * all users and a HashMap of all movies and the users that rated that specific
 * movie.
 *
 */
public class Library {

	/* Instance Variables */
	private HashMap<Item, HashSet<User>> itemList;
	private HashSet<User> userList;

	/* Constructor */
	public Library() {
		itemList = new HashMap<Item, HashSet<User>>();
		userList = new HashSet<User>();
	}

	/**
	 * Setter for movieList
	 * 
	 * @param movieList
	 */
	public void setItemList(HashMap<Item, HashSet<User>> itemList) {
		this.itemList = itemList;
	}

	/**
	 * Getter for userList
	 * 
	 * @return userList
	 */

	public HashSet<User> getUserList() {
		return userList;
	}

	/**
	 * Setter for userList
	 * 
	 * @param userList
	 */
	public void setUserList(HashSet<User> userList) {
		this.userList = userList;
	}

	public HashMap<Item, HashSet<User>> getItemList() {
		return itemList;
	}

}
