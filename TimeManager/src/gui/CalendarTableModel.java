package gui;

import javax.swing.table.AbstractTableModel;

public class CalendarTableModel extends AbstractTableModel {

	private String[] columnNames;
	private Object[][] data = new Object[6][7];
	
	public CalendarTableModel(String[] columnNames) {
		// TODO Auto-generated constructor stub
		this.columnNames = columnNames;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;	
		fireTableCellUpdated(row, col);
	}
    public Class<?> getColumnClass(int col) {
		return (new Integer(1)).getClass();
    }
	public String getColumnName(int col) {
		return columnNames[col];
	}
}
