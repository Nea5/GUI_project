package gui.mypanels;

import gui.TimeManager;

import java.awt.GridLayout;
import java.util.Date;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import model.*;
/**
 * Panel for adding tasks
 * @author Johan Dahlkar 
 * @author Markus Ebbesson 
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class NewTaskPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel l_name, l_date, l_category, l_priority;
	private JTextField tf_name;
	private JComboBox<String> cb_category, cb_priority;
	private JSpinner s_date;
	private SpinnerDateModel s_model;
	/**
	 * Constructs a NewTaskPanel
	 */
	public NewTaskPanel(){
		createLabels();
		createTextFields();
		createComboBoxes();
		createSpinners();		
		this.add(l_name);
		this.add(tf_name);
		this.add(l_date);
		this.add(s_date);
		this.add(l_category);
		this.add(cb_category);
		this.add(l_priority);
		this.add(cb_priority);
		
		this.setLayout(new GridLayout(4, 2));
	}
	/**
	 * Creates labels for all fields in the panel
	 */
	private void createLabels(){
		l_name = new JLabel(TimeManager.rb.getString("l_name") + ": ");
		l_date = new JLabel(TimeManager.rb.getString("l_date") + ": ");
		l_category = new JLabel(TimeManager.rb.getString("l_category") + ": "); 
		l_priority = new JLabel(TimeManager.rb.getString("l_priority") + ": ");
	}
	/**
	 * Creates text field for the panel
	 */
	private void createTextFields(){
		tf_name = new JTextField(16);
	}
	/**
	 * Creates drop down menus/combo boxes with different alternatives
	 */
	private void createComboBoxes(){
		String[] categories = {" ", "School", "Work", "Personal"};
		String[] priorities = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		cb_category = new JComboBox<String>(categories);
		cb_priority = new JComboBox<String>(priorities);
	}
	/**
	 * Creates a spinner for entering date and time
	 */
	private void createSpinners(){
		Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        cal.add(Calendar.HOUR, -1);
        Date earliestDate = cal.getTime();
        cal.add(Calendar.YEAR, 3);
        Date latestDate = cal.getTime();
        s_model = new SpinnerDateModel(date, earliestDate, latestDate, Calendar.YEAR);
        //s_model.setValue(date);
		s_date = new JSpinner(s_model);
		s_date.setEditor(new JSpinner.DateEditor(s_date,"dd/MM/yy HH:mm"));
	}
	/**
	 * Returns the content from the user's input
	 * @return Returns the content from the user's input as an Object array
	 */
	public ToDo getData(){
		String name = tf_name.getText();
		String category = (String)cb_category.getSelectedItem();
		int priority = Integer.parseInt((String)cb_priority.getSelectedItem());
		return (new ToDo(name, category, priority, s_model.getDate()));
	}
	/**
	 * Sets tf_name to editable or not depending on b
	 * @param b 
	 */
	public void setEdit(boolean b){
		tf_name.setEditable(b);
	}
	/**
	 * Sets the text / selected value to tf_name, cb_category, cb_priority and s_model, from an ToDo object
	 * 
	 * @param t 
	 */
	public void setData(ToDo t){
		tf_name.setText(t.getName());
		cb_category.setSelectedItem(t.getCategory());
		cb_priority.setSelectedItem("" + t.getPriority());
		s_model.setValue(t.getDue());
	}
}
