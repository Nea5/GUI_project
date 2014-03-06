package gui;

import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;

import model.ToDoModel;
/**
 * 
 */
public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TimeMenuBar menubar;
	private TabPanel tabbedPane;
	private ToDoModel model;
	
	public MainFrame(ToDoModel model){
		this.model = model;
		createMenuBar(System.getProperty("os.name"));
		createTabbedPane();
		
		this.add(tabbedPane);
		
		this.setJMenuBar(menubar);
		this.setMinimumSize(new Dimension(450,200));
		this.loadPosition();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		
		// TODO loadSize works only if it is after pack(), why?
		this.loadSize();
	}
	
	private void createMenuBar(String osName){
		if(osName.equals("Mac OS X")){
			System.setProperty("apple.laf.useScreenMenuBar", "true"); 
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "TimeManager");	
		}
		menubar = new TimeMenuBar();
	}
	
	private void loadPosition(){
		FileInputStream in;
		File settings = new File("settings.properties");
		if(settings.exists()){
			try {
				in = new FileInputStream("settings.properties");
				Properties prop = new Properties();
				try {
					prop.load(in);
					in.close();
					if (prop.containsKey("windowposx") && prop.containsKey("windowposy")){
						String windowposx = prop.getProperty("windowposx");
						String windowposy = prop.getProperty("windowposy");
						this.setLocation(Integer.parseInt(windowposx), Integer.parseInt(windowposy));
					}
					else{
						// TODO standard position
					}
				} catch (IOException e) {
					e.printStackTrace();
				}	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else{
			// TODO standard position
		}
	}
	
	private void loadSize(){
		FileInputStream in;
		File settings = new File("settings.properties");
		if(settings.exists()){
			try {
				in = new FileInputStream("settings.properties");
				Properties prop = new Properties();
				try {
					prop.load(in);
					in.close();
					
					if (prop.containsKey("windowwidth") && prop.containsKey("windowheight")){
						String windowwidth = prop.getProperty("windowwidth");
						String windowheight = prop.getProperty("windowheight");
						this.setSize(Integer.parseInt(windowwidth), Integer.parseInt(windowheight));
					}
					else{
						// TODO standard size
					}
				} catch (IOException e) {
					e.printStackTrace();
				}	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else{
			// TODO standard size
		}
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
