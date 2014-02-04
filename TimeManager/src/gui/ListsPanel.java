package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListsPanel extends JPanel {
	private JPanel p_north, p_west;
	private JComboBox sort_by, filter;
	private JButton b_new;
	private JTable tasks;
	private JScrollPane tableScroll;
	
	public ListsPanel() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		createPanels();
		createComboBox();
		createButtons();
		createTable();
		
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_west, BorderLayout.WEST);
		
		p_north.add(sort_by);
		p_north.add(filter);
		p_north.add(b_new);
		
		p_west.add(tableScroll);
	}
	
	private void createTable(){
		tasks = new JTable(new TaskTableModel());
		tableScroll = new JScrollPane(tasks);
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
		p_north.setBackground(Color.WHITE);
		p_west.setBackground(Color.WHITE);
	}
	
	private void createButtons(){
		b_new = new JButton("+ New Task");
		
	}
	public static void main(String [] args){
		JFrame frame = new JFrame();
		JPanel lists = new ListsPanel();
		frame.add(lists);
		frame.setMinimumSize(new Dimension(400,400));
		frame.setVisible(true);
		frame.pack();
	}
}
