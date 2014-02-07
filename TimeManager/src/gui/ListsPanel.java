package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 * Panel to display the list view in the time manager
 * @author Johan Dahlkar 
 * @author Markus Ebbesson 
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 *
 */
public class ListsPanel extends JPanel {
	private JPanel p_north, p_center;
	private JComboBox sort_by, filter;
	private JButton b_new;
	private JTable t_tasks;
	private DefaultTableModel t_tasks_model;
	private JScrollPane tableScroll;
	
	private String[] columnNames = {" ", "Task", "Date", "Category", "Priority"};
	private Object[][] data = {
			{
				Boolean.FALSE, "Buy milk", new GregorianCalendar().getTime(),
				"Home", 5
			},
			{
				Boolean.FALSE, "Eat", new GregorianCalendar().getTime(),
				"Personal", 6
			}	
	};
	/**
	 * 
	 */
	public ListsPanel() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		createPanels();
		createComboBox();
		createButtons();
		createTable();
		
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_center, BorderLayout.CENTER);
		
		p_north.add(sort_by);
		p_north.add(filter);
		p_north.add(b_new);
		
		b_new.addActionListener(new NewTaskListener());
		
		p_center.add(tableScroll);
	}
	/**
	 * Creates a JTable with a modified DefaultTableModel
	 */
	private void createTable(){
		t_tasks_model = new DefaultTableModel(data, columnNames){
			@Override
		    public Class<?> getColumnClass(int col) {
				if (col == 2){
					return java.util.GregorianCalendar.class;
				}
				return (getValueAt(0, col).getClass());
		    }
			@Override
			public boolean isCellEditable(int row, int col){
				return (col == 0);
			}
		};
		t_tasks = new JTable(t_tasks_model);
		tableScroll = new JScrollPane(t_tasks);
	}
	/**
	 * Creates all the JComboBoxes, filter list and sort by list
	 */
	private void createComboBox(){
		String [] filters = { "Filter","School", "Personal", "Work"};
		String [] sorts = { "Sort by","Date", "Priority", "Category"};
		sort_by = new JComboBox(sorts);
		filter = new JComboBox(filters);
	}
	/**
	 * Creates all the JPanels
	 */
	private void createPanels(){
		p_north = new JPanel();
		p_center = new JPanel();
		p_north.setBackground(Color.WHITE);
		p_center.setBackground(Color.WHITE);
	}
	/**
	 * Creates the new task button
	 */
	private void createButtons(){
		b_new = new JButton("+ New Task");
		
	}
	/**
	  * Handles input when adding a new task
	  * @author Johan Dahlkar 
	  * @author Markus Ebbesson 
	  * @author Marcus Enderskog
	  * @author Jonas Rosenlind
	  * @author Linnea Sandelin
	  * @author Marcus Utter
	 */
	public class NewTaskListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			NewTaskPanel p_newTask = new NewTaskPanel();
			int result = JOptionPane.showConfirmDialog(null, p_newTask, 
		               "Please Enter a new task", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(result == JOptionPane.OK_OPTION){
				Object[] newTask = p_newTask.getData();
				t_tasks_model.addRow(newTask);
			}
		}
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String [] args){
		JFrame frame = new JFrame();
		JPanel lists = new ListsPanel();
		frame.add(lists);
		frame.setMinimumSize(new Dimension(400,400));
		frame.setVisible(true);
		frame.pack();
	}
}
