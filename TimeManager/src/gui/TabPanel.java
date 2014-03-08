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
	private TablePanel pTable;
	private ToDoModel model;
	
	public TabPanel(ToDoModel model) {
		this.model = model;
		createPanels();
		//this.setBackground(Color.WHITE);
		this.addTab(TimeManager.rb.getString("p_lists"), pTable);
		this.addTab(TimeManager.rb.getString("p_calendar"), p_calendar);
	}
	/**
	 * Creates the different panels shown in the tabs
	 */
	public void createPanels(){
		pTable = new TablePanel(model);
		p_calendar = new JPanel(); // Temp
	}
	public void newTask(){
		pTable.newTask();
	}
	public void editTask(){
		pTable.editTask();
	}
	public void deleteTasks(){
		pTable.deleteTasks();
	}
	public void addNewTaskAction(AbstractAction a){
		pTable.addNewTaskAction(a);
	}
	public void addEditTaskAction(AbstractAction a){
		pTable.addEditTaskAction(a);
	}
	public void addDeleteTaskAction(AbstractAction a){
		pTable.addDeleteTaskAction(a);
	}
	public void addSelectionListener(ListSelectionListener l){
		pTable.addSelectionListener(l);
	}
	public void addTableModelListener(TableModelListener t){
		pTable.addTableModelListener(t);
	}
	public boolean getMarked(){
		return pTable.getMarked();
	}
	public void newFilter(String s){
		pTable.newFilter(s);
	}
	public void addFilterListener(ItemListener l){
		pTable.addFilterListener(l);
	}
	public void addMarkDoneAction(AbstractAction a){
		pTable.addMarkDoneAction(a);
	}
	public void markDone(){
		pTable.markDone();
	}
}
