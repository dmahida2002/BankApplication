
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginWindow implements ActionListener {
	
	private int attempts;
	
	private String providedUser;
	private String providedPass;
	
	private JFrame loginFrame;
	private JPanel loginPanel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel bankTitle;
	private JTextField usernameInput;
	private JTextField passwordInput;
	private JButton loginButton;
	private JButton closeButton;
	
	private HashMap<String, String> loginStorage = new HashMap<String, String>();
	
	public void setFrame(int l, int w) {
		
		loginPanel = new JPanel();
		
		loginFrame = new JFrame();
		loginFrame.setSize(l, w);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.add(loginPanel);
		loginFrame.setLocationRelativeTo(null);
		
		setButton();
	}
	
	private void setPanel() {
		
		loginPanel.setLayout(null);
		
		loginPanel.add(bankTitle);
		loginPanel.add(usernameLabel);
		loginPanel.add(passwordLabel);
		loginPanel.add(usernameInput);
		loginPanel.add(passwordInput);
		loginPanel.add(loginButton);
		loginPanel.add(closeButton);
		
	}
	
	private void setButton() {
		
		loginButton = new JButton("Login");
		closeButton = new JButton("Exit");
		
		loginButton.addActionListener(this);
		closeButton.addActionListener(this);
	}
	
	public void setLabel(String uLabel, String pLabel, String bLabel) {
		
		String title = "Welcome to " + bLabel;
		
		usernameLabel = new JLabel(uLabel);
		passwordLabel = new JLabel(pLabel);
		bankTitle = new JLabel(title);
		
		usernameInput = new JTextField(10);
		passwordInput = new JTextField(15);
	}
	
	public void setLocation(int one, int two, int three, int four) {
		
		bankTitle.setBounds((one + 90), (two - 60), (three + 100), four);
		
		usernameLabel.setBounds(one, two, three, four);
		passwordLabel.setBounds(one, (two + 40), three, four);
		
		usernameInput.setBounds((one + 130), two, (three + 85), four);
		passwordInput.setBounds((one + 130), (two + 40), (three + 85), four);
		
		loginButton.setBounds((one + 65), (two + 120), (three + 85), 25);
		closeButton.setBounds((one + 65), (two + 170), (three + 85), 25);
	}
	
	public void addLogins(String username, String password) {
		
		loginStorage.put(username, password);
	}
	
	public void finalizePanel() {
		
		setPanel();
		loginFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		attempts += 1;
		
		if(closeButton.getModel().isArmed()) {
			
			int result = JOptionPane.showConfirmDialog(loginFrame,"Are you sure you want to exit?", "Exit App?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION) {
				
				System.exit(0);
			}
		}
		
		if(loginButton.getModel().isArmed()) {
			
			providedUser = usernameInput.getText();
			providedPass = passwordInput.getText();
		
			if(loginStorage.containsKey(providedUser) && loginStorage.containsValue(providedPass)) {
			
				System.out.println("The login was successful\n");
			
				loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
			}
		
			else if(loginStorage.containsKey(providedUser) == true && loginStorage.containsValue(providedPass) == false) {
			
				System.out.println("Username: Correct\nPassword: Wrong\n");
			
				if(attempts >= 3) {
				
					System.exit(0);
				}
			
				JOptionPane.showMessageDialog(null, "\n\nYour username or password was incorrect\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
		
			else if(loginStorage.containsKey(providedUser) == false && loginStorage.containsValue(providedPass) == true) {
			
				System.out.println("Username: Wrong\nPassword: Correct\n");
			
				if(attempts >= 3) {
					
					JOptionPane.showMessageDialog(null, "\n\nToo many attempts have been made\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
					
					System.exit(0);
				}
			
				JOptionPane.showMessageDialog(null, "\n\nYour username or password was incorrect\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
		
			else if(loginStorage.containsKey(providedUser) == false && loginStorage.containsValue(providedPass) == false) {
			
				System.out.println("Both inputs were incorrect\n");
			
				if(attempts >= 3) {
					
					JOptionPane.showMessageDialog(null, "\n\nToo many attempts have been made\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
					
					System.exit(0);
				}
			
				JOptionPane.showMessageDialog(null, "\n\nYour username or password was incorrect\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}//end actionPerformed
}
