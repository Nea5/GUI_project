package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;

import model.*;

import java.io.*;

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
public class TablePanel extends JPanel implements Serializable{
	private static final long serialVersionUID = 6529685098267757690L;
	private JPanel p_north;
	private JComboBox<String> filter;
	private JButton b_new, b_edit, b_delete, b_done;
	private TodoTable t_tasks;
	private JScrollPane tableScroll;
	private ToDoModel model;
	/**
	 * Constructs a new ListsPanel
	 */
	public TablePanel(ToDoModel model) {
		this.model = model;
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		createPanels();
		createComboBox();
		createButtons();
		createTable();
		
		this.add(p_north, BorderLayout.NORTH);
		this.add(tableScroll, BorderLayout.CENTER);
		
		p_north.add(filter);
		p_north.add(b_new);
		p_north.add(b_edit);
		p_north.add(b_delete);
		p_north.add(b_done);
	}
	/**
	 * Creates a JTable with a modified DefaultTableModel
	 */
	private void createTable(){
		t_tasks = new TodoTable(model);
		tableScroll = new JScrollPane(t_tasks);
	}
	/**
	 * Creates all the JComboBoxes, filter list and sort by list
	 */
	private void createComboBox(){
		String [] filters = { "Filter","School", "Personal", "Work"};
		filter = new JComboBox<String>(filters);
	}
	/**
	 * Creates all the JPanels
	 */
	private void createPanels(){
		p_north = new JPanel();
		//p_center = new JPanel();
		//p_north.setBackground(Color.WHITE);
		//p_center.setBackground(Color.WHITE);
	}
	/**
	 * Creates the new task button
	 */
	private void createButtons(){
		b_new = new JButton();
		b_edit = new JButton();
		b_delete = new JButton();
		b_done = new JButton();
	}
	public boolean getMarked(){
		return t_tasks.getMarked();
	}
	
	public void addNewTaskAction(AbstractAction a){
		b_new.setAction(a);
	}
	public void addEditTaskAction(AbstractAction a){
		b_edit.setAction(a);
		t_tasks.addEditTaskAction(a);
	}
	public void addDeleteTaskAction(AbstractAction a){
		b_delete.setAction(a);
		t_tasks.addDeleteTaskAction(a);
	}
	public void addMarkDoneAction(AbstractAction a){
		b_done.setAction(a);
		t_tasks.addMarkDoneAction(a);
	}
	
	public void addSelectionListener(ListSelectionListener l){
		ListSelectionModel listSelectModel = t_tasks.getSelectionModel();
		listSelectModel.addListSelectionListener(l);
	}
	
	public void addTableModelListener(TableModelListener t){
		t_tasks.getModel().addTableModelListener(t);
	}
	/**
	 * Opens a JOptionPane to be able to add a new task to the TodoTable
	 */
	public void newTask(){
		NewTaskPanel p_newTask = new NewTaskPanel();
		int result = JOptionPane.showConfirmDialog(this, p_newTask, 
				TimeManager.rb.getString("enter_task_name"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(result == JOptionPane.OK_OPTION){
			ToDo t = p_newTask.getData();
			if(t.getName().equals("")){
				JOptionPane.showMessageDialog(this, TimeManager.rb.getString("new_error"), TimeManager.rb.getString("new_error_text"), JOptionPane.ERROR_MESSAGE);
			}else{
				model.add(t);
				Object[] newTask = new Object[]{Boolean.FALSE, t.getName(), t.getCategory(),
						t.getDue(), t.getPriority()};
				t_tasks.addRow(newTask);
				try{
					ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos.srz")));
					out.writeObject(model);
					out.close();
				}catch(Exception ea){
					ea.printStackTrace();
				}
			}
		}
	}
	/**
	 * Opens a JOptionPane to be able to edit the currently selected task in the TodoTable
	 */
	public void editTask(){
		int i = t_tasks.convertRowIndexToModel(t_tasks.getSelectedRow());
		ToDo t = model.get(i);
		EditPanel p_edit = new EditPanel(t);
		int result = JOptionPane.showConfirmDialog(this, p_edit,
					"Edit task", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);	
		if(result == JOptionPane.OK_OPTION){
			ToDo edit_t = p_edit.getData();
			if(t.isDone()) edit_t.setDone();
			model.edit(edit_t, i);
			Object[] editTask = new Object[]{Boolean.FALSE, edit_t.getName(), edit_t.getCategory(),
					edit_t.getDue(), edit_t.getPriority()};
			t_tasks.editRow(i, editTask);
		}
		try{
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos.srz")));
			out.writeObject(model);
			out.close();
		}catch(Exception ea){
			ea.printStackTrace();
		}
	}
	/**
	 * Deletes the marked tasks in the TodoTable
	 */
	public void deleteTasks(){
		int j = t_tasks.convertRowIndexToModel(t_tasks.getSelectedRow());
		t_tasks.removeRow(j);
		model.delete(j);
		for(int i = 0; i < t_tasks.getRowCount();i++){
			int x = t_tasks.convertRowIndexToModel(i);
			boolean b = (Boolean)t_tasks.getValueAt(i,0);
			if(b){
				t_tasks.removeRow(x);
				model.delete(x);
				b = false;
				// i--;
			}
		}
		try{
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos.srz")));
			out.writeObject(model);
			out.close();
		}catch(Exception ea){
			ea.printStackTrace();
		}
	}
	
	public void newFilter(String s){
		t_tasks.newFilter(s);
	}
	public void addFilterListener(ItemListener l){
		filter.addItemListener(l);
	}
	
	public void markDone(){
		int j = t_tasks.convertRowIndexToModel(t_tasks.getSelectedRow());
		ToDo td = model.get(j);
		td.setDone();
		t_tasks.unMark(j);
		for(int i = 0; i < t_tasks.getRowCount();i++){
			int x = t_tasks.convertRowIndexToModel(i);
			boolean b = (Boolean)t_tasks.getValueAt(i,0);
			if(b){
				ToDo t = model.get(x);
				t.setDone();
				//t_tasks.getModel().fire
				t_tasks.unMark(x);
				try{
					ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos.srz")));
					out.writeObject(model);
					out.close();
				}catch(Exception ea){
					ea.printStackTrace();
				}
			}
		}
	}
	public void rightClickMenu(MouseEvent e){
		t_tasks.rightClickMenu(e);
	}
	public void addRightClickListener(MouseListener ma){
		t_tasks.addMouseListener(ma);
	}

}
