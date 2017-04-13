import java.util.HashMap;

public class Item {

	
	/* Instance Variables */
	int userID;
	int movieID;

	
	/* Constructor */
	public Item(int movieID) {
		userID = 0;
		this.movieID = movieID;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public int getMovieID() {
		return movieID;
	}


	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}	
	
	
}
