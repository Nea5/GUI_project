package gui;

import model.*;

public class EditPanel extends NewTaskPanel {

	public EditPanel(ToDo t) {
		super();
		setEdit(false);
		setData(t);
	}

}
