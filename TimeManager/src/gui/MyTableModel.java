package gui;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames;
	private Object[][] data;
	
	public MyTableModel(String[] columns, Object[][] data) {
		// TODO Auto-generated constructor stub
		this.columnNames = columns;
		this.data = data;
	}

	public void addRow(Object[] row) {
		Object[][] newData = new Object[getRowCount()+1][getColumnCount()];
		for(int i = 0; i < getRowCount(); i++){
			newData[i] = data[i];
		}
		newData[getRowCount()] = row;
		data = newData;
		fireTableDataChanged();
	}
	
	public void removeRow(int row){
		Object[][] newData = new Object[getRowCount()-1][getColumnCount()];
		int i;
		for(i = 0; i < row; i++){
			newData[i] = data[i];
		}
		for(;i < (getRowCount()-1);i++){
			newData[i] = data[i+1];
		}
		data = newData;
		fireTableRowsDeleted(0, getRowCount());
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	public String getColumnName(int col) {
		return columnNames[col];
	}
	@Override
    public Class<?> getColumnClass(int col) {
		if (col == 2){
			return java.util.GregorianCalendar.class;
		}
		return (getValueAt(0, col).getClass());
    }
	@Override
	public boolean isCellEditable(int row, int col){
		return (col == 0);
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;	
		fireTableCellUpdated(row, col);
	}

}
