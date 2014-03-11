package gui;

import gui.customcomp.ClockLabel;
import gui.mypanels.ListPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;

import model.ToDoModel;
/**
 * @author Johan Dahlkar 
 * @author Markus Ebbesson 
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private TimeMenuBar menubar;
	private TabPanel tabbedPane;
	private ToDoModel model;
	private JPanel background, pWest;
	private JLabel clock;
	private JComboBox<String> cList;
	/**
	 * Constructs a new View with a ToDoModel to get and set data to/from
	 * @param model
	 */
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
		pWest.setPreferredSize(new Dimension(150, 250));
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
		this.setMinimumSize(new Dimension(710,300));
		this.loadPosition();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		// TODO loadSize works only if it is after pack(), why?
		this.loadSize();
	}
	/**
	 * Creates all JPanels to be used
	 */
	private void createPanels(){
		background  = new JPanel(new BorderLayout());
		pWest  = new JPanel(new GridBagLayout());
	}
	/**
	 * Creates a JComboBox with a titled border
	 */
	private void createCombobox(){
		String [] lists = { TimeManager.rb.getString("list_week"),
				 			TimeManager.rb.getString("list_month"), 
				 			TimeManager.rb.getString("list_done"), 
				 			TimeManager.rb.getString("list_overdue")};
		cList= new JComboBox<String>(lists);
		cList.setBorder(BorderFactory.createTitledBorder(TimeManager.rb.getString("list")));
	}
	/**
	 * Creates a ClockLabel
	 */
	private void createClock(){
		clock = new ClockLabel(TimeManager.rb.getString("clock_label"),
					"dd/MM/yy", "HH:mm:ss");
	}
	/**
	 * Creates a TimeMenuBar, and makes it appear on the right location 
	 * depending on which Operating System is used
	 * 
	 * @param osName Operating System Name
	 */
	private void createMenuBar(String osName){
		if(osName.equals("Mac OS X")){
			System.setProperty("apple.laf.useScreenMenuBar", "true"); 
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "TimeManager");
		}
		menubar = new TimeMenuBar();
	}
	/**
	 * Loads the position of this View from settings.properties
	 */
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
	/**
	 * Loads the size of this view (Window size) from settings.properties
	 */
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
	/**
	 * Opens a JOptionPane showing a ListPanel. The ListPanel shows
	 * different kind of data depending on the String value
	 * @param s String to use
	 */
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
	/**
	 * Creates a TabPanel
	 */
	private void createTabbedPane(){
		tabbedPane = new TabPanel(model);
		tabbedPane.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        System.out.println("Tab: " + tabbedPane.getSelectedIndex());
		        TimeManager.active_tab = Integer.toString(tabbedPane.getSelectedIndex());
		    }
		});
	}
	/**
	 * Runs the newTask() method in TabPanel
	 */
	public void newTask(){
		tabbedPane.newTask();
	}
	/**
	 * Runs the editTask() method in TabPanel
	 */
	public void editTask(){
		tabbedPane.editTask();
	}
	/**
	 * Runs the deleteTasks() method in TabPanel
	 */
	public void deleteTasks(){
		tabbedPane.deleteTasks();
	}
	/**
	 * Runs addNewTaskAction(AbstractAction) method in TabPanel and TimeMenuBar
	 * @param a AbstractAction to use
	 */
	public void addNewTaskAction(AbstractAction a){
		tabbedPane.addNewTaskAction(a);
		menubar.addNewTaskAction(a);
	}
	/**
	 * Runs addEditTaskAction(AbstractAction) method in TabPanel and TimeMenuBar
	 * @param a AbstractAction to use
	 */
	public void addEditTaskAction(AbstractAction a){
		tabbedPane.addEditTaskAction(a);
		menubar.addEditTaskAction(a);
	}
	/**
	 * Runs addDeleteTaskAction(AbstractAction) method in TabPanel and TimeMenuBar
	 * @param a AbstractAction to use
	 */
	public void addDeleteTaskAction(AbstractAction a){
		tabbedPane.addDeleteTaskAction(a);
		menubar.addDeleteTaskAction(a);
	}
	/**
	 * Runs addHelpAction(AbstractAction) method in TimeMenuBar
	 * @param a AbstractAction to use
	 */
	public void addHelpAction(AbstractAction a){
		menubar.addHelpAction(a);
	}
	/**
	 * Runs the addSelectionListener(ListSelectionListener) method in TabPanel
	 * @param l ListSelectionListener to use
	 */
	public void addSelectionListener(ListSelectionListener l){
		tabbedPane.addSelectionListener(l);
	}
	/**
	 * Runs the addTableModelListener(TableModelListener) method in TabPanel
	 * @param t TableModelListener to use
	 */
	public void addTableModelListener(TableModelListener t){
		tabbedPane.addTableModelListener(t);
	}
	/**
	 * Runs the getMarked() method in TabPanel
	 * @return boolean
	 */
	public boolean getMarked(){
		return tabbedPane.getMarked();
	}
	/**
	 * Runs the addLanguageListener(ItemListener) method in TimeMenuBar
	 * @param l ItemListener to use
	 */
	public void addLanguageListener(ItemListener l){
		menubar.addLanguageListener(l);
	}
	/**
	 * Runs the addLAFListener(ItemListener) method in TimeMenuBar
	 * @param l ItemListener to use
	 */
	public void addLAFListener(ItemListener laf){
		menubar.addLAFListener(laf);
	}
	/**
	 * Runs the newFilter(String) method in TabPanel
	 * @param s String to use
	 */
	public void newFilter(String s){
		tabbedPane.newFilter(s);
	}
	/**
	 * Runs the addFilterListener(ItemListener) in TabPanel
	 * @param l ItemListener to use
	 */
	public void addFilterListener(ItemListener l){
		tabbedPane.addFilterListener(l);
	}
	/**
	 * Runs the addMarkDoneAction(AbstractAction) method in TabPanel
	 * @param a AbstractAction to use
	 */
	public void addMarkDoneAction(AbstractAction a){
		tabbedPane.addMarkDoneAction(a);
	}
	/**
	 * Runs the markDone() method in TabPanel
	 */
	public void markDone(){
		tabbedPane.markDone();
	}
	/**
	 * Adds a ActionListener to cList
	 * @param l ActionListener to add
	 */
	public void addListListener(ActionListener l){
		cList.addActionListener(l);
	}
	/**
	 * Runs the addRightClickListener(MouseListener) method in TabPanel
	 * @param ma MouseListener to use
	 */
	public void addRightClickListener(MouseListener ma){
		tabbedPane.addRightClickListener(ma);
	}
	/**
	 * Runs the rightClickMenu(MouseEvent) method in TabPanel
	 * @param e MouseEvent to use
	 */
	public void rightClickMenu(MouseEvent e){
		tabbedPane.rightClickMenu(e);
	}
	/**
	 * Runs the prevMonth() method in TabPanel
	 */
	public void prevMonth(){
		tabbedPane.prevMonth();
	}
	/**
	 * Runs the nextMonth() method in TabPanel
	 */
	public void nextMonth (){
		tabbedPane.nextMonth();
	}
	/**
	 * Runs the changeYear() method in TabPanel
	 */
	public void changeYear(){
		tabbedPane.changeYear();
	}
	/**
	 * Runs the addNextMonthAction(AbstractAction) method in TabPanel
	 * @param a AbstractAction to use
	 */
	public void addNextMonthAction(AbstractAction a){
		tabbedPane.addNextMonthAction(a);
	}
	/**
	 * Runs the addPrevMonthAction(AbstractAction) method in TabPanel
	 * @param a AbstractAction to use
	 */
	public void addPrevMonthAction(AbstractAction a){
		tabbedPane.addPrevMonthAction(a);
	}
	/**
	 * Runs the addChangeYearListener(ActionListener) method in TabPanel
	 * @param a ActionListener to use
	 */
	public void addChangeYearListener(ActionListener a){
		tabbedPane.addChangeYearListener(a);
	}
}
