package gui;

import java.awt.BorderLayout;
import javax.swing.*;

public class MessagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel textArea;
	
	public MessagePanel(String msg) {
		textArea = new JLabel(msg);
		this.setLayout(new BorderLayout());
		this.add(textArea, BorderLayout.CENTER);
	}
}
