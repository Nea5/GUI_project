package gui;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

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
	private CalendarPanel pCalendar;
	private TablePanel pTable;
	private ToDoModel model;
	
	public TabPanel(ToDoModel model) {
		this.model = model;
		createPanels();
		//this.setBackground(Color.WHITE);
		this.addTab(TimeManager.rb.getString("p_lists"), pTable);
		this.addTab(TimeManager.rb.getString("p_calendar"), pCalendar);
	}
	/**
	 * Creates the different panels shown in the tabs
	 */
	public void createPanels(){
		pTable = new TablePanel(model);
		pCalendar = new CalendarPanel(model); // Temp
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
	public void addRightClickListener(MouseListener ma){
		pTable.addRightClickListener(ma);
	}
	public void rightClickMenu(MouseEvent e){
		pTable.rightClickMenu(e);
	}
	public void prevMonth(){
		pCalendar.prevMonth();
	}
	public void nextMonth (){
		pCalendar.nextMonth();
	}
	public void changeYear(){
		pCalendar.changeYear();
	}
	public void addNextMonthAction(AbstractAction a){
		pCalendar.addNextMonthAction(a);
	}
	public void addPrevMonthAction(AbstractAction a){
		pCalendar.addPrevMonthAction(a);
	}
	public void addChangeYearListener(ActionListener a){
		pCalendar.addChangeYearListener(a);
	}
}
