
public class BankManagement {	
	
	public static void main(String[] args) {
		
		LoginWindow login = new LoginWindow();
		
		login.setFrame(600, 350);
		
		login.setLabel("Username: ", "Password: ", "Chase");
		login.setLocation(150, 100, 80, 25);
		
		login.addLogins("Danel322", "Scoops79");
		login.addLogins("dmahida06", "JuneSixJune6");
		login.addLogins("runner3222", "Scoops79");
		
		login.finalizePanel();
	}

}