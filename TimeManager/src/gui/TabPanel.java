package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabPanel extends JTabbedPane {
	private JPanel p_lists, p_calendar;
	
	public TabPanel() {
		createPanels();
		this.setBackground(Color.WHITE);
		this.addTab("List", p_lists);
		this.addTab("Calendar", p_calendar);
	}
	
	public void createPanels(){
		p_lists = new ListsPanel();
		p_calendar = new JPanel(); // Temp
	}
	
	public static void main(String [] args){
		JFrame frame = new JFrame();
		JTabbedPane lists = new TabPanel();
		frame.add(lists);
		frame.setVisible(true);
		frame.pack();
	}
}
