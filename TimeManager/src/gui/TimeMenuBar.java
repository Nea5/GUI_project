package gui;

import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TimeMenuBar extends JMenuBar {
	private JMenu m_file, m_edit, m_help;
	private JMenuItem mi_new, mi_help;
	
		
	public TimeMenuBar() {
		createMenus();
		createMenuItems();
		
		m_file.add(mi_new);
		
		m_help.add(mi_help);
		
		this.add(m_file);
		this.add(m_edit);
		this.add(m_help);
	}
	
	
	
	private void createMenus(){
		m_file = new JMenu(TimeManager.rb.getString("m_file"));
		m_edit = new JMenu(TimeManager.rb.getString("m_edit"));
		m_help = new JMenu(TimeManager.rb.getString("m_help"));
	}
	
	private void createMenuItems(){
		mi_new = new JMenuItem(TimeManager.rb.getString("mi_new"));
		mi_help = new JMenuItem(TimeManager.rb.getString("mi_help"));
	}
	
	//public static void main(String[] args) {
		//JFrame frame = new JFrame("Test");
		//frame.setVisible(true);
		//JMenuBar menubar = new TimeMenuBar();
		//frame.setJMenuBar(menubar);

	//}

}
