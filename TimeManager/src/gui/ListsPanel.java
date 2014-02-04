package gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class ListsPanel extends JPanel {
	private JPanel p_north, p_west;
	private JComboBox sort_by, filter;
	private JButton b_new;
	private JList tasks;
	
	public ListsPanel() {
		this.setLayout(new BorderLayout());
		createPanels();
		createComboBox();
		createButtons();
		createList();
		
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_west, BorderLayout.WEST);
		
		p_north.add(sort_by);
		p_north.add(filter);
		p_north.add(b_new);
		
		
	}
	private void createList(){
		String[] temp = {"Class School", "Buy Milk"};
		tasks = new JList(temp);
	}
	
	private void createComboBox(){
		String [] filters = { "Filter","School", "Personal", "Work"};
		String [] sorts = { "Sort by","Date", "Priority", "Category"};
		sort_by = new JComboBox(sorts);
		filter = new JComboBox(filters);
	}
	
	private void createPanels(){
		p_north = new JPanel();
		p_west = new JPanel();
	}
	
	private void createButtons(){
		b_new = new JButton("+ New Task");
		
	}
	public static void main(String [] args){
		JFrame frame = new JFrame();
		JPanel lists = new ListsPanel();
		frame.add(lists);
		frame.setVisible(true);
		frame.pack();
	}
}
