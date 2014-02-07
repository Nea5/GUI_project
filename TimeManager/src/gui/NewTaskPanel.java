package gui;

import java.awt.GridLayout;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class NewTaskPanel extends JPanel {
	private JLabel l_name, l_date, l_category, l_priority;
	private JTextField tf_name;
	private JComboBox cb_category, cb_priority;
	private JSpinner s_date;
	private SpinnerDateModel s_model;
	private SimpleDateFormat s_format;
	
	
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
	
	private void createLabels(){
		l_name = new JLabel("Name:");
		l_date = new JLabel("Date:");
		l_category = new JLabel("Category:"); 
		l_priority = new JLabel("Priority:");
	}
	
	private void createTextFields(){
		tf_name = new JTextField(16);
	}
	
	private void createComboBoxes(){
		String[] categories = {" ", "School", "Work", "Personal"};
		String[] priorities = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		cb_category = new JComboBox<String>(categories);
		cb_priority = new JComboBox<String>(priorities);
	}
	
	private void createSpinners(){
		Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        s_model = new SpinnerDateModel();
        s_model.setValue(date);
		s_date = new JSpinner(s_model);
		s_date.setEditor(new JSpinner.DateEditor(s_date,"yyyy-MM-dd HH:mm"));
		s_format = ((JSpinner.DateEditor) s_date.getEditor()).getFormat();
	    s_format.applyPattern("yyyy-MM-dd HH:mm");
	}
	
	public Object[] getData(){
		String name = tf_name.getText();
		Date date = s_model.getDate();
		String category = (String)cb_category.getSelectedItem();
		int priority = Integer.parseInt((String)cb_priority.getSelectedItem());
		Calendar newDate = new GregorianCalendar();
		newDate.setTime(date);
		return (new Object[]{
			Boolean.FALSE, name, newDate.getTime(), category, priority 
		});
	}
	
	public static void main(String [] args){
		JFrame frame = new JFrame();
		JPanel panel = new NewTaskPanel();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
	}
}
