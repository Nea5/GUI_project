package gui.todotable;

import javax.swing.table.AbstractTableModel;
/**
 * 
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class TodoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] columnNames;
	private Object[][] data;
	/**
	 * Constructs a TodoTableModel with column names and some data
	 * @param columns Column names
	 * @param data Data 
	 */
	public TodoTableModel(String[] columns, Object[][] data) {
		// TODO Auto-generated constructor stub
		this.columnNames = columns;
		this.data = data;
	}
	/**
	 * Adds a row to the data
	 * @param row row to add
	 */
	public void addRow(Object[] row) {
		Object[][] newData = new Object[getRowCount()+1][getColumnCount()];
		for(int i = 0; i < getRowCount(); i++){
			newData[i] = data[i];
		}
		newData[getRowCount()] = row;
		data = newData;
		fireTableDataChanged();
	}
	/**
	 * Removes a row from data
	 * @param row Row to remove
	 */
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
	/**
	 * Gets the row count
	 */
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}
	/**
	 * Gets the column count
	 */
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	@Override
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
	/**
	 * Returns true if col is 0
	 */
	public boolean isCellEditable(int row, int col){
		return (col == 0);
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	/**
	 * Sets the data[row][col] to value
	 */
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;	
		fireTableCellUpdated(row, col);
	}

}
