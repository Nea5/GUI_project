package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

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
public class TodoTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TodoTableModel model;
	private String[] columnNames = {" ", TimeManager.rb.getString("task"), TimeManager.rb.getString("category"),TimeManager.rb.getString("date"), TimeManager.rb.getString("priority")};
	private Object[][] data;
	private TableRowSorter<TodoTableModel> sorter;
	private ToDoModel tdModel;
	private DateCellRenderer dateRenderer;
	private RightClickMenu rClickMenu;
	
	public TodoTable(ToDoModel tdModel) {
		this.tdModel = tdModel;
		this.data = tdModel.getData();
		addModel();
		addDateCellRenderer();

		createPopupMenu();
	}
	
	private void createPopupMenu(){
		rClickMenu = new RightClickMenu();
	}
	
	/**
	 * Sets the model of this Table
	 */
	private void addModel(){
		model = new TodoTableModel(columnNames, data);
		this.setModel(model);
		sorter = new TableRowSorter<TodoTableModel>(model);
		this.setRowSorter(sorter);
	}
	
	private void addDateCellRenderer(){
		dateRenderer = new DateCellRenderer("dd/MM/yy HH:mm");
		this.getColumnModel().getColumn(3).setCellRenderer(dateRenderer);
	}
	
	/**
	 * Returns this tables DefaultTableModel model
	 */
	public TodoTableModel getModel(){
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
	public void unMark(int row){
		model.setValueAt(false, row, 0);
	}
	public void newFilter(final String category){
		RowFilter<TodoTableModel, Integer> filter = new RowFilter<TodoTableModel, Integer>(){
			@Override
			public boolean include(
					javax.swing.RowFilter.Entry<? extends TodoTableModel, ? extends Integer> entry) {	
			
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
	
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if(!isRowSelected(row)){
			c.setBackground(getBackground());
			int modelRow = convertRowIndexToModel(row);
			Date currentTime = new Date();
			Date rowDate = (Date)model.getValueAt(modelRow, 3);
			if (currentTime.compareTo(rowDate) >= 0) {
				c.setBackground(Color.RED);
			}else if (currentTime.compareTo(rowDate) < 0) {
				c.setBackground(Color.WHITE);
			}
			boolean done = tdModel.get(modelRow).isDone();
			if(done){
				c.setBackground(Color.GREEN);
			}
			
		}
		return c;
	}
	public void rightClickMenu(MouseEvent e){
        int r = this.rowAtPoint(e.getPoint());
        if (r >= 0 && r < this.getRowCount()) {
            this.setRowSelectionInterval(r, r);
        } else {
            this.clearSelection();
        }

        int rowindex = this.getSelectedRow();
        if (rowindex < 0)
            return;
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
            rClickMenu.show(e.getComponent(), e.getX(), e.getY());
        }
	}
	public void addEditTaskAction(AbstractAction a){
		rClickMenu.addEditTaskAction(a);
	}
	public void addDeleteTaskAction(AbstractAction a){
		rClickMenu.addDeleteTaskAction(a);
	}
	public void addMarkDoneAction(AbstractAction a){
		rClickMenu.addMarkDoneAction(a);
	}
}
