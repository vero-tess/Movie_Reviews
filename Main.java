
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item(); 
		Item item4 = new Item();
		
		user1.addRating(item1, 5);
		user1.addRating(item2, 2);
		user1.addRating(item3, 4);
		
		System.out.println("user1 average: "+user1.getAverageRatings());
		user2.addRating(item1, 4);
		user2.addRating(item2, 3);
		user2.addRating(item4, 5);
		System.out.println("user2 average: "+user2.getAverageRatings());
		
		user3.addRating(item1, 2);
		user3.addRating(item3, 4);
		user3.addRating(item4, 3);
		System.out.println("user3 average: "+user3.getAverageRatings());
		
		PearsonCorrelation p = new PearsonCorrelation();
		System.out.println("similarity between 1 and 2: "+p.returnSimilarity(user1, user2));
		
		System.out.println("similarity between 1 and 3: " + p.returnSimilarity(user1, user3));
		
		
		
	}

}
