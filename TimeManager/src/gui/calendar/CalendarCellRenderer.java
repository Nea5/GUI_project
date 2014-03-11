package gui.calendar;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.ToDo;
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
public class CalendarCellRenderer extends DefaultTableCellRenderer {
	private ToDoModel tdModel;
	private int month, year;
	/**
	 * Constructs a CalendarCellRenderer that renders the cells of our
	 * CalendarTable.
	 * 
	 * @param month Month to use
	 * @param year Year to use
	 * @param tdModel Used to get data from.
	 */
	public CalendarCellRenderer(int month, int year, ToDoModel tdModel){
		this.tdModel = tdModel;
		this.month = month;
		this.year = year;
	}
	/**
	 * Colors the cells.
	 */
	public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, 
													boolean focused, int row, int column){
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);	
		if(column == 5 || column == 6) { // Colors the weekends red
			setBackground(new Color(255, 220, 220)); 
		}else{ // Colors the rest white
			setBackground(Color.WHITE);
		}
		Iterator<ToDo> it = tdModel.getMonth(month, year); // Gets the ToDos year and month
		Calendar cal = new GregorianCalendar(Locale.GERMAN);
		Calendar cal2 = new GregorianCalendar(year, month,1);
		int fD = cal2.get(GregorianCalendar.DAY_OF_WEEK);
		if(fD > 1){ // Converts to European week standard.
			fD--;
		}else{
			fD = 7;
		}
		// Colors the cells with ToDos yellow
		while(it.hasNext()){
			ToDo t = it.next();
			Date due = t.getDue();
			cal.setTime(due);
			int d = cal.get(Calendar.DAY_OF_MONTH);
			int day = fD +d;
			d = (day-2) % 7;
			int w = (day-2) / 7;
			if((d) == column && (w) == row){
				setBackground(new Color(255, 255, 195));
			}
		}
		return this;
	}
}
