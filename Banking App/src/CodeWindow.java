import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CodeWindow {
	
	private int comb = (int)(Math.random()*(9999 - 1000 + 1) + 1000);
	
	protected static String masterCode;
			
	protected JFrame codeFrame;
	private JPanel codePanel;
	
	private JLabel codeLabel;
	
	public void setFrame(int l, int w) {
		
		codePanel = new JPanel();
		
		codeFrame = new JFrame();
		codeFrame.setSize(l, w);
		codeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		codeFrame.add(codePanel);
		codeFrame.setResizable(false);
		
		setLabel();
	}
	
	private void setPanel() {
		
		codePanel.setLayout(null);
		
		codePanel.add(codeLabel);
	}
	
	private void setLabel() {
		
		CodeWindow.masterCode = Integer.toString(comb);
		
		codeLabel = new JLabel(CodeWindow.masterCode);
		
		codeLabel.setFont(new Font("Bold", 45, 25));
	}
	
	public void setLocation(int one, int two, int three, int four) {
		
		codeLabel.setBounds((one + 251), (two + 133), (three + 85), 25);
		codeLabel.setHorizontalAlignment(JLabel.CENTER);
	}
	
	public void finalizePanel() {
		
		setPanel();
		codeFrame.setVisible(true);
	}
}
