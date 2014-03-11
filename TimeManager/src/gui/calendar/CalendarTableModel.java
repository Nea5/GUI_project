package gui.calendar;

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
public class CalendarTableModel extends AbstractTableModel {
	private String[] columnNames;
	private Object[][] data = new Object[6][7];
	/**
	 * Constructs a CalendarTableModel with the specified Column Names
	 * @param columnNames Column Names
	 */
	public CalendarTableModel(String[] columnNames) {
		// TODO Auto-generated constructor stub
		this.columnNames = columnNames;
	}
	/**
	 * Gets number of rows
	 */
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 6;
	}
	/**
	 * Gets number of Columns
	 */
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}
	/**
	 * Gets the value at data[rowIndex][columnIndex]
	 * @param rowIndex
	 * @param colIndex
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	/**
	 * Sets the value at data[row][col] to value
	 * @param value
	 * @param row
	 * @param col
	 */
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;	
		fireTableCellUpdated(row, col);
	}
	/**
	 * Gets the class of this column
	 * @param col column 
	 */
    public Class<?> getColumnClass(int col) {
		return (new Integer(1)).getClass();
    }
    /**
     * Gets the Column Name of this column
     * @param col column
     */
	public String getColumnName(int col) {
		return columnNames[col];
	}
}
