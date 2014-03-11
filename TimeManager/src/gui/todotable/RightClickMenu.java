package gui.todotable;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
/**
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class RightClickMenu extends JPopupMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem miDelete, miEdit, miDone;
	/**
	 * Constructs a RightClickMenu()
	 */
	public RightClickMenu() {
		createMenuItems();
		this.add(miDone);
		this.add(miEdit);
		this.add(miDelete);
	}
	/**
	 * Creates all the JMenuItems to be used
	 */
	private void createMenuItems(){
		miDelete = new JMenuItem();
		miEdit = new JMenuItem();
		miDone = new JMenuItem();
	}
	/**
	 * Sets a AbstractAction to miEdit
	 * @param a AbstractAction to set
	 */
	public void addEditTaskAction(AbstractAction a){
		miEdit.setAction(a);
	}
	/**
	 * Sets a AbstractAction to miDelete
	 * @param a AbstractAction to set
	 */
	public void addDeleteTaskAction(AbstractAction a){
		miDelete.setAction(a);
	}
	/**
	 * Sets a AbstractAction to miDelete
	 * @param a AbstractAction to set
	 */
	public void addMarkDoneAction(AbstractAction a){
		miDone.setAction(a);
	}
}
