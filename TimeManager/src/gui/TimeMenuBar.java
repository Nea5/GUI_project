package gui;

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
		m_file = new JMenu("File");
		m_edit = new JMenu("Edit");
		m_help = new JMenu("Help");
	}
	
	private void createMenuItems(){
		mi_new = new JMenuItem("New Task");
		mi_help = new JMenuItem("Help?");
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		frame.setVisible(true);
		JMenuBar menubar = new TimeMenuBar();
		frame.setJMenuBar(menubar);

	}

}
