package gui;

import model.*;

public class EditPanel extends NewTaskPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditPanel(ToDo t) {
		super();
		setEdit(false);
		setData(t);
	}

}
