package gui;

import java.awt.Dimension;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;

import model.ToDoModel;
/**
 * 
 */
public class MainFrame extends JFrame {
	private TimeMenuBar menubar;
	private TabPanel tabbedPane;
	private ToDoModel model;
	
	public MainFrame(ToDoModel model){
		this.model = model;
		createMenuBar(System.getProperty("os.name"));
		createTabbedPane();
		
		this.add(tabbedPane);
		
		this.setJMenuBar(menubar);
		this.setMinimumSize(new Dimension(485,560));
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
	}
	
	private void createMenuBar(String osName){
		if(osName.equals("Mac OS X")){
			System.setProperty("apple.laf.useScreenMenuBar", "true"); 
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "TimeManager");	
		}
		menubar = new TimeMenuBar();
	}
	
	private void createTabbedPane(){
		tabbedPane = new TabPanel(model);
	}
	public void newTask(){
		tabbedPane.newTask();
	}
	public void editTask(){
		tabbedPane.editTask();
	}
	public void deleteTasks(){
		tabbedPane.deleteTasks();
	}
	public void addNewTaskAction(AbstractAction a){
		tabbedPane.addNewTaskAction(a);
		menubar.addNewTaskAction(a);
	}
	public void addEditTaskAction(AbstractAction a){
		tabbedPane.addEditTaskAction(a);
		menubar.addEditTaskAction(a);
	}
	public void addDeleteTaskAction(AbstractAction a){
		tabbedPane.addDeleteTaskAction(a);
		menubar.addDeleteTaskAction(a);
	}
	public void addHelpAction(AbstractAction a){
		menubar.addHelpAction(a);
	}
	public void addSelectionListener(ListSelectionListener l){
		tabbedPane.addSelectionListener(l);
	}
	public void addTableModelListener(TableModelListener t){
		tabbedPane.addTableModelListener(t);
	}
	public boolean getMarked(){
		return tabbedPane.getMarked();
	}
	public void addLanguageListener(ItemListener l){
		menubar.addLanguageListener(l);
	}
	public void newFilter(String s){
		tabbedPane.newFilter(s);
	}
	public void addFilterListener(ItemListener l){
		tabbedPane.addFilterListener(l);
	}
}
