package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

public class MessagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	
	public MessagePanel(String msg) {
		textArea = new JTextArea(msg);
		
		int columnWidth = 50;
		textArea.setColumns(columnWidth);
		
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		textArea.setEnabled(false);
		textArea.setDisabledTextColor(Color.BLACK);
		
		textArea.setBackground(null);
		textArea.setBorder(null);
		
		textArea.setSize(textArea.getPreferredSize().width, 1);
		
		this.setLayout(new BorderLayout());
		this.add(textArea, BorderLayout.CENTER);
	}
}
