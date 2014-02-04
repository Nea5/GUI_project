package gui;

import java.util.GregorianCalendar;

import javax.swing.table.AbstractTableModel;

public class TaskTableModel extends AbstractTableModel {
	String[] columnNames = {" ", "Task", "Date", "Category", "Priority"};
	Object[][] data = {
			{
				Boolean.FALSE, "Buy milk", new GregorianCalendar().getTime(),
				"Home", 5
			},
			{
				Boolean.FALSE, "Eat", new GregorianCalendar().getTime(),
				"Personal", 6
			}	
	};
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int col){
		return columnNames[col];
	}
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public Class getColumnClass(int col){
		if (col == 2){
			return java.util.GregorianCalendar.class;
		}
		return (getValueAt(0, col).getClass());
	}
	
	public void setValueAt(Object value, int row, int col){
		data[row][col] = value;
	}
	
	public boolean isCellEditable(int row, int col){
		return (col == 0);
	}
}
