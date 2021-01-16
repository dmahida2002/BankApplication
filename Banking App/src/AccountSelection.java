import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AccountSelection implements ActionListener {
	
	private int One;
	private int Two;
	private int Three;
	private int Four;
	private int L;
	private int W;
	
	private JFrame selectionFrame;
	private JPanel selectionPanel;
	
	private JLabel selectMessage;
	
	private JButton checkButton;
	private JButton saveButton;
	private JButton logoutButton;
	
	public void setFrame(int l, int w) {
		
		this.L = l;
		this.W = w;
		
		selectionPanel = new JPanel();
		
		selectionFrame = new JFrame();
		selectionFrame.setSize(l, w);
		selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectionFrame.add(selectionPanel);
		selectionFrame.setLocationRelativeTo(null);
		selectionFrame.setResizable(false);
		
		setLabel();
		setButton();
	}
	
	private void setPanel() {
		
		selectionPanel.setLayout(null);
		
		selectionPanel.add(selectMessage);
		selectionPanel.add(checkButton);
		selectionPanel.add(saveButton);
		selectionPanel.add(logoutButton);
	}
	
	private void setLabel() {
		
		selectMessage = new JLabel("Select account type");
		
		checkButton = new JButton("Checking");
		saveButton = new JButton("Savings");
		logoutButton = new JButton("Logout");
	}
	
	public void setLocation(int one, int two, int three, int four) {
		
		this.One = one;
		this.Two = two;
		this.Three = three;
		this.Four = four;
		
		selectMessage.setBounds(220, (two - 73), (three + 100), four);
		
		checkButton.setBounds((215 - 65), two, (three + 190), four);
		saveButton.setBounds((215 - 65), (two + 55), (three + 190), four);
		logoutButton.setBounds((215 - 65), (two + 170), (three + 190), four);
	}
	
	private void setButton() {
		
		checkButton.addActionListener(this);
		saveButton.addActionListener(this);
		logoutButton.addActionListener(this);
	}
	
	public void finalizePanel() {
		
		setPanel();
		selectionFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(logoutButton.getModel().isArmed()) {
			
			int result = JOptionPane.showConfirmDialog(selectionFrame,"Are you sure you want to log out?", "Log out?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION) {
				
				System.out.println("User logged out\n");
				
				LoginWindow LW = new LoginWindow();
				
				LW.setFrame(L, W);
				LW.setLabel("Username: ", "Password: ", "Chase");
				LW.setLocation(One, Two, Three, Four);
				LW.finalizePanel();
				
				selectionFrame.dispose();
			}
		}
		
		if(checkButton.getModel().isArmed()) {
			
			System.out.println("Checking account was selected\n");
			
			CheckingAccountManagement CA = new CheckingAccountManagement();
			
			CA.setFrame(L, W);
			CA.finalizePanel();
			CA.setLocation(One, Two, Three, Four);
			
			selectionFrame.setVisible(false);
			selectionFrame.dispose();
		}
		
		if(saveButton.getModel().isArmed()) {
			
			System.out.println("Savings account was selected\n");
		}
	}
}
