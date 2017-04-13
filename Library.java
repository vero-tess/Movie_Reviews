import java.util.HashMap;
import java.util.HashSet;

public class Library {

	// HashSet<User>
	private HashMap<Item, HashSet<User>> userList;

	
	public Library(HashMap<Item, HashSet<User>> userList) {
		this.userList = userList;
	}

	

	public HashMap<Item, HashSet<User>> getUserList() {
		return userList;
	}



	public void setUserList(HashMap<Item, HashSet<User>> userList) {
		this.userList = userList;
	}
	
	

}
