package gui;

import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class TimeMenuBar extends JMenuBar {
	private JMenu m_file, m_edit, m_help;
	private JMenuItem mi_new, mi_help;
	
	private JMenu m_language;
    private JRadioButtonMenuItem Svenska;
    private JRadioButtonMenuItem English;
	private ButtonGroup group;



		
	public TimeMenuBar() {
		createMenus();
		createMenuItems();
		
		m_file.add(mi_new);
		m_file.add(m_language);
		m_help.add(mi_help);
		m_language.add(Svenska);
		m_language.add(English);
		group.add(Svenska);
		group.add(English);
		
		
		this.add(m_file);
		this.add(m_edit);
		this.add(m_help);
		
		
	}
	

	
	private void createMenus(){
		m_file = new JMenu(TimeManager.rb.getString("m_file"));
		m_edit = new JMenu(TimeManager.rb.getString("m_edit"));
		m_help = new JMenu(TimeManager.rb.getString("m_help"));
		m_language = new JMenu(TimeManager.rb.getString("m_language"));
	}
	
	private void createMenuItems(){
		mi_new = new JMenuItem(TimeManager.rb.getString("mi_new"));
		mi_help = new JMenuItem(TimeManager.rb.getString("mi_help"));
		group = new ButtonGroup();
		Svenska = new JRadioButtonMenuItem("Svenska");
		English = new JRadioButtonMenuItem("English");
		Svenska.setSelected(true);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		frame.setVisible(true);
		JMenuBar menubar = new TimeMenuBar();
		frame.setJMenuBar(menubar);

	}

}
