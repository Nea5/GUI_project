package gui;

import java.util.*;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.*;

/**
 * 
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class TodoTable extends JTable {
	private MyTableModel model;
	private String[] columnNames = {" ", TimeManager.rb.getString("task"), TimeManager.rb.getString("category"),TimeManager.rb.getString("date"), TimeManager.rb.getString("priority")};
	private Object[][] data;
	private TableRowSorter<MyTableModel> sorter;
	
	public TodoTable(Object[][] data) {
		this.data = data;
		addModel();
		
	}
	/**
	 * Sets the model of this Table
	 */
	private void addModel(){
		/*model = new DefaultTableModel(data, columnNames){
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
		};*/
		model = new MyTableModel(columnNames, data);
		this.setModel(model);
		sorter = new TableRowSorter<MyTableModel>(model);
		this.setRowSorter(sorter);
	}
	/**
	 * Returns this tables DefaultTableModel model
	 */
	public MyTableModel getModel(){
		return model;
	}
	/**
	 * Adds a new row to this table with the data rowData
	 * 
	 * @param rowData	data to be added.
	 */
	public void addRow(Object[] rowData){
		model.addRow(rowData);
		model.fireTableDataChanged();
	}
	/**
	 * Edits the row on index row with the data from rowData
	 * 
	 * @param row		Index of the row to be edited
	 * @param rowData	The new data
	 */
	public void editRow(int row, Object[] rowData){
		model.setValueAt(rowData[0], row, 0);
		model.setValueAt(rowData[1], row, 1);
		model.setValueAt(rowData[2], row, 2);
		model.setValueAt(rowData[3], row, 3);
		model.setValueAt(rowData[4], row, 4);
	}
	/**
	 * Removes the row in this table on index row
	 * 
	 * @param row Index of the row to be removed
	 */
	public void removeRow(int row){
		model.removeRow(row);
	}
	/**
	 * Returns true if there is at least one row in this table that
	 * is marked. False otherwise
	 * 
	 * @return True or False
	 */
	public boolean getMarked(){
		boolean marked = false;
		for(int i = 0; i < model.getRowCount();i++){
			if((Boolean)model.getValueAt(i, 0)){
				marked = true;
			}
		}
		return marked;
	} 
	
	public void newFilter(final String category){
		RowFilter<MyTableModel, Integer> filter = new RowFilter<MyTableModel, Integer>(){
			@Override
			public boolean include(
					javax.swing.RowFilter.Entry<? extends MyTableModel, ? extends Integer> entry) {	
			
				if(category.equals("Filter")){
					return true;
				} else if(entry.getValue(2).equals(category)){
					return true;
				}
				return false;
			}
			
		};
		sorter.setRowFilter(filter);
	}
}
