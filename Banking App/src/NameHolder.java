import java.util.HashMap;

public class NameHolder {
	
	protected static HashMap<String, String> nameStorage = new HashMap<String, String>();

	public static void addNames(String text, String name) {
		
		nameStorage.put(text, name);
	}
	
}
