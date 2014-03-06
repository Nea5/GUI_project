package gui;

import java.awt.BorderLayout;
import javax.swing.*;

public class HelpPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l_help;
	private JTextArea textArea;
	
	public HelpPanel() {
		createLabels();
		createTextArea();
		this.setLayout(new BorderLayout());
		this.add(l_help, BorderLayout.NORTH);
		this.add(textArea, BorderLayout.CENTER);
	}
	/**
	 * Constructs all the JLables to be used
	 */
	private void createLabels(){
		l_help = new JLabel(TimeManager.rb.getString("l_help"));
	}
	/**
	 * Constructs all the JTextAreas to be used 
	 */
	private void createTextArea(){
		textArea = new JTextArea(TimeManager.rb.getString("help_info"));
	}
}
