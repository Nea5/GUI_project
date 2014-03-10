package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ToDoModel;

public class CalendarPanel extends JPanel {
	private CalendarTable calTable;
	private JScrollPane tableScroll;
	private ToDoModel tdModel;
	private JButton bNext, bPrev;
	private int realDay, realMonth, realYear, currentMonth, currentYear;
	private GregorianCalendar cal;
	private JPanel pNorth;
	private JComboBox cbYear;
	private JLabel lMonth;
	private String[] months =  {
			TimeManager.rb.getString("jan"),
			TimeManager.rb.getString("feb"),
			TimeManager.rb.getString("mar"),
			TimeManager.rb.getString("apr"),
			TimeManager.rb.getString("may"),
			TimeManager.rb.getString("jun"),
			TimeManager.rb.getString("jul"),
			TimeManager.rb.getString("aug"),
			TimeManager.rb.getString("sep"),
			TimeManager.rb.getString("oct"),
			TimeManager.rb.getString("nov"),
			TimeManager.rb.getString("dec")};
	
	public CalendarPanel(ToDoModel tdModel) {
		this.tdModel = tdModel;
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		setTime();
		createButtons();
		createCalendar();
		createPanels();
		createComboBox();
		createLabels();
		
		this.add(pNorth, BorderLayout.NORTH);
		this.add(tableScroll, BorderLayout.CENTER);
		
		pNorth.add(bPrev);
		pNorth.add(lMonth);
		pNorth.add(cbYear);
		pNorth.add(bNext);
		
		
		
	}
	private void setTime(){
		cal = new GregorianCalendar(); //Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
		realMonth = cal.get(GregorianCalendar.MONTH); //Get month
		realYear = cal.get(GregorianCalendar.YEAR); //Get year
		currentMonth = realMonth; //Match month and year
		currentYear = realYear;
	}
	private void createButtons(){
		bNext = new JButton();
		bPrev = new JButton();
	}
	private void createCalendar(){
		calTable = new CalendarTable(cal, tdModel);
		tableScroll = new JScrollPane(calTable);
	}
	private void createPanels(){
		pNorth = new JPanel();
	}
	private void createComboBox(){
		cbYear = new JComboBox();
		for (int i=realYear-1; i<=realYear+5; i++){
			cbYear.addItem(String.valueOf(i));
		}
	}
	private void createLabels(){
		lMonth = new JLabel();
		lMonth.setText(months[currentMonth]); 
	}
	private void setComponents(){
		//Allow/disallow buttons
				bPrev.setEnabled(true);
				bNext.setEnabled(true);
				if (currentMonth == 0 && currentYear <= realYear-1){bPrev.setEnabled(false);} //Too early
				if (currentMonth == 11 && currentYear >= realYear+5){bNext.setEnabled(false);} //Too late
				lMonth.setText(months[currentMonth]); //Refresh the month label (at the top)
				//lMonth.setBounds(160-lMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
				cbYear.setSelectedItem(String.valueOf(currentYear)); //Select the correct year in the combo box
	}
	
	public void prevMonth(){
		if (currentMonth == 0){ //Back one year
			currentMonth = 11;
			currentYear -= 1;
		}
		else{ //Back one month
			currentMonth -= 1;
		}
		setComponents();
		calTable.updateTable(currentMonth, currentYear);
	}
	public void nextMonth (){
		if (currentMonth == 11){ //Foward one year
			currentMonth = 0;
			currentYear += 1;
		}
		else{ //Foward one month
			currentMonth += 1;
		}
		setComponents();
		calTable.updateTable(currentMonth, currentYear);
	}
	public void changeYear(){
		if (cbYear.getSelectedItem() != null){
			String b = cbYear.getSelectedItem().toString();
			currentYear = Integer.parseInt(b);
			calTable.updateTable(currentMonth, currentYear);
		}
		setComponents();
		calTable.updateTable(currentMonth, currentYear);
	}
	public void addNextMonthAction(AbstractAction a){
		bNext.setAction(a);
	}
	public void addPrevMonthAction(AbstractAction a){
		bPrev.setAction(a);
	}
	public void addChangeYearListener(ActionListener a){
		cbYear.addActionListener(a);
	}
}
