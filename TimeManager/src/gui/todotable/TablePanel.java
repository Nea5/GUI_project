package gui.todotable;

import gui.TimeManager;
import gui.mypanels.EditPanel;
import gui.mypanels.NewTaskPanel;

import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private JButton bNew, bEdit, bDelete, bDone;
	private TodoTable todoTable;
	private JScrollPane tableScroll;
	private ToDoModel model;
	/**
	 * Constructs a new TablePanel with a ToDoModel to get
	 * and set data to/from
	 * 
	 * @param model ToDoModel to get and set data to/from
	 */
	public TablePanel(ToDoModel model) {
		this.model = model;
		this.setLayout(new BorderLayout());
		// this.setBackground(Color.BLACK);
		createPanels();
		createComboBox();
		createButtons();
		createTable();
		this.add(p_north, BorderLayout.NORTH);
		this.add(tableScroll, BorderLayout.CENTER);
		p_north.add(filter);
		p_north.add(bNew);
		p_north.add(bEdit);
		p_north.add(bDelete);
		p_north.add(bDone);
	}
	/**
	 * Creates a ToDoTable and adds it to a JScrollPane
	 */
	private void createTable(){
		todoTable = new TodoTable(model);
		tableScroll = new JScrollPane(todoTable);
	}
	/**
	 * Creates a JComobBox
	 */
	private void createComboBox(){
		String [] filters = { "Filter","School", "Personal", "Work"};
		filter = new JComboBox<String>(filters);
	}
	/**
	 * Creates all the JPanels to be used
	 */
	private void createPanels(){
		p_north = new JPanel();
	}
	/**
	 * Creates all the JButtons to be used
	 */
	private void createButtons(){
		bNew = new JButton();
		bEdit = new JButton();
		bDelete = new JButton();
		bDone = new JButton();
	}
	/**
	 * Runs the getMarked() method in TodoTable
	 * @return boolean
	 */
	public boolean getMarked(){
		return todoTable.getMarked();
	}
	/**
	 * Sets a AbstractAction to bNew
	 * @param a AbstractAction to set
	 */
	public void addNewTaskAction(AbstractAction a){
		bNew.setAction(a);
	}
	/**
	 * Sets a AbstractAction to bEdit, and runs the
	 * addEditTaskAction(AbstractAction) in TodoTable
	 * @param a AbstractAction to set and use
	 */
	public void addEditTaskAction(AbstractAction a){
		bEdit.setAction(a);
		todoTable.addEditTaskAction(a);
	}
	/**
	 * Sets a AbstractAction to bDelete, and runs the
	 * addDeleteTaskAction(AbstractAction) in TodoTable
	 * @param a AbstractAction to set and use
	 */
	public void addDeleteTaskAction(AbstractAction a){
		bDelete.setAction(a);
		todoTable.addDeleteTaskAction(a);
	}
	/**
	 * Sets a AbstractAction to bDone, and runs the
	 * addMarkDoneAction(AbstractAction) in TodoTable
	 * @param a AbstractAction to set and use
	 */
	public void addMarkDoneAction(AbstractAction a){
		bDone.setAction(a);
		todoTable.addMarkDoneAction(a);
	}
	/**
	 * Adds a ListSelectionListener to TodoTables model
	 * @param l ListSelectionListener to add
	 */
	public void addSelectionListener(ListSelectionListener l){
		ListSelectionModel listSelectModel = todoTable.getSelectionModel();
		listSelectModel.addListSelectionListener(l);
	}
	/**
	 * Adds a TableModelListener to TodoTables model
	 * @param t
	 */
	public void addTableModelListener(TableModelListener t){
		todoTable.getModel().addTableModelListener(t);
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
			if(t.getName().equals("")){ // If no name has been written open a warning message
				JOptionPane.showMessageDialog(this, TimeManager.rb.getString("new_error"), TimeManager.rb.getString("new_error_text"), JOptionPane.ERROR_MESSAGE);
			}else{//Adds the new task to TodoModel and saves it.
				model.add(t);
				Object[] newTask = new Object[]{Boolean.FALSE, t.getName(), t.getCategory(),
						t.getDue(), t.getPriority()};
				todoTable.addRow(newTask);
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
		int i = todoTable.convertRowIndexToModel(todoTable.getSelectedRow());
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
			todoTable.editRow(i, editTask);
		}
		try{ // Saves the changes
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos.srz")));
			out.writeObject(model);
			out.close();
		}catch(Exception ea){
			ea.printStackTrace();
		}
	}
	/**
	 * Deletes the marked and selected tasks in the TodoTable
	 */
	public void deleteTasks(){
		int j = todoTable.convertRowIndexToModel(todoTable.getSelectedRow());
		todoTable.removeRow(j);
		model.delete(j);
		for(int i = 0; i < todoTable.getRowCount();i++){
			int x = todoTable.convertRowIndexToModel(i);
			boolean b = (Boolean)todoTable.getValueAt(i,0);
			if(b){
				todoTable.removeRow(x);
				model.delete(x);
				b = false;
				// i--;
			}
		}
		try{ // Saves the changes
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos.srz")));
			out.writeObject(model);
			out.close();
		}catch(Exception ea){
			ea.printStackTrace();
		}
	}
	/**
	 * Runs the newFilter(String) method in TodoTable
	 * @param s String to use
	 */
	public void newFilter(String s){
		todoTable.newFilter(s);
	}
	/**
	 * Adds a ItemListener to filter
	 * @param l ItemListener to add
	 */
	public void addFilterListener(ItemListener l){
		filter.addItemListener(l);
	}
	/**
	 * Marks all the checked in and selected tasks in todoTable as done.
	 */
	public void markDone(){
		int j = todoTable.convertRowIndexToModel(todoTable.getSelectedRow());
		ToDo td = model.get(j);
		td.setDone();
		todoTable.unMark(j);
		for(int i = 0; i < todoTable.getRowCount();i++){
			int x = todoTable.convertRowIndexToModel(i);
			boolean b = (Boolean)todoTable.getValueAt(i,0);
			if(b){//Checked in
				ToDo t = model.get(x);
				t.setDone();
				todoTable.unMark(x);
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
	/**
	 * Runs the rightClickMenu(MouseEvent) method in TodoTable
	 * @param e MouseEven to use
	 */
	public void rightClickMenu(MouseEvent e){
		todoTable.rightClickMenu(e);
	}
	/**
	 * Adds a MouseListener to todoTable
	 * @param ma MouseListener to add
	 */
	public void addRightClickListener(MouseListener ma){
		todoTable.addMouseListener(ma);
	}
}
