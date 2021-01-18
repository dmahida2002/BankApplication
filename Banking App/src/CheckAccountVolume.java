import java.util.HashMap;

public class CheckAccountVolume {
	
	private static HashMap<String, Integer> balance = new HashMap<String, Integer>();
	
	public static void modifyBalance(String text, int amount) {
		
		balance.put(text, amount);
	}

	public static int getBalance(String providedUser) {
		
		return balance.get(providedUser).intValue();
	}
}
