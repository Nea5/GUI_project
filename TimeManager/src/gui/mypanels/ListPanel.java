package gui.mypanels;

import java.awt.LayoutManager;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.ToDo;
/**
 * 
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class ListPanel extends JPanel {
	private JList lListName;
	private DefaultListModel listModel = new DefaultListModel();
	/**
	 * Constructs a ListPanel that show some data
	 * @param data Data to be shown
	 */
	public ListPanel(Iterator<ToDo> data) {
		createList();
		addData(data);
		this.add(lListName);
	}
	/**
	 * Creates a JList with a DefaultListModel
	 */
	private void createList(){
		lListName = new JList(listModel);	
	}
	/**
	 * Adds data to the JList
	 * @param data Data to add
	 */
	private void addData(Iterator<ToDo> data){
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm");
		while(data.hasNext()){
			ToDo t = data.next(); // ToDo item to use
			String date = f.format(t.getDue());
			listModel.addElement(t.getName() + " " + date); //Adds the name and date of the todo item
		}
	}
}
