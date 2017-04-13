import java.util.HashMap;
import java.util.HashSet;

public class Library {

	// HashSet<User>
	private HashMap<Item, HashSet<User>> movieList;
	private HashSet<User> userList;

	
	public Library(HashMap<Item, HashSet<User>> movieList) {
		this.movieList = movieList;
	}

	

	public HashMap<Item, HashSet<User>> getMovieList() {
		return movieList;
	}



	public void setMovieList(HashMap<Item, HashSet<User>> movieList) {
		this.movieList = movieList;
	}
	
	

}
