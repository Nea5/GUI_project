package gui;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import model.ToDo;
import model.ToDoModel;

public class CalendarTable extends JTable {
	private Calendar date;
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
	
	public CalendarTable(Calendar date, ToDoModel tdModel) {
		this.tdModel = tdModel;
		month = date.get(Calendar.MONTH);
		year = date.get(Calendar.YEAR);
		this.date = date;
		this.setRowSelectionAllowed(false);
		//this.setColumnSelectionAllowed(true);
		//this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowHeight(40);
		this.setModel(tableModel);
		this.setShowGrid(true);
		this.setGridColor(Color.BLACK);
		
		updateTable(month, year);
	}
	
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
		if(som > 1){
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
