package gui.todotable;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * 
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class DateCellRenderer extends DefaultTableCellRenderer {
	private SimpleDateFormat f;
	/**
	 * Constructs a DateCellRenderer with a format
	 * @param format Format to use, must be in SimpleDateFormat date and time 
	 * 				pattern
	 */
	public DateCellRenderer(String format) {
		this.f = new SimpleDateFormat(format);
	}
	
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if( value instanceof Date) {//If the cell is a Date
            value = f.format(value); //formats it using format
        }
        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
}
