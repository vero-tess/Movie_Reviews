import java.util.HashMap;
import java.util.HashSet;

public class Library {

	// HashSet<User>
	private HashMap<Item, HashSet<User>> movieList;
	private HashSet<User> userList;

	
	public Library() {
		movieList = new HashMap<Item, HashSet<User>>();
		userList = new HashSet<User>();
	}

	

	public HashMap<Item, HashSet<User>> getMovieList() {
		return movieList;
	}



	public void setMovieList(HashMap<Item, HashSet<User>> movieList) {
		this.movieList = movieList;
	}



	public HashSet<User> getUserList() {
		return userList;
	}



	public void setUserList(HashSet<User> userList) {
		this.userList = userList;
	}
	
	

}
