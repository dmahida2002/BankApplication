import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ForgotReset extends CodeWindow implements ActionListener {
	
	CodeWindow CW = new CodeWindow();
	
	public String user;
	
	private int One;
	private int Two;
	private int Three;
	private int Four;
	private int L;
	private int W;
	
	private JFrame forgotFrame;
	private JPanel forgotPanel;
	
	private JLabel codeLabel;
	
	private JTextField codeInput;
	private JTextField passwordInput;
	
	private JButton verifyButton;
	private JButton changeButton;
	private JButton cancelButton;
	
	public void setFrame(int l, int w) {
		
		this.L = l;
		this.W = w;
		
		forgotPanel = new JPanel();
		
		forgotFrame = new JFrame();
		forgotFrame.setSize(l, w);
		forgotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		forgotFrame.add(forgotPanel);
		forgotFrame.setLocationRelativeTo(null);
		forgotFrame.setResizable(false);
		
		setLabel();
		setButton();
		
		CW.setFrame(l, w);
		CW.setLocation(One, Two, Three, Four);
		CW.finalizePanel();
	}
	
	private void setPanel() {
		
		forgotPanel.setLayout(null);
		
		forgotPanel.add(codeLabel);
		
		forgotPanel.add(codeInput);
		
		forgotPanel.add(verifyButton);
		forgotPanel.add(cancelButton);
	}
	
	private void setButton() {
		
		verifyButton = new JButton("Verify");
		changeButton = new JButton("Change Password");
		cancelButton = new JButton("Cancel");
		
		verifyButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	
	private void setLabel() {
		
		codeLabel = new JLabel("Enter your code below:");
		
		codeLabel.setFont(new Font("Bold", 45, 15));
		
		codeInput = new JTextField(4);
		passwordInput = new JTextField(15);
	}
	
	public void setLocation(int one, int two, int three, int four) {
		
		this.One = one;
		this.Two = two;
		this.Three = three;
		this.Four = four;
		
		codeLabel.setBounds((one + 65), (two - 40), (three + 85), 25);
		
		codeInput.setBounds((one + 65), (two + 50), (three + 85), 25);
		passwordInput.setBounds((One + 65), (Two + 50), (Three + 85), 25);
		
		verifyButton.setBounds((one + 65), (two + 120), (three + 85), 25);
		changeButton.setBounds((one + 65), (two + 120), (three + 85), 25);
		cancelButton.setBounds((one + 65), (two + 170), (three + 85), 25);
	}
	
	public void finalizePanel() {
		
		setPanel();
		forgotFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(cancelButton.getModel().isArmed()) {
			
			int result = JOptionPane.showConfirmDialog(null,"\nAre you sure you want to cancel?\n\n", "Cancel Password Reset??", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION) {
				
				System.out.println("User cancelled forget password\n");
				
				LoginWindow LW = new LoginWindow();
				
				LW.setFrame(L, W);
				LW.setLabel("Username: ", "Password: ", "Chase");
				LW.setLocation(One, Two, Three, Four);
				LW.finalizePanel();
				
				CW.codeFrame.dispose();
				forgotFrame.dispose();
			}
		}
		
		if(verifyButton.getModel().isArmed()) {
			
			String mastercode = CodeWindow.masterCode;
			
			String providedCode = codeInput.getText();
			
			if(providedCode.equals(masterCode) == true) {
				
				System.out.println("Code has been verified\n");
				
				CW.codeFrame.dispose();
				
				codeLabel.setText("New password:");
				
				codeLabel.setBounds((One + 90), (Two - 40), (Three + 85), 25);
				
				forgotPanel.add(changeButton);
				forgotPanel.add(passwordInput);
				
				forgotPanel.remove(verifyButton);
				forgotPanel.remove(codeInput);
				
				changeButton.addActionListener(this);
				
				codeLabel.paintImmediately(codeLabel.getVisibleRect());
				verifyButton.paintImmediately(verifyButton.getVisibleRect());
				codeInput.paintImmediately(codeInput.getVisibleRect());
			}
			
			else if(providedCode.equals(masterCode) == false) {

				JOptionPane.showMessageDialog(null, "\nThe code entered is incorrect\n\n", "Invalid Code", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (changeButton.getModel().isArmed()) {
			
			LoginHolder.addLogins(user, passwordInput.getText());
			
			JOptionPane.showMessageDialog(null, "\nPassword Changed!\n\n", "Password Changed", JOptionPane.INFORMATION_MESSAGE);
			
			LoginWindow LW = new LoginWindow();
			
			LW.setFrame(L, W);
			
			LW.setLabel("Username: ", "Password: ", "Chase");
			LW.setLocation(One, Two, Three, Four);
			
			LW.finalizePanel();
			
			forgotFrame.dispose();	
		}
	}
}
