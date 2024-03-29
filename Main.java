import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the Recommendation Tool.");
		System.out.println("Enter the file which you wish to use and press ENTER: ");
		String inputFile = in.nextLine();

		// check for validity of inputFile
		File f = new File(inputFile);
		if (f.exists() && !f.isDirectory()) {

			// read in file
			FileReader fr = new FileReader(inputFile);
			ArrayList<String> lines = fr.getLines();

			// parse file contents and create data structures
			FileParser fp = new FileParser(lines);
			fp.createUserList();
			fp.createItemMap();

			HashMap<Item, HashSet<User>> itemMap = fp.getItemsList();
			HashSet<User> usersList = fp.getUsersList();

			Library library = new Library();
			library.setItemList(itemMap);
			library.setUserList(usersList);
			
			//check which is the best prediction model based on the data
			CheckBestPredictor check = new CheckBestPredictor(library);
			Prediction prediction = check.returnBestPredictor();
			
			
			boolean flag = false;
			while (!flag) {

				System.out.println("If you would like to predict the user's likely preference for an item type 1,"
						+ " if you would like to display the n-highest predicted preferences for a user type 2.");
				String userChoice = in.nextLine();

				// part A
				if (userChoice.equalsIgnoreCase("1")) {

					flag = true;

					System.out.println("Type the ID of the USER and press ENTER: "); // 2 for testing															
					String userID = in.nextLine();

					System.out.println("Type the ID of the ITEM and press ENTER: "); // 4 for testing																		
					String itemID = in.nextLine();

					User user = null;
					for (User u : library.getUserList()) {
						if (u.getUserID().equalsIgnoreCase(userID)) {
							user = u;
							break;
						}
					}

					Item item = null;
					for (Item i : library.getItemList().keySet()) {
						if (i.getItemID().equalsIgnoreCase(itemID)) {
							item = i;
							break;
						}
					}
				
					double predictionReturn = prediction.predictPreference(user, item);
					System.out.println("Prediction: " + predictionReturn);
					System.out.println(" ");
				}
				// part B
				else if (userChoice.equalsIgnoreCase("2")) {

					flag = true;

					System.out.println("Type the ID of the USER and press ENTER: "); // 2 for testing
					String userID = in.nextLine();

					System.out.println("Type the THRESHOLD and press ENTER: "); // 2 for testing
					int threshold = in.nextInt();

					User user = null;
					for (User u : library.getUserList()) {
						if (u.getUserID().equalsIgnoreCase(userID)) {
							user = u;
							break;
						}
					}

					HashMap<Item, Double> ratings = prediction.produceRatings(user, threshold); // 2 for testing
					for (Item i : ratings.keySet()) {
						System.out.println("Item: " + i.getItemID() + " | " + "Rating: " + ratings.get(i));
					}
				}
				// error
				else {
					System.out.println("You have not slected a valid value. Please try again.");
				}
			}
		} else {
			System.out.println("Something was wrong with your input file.");
			System.out.println("Make sure your file is a valid file or file path and try again.");
		}
		in.close();
	}
}
