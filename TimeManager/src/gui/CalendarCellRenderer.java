package gui;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.ToDo;
import model.ToDoModel;

public class CalendarCellRenderer extends DefaultTableCellRenderer {
	private ToDoModel tdModel;
	private Calendar date;
	private int month, year;
	
	public CalendarCellRenderer(int month, int year, ToDoModel tdModel){
		this.tdModel = tdModel;
		this.month = month;
		this.year = year;
	}

	public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, 
													boolean focused, int row, int column){
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		
		if(column == 5 || column == 6) {
			setBackground(new Color(255, 220, 220));
		}else{
			setBackground(Color.WHITE);
		}
		Iterator<ToDo> it = tdModel.getMonth(month, year);
		Calendar cal = new GregorianCalendar();
		while(it.hasNext()){
			ToDo t = it.next();
			Date due = t.getDue();
			cal.setTime(due);
			int d = cal.get(GregorianCalendar.DAY_OF_WEEK);
			int w = cal.get(GregorianCalendar.WEEK_OF_MONTH);
			if(d > 1){
				d--;
			}else{
				d = 7;
				w--;
			}
			if((d-1) == column && (w-1) == row){
				setBackground(new Color(255, 255, 195));
			}
		}
		return this;
	}
}
