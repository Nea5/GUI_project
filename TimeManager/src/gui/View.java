package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;

import model.ToDoModel;
/**
 * 
 */
public class View extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TimeMenuBar menubar;
	private TabPanel tabbedPane;
	private ToDoModel model;
	private JPanel background, pWest;
	private JLabel clock;
	private JComboBox<String> cList;
	
	public View(ToDoModel model){
		this.model = model;
		createMenuBar(System.getProperty("os.name"));
		createTabbedPane();
		createClock();
		createPanels();
		createCombobox();
		
		int eb = 10;
			
		Border border = BorderFactory.createEmptyBorder(eb, eb, eb, eb);
		background.setBorder(border);
		
		this.add(background);
		
		background.add(tabbedPane, BorderLayout.CENTER);
		background.add(pWest, BorderLayout.WEST);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		pWest.add(clock, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 2;
		pWest.add(cList, c);
		
		
		this.setJMenuBar(menubar);
		this.setMinimumSize(new Dimension(700,600));
		this.loadPosition();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		
		// TODO loadSize works only if it is after pack(), why?
		this.loadSize();
	}
	private void createPanels(){
		background  = new JPanel(new BorderLayout());
		pWest  = new JPanel(new GridBagLayout());
	}
	private void createCombobox(){
		String [] lists = { TimeManager.rb.getString("list_week"),
				 			TimeManager.rb.getString("list_month"), 
				 			TimeManager.rb.getString("list_done"), 
				 			TimeManager.rb.getString("list_overdue")};
		cList= new JComboBox<String>(lists);
		cList.setBorder(BorderFactory.createTitledBorder(TimeManager.rb.getString("list")));
	}
	private void createClock(){
		clock = new ClockLabel(TimeManager.rb.getString("clock_label"),
					"dd/MM/yy", "HH:mm:ss");
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
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else{

		}
	}
	
	public void list(String s){
		JPanel pListed;
		if(s.equals(TimeManager.rb.getString("list_week"))){
			pListed = new ListPanel(model.getWeek());
			JOptionPane.showMessageDialog(this, pListed, 
			TimeManager.rb.getString("list")+ " " +
			TimeManager.rb.getString("list_week"), JOptionPane.PLAIN_MESSAGE);
		}else if(s.equals(TimeManager.rb.getString("list_month"))){
			Calendar cal = new GregorianCalendar();
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			pListed = new ListPanel(model.getMonth(month, year));
			JOptionPane.showMessageDialog(this, pListed, 
			TimeManager.rb.getString("list")+ " " +
			TimeManager.rb.getString("list_month"), JOptionPane.PLAIN_MESSAGE);
		}else if(s.equals(TimeManager.rb.getString("list_done"))){
			pListed = new ListPanel(model.getDone());
			JOptionPane.showMessageDialog(this, pListed, 
			TimeManager.rb.getString("list")+ " " +
			TimeManager.rb.getString("list_done"), JOptionPane.PLAIN_MESSAGE);
		}else if(s.equals(TimeManager.rb.getString("list_overdue"))){
			pListed = new ListPanel(model.getOverdue());
			JOptionPane.showMessageDialog(this, pListed, 
			TimeManager.rb.getString("list") + " " +
			TimeManager.rb.getString("list_overdue"), JOptionPane.PLAIN_MESSAGE);
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
	
	public void addLAFListener(ItemListener laf){
		menubar.addLAFListener(laf);
	}
	
	public void newFilter(String s){
		tabbedPane.newFilter(s);
	}
	public void addFilterListener(ItemListener l){
		tabbedPane.addFilterListener(l);
	}
	
	public void addMarkDoneAction(AbstractAction a){
		tabbedPane.addMarkDoneAction(a);
	}
	public void markDone(){
		tabbedPane.markDone();
	}
	public void addListListener(ActionListener l){
		cList.addActionListener(l);
	}
	public void addRightClickListener(MouseListener ma){
		tabbedPane.addRightClickListener(ma);
	}
	public void rightClickMenu(MouseEvent e){
		tabbedPane.rightClickMenu(e);
	}
	public void prevMonth(){
		tabbedPane.prevMonth();
	}
	public void nextMonth (){
		tabbedPane.nextMonth();
	}
	public void changeYear(){
		tabbedPane.changeYear();
	}
	public void addNextMonthAction(AbstractAction a){
		tabbedPane.addNextMonthAction(a);
	}
	public void addPrevMonthAction(AbstractAction a){
		tabbedPane.addPrevMonthAction(a);
	}
	public void addChangeYearListener(ActionListener a){
		tabbedPane.addChangeYearListener(a);
	}
}
