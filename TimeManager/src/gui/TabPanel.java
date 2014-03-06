package gui;

import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;

import model.ToDoModel;
/**
 * Creates tabs with different panels
 * @author Johan Dahlkar 
 * @author Markus Ebbesson 
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class TabPanel extends JTabbedPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p_calendar;
	private ListsPanel p_lists;
	private ToDoModel model;
	
	public TabPanel(ToDoModel model) {
		this.model = model;
		createPanels();
		//this.setBackground(Color.WHITE);
		this.addTab(TimeManager.rb.getString("p_lists"), p_lists);
		this.addTab(TimeManager.rb.getString("p_calendar"), p_calendar);
	}
	/**
	 * Creates the different panels shown in the tabs
	 */
	public void createPanels(){
		p_lists = new ListsPanel(model);
		p_calendar = new JPanel(); // Temp
	}
	public void newTask(){
		p_lists.newTask();
	}
	public void editTask(){
		p_lists.editTask();
	}
	public void deleteTasks(){
		p_lists.deleteTasks();
	}
	public void addNewTaskAction(AbstractAction a){
		p_lists.addNewTaskAction(a);
	}
	public void addEditTaskAction(AbstractAction a){
		p_lists.addEditTaskAction(a);
	}
	public void addDeleteTaskAction(AbstractAction a){
		p_lists.addDeleteTaskAction(a);
	}
	public void addSelectionListener(ListSelectionListener l){
		p_lists.addSelectionListener(l);
	}
	public void addTableModelListener(TableModelListener t){
		p_lists.addTableModelListener(t);
	}
	public boolean getMarked(){
		return p_lists.getMarked();
	}
	public void newFilter(String s){
		p_lists.newFilter(s);
	}
	public void addFilterListener(ItemListener l){
		p_lists.addFilterListener(l);
	}
}
