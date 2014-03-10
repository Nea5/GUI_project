package gui;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class RightClickMenu extends JPopupMenu {

	private JMenuItem miDelete, miEdit, miDone;
	
	public RightClickMenu() {
		createMenuItems();
		this.add(miDone);
		this.add(miEdit);
		this.add(miDelete);
	}
	private void createMenuItems(){
		miDelete = new JMenuItem();
		miEdit = new JMenuItem();
		miDone = new JMenuItem();
	}
	public void addEditTaskAction(AbstractAction a){
		miEdit.setAction(a);
	}
	public void addDeleteTaskAction(AbstractAction a){
		miDelete.setAction(a);
	}
	public void addMarkDoneAction(AbstractAction a){
		miDone.setAction(a);
	}

}
