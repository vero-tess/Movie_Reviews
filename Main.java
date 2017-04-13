import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/////////// File Reading/Parsing Testing ///////////////
		
		FileReader fr = new FileReader("ratings_test2.txt");
		ArrayList<String> lines = fr.getLines();
		
		FileParser2 fp = new FileParser2(lines);
		fp.createUserList();
		fp.createMoviesMap();
		
		HashMap<Item, HashSet<User>> moviesMap = fp.getMoviesList();
		HashSet<User> usersList = fp.getUsersList();
		System.out.println("Users List: ");
		
		
		
		Library library = new Library();
		library.setMovieList(moviesMap);
		library.setUserList(usersList);

		for (User user : library.getUserList()) {
			System.out.println("User ID: " + user.getUserID());
		}
		
		System.out.println("Movies List: ");
		
		for(Item i: library.getMovieList().keySet()) { 
			System.out.println(i.getMovieID());
		}
		Predictions prediction = new Predictions(library);
		User user = null; 
		for(User u: library.getUserList()) { 
			if (u.getUserID() == 2) { 
				user = u;
				break;
			}
		}
	//	System.out.println("User:" + user);
		//System.out.println("");
		Item movie = null;
		
		for(Item i: library.getMovieList().keySet()) { 
			if (i.getMovieID() == 4) { 
				movie = i;
				break;
			}
		}
	//	System.out.println("Movie: " + movie);
 		
	//	double predictionReturn = prediction.predictPreference(user, movie);
		
		HashMap<Item, Double> ratings = prediction.produceRatings(user, 2);
		
		for(Item i: ratings.keySet()) { 
			System.out.println(i.getMovieID() + " : " + ratings.get(i));
		}
		//System.out.println("Prediction: " + predictionReturn);
		
		///////////////////////////////////////////////////////
		

//		User user1 = new User();
//		User user2 = new User();
//		User user3 = new User();
//
//		Item item1 = new Item();
//		Item item2 = new Item();
//		Item item3 = new Item();
//		Item item4 = new Item();
//
//		user1.addRating(item1, 5);
//		user1.addRating(item2, 2);
//		user1.addRating(item3, 4);
//
//		System.out.println("user1 average: " + user1.getAverageRatings());
//		user2.addRating(item1, 4);
//		user2.addRating(item2, 3);
//		user2.addRating(item4, 5);
//		System.out.println("user2 average: " + user2.getAverageRatings());
//
//		user3.addRating(item1, 2);
//		user3.addRating(item3, 4);
//		user3.addRating(item4, 3);
//		System.out.println("user3 average: " + user3.getAverageRatings());
//
//		PearsonCorrelation p = new PearsonCorrelation();
//		System.out.println("similarity between 1 and 2: " + p.returnSimilarity(user1, user2));
//
//		System.out.println("similarity between 1 and 3: " + p.returnSimilarity(user1, user3));
//
//		Neighborhoods n = new Neighborhoods(2);
//
//		HashMap<User, Double> test = new HashMap();
//
//		test.put(user1, 2.0);
//		test.put(user2, 1.0);
//		test.put(user3, 10.0);
//
//		test.put(user1, 20.0);
//		test.put(user2, 11.0);
//		test.put(user3, -10.0);
//
//		Map<User, Double> test1 = n.generateNeighborhood(test);
//
//		for (User u : test1.keySet()) {
//			System.out.println(test.get(u));
//		}
	}

}
