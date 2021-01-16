import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CheckingAccountManagement implements ActionListener {
	
	private int One;
	private int Two;
	private int Three;
	private int Four;
	private int L;
	private int W;
	
	private int changeAmount;
	private int totalBalance = 3000;
	
	private JFrame checkFrame;
	private JPanel checkPanel;
	
	private JLabel welcomeMessage;
	private JLabel accountType = new JLabel("CHECKING");
	private JLabel dollarSign = new JLabel("$");
	private JLabel currentBalance;
	
	private JTextField changeAmountInput;
	
	ButtonGroup actionGroup = new ButtonGroup();
	private JRadioButton depositOption = new JRadioButton("Deposit");
	private JRadioButton withdrawOption = new JRadioButton("Withdraw");
	
	private JButton completeTransactionButton;
	private JButton savingsButton;
	private JButton logoutButton;
	
	public void setFrame(int l, int w) {
		
		this.L = l;
		this.W = w;
		
		checkPanel = new JPanel();
		
		checkFrame = new JFrame();
		checkFrame.setSize((l - 190), (w + 250));
		checkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkFrame.add(checkPanel);
		checkFrame.setLocationRelativeTo(null);
		checkFrame.setResizable(false);
		
		setLabel();
		setButton();
	}
	
	private void setPanel() {
		
		checkPanel.setLayout(null);
		
		checkPanel.add(welcomeMessage);
		checkPanel.add(accountType);
		
		checkPanel.add(currentBalance);
		
		checkPanel.add(dollarSign);
		
		checkPanel.add(depositOption);
		checkPanel.add(withdrawOption);
		
		checkPanel.add(changeAmountInput);
		
		checkPanel.add(completeTransactionButton);
		checkPanel.add(savingsButton);
		checkPanel.add(logoutButton);
	}
	
	private void setButton() {
		
		actionGroup.add(depositOption);
		actionGroup.add(withdrawOption);
		
		depositOption.addActionListener(this);
		withdrawOption.addActionListener(this);
		completeTransactionButton.addActionListener(this);
		savingsButton.addActionListener(this);
		logoutButton.addActionListener(this);
	}
	
	private void setLabel() {
		
		LoginWindow LW = new LoginWindow();
		
		welcomeMessage = new JLabel("Welcome, " + NameHolder.nameStorage.get("") + "!");
		currentBalance = new JLabel("$" + Integer.toString(totalBalance));
		
		changeAmountInput = new JTextField(8);
		
		completeTransactionButton = new JButton("Complete Transaction");
		savingsButton = new JButton("Switch to savings account");
		logoutButton = new JButton("Logout");
	}
	
	public void setLocation(int one, int two, int three, int four) {
		
		this.One = one;
		this.Two = two;
		this.Three = three;
		this.Four = four;
		
		welcomeMessage.setBounds((one - 130), (two - 75), (three + 50), four);
		accountType.setBounds((one + 160), (two - 75), (three + 50), four);
		
		currentBalance.setBounds((one - 55), (two + 10), (three + 145), 60);
		
		depositOption.setBounds((one - 80), (two + 125), (three), four);
		withdrawOption.setBounds((one + 95), (two + 125), (three), 25);
		
		dollarSign.setBounds((one - 60), (two + 190), (three + 100), 25);
		
		changeAmountInput.setBounds((one - 20), (two + 192), (three + 100), 25);
		
		completeTransactionButton.setBounds((one - 65), (two + 250), (three + 145), 31);
		savingsButton.setBounds((one - 65), (two + 300), (three + 145), 25);
		logoutButton.setBounds((one - 65), (two + 390), (three + 145), 25);
		
		currentBalance.setFont(new Font("Bold", 45, 75));
	}
	
	public void finalizePanel() {
		
		setPanel();
		checkFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		currentBalance.setText("$" + Integer.toString(totalBalance));
		
		if(logoutButton.getModel().isArmed()) {
			
			int result = JOptionPane.showConfirmDialog(checkFrame,"Are you sure you want to log out?", "Log out?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION) {
				
				System.out.println("User logged out\n");
				
				LoginWindow LW = new LoginWindow();
				
				LW.setFrame(L, W);
				
				LW.setLabel("Username: ", "Password: ", "Chase");
				LW.setLocation(One, Two, Three, Four);
				
				LW.finalizePanel();
				
				checkFrame.dispose();
			}
		}
		
		if(completeTransactionButton.getModel().isArmed()) {
			
			if(depositOption.isSelected()) {
				
				changeAmount = Integer.valueOf(changeAmountInput.getText());
				
				System.out.println("User deposited $" + changeAmount + "\n");
				
				totalBalance += changeAmount;
				
				currentBalance = new JLabel("$" + Integer.toString(totalBalance));
				
				currentBalance.setText("$" + Integer.toString(totalBalance));
				
				checkFrame.repaint();
				checkFrame.setVisible(true);
			}
			
			else if(withdrawOption.isSelected()) {
				
				changeAmount = Integer.valueOf(changeAmountInput.getText());
				
				System.out.println("User withdrawed $" + changeAmount + "\n");
				
				totalBalance -= changeAmount;
				
				currentBalance.setText("$" + Integer.toString(totalBalance));
				
				checkFrame.repaint();
				checkFrame.setVisible(true);
			}
		}
	}
}
