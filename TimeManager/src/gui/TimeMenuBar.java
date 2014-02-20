package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class TimeMenuBar extends JMenuBar {
	private JMenu m_file, m_edit, m_help;
	private JMenuItem mi_new, mi_help, mi_edit, mi_delete;
	private JMenu m_language;
    private JRadioButtonMenuItem Svenska;
    private JRadioButtonMenuItem English;
	private ButtonGroup group;


		
	public TimeMenuBar() {
		createMenus();
		createMenuItems();
		
		m_file.add(mi_new);
		m_file.add(m_language);
		
		m_edit.add(mi_edit);
		m_edit.add(mi_delete);
		
		m_help.add(mi_help);
		
		m_language.add(Svenska);
		m_language.add(English);
		group.add(English);
		group.add(Svenska);
		
		mi_edit.setEnabled(false);
		mi_delete.setEnabled(false);
		
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
		mi_edit = new JMenuItem("Edit");
		mi_delete = new JMenuItem("Delete");
		group = new ButtonGroup();
		English = new JRadioButtonMenuItem("English");
		Svenska = new JRadioButtonMenuItem("Svenska");	
		
		Svenska.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e)
            {
             /*   //getItem returns an object so it gets cast
                //as a String to retrieve the item value
                String item = (String)e.getItem();
                if (e.getStateChange() == ItemEvent.SELECTED)
                {
                	Locale Svenska = new Locale("sv", "SE");
            		TimeManager.rb = ResourceBundle.getBundle("gui.resources.language", Svenska);
                }
                else
                {
            		TimeManager.rb = ResourceBundle.getBundle("gui.resources.language", Locale.ENGLISH);

                } */
            }
        });
		English.setSelected(true);
	}
	
	public JMenuItem getEditItem(){
		return mi_edit;
	}
	
	public JMenuItem getDeleteItem(){
		return mi_delete;
	}
	
	public JMenuItem getNewItem(){
		return mi_new;
	}
	
	public JMenuItem getHelpItem(){
		return mi_help;
	}
}
