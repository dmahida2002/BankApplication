import java.util.HashMap;

public class LoginHolder {
	
	protected static HashMap<String, String> loginStorage = new HashMap<String, String>();
	
	public static void addLogins(String username, String password) {
		
		loginStorage.put(username, password);
	}
	
}
