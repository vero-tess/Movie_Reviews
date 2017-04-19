import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Parses the lines of a file and populates
 * all of the data structures used.
 *
 */
public class FileParser {

	
	/* Instance variables */
	private ArrayList<String> lines;
	private HashMap<Item, HashSet<User>> itemList;
	private HashSet<User> userList;

	
	/* Constructor */
	public FileParser(ArrayList<String> lines) {
		itemList = new HashMap<Item, HashSet<User>>();
		userList = new HashSet<User>();
		this.lines = lines;
		if (lines == null) {
			throw new NullPointerException("No contents in the file.");
		}
	}

	
	/**
	* Creates a hashset of user objects.
	*/
	public void createUserList() {

		String userId;
		String itemId;
		String userRatingString;
//		int userID;
//		int itemID;
		double userRating;
		User user;

		for (String line : lines) {
			String delims = "::";
			String[] tokens = line.split(delims);

			userId = tokens[0];
//			userID = Integer.parseInt(userIdString);

			if (!userList.isEmpty()) {
				boolean found = false;
				for (User u : userList) {
					if (u.getUserID().equalsIgnoreCase(userId)) {
						found = true;
						break;
					}
				}
				if (found == false) {
					user = new User(userId);
					userList.add(user);
				}
			} else {
				user = new User(userId);
				userList.add(user);
			}
		}
	}

	
	/**
	 * Creates a hashmap of item objects and a hashset of all the users
	 * who have rated that item (the user's rating for that item) AND
	 * creates a hashmap for each user and populates it with items that they have
	 * rated, including their rating
	 */
	public void createItemMap() {

		String userId;
		String itemId;
		String userRatingString;
		double userRating;
		User user;
		Item item; // key in HM

		for (String line : lines) {
			String lineTemp;
			String delims = "::|\";\"|\"";
			String[] tokens;
			

			if(line.startsWith("\"")){
			    //start at the 1st character not the 0th
				line = line.substring(1);
			    tokens = line.split(delims);
			}
			else {
				tokens = line.split(delims);
			}
			    
			userId = tokens[0];
			System.out.println("User ID: " + userId);
//			userID = Integer.parseInt(userIdString);
			// user = new User(userID);


			itemId = tokens[1];
//			itemID = Integer.parseInt(movieIdString);
			System.out.println("Item ID: " + itemId);


			userRatingString = tokens[2];
			userRating = Double.parseDouble(userRatingString);
			System.out.println("User Rating: " + userRating);


			// the movie list is not empty
			boolean foundUser = false; 
			boolean foundItem = false;
			if (!itemList.isEmpty()) {

				for (Item i : itemList.keySet()) {

					// the movie exists in the hashmap
					if (i.getItemID().equalsIgnoreCase(itemId)) {
						HashSet<User> tempUser = itemList.get(i);
						foundItem = true; // found the movie!
						for (User u : userList) {

							// user exists in the userList!
							if (u.getUserID().equalsIgnoreCase(userId)) {
								tempUser.add(u); // add the user for the movie
								itemList.put(i, tempUser); // re-put the
															// movie-userList
															// pair in HashMap

								u.addRating(i, userRating); // add the movie to
															// the user Ratings

								foundUser = true; // found the user!
								break;
							}
						}
						// if user is not found
						if (foundUser == false) {
							// create new user
							user = new User(userId);
							// add the movie for the user
							user.addRating(i, userRating);
							// add the user for the movie
							tempUser.add(user);
							// put the movie-userList pair on the hashmap
							itemList.put(i, tempUser);
							// put the user on the HashSet of users
							userList.add(user);
						}
						break;
					}
				}
				// if item is not found
				if (foundItem == false) {
					// create movie object
					item = new Item(itemId);
					// create HashSet of users
					HashSet<User> users = new HashSet<User>();

					for (User u : userList) {
						// user exists!
						if (u.getUserID().equalsIgnoreCase(userId)) {
							// add user to the HashSet for the movie
							users.add(u);
							// add the movie rating to the user
							u.addRating(item, userRating);
							// put the movie-userList pair on the hashMap
							itemList.put(item, users);

							foundUser = true; // user was found!
							break;
						}
					}
					// user was not found!
					if (foundUser == false) {
						// create new user
						user = new User(userId);
						// add the movie for the user
						user.addRating(item, userRating);
						// add the user for the movie
						users.add(user);
						itemList.put(item, users);
						userList.add(user);
					}
				}
			} else {
				// HashMap of movies was empty!
				// create new movie
				item = new Item(itemId);
				HashSet<User> users = new HashSet<User>(); // create HashSet of
															// users

				for (User u : userList) {
					// found the user
					if (u.getUserID().equalsIgnoreCase(userId)) {
						// add to HashSet for the movie
						users.add(u);
						// add the movie for the user
						u.addRating(item, userRating);

						itemList.put(item, users);
						foundUser = true;
						break;
					}
				}
				if (foundUser == false) {
					// user was not found, so create user object
					user = new User(userId);
					// add the movie for the user
					user.addRating(item, userRating);
					// add the user for the movie
					users.add(user);
					itemList.put(item, users);
					userList.add(user);
				}
			}
		}
	
		// Print itemList
//		for(Item i: itemList.keySet()) { 
//			System.out.println("item: " + i.getItemID()); 
//			System.out.print("Users: ");
//			for(User u: itemList.get(i)) { 
//				System.out.print(u.getUserID()+ " ");
//			}
//			System.out.println();
//		}
		
		// Print userList
//		for(User u: userList) { 
//			System.out.println("User: "+u.getUserID());
//			for(Item i: u.getItemRatings().keySet()) { 
//				System.out.print("Item "+i.getItemID()+ ", Rating: " + u.getItemRatings().get(i) + " ");
//			}
//			System.out.println();
//		}
	}
	
	
	/**
	 * Getter for ItemList
	 * @return itemList
	 */
	public HashMap<Item, HashSet<User>> getItemsList() {
		return itemList;
	}
	
	
	/**
	 * Getter for userList
	 * @return userList
	 */
	public HashSet<User> getUsersList() {
		return userList;
	}

	
}