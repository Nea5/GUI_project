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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
	private JComboBox filter;
	private JButton b_new, b_edit, b_delete;
	private TodoTable t_tasks;
	private JScrollPane tableScroll;
	private Object[][] data;
	private ToDoManager manager;
	/**
	 * 
	 */
	public ListsPanel() {
		this.loadSaved();
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		createPanels();
		createComboBox();
		createButtons();
		createTable();
		
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_center, BorderLayout.CENTER);
		
		p_north.add(filter);
		p_north.add(b_new);
		p_north.add(b_edit);
		p_north.add(b_delete);

		p_center.add(tableScroll);
	}
	private void loadSaved(){
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
	}
	/**
	 * Creates a JTable with a modified DefaultTableModel
	 */
	private void createTable(){
		t_tasks = new TodoTable(data);
		tableScroll = new JScrollPane(t_tasks);
	}
	/**
	 * Creates all the JComboBoxes, filter list and sort by list
	 */
	private void createComboBox(){
		String [] filters = { "Filter","School", "Personal", "Work"};
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
		b_new = new MyButton(TimeManager.rb.getString("new_task_button"), "Add24");
		b_edit = new MyButton("Edit", "Edit24");
		b_edit.setEnabled(false);
		b_delete = new MyButton("Delete", "Delete24");
		b_delete.setEnabled(false);
	}
	public void newTask(){
		NewTaskPanel p_newTask = new NewTaskPanel();
		int result = JOptionPane.showConfirmDialog(this, p_newTask, 
				TimeManager.rb.getString("enter_task_name"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(result == JOptionPane.OK_OPTION){
			ToDo t = p_newTask.getData();
			manager.add(t);
			Object[] newTask = new Object[]{Boolean.FALSE, t.getName(), t.getCategory(),
					t.getDue(), t.getPriority()};
			t_tasks.addRow(newTask);
			try{
				ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos.srz")));
				out.writeObject(manager);
				out.close();
			}catch(Exception ea){
				ea.printStackTrace();
			}
		}
	}
	
	public void editTask(){
		int i = t_tasks.convertRowIndexToModel(t_tasks.getSelectedRow());
		ToDo t = manager.get(i);
		EditPanel p_edit = new EditPanel(t);
		int result = JOptionPane.showConfirmDialog(this, p_edit,
					"Edit task", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);	
		if(result == JOptionPane.OK_OPTION){
			ToDo edit_t = p_edit.getData();
			manager.edit(edit_t, i);
			Object[] editTask = new Object[]{Boolean.FALSE, edit_t.getName(), edit_t.getCategory(),
					edit_t.getDue(), edit_t.getPriority()};
			t_tasks.editRow(i, editTask);
			try{
				ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos.srz")));
				out.writeObject(manager);
				out.close();
			}catch(Exception ea){
				ea.printStackTrace();
			}
		}
	}
	
	public void deleteTasks(){
		for(int i = 0; i < t_tasks.getRowCount();i++){
			int x = t_tasks.convertRowIndexToModel(i);
			boolean b = (boolean)t_tasks.getValueAt(i,0);
			System.out.println("" + b);
			if(b){
				System.out.println("i " + i);
				System.out.println("x " + x);
				t_tasks.removeRow(x);
				manager.delete(x);
				b = false;
				i--;
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
	
	public void filterTasks(){
		
	}
	
	public JButton getNewButton(){
		return b_new;
	}
	
	public JButton getEditButton(){
		return b_edit;
	}
	
	public JButton getDeleteButton(){
		return b_delete;
	}
	
	public TodoTable getTable(){
		return t_tasks;
	}
}
