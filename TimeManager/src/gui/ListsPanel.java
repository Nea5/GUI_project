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

import java.io.*;

import todo.*;

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
public class ListsPanel extends JPanel implements Serializable{
	private static final long serialVersionUID = 6529685098267757690L;
	private JPanel p_north, p_center;
	private JComboBox sort_by, filter;
	private JButton b_new;
	private JTable t_tasks;
	private DefaultTableModel t_tasks_model;
	private JScrollPane tableScroll;
	
	private String[] columnNames = {" ", TimeManager.rb.getString("task"), TimeManager.rb.getString("category"),TimeManager.rb.getString("date"), TimeManager.rb.getString("priority")};
	private Object[][] data;
	private ToDoManager manager;
	/**
	 * 
	 */
	public ListsPanel() {
		try{
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("todos.srz")));
			manager = (ToDoManager)in.readObject();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(manager == null){
			manager = new ToDoManager();
		}
		data = manager.getData();
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
		b_new = new JButton("+ " + TimeManager.rb.getString("new_task_button"));
		
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
					TimeManager.rb.getString("enter_task_name"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(result == JOptionPane.OK_OPTION){
				ToDo t = p_newTask.getData();
				manager.add(t);
				Object[] newTask = new Object[]{Boolean.FALSE, t.getName(), t.getCategory(),
						t.getDue(), t.getPriority()};
				t_tasks_model.addRow(newTask);
				try{
					ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos.srz")));
					out.writeObject(manager);
					out.close();
				}catch(Exception ea){
					ea.printStackTrace();
				}
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
