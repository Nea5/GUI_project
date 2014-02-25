package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class TimeMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
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
	/**
	 * Constructs all JMenuItems to be used in this JMenuBar. And adds ItemListener to RadioButtons
	 */
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
            /**
             * Changes language
             */
            public void itemStateChanged(ItemEvent e)
            {
            	
                if (e.getStateChange() == ItemEvent.SELECTED)
                {
                	try {
                		FileOutputStream out = new FileOutputStream("settings.properties");
                        Properties props = new Properties();
                     	props.setProperty("language", "Svenska");
                        props.store(out, null);
	                    out.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                else
                {
                	try {
                 		FileOutputStream out = new FileOutputStream("settings.properties");
                        Properties props = new Properties();
                      	props.setProperty("language", "English");
                        props.store(out, null);
 	                    out.close();
 					} catch (IOException e1) {
 						// TODO Auto-generated catch block
 						e1.printStackTrace();
 					}
                } 
            }
        });
		if(TimeManager.language.equals("Svenska")){
			Svenska.setSelected(true);
		} else {
			English.setSelected(true);
		}
	}
	/**
	 * Returns a JMenuItem
	 * @return mi_edit
	 */
	public JMenuItem getEditItem(){
		return mi_edit;
	}
	/**
	 * Returns a JMenuItem
	 * @return mi_delete
	 */
	public JMenuItem getDeleteItem(){
		return mi_delete;
	}
	/**
	 * Returns a JMenuItem
	 * @return mi_new
	 */
	public JMenuItem getNewItem(){
		return mi_new;
	}
	/**
	 * Returns a JMenuItem
	 * @return mi_help
	 */
	public JMenuItem getHelpItem(){
		return mi_help;
	}
}
