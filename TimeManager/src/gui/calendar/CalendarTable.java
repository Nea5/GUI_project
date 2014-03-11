package gui.calendar;

import gui.TimeManager;

import java.awt.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import model.ToDoModel;
/**
 * 
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class CalendarTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {TimeManager.rb.getString("mon"),
			TimeManager.rb.getString("tue"),
			TimeManager.rb.getString("wen"),
			TimeManager.rb.getString("thu"),
			TimeManager.rb.getString("fri"),
			TimeManager.rb.getString("sat"),
			TimeManager.rb.getString("sun")
	};
	private CalendarTableModel tableModel = new CalendarTableModel(columnNames);
	private ToDoModel tdModel;
	private int month, year, som, nod;
	/**
	 * Constructs a Calendar table with a date and a ToDoModel
	 * @param date Date to show
	 * @param tdModel Used to get data.
	 */
	public CalendarTable(Calendar date, ToDoModel tdModel) {
		this.tdModel = tdModel;
		month = date.get(Calendar.MONTH);
		year = date.get(Calendar.YEAR);
		this.setRowSelectionAllowed(false);
		//this.setColumnSelectionAllowed(true);
		//this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowHeight(40);
		this.setModel(tableModel);
		this.setShowGrid(true);
		this.setGridColor(Color.BLACK);
		
		updateTable(month, year);
	}
	/**
	 * Updates which month of which year this table should show
	 * 
	 * @param month Month to be shown
	 * @param year Year to be shown
	 */
	public void updateTable(int month, int year){
		for (int i=0; i<6; i++){
			for (int j=0; j<7; j++){
				tableModel.setValueAt(null, i, j);
			}
		}
		GregorianCalendar cal = new GregorianCalendar(year, month,1);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		if(som > 1){// Converts to European week standard.
			som--;
		}else{
			som = 7;
		}
		for (int i=1; i<=nod; i++){
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			tableModel.setValueAt(i, row, column);
		}
		setDefaultRenderer(this.getColumnClass(0), new CalendarCellRenderer(month, year, tdModel));
	}
}
