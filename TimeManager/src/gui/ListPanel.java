package gui;

import java.awt.LayoutManager;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.ToDo;

public class ListPanel extends JPanel {
	private JList lListName;
	private DefaultListModel listModel = new DefaultListModel();
	public ListPanel(Iterator<ToDo> data) {
		createList();
		addData(data);
		this.add(lListName);
	}
	private void createList(){
		lListName = new JList(listModel);	
	}
	private void addData(Iterator<ToDo> data){
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm");
		while(data.hasNext()){
			ToDo t = data.next();
			String date = f.format(t.getDue());
			listModel.addElement(t.getName() + " " + date);
		}
	}
}
