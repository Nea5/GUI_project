package gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.*;

public class MessagePanel extends JPanel {
	private JLabel mheader,textArea;
	
	public MessagePanel(String msg) {
		textArea = new JLabel(msg);
		this.setLayout(new BorderLayout());
		this.add(textArea, BorderLayout.CENTER);
	}
}
