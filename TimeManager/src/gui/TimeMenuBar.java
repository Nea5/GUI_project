package gui;

import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
/**
 * 
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class TimeMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private JMenu mFile, mEdit, mHelp;
	private JMenuItem miNew, miHelp, miEdit, miDelete;
	private JMenu mLanguage;
    private JRadioButtonMenuItem rbSvenska;
    private JRadioButtonMenuItem rbEnglish;
	private ButtonGroup group;
	private JMenu mLookandfeel;
	private JRadioButtonMenuItem rbMetal;
    private JRadioButtonMenuItem rbNimbus;
	private ButtonGroup LAF;
	private ImageIcon iSvenska, iEnglish;
	/** 
	 * Constructs a TimeMenuBar
	 */
	public TimeMenuBar() {
		getResources();
		createMenus();
		createMenuItems();
		mFile.add(miNew);
		mFile.add(mLanguage);
		mFile.add(mLookandfeel);
		mEdit.add(miEdit);
		mEdit.add(miDelete);
		mHelp.add(miHelp);
		mLanguage.add(rbSvenska);
		mLanguage.add(rbEnglish);
		group.add(rbEnglish);
		group.add(rbSvenska);
		mLookandfeel.add(rbMetal);
		mLookandfeel.add(rbNimbus);
		LAF.add(rbMetal);
		LAF.add(rbNimbus);
		miDelete.setEnabled(false);
		miEdit.setEnabled(false);
		this.add(mFile);
		this.add(mEdit);
		this.add(mHelp);
	}
	/**
	 * Constructs all the JMenus to be used in this JMenuBar
	 */
	private void createMenus(){
		mFile = new JMenu(TimeManager.rb.getString("m_file"));
		mEdit = new JMenu(TimeManager.rb.getString("m_edit"));
		mHelp = new JMenu(TimeManager.rb.getString("m_help"));
		mLanguage = new JMenu(TimeManager.rb.getString("m_language"));
		mLookandfeel = new JMenu(TimeManager.rb.getString("m_lookandfeel"));
	}
	/**
	 * Loads in the Icons to be used
	 */
	private void getResources(){
		ClassLoader cl = getClass().getClassLoader();
		iSvenska = new ImageIcon(cl.getResource("resources/Sweden24.png"));
		iEnglish = new ImageIcon(cl.getResource("resources/English24.png"));
	}
	/**
	 * Constructs all JMenuItems to be used in this JMenuBar. And adds ItemListener to RadioButtons
	 */
	private void createMenuItems(){
		miNew = new JMenuItem();
		miHelp = new JMenuItem();
		miEdit = new JMenuItem();
		miDelete = new JMenuItem();
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
		
		LAF = new ButtonGroup();
		rbMetal = new JRadioButtonMenuItem("Metal");
		rbNimbus = new JRadioButtonMenuItem("Nimbus");
		if(TimeManager.look_and_feel.equals("Nimbus")){
			rbNimbus.setSelected(true);
		} else {
			rbMetal.setSelected(true);
		}
	}
	/**
	 * Adds a ItemListener to rbSvenska
	 * @param l ItemListener to add
	 */
	public void addLanguageListener(ItemListener l){
		rbSvenska.addItemListener(l);
	}
	/**
	 * Adds a ItemListener to rbNimbus
	 * @param l ItemListener to add
	 */
	public void addLAFListener(ItemListener laf){
		rbNimbus.addItemListener(laf);
	}
	/**
	 * Sets a AbstractAction to miNew
	 * @param a AbstractAction to set
	 */
	public void addNewTaskAction(AbstractAction a){
		miNew.setAction(a);
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
	 * Sets a AbstractAction to miHelp
	 * @param a AbstractAction to set
	 */
	public void addHelpAction(AbstractAction a){
		miHelp.setAction(a);
	}
}
