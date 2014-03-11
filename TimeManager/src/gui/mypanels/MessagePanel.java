package gui.mypanels;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
/**
 * 
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class MessagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	/**
	 * Creates a MessagePanel that shows a message
	 * @param msg Message to show
	 */
	public MessagePanel(String msg) {
		textArea = new JTextArea(msg); //Creates a JTextArea to show msg
		int columnWidth = 50;
		textArea.setColumns(columnWidth);
		textArea.setWrapStyleWord(true); //Some settings
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
