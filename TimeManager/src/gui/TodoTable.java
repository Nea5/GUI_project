package gui;

import java.util.*;

import todo.*;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TodoTable extends JTable {
	private DefaultTableModel model;
	private String[] columnNames = {" ", TimeManager.rb.getString("task"), TimeManager.rb.getString("category"),TimeManager.rb.getString("date"), TimeManager.rb.getString("priority")};
	private Object[][] data;
	
	public TodoTable(Object[][] data) {
		this.data = data;
		addModel();
		
	}
	private void addModel(){
		model = new DefaultTableModel(data, columnNames){
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
		};
		this.setModel(model);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);	
	}
	public DefaultTableModel getModel(){
		return model;
	}
	public void addRow(Object[] rowData){
		model.addRow(rowData);
		model.fireTableDataChanged();
	}
	public void editRow(int row, Object[] rowData){
		model.setValueAt(rowData[0], row, 0);
		model.setValueAt(rowData[1], row, 1);
		model.setValueAt(rowData[2], row, 2);
		model.setValueAt(rowData[3], row, 3);
		model.setValueAt(rowData[4], row, 4);
	}
	public void removeRow(int row){
		System.out.println("" + model.getValueAt(row,1) + row);
		model.removeRow(row);
	}
	public boolean getMarked(){
		boolean marked = false;
		for(int i = 0; i < model.getRowCount();i++){
			if((boolean)model.getValueAt(i, 0)){
				marked = true;
			}
		}
		return marked;
	} 
}
