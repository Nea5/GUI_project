package gui;

import gui.calendar.CalendarPanel;
import gui.todotable.TablePanel;

import java.awt.Color;
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
	private static final long serialVersionUID = 1L;
	private CalendarPanel pCalendar;
	private TablePanel pTable;
	private ToDoModel model;
	/**
	 * Constructs a TabPanel with a ToDoModel to get and set data from/to
	 * @param model ToDoModel to use
	 */
	public TabPanel(ToDoModel model) {
		this.model = model;
		createPanels();
		this.setBackground(Color.WHITE);
		this.addTab(TimeManager.rb.getString("p_lists"), pTable);
		this.addTab(TimeManager.rb.getString("p_calendar"), pCalendar);
	}
	/**
	 * Creates all the JPanels to be used
	 */
	private void createPanels(){
		pTable = new TablePanel(model);
		pCalendar = new CalendarPanel(model); // Temp
	}
	/**
	 * Runs the newTask() method in TablePanel
	 */
	public void newTask(){
		pTable.newTask();
	}
	/**
	 * Runs the editTask() method in TablePanel
	 */
	public void editTask(){
		pTable.editTask();
	}
	/**
	 * Runs the deleteTasks() method in TablePanel
	 */
	public void deleteTasks(){
		pTable.deleteTasks();
	}
	/**
	 * Runs the addNewTaskAction(AbstractAction) method in TablePanel
	 * @param a AbstractAction to use
	 */
	public void addNewTaskAction(AbstractAction a){
		pTable.addNewTaskAction(a);
	}
	/**
	 * Runs the addEditTaskAction(AbstractAction) method in TablePanel
	 * @param a AbstractAction to use
	 */
	public void addEditTaskAction(AbstractAction a){
		pTable.addEditTaskAction(a);
	}
	/**
	 * Runs the addDeleteTaskAction(AbstractAction) method in TablePanel
	 * @param a AbstractAction to use
	 */
	public void addDeleteTaskAction(AbstractAction a){
		pTable.addDeleteTaskAction(a);
	}
	/**
	 * Runs the addSelectionListener(ListSelectionListener) method in TablePanel
	 * @param l ListSelectionListener to use
	 */
	public void addSelectionListener(ListSelectionListener l){
		pTable.addSelectionListener(l);
	}
	/**
	 * Runs the addTableModelListener(TableModelListener) method in TablePanel
	 * @param t TableModelListener to use
	 */
	public void addTableModelListener(TableModelListener t){
		pTable.addTableModelListener(t);
	}
	/**
	 * Runs the getMarked() method in TablePanel
	 * @return boolean
	 */
	public boolean getMarked(){
		return pTable.getMarked();
	}
	/**
	 * Runs the newFilter(String) method in TablePanel
	 * @param s String to use
	 */
	public void newFilter(String s){
		pTable.newFilter(s);
	}
	/**
	 * Runs the addFilterListener(ItemListener) in TablePanel
	 * @param l ItemListener to use
	 */
	public void addFilterListener(ItemListener l){
		pTable.addFilterListener(l);
	}
	/**
	 * Runs the addMarkDoneAction(AbstractAction) method in TablePanel
	 * @param a AbstractAction to use
	 */
	public void addMarkDoneAction(AbstractAction a){
		pTable.addMarkDoneAction(a);
	}
	/**
	 * Runs the markDone() method in TablePanel
	 */
	public void markDone(){
		pTable.markDone();
	}
	/**
	 * Runs the addRightClickListener(MouseListener) method in TablePanel
	 * @param ma MouseListener to use
	 */
	public void addRightClickListener(MouseListener ma){
		pTable.addRightClickListener(ma);
	}
	/**
	 * Runs the rightClickMenu(MouseEvent) method in TablePanel
	 * @param e MouseEvent to use
	 */
	public void rightClickMenu(MouseEvent e){
		pTable.rightClickMenu(e);
	}
	/**
	 * Runs the prevMonth() method in CalendarPanel
	 */
	public void prevMonth(){
		pCalendar.prevMonth();
	}
	/**
	 * Runs the nextMonth() method in CalendarPanel
	 */
	public void nextMonth (){
		pCalendar.nextMonth();
	}
	/**
	 * Runs the changeYear() method in CalendarPanel
	 */
	public void changeYear(){
		pCalendar.changeYear();
	}
	/**
	 * Runs the addNextMonthAction(AbstractAction) method in CalendarPanel
	 * @param a AbstractAction to use
	 */
	public void addNextMonthAction(AbstractAction a){
		pCalendar.addNextMonthAction(a);
	}
	/**
	 * Runs the addPrevMonthAction(AbstractAction) method in CalendarPanel
	 * @param a AbstractAction to use
	 */
	public void addPrevMonthAction(AbstractAction a){
		pCalendar.addPrevMonthAction(a);
	}
	/**
	 * Runs the addChangeYearListener(ActionListener) method in CalendarPanel
	 * @param a ActionListener to use
	 */
	public void addChangeYearListener(ActionListener a){
		pCalendar.addChangeYearListener(a);
	}
}
