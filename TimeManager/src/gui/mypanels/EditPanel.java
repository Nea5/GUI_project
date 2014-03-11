package gui.mypanels;

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
public class EditPanel extends NewTaskPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructs a EditPanel that show some data.
	 * @param t Data to be shown
	 */
	public EditPanel(ToDo t) {
		super();
		setEdit(false); //Set edit to false
		setData(t); //Set data
	}
}
