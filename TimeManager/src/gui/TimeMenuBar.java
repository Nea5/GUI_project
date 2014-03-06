package gui;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class TimeMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private JMenu m_file, m_edit, m_help;
	private JMenuItem mi_new, mi_help, mi_edit, mi_delete;
	private JMenu m_language;
    private JRadioButtonMenuItem rbSvenska;
    private JRadioButtonMenuItem rbEnglish;
	private ButtonGroup group;
	private ImageIcon iSvenska, iEnglish;

	public TimeMenuBar() {
		this.setBackground(Color.BLACK);
		getResources();
		createMenus();
		createMenuItems();
		m_file.add(mi_new);
		m_file.add(m_language);
		m_edit.add(mi_edit);
		m_edit.add(mi_delete);
		m_help.add(mi_help);
		m_language.add(rbSvenska);
		m_language.add(rbEnglish);
		group.add(rbEnglish);
		group.add(rbSvenska);
		mi_delete.setEnabled(false);
		mi_edit.setEnabled(false);
		this.add(m_file);
		this.add(m_edit);
		this.add(m_help);
	}
	/**
	 * Constructs all the JMenus to be used in this JMenuBar
	 */
	private void createMenus(){
		m_file = new JMenu(TimeManager.rb.getString("m_file"));
		m_edit = new JMenu(TimeManager.rb.getString("m_edit"));
		m_help = new JMenu(TimeManager.rb.getString("m_help"));
		m_language = new JMenu(TimeManager.rb.getString("m_language"));
	}
	
	private void getResources(){
		ClassLoader cl = getClass().getClassLoader();
		iSvenska = new ImageIcon(cl.getResource("resources/Sweden24.png"));
		iEnglish = new ImageIcon(cl.getResource("resources/English24.png"));
	}
	/**
	 * Constructs all JMenuItems to be used in this JMenuBar. And adds ItemListener to RadioButtons
	 */
	private void createMenuItems(){
		mi_new = new JMenuItem();
		mi_help = new JMenuItem();
		mi_edit = new JMenuItem();
		mi_delete = new JMenuItem();
		group = new ButtonGroup();
		rbEnglish = new JRadioButtonMenuItem("English");
		rbEnglish.setIcon(iEnglish);
		rbSvenska = new JRadioButtonMenuItem("Svenska");
		rbSvenska.setIcon(iSvenska);
		if(TimeManager.language.equals("Svenska")){
			rbSvenska.setSelected(true);
		} else {
			rbEnglish.setSelected(true);
		}
	}
	
	public void addLanguageListener(ItemListener l){
		rbSvenska.addItemListener(l);
	}
	
	public void addNewTaskAction(AbstractAction a){
		mi_new.setAction(a);
	}
	
	public void addEditTaskAction(AbstractAction a){
		mi_edit.setAction(a);
	}
	
	public void addDeleteTaskAction(AbstractAction a){
		mi_delete.setAction(a);
	}
	
	public void addHelpAction(AbstractAction a){
		mi_help.setAction(a);
	}
}
