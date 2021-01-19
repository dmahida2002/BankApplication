
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginWindow extends LoginHolder implements ActionListener {
	
	private int One;
	private int Two;
	private int Three;
	private int Four;
	private int L;
	private int W;
	
	private int attempts;
	
	public String providedUser;
	private String providedPass;
	
	private JFrame loginFrame;
	private JPanel loginPanel;
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel bankTitle;
	
	private JTextField usernameInput;
	private JTextField passwordInput;
	
	private JButton loginButton;
	private JButton newAccountButton;
	private JButton forgotButton;
	private JButton closeButton;
	
	public void setFrame(int l, int w) {
		
		this.L = l;
		this.W = w;
		
		loginPanel = new JPanel();
		
		loginFrame = new JFrame();
		loginFrame.setSize(l, w);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.add(loginPanel);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setResizable(false);
		
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
		loginPanel.add(newAccountButton);
		loginPanel.add(forgotButton);
		loginPanel.add(closeButton);
	}
	
	private void setButton() {
		
		loginButton = new JButton("Login");
		newAccountButton = new JButton("+");
		forgotButton = new JButton("?");
		closeButton = new JButton("Exit");
		
		loginButton.addActionListener(this);
		newAccountButton.addActionListener(this);
		forgotButton.addActionListener(this);
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
		
		this.One = one;
		this.Two = two;
		this.Three = three;
		this.Four = four;
		
		bankTitle.setBounds((one + 90), (two - 60), (three + 100), four);
		
		usernameLabel.setBounds(one, two, three, four);
		passwordLabel.setBounds(one, (two + 40), three, four);
		
		usernameInput.setBounds((one + 130), two, (three + 90), four);
		passwordInput.setBounds((one + 130), (two + 45), (three + 90), four);
		
		loginButton.setBounds((one + 65), (two + 120), (three + 85), 25);
		newAccountButton.setBounds((one + 5), (two + 120), (three - 30), 25);
		forgotButton.setBounds((one + 238), (two + 120), (three - 30), 25);
		closeButton.setBounds((one + 65), (two + 170), (three + 85), 25);
	}
	
	public void finalizePanel() {
		
		attempts = 0;
		setPanel();
		loginFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(closeButton.getModel().isArmed()) {
			
			int result = JOptionPane.showConfirmDialog(null,"\nAre you sure you want to exit?\n\n", "Exit App?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION) {
				
				System.exit(0);
			}
		}
		
		if(newAccountButton.getModel().isArmed()) {
			
			System.out.println("Create new account selected\n");
			
			AccountCreation createNew = new AccountCreation();
			
			createNew.setFrame(L, W);
			
			createNew.setLocation(One, Two, Three, Four);
			
			createNew.finalizePanel();
			
			loginFrame.setVisible(false);
			loginFrame.dispose();
		}
		
		if(forgotButton.getModel().isArmed()) {
			
			System.out.println("Forgot password selected\n");
			
			String forgotInput = JOptionPane.showInputDialog("Enter your username:");
			
			if(loginStorage.containsKey(forgotInput) == false) {
				
				JOptionPane.showMessageDialog(null, "\nThis user was not found.\n\n", "Unable to find user", JOptionPane.ERROR_MESSAGE);
			}
			
			else if(loginStorage.containsKey(forgotInput) == true) {
				
				System.out.println("Input username is correct\n");
				
				ForgotReset FR = new ForgotReset();
				
				FR.user = forgotInput;
				
				FR.setFrame(L, W);
				
				FR.setLocation(One, Two, Three, Four);
				
				FR.finalizePanel();
				
				loginFrame.dispose();
				
			}
		}
		
		if(loginButton.getModel().isArmed()) {
			
			attempts += 1;
			
			providedUser = usernameInput.getText();
			providedPass = passwordInput.getText();
		
			if(loginStorage.containsKey(providedUser) && loginStorage.containsValue(providedPass)) {
				
				System.out.println("The login was successful\n");
				
				CheckingAccountManagement.providedUser = this.providedUser;
				
				AccountSelection select = new AccountSelection();
				
				select.setFrame(L, W);
				
				select.setLocation(One, Two, Three, Four);
				
				select.finalizePanel();
				
				loginFrame.dispose();
			}
		
			else if(LoginHolder.loginStorage.containsKey(providedUser) == true && LoginHolder.loginStorage.containsValue(providedPass) == false) {
			
				System.out.println("Username: Correct\nPassword: Wrong\n");
			
				if(attempts >= 3) {
					
					JOptionPane.showMessageDialog(null, "\nToo many attempts have been made\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
					
					System.exit(0);
				}
			
				JOptionPane.showMessageDialog(null, "\nYour username or password was incorrect\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
		
			else if(LoginHolder.loginStorage.containsKey(providedUser) == false && LoginHolder.loginStorage.containsValue(providedPass) == true) {
			
				System.out.println("Username: Wrong\nPassword: Correct\n");
			
				if(attempts >= 3) {
					
					JOptionPane.showMessageDialog(null, "\nToo many attempts have been made\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
					
					System.exit(0);
				}
			
				JOptionPane.showMessageDialog(null, "\nYour username or password was incorrect\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
		
			else if(LoginHolder.loginStorage.containsKey(providedUser) == false && LoginHolder.loginStorage.containsValue(providedPass) == false) {
			
				System.out.println("Both inputs were incorrect\n");
			
				if(attempts >= 3) {
					
					JOptionPane.showMessageDialog(null, "\nToo many attempts have been made\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
					
					System.exit(0);
				}
			
				JOptionPane.showMessageDialog(null, "\nYour username or password was incorrect\n\n", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
