import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountCreation extends LoginHolder implements ActionListener {
	
	private int One;
	private int Two;
	private int Three;
	private int Four;
	private int L;
	private int W;
	
	private JFrame createFrame;
	private JPanel createPanel;
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	private JTextField usernameInput;
	private JTextField passwordInput;
	
	private JButton createAccountButton;
	private JButton cancelButton;
	
	public void setFrame(int l, int w) {
		
		this.L = l;
		this.W = w;
		
		createPanel = new JPanel();
		
		createFrame = new JFrame();
		createFrame.setSize(l, w);
		createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createFrame.add(createPanel);
		createFrame.setLocationRelativeTo(null);
		createFrame.setResizable(false);
		
		setLabel();
		setButton();
	}
	
	private void setPanel() {
		
		createPanel.setLayout(null);
		
		createPanel.add(usernameLabel);
		createPanel.add(passwordLabel);
		createPanel.add(usernameInput);
		createPanel.add(passwordInput);
		createPanel.add(createAccountButton);
		createPanel.add(cancelButton);
	}
	
	private void setButton() {
		
		createAccountButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	
	private void setLabel() {
		
		usernameLabel = new JLabel("New Username:");
		passwordLabel = new JLabel("New Password:");
		
		usernameInput = new JTextField(10);
		passwordInput = new JTextField(15);
		
		createAccountButton = new JButton("Create Account");
		cancelButton = new JButton("Cancel");
	}
	
	public void setLocation(int one, int two, int three, int four) {
		
		this.One = one;
		this.Two = two;
		this.Three = three;
		this.Four = four;
		
		usernameLabel.setBounds((one - 10), two, (three + 50), four);
		passwordLabel.setBounds((one - 10), (two + 40), (three + 50), four);
		
		usernameInput.setBounds((one + 130), two, (three + 90), four);
		passwordInput.setBounds((one + 130), (two + 45), (three + 90), four);
		
		createAccountButton.setBounds((one + 65), (two + 120), (three + 85), 25);
		cancelButton.setBounds((one + 65), (two + 170), (three + 85), 25);
	}
	
	public void finalizePanel() {
		
		setPanel();
		createFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(cancelButton.getModel().isArmed()) {
			
			int result = JOptionPane.showConfirmDialog(createFrame,"Are you sure you want to cancel?", "Cancel new account?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION) {
				
				System.out.println("Account creation was canceled\n");
				
				LoginWindow LW = new LoginWindow();
				
				LW.setFrame(L, W);
				
				LW.setLabel("Username: ", "Password: ", "Chase");
				LW.setLocation(One, Two, Three, Four);
				
				LW.finalizePanel();
				
				createFrame.dispose();
			}
		}
		
		if(createAccountButton.getModel().isArmed()) {
			
			if(usernameInput.getText().length() < 5 || passwordInput.getText().length() < 5) {
				
				JOptionPane.showMessageDialog(null, "\n\nPlease enter a username and password that is greater than 7 characters long\n\n", "Account Creation Error", JOptionPane.ERROR_MESSAGE);
			}
			
			if(loginStorage.containsKey(usernameInput.getText()) == true) {
				
				JOptionPane.showMessageDialog(null, "\n\nThe username provided is taken. Please select another username\n\n", "Account Creation Error", JOptionPane.ERROR_MESSAGE);
			}
			
			if(usernameInput.getText().length() >= 5 && passwordInput.getText().length() >= 5 
			&& usernameInput.getText() != "12345" && passwordInput.getText() != "12345"
			&& loginStorage.containsKey(usernameInput.getText()) == false) {
				
				NameHolder.addNames(usernameInput.getText(), "Test");
				
				System.out.println("New account was created\n");
				
				LoginHolder.addLogins(usernameInput.getText(), passwordInput.getText());
				
				createFrame.setBackground(Color.DARK_GRAY);
				
				JOptionPane.showMessageDialog(null, "\n\nAccount Created!\n\n", "New Account", JOptionPane.INFORMATION_MESSAGE);
				
				LoginWindow LW = new LoginWindow();
				
				LW.setFrame(L, W);
				
				LW.setLabel("Username: ", "Password: ", "Chase");
				LW.setLocation(One, Two, Three, Four);
				
				LW.finalizePanel();
				
				createFrame.dispose();
			}
		}
	}
}
