package gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.*;

public class HelpPanel extends JPanel {
	private JLabel l_help;
	private JTextArea textArea;
	
	public HelpPanel() {
		createLabels();
		createTextArea();
		this.setLayout(new BorderLayout());
		this.add(l_help, BorderLayout.NORTH);
		this.add(textArea, BorderLayout.CENTER);
	}
	
	private void createLabels(){
		l_help = new JLabel("Help");
	}
	
	private void createTextArea(){
		textArea = new JTextArea("Some help info");
	}
}
